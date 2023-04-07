// Logging layer will help for the devops team in:
// 1. Debugging our microservice
// 2. Keep track of the changes going within the container

package main

import (
	"context"
	"log_fetcher_microservice/myTypes"
	"time"

	log "github.com/sirupsen/logrus"
)

type loggingService struct {
	// chaining the layers and basically will help in making a middleware type of architecture
	next LogFetcher 
}

func NewLoggingService(next LogFetcher) LogFetcher {
	return &loggingService{
		next: next,
	}
}

func (l *loggingService) FetchLog(ctx context.Context, asked string) (resp myTypes.MyLog, err error) {
	// Will be called after the chain of events/mini-services
	defer func (begin time.Time)  {
		log.WithFields(log.Fields{
			"requestID": ctx.Value("requestID"),
			"took": time.Since(begin),
			"err": err,
			"resp": resp,
		}).Info("fetchLog")
	}(time.Now())

	return l.next.FetchLog(ctx, asked)
}


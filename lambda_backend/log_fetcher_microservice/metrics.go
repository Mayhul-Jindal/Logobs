// Metrics layer which will basically help in:
// 1. Pushing our service's metrics to time-series databases like TimescaleDB/ InfluxDB/ Prometheus
// 2. Increase observability by making traces using spans by using open-telemetry API. 

package main

import (
	"context"
	"fmt"
	"log_fetcher_microservice/myTypes"
)

type metricService struct{
	next LogFetcher
}

func NewMetricService(next LogFetcher) LogFetcher{
	return &metricService{
		next: next,
	}
}

func (m *metricService) FetchLog(ctx context.Context, asked string) (myTypes.MyLog, error) {
	fmt.Println("pushing metrics to any time-series database...")
	// Logic for making spans for open-telemetry or pushing metrics to time-series databse will come here 
	return m.next.FetchLog(ctx, asked)
}

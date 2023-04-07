// Here comes the business logic which has a simple function to:
// 1. Fetch data from elastic search
package main

import (
	"context"
	"log_fetcher_microservice/myTypes"
)

// Making a interface for LogFetcher service
type LogFetcher interface{
	FetchLog(context.Context, string) (myTypes.MyLog ,error)
}

// Implementing LogFetcher interface by making a struct
type logFetcher struct{}

func (l *logFetcher) FetchLog(ctx context.Context, asked string) (myTypes.MyLog, error){
	resp, err := dbGet();
	if err != nil {
		return myTypes.MyLog{}, err
	}
	return resp, err
}

// var db = struct{
// 	TimeStamp time.Time
// }{

// }

func dbGet() (myTypes.MyLog, error){
	// mock data that will come from elastic search
	object := myTypes.MyLog{
		Timestamp: "19.10.2003",  
   	 	StatusCode: 500,
	}

	return object, nil
}

package main

import "context"

func main() {
	ctx := context.WithValue(context.Background(), "requestID", "123132324")

	svc := NewLoggingService(NewMetricService(&logFetcher{}))

	svc.FetchLog(ctx, "hello")
}

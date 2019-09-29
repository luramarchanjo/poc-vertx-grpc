# Overview

This is a benchmarket beetwen common grpc-java and vertx-grpc

# Setup

Install the [GHZ - Simple gRPC benchmarking and load testing tool]

# Test

## Testing Common gRPC Java

~~~
ghz --insecure --proto invoice-service.proto \
--call InvoiceService.invoice -c 200 -z 30s \
-d '{"content":"Bla bla bla"}' 127.0.0.1:8081
~~~

## Testing Vertx gPRC

~~~
ghz --insecure --proto invoice-service.proto \
--call InvoiceService.invoice -c 200 -z 30s \
-d '{"content":"Bla bla bla"}' 127.0.0.1:8080
~~~

# Results

## Common gPRC Java

~~~
Summary:
  Count:	49703
  Total:	30.00 s
  Slowest:	1.02 s
  Fastest:	100.34 ms
  Average:	120.54 ms
  Requests/sec:	1656.64

Response time histogram:
  100.336 [1]	|
  192.410 [48404]	|∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎
  284.484 [725]	|∎
  376.559 [173]	|
  468.633 [0]	|
  560.708 [0]	|
  652.782 [0]	|
  744.856 [0]	|
  836.931 [0]	|
  929.005 [84]	|
  1021.080 [116]	|

Latency distribution:
  10% in 101.91 ms 
  25% in 103.71 ms 
  50% in 108.63 ms 
  75% in 123.04 ms 
  90% in 146.10 ms 
  95% in 162.57 ms 
  99% in 211.56 ms 

Status code distribution:
  [OK]            49503 responses   
  [Unavailable]   200 responses     

Error distribution:
  [200]   rpc error: code = Unavailable desc = transport is closing
~~~
  
## Vertx gPRC

~~~
Summary:
  Count:	55744
  Total:	30.01 s
  Slowest:	879.95 ms
  Fastest:	100.17 ms
  Average:	107.45 ms
  Requests/sec:	1857.40

Response time histogram:
  100.170 [1]	|
  178.148 [55155]	|∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎∎
  256.125 [189]	|
  334.103 [0]	|
  412.080 [0]	|
  490.058 [0]	|
  568.035 [0]	|
  646.013 [0]	|
  723.990 [0]	|
  801.968 [43]	|
  879.945 [157]	|

Latency distribution:
  10% in 100.67 ms 
  25% in 101.22 ms 
  50% in 102.60 ms 
  75% in 105.54 ms 
  90% in 110.94 ms 
  95% in 117.38 ms 
  99% in 149.03 ms 

Status code distribution:
  [OK]            55545 responses   
  [Unavailable]   199 responses     

Error distribution:
  [199]   rpc error: code = Unavailable desc = transport is closing
~~~


[GHZ - Simple gRPC benchmarking and load testing tool]: https://ghz.sh/
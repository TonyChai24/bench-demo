##data
```
wrk -t4 -c128 -d10s --latency http://localhost:5050/bench
Running 10s test @ http://localhost:5050/bench
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.14ms    0.95ms  14.70ms   88.40%
    Req/Sec     4.50k   429.69     9.20k    90.80%
  Latency Distribution
     50%    7.12ms
     75%    7.43ms
     90%    7.80ms
     99%   10.33ms
  179871 requests in 10.10s, 14.24MB read
Requests/sec:  17803.78
Transfer/sec:      1.41MB
```
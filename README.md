# bench-demo
##docker
```
docker build -t java8-htop .
docker run -p 8080:8080 --rm -it bench-demo:0.1.0 /bin/bash
```

##wrk
打满cpu
```
wrk -t12 -c1000 -d10s -T30s  --latency http://192.168.99.100:8080/config/hello
Running 10s test @ http://192.168.99.100:8080/config/hello
  12 threads and 1000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   135.45ms  112.14ms   1.19s    94.28%
    Req/Sec   699.29    188.06     1.45k    75.46%
  Latency Distribution
     50%  111.28ms
     75%  127.12ms
     90%  157.46ms
     99%  738.06ms
  83648 requests in 10.05s, 12.29MB read
Requests/sec:   8324.60
Transfer/sec:      1.22MB

```
##mac proc 单机
打满所有cpu,nio2
```
wrk -t12 -c1000 -d10s -T30s  --latency http://localhost:8080/config/hello
Running 10s test @ http://localhost:8080/config/hello
  12 threads and 1000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    17.31ms   12.86ms 146.45ms   75.98%
    Req/Sec     2.33k     0.87k    5.71k    71.35%
  Latency Distribution
     50%   15.23ms
     75%   23.43ms
     90%   33.13ms
     99%   48.85ms
  280358 requests in 10.09s, 41.22MB read
  Socket errors: connect 0, read 980, write 0, timeout 0
Requests/sec:  27790.23
Transfer/sec:      4.09MB
```
##mac pro docker nio2
```
wrk -t12 -c1000 -d10s -T30s  --latency http://192.168.99.100:8080/config/hello
Running 10s test @ http://192.168.99.100:8080/config/hello
  12 threads and 1000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   123.20ms   66.36ms   1.46s    94.90%
    Req/Sec   701.16    180.20     1.27k    71.57%
  Latency Distribution
     50%  113.94ms
     75%  131.67ms
     90%  154.43ms
     99%  453.04ms
  83742 requests in 10.04s, 12.30MB read
Requests/sec:   8341.93
Transfer/sec:      1.23MB
```

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

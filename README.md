# bench-demo
##build
```
docker build -t openresty-htop conf/
docker build -t nginx-bench .
```
##run
```
docker run -p 8080:8080 -td nginx-bench
```
##wrk
```
wrk -t12 -c1000 -d10s -T30s  --latency http://192.168.99.100:8080/hello
```
- cpu单核可打满
```
Running 10s test @ http://192.168.99.100:8080/hello
  12 threads and 1000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    66.11ms  148.31ms   4.31s    98.07%
    Req/Sec     1.48k   225.03     1.69k    92.59%
  Latency Distribution
     50%   52.03ms
     75%   53.92ms
     90%   56.76ms
     99%  336.87ms
  174568 requests in 10.09s, 29.46MB read
  Socket errors: connect 0, read 5, write 0, timeout 0
Requests/sec:  17294.94
Transfer/sec:      2.92MB
```

##ab
```
ab -c 1000 -n 15000 http://192.168.99.100:8080/hello
```

- cpu利用单核到28%
```
ab -c 1000 -n 15000 -r http://192.168.99.100:8080/hello
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking 192.168.99.100 (be patient)
Completed 1500 requests
Completed 3000 requests
Completed 4500 requests
Completed 6000 requests
Completed 7500 requests
Completed 9000 requests
Completed 10500 requests
Completed 12000 requests
Completed 13500 requests
Completed 15000 requests
Finished 15000 requests


Server Software:        openresty/1.9.15.1
Server Hostname:        192.168.99.100
Server Port:            8080

Document Path:          /hello
Document Length:        6 bytes

Concurrency Level:      1000
Time taken for tests:   5.743 seconds
Complete requests:      15000
Failed requests:        0
Total transferred:      2295000 bytes
HTML transferred:       90000 bytes
Requests per second:    2611.91 [#/sec] (mean)
Time per request:       382.861 [ms] (mean)
Time per request:       0.383 [ms] (mean, across all concurrent requests)
Transfer rate:          390.26 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        9  150 146.9    129    1340
Processing:    11  218  89.9    232     382
Waiting:        9  148  58.9    172     343
Total:        100  367 128.6    364    1523

Percentage of the requests served within a certain time (ms)
  50%    364
  66%    378
  75%    383
  80%    385
  90%    390
  95%    395
  98%    400
  99%   1299
 100%   1523 (longest request)
```
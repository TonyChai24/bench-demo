# bench-demo
##start
mvn spring-boot:run
##view config
curl -i localhost:8080/config/tomcat

##docker
```
docker run -p 8080:8080 --privileged -it bench-demo:0.1.0 /bin/bash
```
##change open files
```
ulimit -n 1024000
echo 6553560 > /proc/sys/fs/file-max
```
现在这个是运行时修改,需要启动--privileged

##wrk
```
wrk -t12 -c100 -d10s -T30s  --latency http://192.168.99.100:8080/config/hello

```

##默认配置
```
ab -c 1000 -n 15000 -r http://192.168.99.100:8080/config/hello
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


Server Software:
Server Hostname:        192.168.99.100
Server Port:            8080

Document Path:          /config/hello
Document Length:        5 bytes

Concurrency Level:      1000
Time taken for tests:   14.631 seconds
Complete requests:      15000
Failed requests:        0
Total transferred:      2340000 bytes
HTML transferred:       75000 bytes
Requests per second:    1025.20 [#/sec] (mean)
Time per request:       975.419 [ms] (mean)
Time per request:       0.975 [ms] (mean, across all concurrent requests)
Transfer rate:          156.18 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        1  439 658.2    152    4903
Processing:     5  255 390.1    106    9433
Waiting:        4  252 390.0    104    9433
Total:         14  694 790.6    295   11970

Percentage of the requests served within a certain time (ms)
  50%    295
  66%    635
  75%    973
  80%   1341
  90%   1697
  95%   2430
  98%   2806
  99%   3292
 100%  11970 (longest request)

```

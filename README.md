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
Time taken for tests:   17.406 seconds
Complete requests:      15000
Failed requests:        2206
   (Connect: 0, Receive: 1103, Length: 1103, Exceptions: 0)
Total transferred:      2431975 bytes
HTML transferred:       69485 bytes
Requests per second:    861.75 [#/sec] (mean)
Time per request:       1160.428 [ms] (mean)
Time per request:       1.160 [ms] (mean, across all concurrent requests)
Transfer rate:          136.44 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        1  359 168.3    333    2311
Processing:    50  428 485.2    265   14074
Waiting:        0  394 485.9    202   14074
Total:        269  787 506.4    685   14580

Percentage of the requests served within a certain time (ms)
  50%    685
  66%    838
  75%    948
  80%   1009
  90%   1194
  95%   1568
  98%   1914
  99%   2213
 100%  14580 (longest request)

```

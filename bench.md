##docker(`2核cpu,1G内存,java8`)
- cpu info
```
root@549752a5b459:/# cat /proc/cpuinfo | grep 'model name'
model name	: Intel(R) Core(TM) i7-5650U CPU @ 2.20GHz
model name	: Intel(R) Core(TM) i7-5650U CPU @ 2.20GHz
```
- memory
```
root@549752a5b459:/# free -m
             total       used       free     shared    buffers     cached
Mem:           995        926         69        115          1        149
-/+ buffers/cache:        774        220
Swap:         1142        253        888
```
单位为M
- java版本
```
root@549752a5b459:/# java -version
openjdk version "1.8.0_72-internal"
OpenJDK Runtime Environment (build 1.8.0_72-internal-b15)
OpenJDK 64-Bit Server VM (build 25.72-b15, mixed mode)
```
- result
```
wrk -t4 -c128 -d60s --latency http://192.168.99.100:8080/config/hello
Running 1m test @ http://192.168.99.100:8080/config/hello
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    57.38ms   61.62ms 690.85ms   87.37%
    Req/Sec   739.80    162.76     1.45k    70.83%
  Latency Distribution
     50%   36.93ms
     75%   73.10ms
     90%  135.93ms
     99%  295.27ms
  176685 requests in 1.00m, 30.53MB read
Requests/sec:   2941.73
Transfer/sec:    520.51KB
```

##mac air(`2核物理cpu,4核逻辑cpu,8G内存,java8`)
- cpu
```
sysctl -n machdep.cpu.brand_string
Intel(R) Core(TM) i7-5650U CPU @ 2.20GHz
sysctl -n hw.physicalcpu
2
sysctl -n hw.logicalcpu
4
```
- 持续请求60s

```
wrk -t4 -c128 -d60s --latency http://localhost:8080/config/hello
Running 1m test @ http://localhost:8080/config/hello
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    17.82ms   27.31ms 531.87ms   95.04%
    Req/Sec     2.35k   459.20     4.83k    72.38%
  Latency Distribution
     50%   12.93ms
     75%   17.61ms
     90%   25.94ms
     99%  147.63ms
  561390 requests in 1.00m, 97.01MB read
  Socket errors: connect 0, read 6, write 0, timeout 0
Requests/sec:   9341.73
Transfer/sec:      1.61MB
```
- 持续请求30s
```
wrk -t4 -c128 -d30s --latency http://localhost:8080/config/hello
Running 30s test @ http://localhost:8080/config/hello
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    11.68ms   11.89ms 378.64ms   95.60%
    Req/Sec     2.96k   443.01     5.44k    73.17%
  Latency Distribution
     50%   10.52ms
     75%   12.98ms
     90%   17.63ms
     99%   61.12ms
  353849 requests in 30.07s, 61.14MB read
  Socket errors: connect 0, read 6, write 0, timeout 0
Requests/sec:  11768.42
Transfer/sec:      2.03MB
```
- 持续10s
```
wrk -t4 -c128 -d10s --latency http://localhost:8080/config/hello
Running 10s test @ http://localhost:8080/config/hello
  4 threads and 128 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    11.90ms   15.33ms 379.81ms   96.03%
    Req/Sec     3.09k   490.36     5.12k    73.47%
  Latency Distribution
     50%    9.82ms
     75%   12.51ms
     90%   17.17ms
     99%   89.16ms
  123199 requests in 10.05s, 21.29MB read
  Socket errors: connect 0, read 5, write 0, timeout 0
Requests/sec:  12259.63
Transfer/sec:      2.12MB
```

##对比
http://menelic.com/2016/01/06/java-rest-api-benchmark-tomcat-vs-jetty-vs-grizzly-vs-undertow/
```
CPU:
model name	: Intel(R) Core(TM) i7-3537U CPU @ 2.00GHz
model name	: Intel(R) Core(TM) i7-3537U CPU @ 2.00GHz
model name	: Intel(R) Core(TM) i7-3537U CPU @ 2.00GHz
model name	: Intel(R) Core(TM) i7-3537U CPU @ 2.00GHz

RAM:
             total       used       free     shared    buffers     cached
Mem:          7.7G       4.9G       2.7G       399M       206M       2.3G
-/+ buffers/cache:       2.5G       5.2G
Swap:         7.9G         0B       7.9G
Java version:
java version "1.8.0_66"
Java(TM) SE Runtime Environment (build 1.8.0_66-b17)
Java HotSpot(TM) 64-Bit Server VM (build 25.66-b17, mixed mode)

OS:
Linux arcad-idea 3.16.0-57-generic #77~14.04.1-Ubuntu SMP Thu Dec 17 23:20:00 UTC 2015 x86_64 x86_64 x86_64 GNU/Linux
```
- 128并发,10w请求
qps: tomcat 1.8w jetty 2.5w undertow 2.4w
rt:  tomcat 7ms  jetty 5ms  undertow 5.3ms
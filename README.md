# bench-demo
##docker
```
docker build -t java8-htop .
mvn package -Dmaven.test.skip=true
docker run -p 8080:8080 -p 9090:9090 --rm -it bench-demo:0.1.0 /bin/bash
```
##jmx
- [Monitoring Java Applications Running Inside Docker Containers](http://www.jamasoftware.com/blog/monitoring-java-applications/)
- [Monitoring Tomcat StandardThreadExecutor's queue size (MBean)](http://stackoverflow.com/questions/26073272/monitoring-tomcat-standardthreadexecutors-queue-size-mbean)
- [JMX 标准MBean的开发应用](http://topmanopensource.iteye.com/blog/518415)
##查看tomcat的线程池状态
- 使用jolokia
http://localhost:8080/jolokia/list
- 查看tomcat的基本配置
http://localhost:8080/jolokia/read/Tomcat:port=8080,type=ProtocolHandler
http://localhost:8080/jolokia/read/Tomcat:type=Engine

##查看tcp链接
- 查看accept队列默认大小
```
cat /proc/sys/net/core/somaxconn
128
```
>该队列的长度为 min(backlog, somaxconn)，默认情况下，somaxconn 的值为 128
- 查看syn队列大小
```
cat /proc/sys/net/ipv4/tcp_max_syn_backlog
```
SYN queue 队列长度由 /proc/sys/net/ipv4/tcp_max_syn_backlog 指定，默认为2048
- 查看服务端的tcp队列配置
```
ss -l
```
>在LISTEN状态，其中 Send-Q 即为Accept queue的最大值，Recv-Q 则表示Accept queue中等待被服务器accept()。
- 查看SYN queue 溢出
```
netstat -s | grep LISTEN
4375 SYNs to LISTEN sockets dropped
```
- 查看Accept queue 溢出
```
netstat -s | grep TCPBacklogDrop
```
- 完整ss -l
```
root@88f8e6fb59ba:/# netstat -s
Ip:
    2260553 total packets received
    0 forwarded
    0 incoming packets discarded
    2260553 incoming packets delivered
    1458951 requests sent out
Icmp:
    0 ICMP messages received
    0 input ICMP message failed.
    ICMP input histogram:
    0 ICMP messages sent
    0 ICMP messages failed
    ICMP output histogram:
Tcp:
    0 active connections openings
    11796 passive connection openings
    0 failed connection attempts
    0 connection resets received
    947 connections established
    2260553 segments received
    1443168 segments send out
    16039 segments retransmited
    0 bad segments received.
    338 resets sent
Udp:
    0 packets received
    0 packets to unknown port received.
    0 packet receive errors
    4 packets sent
UdpLite:
TcpExt:
    1561 SYN cookies sent
    3955 SYN cookies received
    338 invalid SYN cookies received
    5599 TCP sockets finished time wait in fast timer
    156048 delayed acks sent
    335 delayed acks further delayed because of locked socket
    Quick ack mode was activated 7604 times
    2841 times the listen queue of a socket overflowed
    4402 SYNs to LISTEN sockets dropped
    51281 packet headers predicted
    1135899 acknowledgments not containing data payload received
    5343 predicted acknowledgments
    1880 congestion windows recovered without slow start after partial ack
    2743 other TCP timeouts
    TCPLossProbes: 11057
    TCPLossProbeRecovery: 1199
    7605 DSACKs sent for old packets
    7970 DSACKs received
    TCPDSACKIgnoredNoUndo: 6192
    TCPSackShiftFallback: 476
    TCPTimeWaitOverflow: 338
    TCPReqQFullDoCookies: 1615
    TCPChallengeACK: 1
    TCPSynRetrans: 260
    TCPOrigDataSent: 1091904
    TCPACKSkippedSynRecv: 45
IpExt:
    InOctets: 179955102
    OutOctets: 245105216
    InNoECTPkts: 2260553
```
##doc
- [TCP/IP协议中backlog参数](http://www.cnblogs.com/Orgliny/p/5780796.html?from=timeline)
- [我就是认真，为了一个net.ipv4.tcp_tw_recycle参数](http://udn.yyuap.com/thread-99657-1-1.html)


#测试数据
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

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

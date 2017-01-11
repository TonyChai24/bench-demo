FROM java:8
# Open file limit
#RUN echo "fs.file-max=3255296" >> /etc/sysctl.conf
#RUN echo "net.ipv4.ip_conntrack_max = 3255296" >> /etc/sysctl.conf
#RUN echo "net.ipv4.netfilter.ip_conntrack_max = 3255296" >> /etc/sysctl.conf
#RUN sysctl -p /etc/sysctl.conf

#RUN echo '# open files' >> /etc/security/limits.d/local.conf && \
#    echo '*          soft    nofile    1024000' >> /etc/security/limits.d/local.conf && \
#    echo '*          hard    nofile    1024000' >> /etc/security/limits.d/local.conf

#RUN echo "*               soft    nofile          1024000" >> /etc/security/limits.conf
#RUN echo "*               hard    nofile          1024000" >> /etc/security/limits.conf
RUN apt-get update
RUN apt-get install htop
ENV JAVA_OPTS="\
-Xmx300m \
-XX:+UseParallelGC \
-XX:ParallelGCThreads=4 \
-XX:+UseParallelOldGC \
-XX:+UseAdaptiveSizePolicy \
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/ \
-Xloggc:/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:GCLogFileSize=10M"
VOLUME /tmp
ADD bench-demo-0.1.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
FROM picoded/ubuntu-openjdk-8-jdk
#RUN curl 'https://bintray.com/user/downloadSubjectPublicKey?username=pcp' | apt-key add -
RUN echo "deb http://dl.bintray.com/pcp/trusty trusty main" | tee -a /etc/apt/sources.list
RUN apt-get update
#RUN sed -i "s/^exit 101$/exit 0/" /usr/sbin/policy-rc.d
RUN apt-get install pcp pcp-webapi --assume-yes --force-yes
RUN apt-get install htop
RUN apt-get install nmon
RUN apt-get install net-tools
RUN apt-get install lsof --assume-yes

RUN update-rc.d pmcd defaults
# Starting pmcd on startup
RUN update-rc.d pmwebd defaults
# Starting pmwebd on startup
RUN update-rc.d pmlogger defaults
# Starting Logger on startup
RUN service pmcd restart
RUN service pmwebd restart
RUN service pmlogger restart

#RUN systemctl enable pmcd.service
#RUN systemctl enable pmwebd.service
#RUN systemctl enable pmlogger.service
#RUN update-rc.d -f pmlogger remove
#RUN update-rc.d pmlogger defaults 94 06
#RUN update-rc.d -f pmie remove
#RUN update-rc.d pmie defaults 94 06

EXPOSE 44321
EXPOSE 44323
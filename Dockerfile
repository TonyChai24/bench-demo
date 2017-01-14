FROM java:8
RUN apt-get update
RUN apt-get install htop
RUN apt-get install nmon
RUN apt-get install net-tools
RUN apt-get install lsof --assume-yes
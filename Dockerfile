FROM java:8
RUN apt-get update
RUN apt-get install htop
RUN apt-get install nmon
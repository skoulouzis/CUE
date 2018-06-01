FROM ubuntu:16.04


##install java
ENV DEBIAN_FRONTEND=noninteractive 
RUN apt-get update && DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends 
RUN apt-get upgrade -y
RUN apt-get install -y wget software-properties-common
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get update
RUN "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections
RUN apt-get install -y oracle-java8-installer
# RUN apt-get install oracle-java8-set-default


WORKDIR /root
RUN wget https://github.com/QCAPI-DRIP/DRIP-integradation/releases/download/0.1/apache-tomcat-7.0.75.tar.gz
RUN tar -xzf apache-tomcat-7.0.75.tar.gz



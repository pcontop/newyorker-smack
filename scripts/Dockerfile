FROM ubuntu:16.04

RUN apt-get update

# Install some utilities
RUN apt-get install -y git curl wget jq

#Install Java
RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:openjdk-r/ppa -y
RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
RUN update-java-alternatives -s java-1.8.0-openjdk-amd64

# Install Spark
RUN curl -O http://d3kbcqa49mib13.cloudfront.net/spark-2.2.0-bin-hadoop2.7.tgz
RUN tar -zxvf spark-2.2.0-bin-hadoop2.7.tgz -C /opt
# (uncomment if using an already-downloaded tarball and comment out the above two lines)
#ADD spark-1.5.2-bin-hadoop2.6.tgz /opt
ENV SPARK_HOME /opt/spark-2.2.0-bin-hadoop2.7
ENV PATH $SPARK_HOME/bin:$PATH

CMD /bin/bash -i -l
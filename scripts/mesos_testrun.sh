#Run ZK
docker run -d --net=host netflixoss/exhibitor:1.5.2

#Run Mesos_Master

docker run -d --net=host \
  -e MESOS_PORT=5050 \
  -e MESOS_ZK=zk://127.0.0.1:2181/mesos \
  -e MESOS_QUORUM=1 \
  -e MESOS_REGISTRY=in_memory \
  -e MESOS_LOG_DIR=/var/log/mesos \
  -e MESOS_WORK_DIR=/var/tmp/mesos \
  -v "$(pwd)/log/mesos:/var/log/mesos" \
  -v "$(pwd)/tmp/mesos:/var/tmp/mesos" \
  mesosphere/mesos-master:1.7.1

#Run Mesos_Slave

docker run -d --net=host --privileged \
  -e MESOS_PORT=5051 \
  -e MESOS_MASTER=zk://127.0.0.1:2181/mesos \
  -e MESOS_SWITCH_USER=0 \
  -e MESOS_CONTAINERIZERS=docker,mesos \
  -e MESOS_LOG_DIR=/var/log/mesos \
  -e MESOS_WORK_DIR=/var/tmp/mesos \
  -v "$(pwd)/log/mesos:/var/log/mesos" \
  -v "$(pwd)/tmp/mesos:/var/tmp/mesos" \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /cgroup:/cgroup \
  -v /sys:/sys \
  -v /usr/local/bin/docker:/usr/local/bin/docker \
  mesosphere/mesos-slave:1.7.1


# Caught Mesos_Master ID, executed on it:

docker exec -it f586329b0dcf bash

#Preparation
apt-get install curl -y

#Install java
apt-get update -y
#apt-get upgrade -y
apt-get install software-properties-common -y
add-apt-repository ppa:openjdk-r/ppa -y
apt-get update -y
apt-get install openjdk-8-jdk -y
update-java-alternatives -s java-1.8.0-openjdk-amd64

#Install Spark

curl -O http://d3kbcqa49mib13.cloudfront.net/spark-2.2.0-bin-hadoop2.7.tgz
tar -zxvf spark-2.2.0-bin-hadoop2.7.tgz -C /opt
SPARK_HOME=/opt/spark-2.2.0-bin-hadoop2.7
PATH=$SPARK_HOME/bin:$PATH


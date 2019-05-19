cassandra_machine=$(docker ps | grep 'cassandra' | grep -o '[^ ]*cassandra[^ ]*'|tail -1)

docker exec -it $cassandra_machine cqlsh

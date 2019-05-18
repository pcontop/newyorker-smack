cassandra_machine=$(docker ps | grep 'cassandra' | grep -o '[^ ]*cassandra[^ ]*'|tail -1)
cassandra_sql=yelp.sql

docker cp $cassandra_sql $cassandra_machine:/
docker exec -d  $cassandra_machine cqlsh -f /yelp.sql
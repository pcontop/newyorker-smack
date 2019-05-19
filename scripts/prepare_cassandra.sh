cassandra_machine=$(docker ps | grep 'cassandra' | grep -o '[^ ]*cassandra[^ ]*'|tail -1)

function run_sql() {
    sql=$1
    docker cp $sql $cassandra_machine:/
    docker exec -d  $cassandra_machine cqlsh -f /$sql
}

run_sql keyspace.sql
run_sql tables.sql
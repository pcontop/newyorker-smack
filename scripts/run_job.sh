file_tar_gz=$1

cassandra_machine=$(docker ps | grep 'cassandra' | grep -o '[^ ]*cassandra[^ ]*'|tail -1)
spark_machine=$(docker ps | grep 'spark-cli' | grep -o '[^ ]*spark-solo[^ ]*')
process_jar=NewYorker-assembly-1.0.jar
tmp_dir=tmp
destination_dir=/datafiles

function clear_destination() {
	docker exec -it $spark_machine rm -rf $destination_dir
	docker exec -it $spark_machine mkdir $destination_dir
}

function send_file() {
	tmp_file=$1
	rm -rf $tmp_dir 2>/dev/null
	mkdir $tmp_dir
	tar xvf $file_tar_gz -C $tmp_dir $1
	docker cp $tmp_dir/$tmp_file $spark_machine:$destination_dir/
	rm -rf $tmp_dir 2>/dev/null
}

function exec_spark(){
	class=$1
	docker exec -it \
	$spark_machine spark-submit \
	--class $class  \
	--executor-memory 6g \
	--num-executors 1 \
	/$process_jar /datafiles $cassandra_machine

	clear_destination
}
	
#Send jar
docker cp $process_jar $spark_machine:/

clear_destination
send_file business.json
exec_spark pcontop.ny.lab.reader.BusinessReader




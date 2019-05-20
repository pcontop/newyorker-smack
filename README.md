# What is the project?

This project was made to deploy a docker stack to read the .json files contained on the tar.gz files from the
yelp challenge (https://www.yelp.com/dataset_challenge/dataset) into a tabulated format into Cassandra.

The stacker has been deployed on two instances, one for cloudera (named 'Cloudera'), and another for the spark-cli
(named 'spark-cli'), on a stack names nylab.

# How to use this system:

## Installation

- Copy all the files on the scripts subdir to a directory in a machine with docker installed, and internet
access to the Docker repository.

- Initiate the process running the create_stack.sh:

```bash
 ./create_stack.sh
```

This will download and initiate the stack with spark-cli and the cassandra images.

- Initialize the cassandra tables on the cassandra image:

```bash
 ./cassandra_initiate.sh
```

## Accessing Cassandra

- You may lon on cassandra starting the script:

```bash
./connect_cassandra.sh
```

The tables are in the run_test keyspace:

```bash
cqlsh:run_test> use run_test;
```

and those are the tables:

```bash
cqlsh:run_test> describe tables;
```

business  photo  checkin  tip  user  review

To leave Cassandra, you may use this command:

```bash
cqlsh:run_test> exit;
```

## Running the project

- To run the project, call the script, referencing the tar.gz from yelp to be processed:

```bash
./run_job.sh <tar.gz file>
```

for instance:

```bash
./run_job.sh yelp_dataset.tar
```

This will initialize the process that will extract the json files and load them to the six tables above.


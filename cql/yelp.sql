CREATE KEYSPACE if not exists run_test
  WITH REPLICATION = {
   'class' : 'SimpleStrategy',
   'replication_factor' : 1
  };


create table if not exists run_test.yelp
(
   business_id text primary key,
   name text,
   address text,
   city text,
   state text,
   postal_code text,
   latitude decimal,
   longitude decimal,
   stars decimal,
   review_count int,
   is_open int,
   attributes map<text, text>,
   categories text,
   hours map<text,text>
);
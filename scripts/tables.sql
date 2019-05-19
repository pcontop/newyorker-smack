create table if not exists run_test.business
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

create table if not exists run_test.photo
(
    caption text,
    photo_id text primary key,
    business_id text,
    label text
);
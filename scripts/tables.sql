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

create table if not exists run_test.tip
(
    user_id text,
    business_id text,
    text text,
    date text,
    compliment_count int,
    primary key (user_id, business_id, date)
);

create table if not exists run_test.checkin
(
    business_id text,
    date text,
    primary key (business_id)
);

create table if not exists run_test.review
(
   review_id text primary key,
   user_id text,
   business_id text,
   stars decimal,
   useful int,
   funny int,
   cool int,
   text text,
   date text
);

create table if not exists run_test.user
(
    user_id text primary key,
    name text,
    review_count int,
    yelping_since text,
    useful int,
    funny int,
    cool int,
    elite text,
    friends text,
    fans int,
    average_stars decimal,
    compliment_hot int,
    compliment_more int,
    compliment_profile int,
    compliment_cute int,
    compliment_list int,
    compliment_note int,
    compliment_plain int,
    compliment_cool int,
    compliment_funny int,
    compliment_writer int,
    compliment_photos int
);

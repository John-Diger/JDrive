create table bucket (
    id bigint auto_increment primary key,
    shared_file longblob,
    created_at datetime,
    modified_at datetime,
    status boolean
);

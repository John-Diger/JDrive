create table bucket (
	id bigint auto_increment primary key,
    shared_file longblob,
    created_at datetime,
    modified_at datetime,
    status boolean
) default charset = utf8mb4;

drop table bucket;

select * from bucket;

insert into bucket(shared_file, created_at, modified_at, status) values
(LOAD_FILE('E:/Priority/JDrive/JDrive/src/main/resources/shared/1주차수업이미지.jpg'), now(), now(), true);
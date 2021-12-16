select * from USERS
select * from score


create sequence SEQ_BB;

create table BB (
    seq number not null primary key
    , writer varchar2(20) not null
    , pwd varchar2(20) not null
    , email varchar2(100) 
    , title varchar2(200) not null
    , writedate date default sysdate
    , readed number default 0
    , tag number(1) default  0   -- 0 text , 1 html
    , filename varchar(50)
    , filerealname varchar(50)
    , content clob
);

select * from bb;

drop table BB;

insert into bb (seq, writer, pwd, title) values (1, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (2, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (3, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (4, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (5, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (6, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (7, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (8, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (9, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (10, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (11, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (12, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (13, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (14, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (15, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (16, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (17, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (18, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (19, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (20, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (21, '둘리', '1', '11');
insert into bb (seq, writer, pwd, title) values (22, '둘리', '1', '11');


select * from bbs_file;


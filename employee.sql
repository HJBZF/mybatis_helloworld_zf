create table zftbl_employee(
       id number(11) primary key,
       last_name varchar(255),
       gender char(1),
       email varchar(255)
)
create sequence seq_zftbl_employee_id start with 1;
select * from zftbl_employee;
insert into zftbl_employee values(seq_zftbl_employee_id.nextval,'tom',0,'1838947259@qq.com');
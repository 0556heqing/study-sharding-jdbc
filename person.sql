create table person
(
  id            bigint auto_increment
    primary key,
  name          varchar(255) null
  comment '姓名',
  sex           int          null
  comment '性别',
  birthday      date         null
  comment '生日',
  addr_province varchar(255) null
  comment '省份',
  remark        varchar(255) null
  comment '备注'
)
  comment '用户';
create table TB_USER
(
  id     VARCHAR2(32),
  name   VARCHAR2(32),
  age    NUMBER(3),
  sex    NUMBER(1),
  mobile NUMBER(12),
  remark VARCHAR2(1000)
);
create table TB_ROLE(
   id varchar2(32),
   role_name varchar2(32)
);
create table TB_USER_ROLE(
   id varchar2(32),
   user_id varchar2(32),
   role_id varchar2(32)
);

create table TB_USER_INFO(
  id varchar2(33),
  username varchar2(33),
  name varchar2(33),
  password varchar2(33),
  salt varchar2(33),
  state number(1)
);


create table TB_SYS_ROLE(
  id varchar2(33),
  role varchar2(33),
  description varchar2(33),
  available NUMBER(1)
);


create table tb_Sys_Permission(
  id varchar2(33),
  name varchar2(33),
  resource_Type varchar2(33),
  url varchar2(200),
  permission varchar2(200),
  parent_Id Number(30),
  parent_Ids varchar2(200),
  available Number(1)
);

create table sys_user_role(
 user_id varchar2(33),
 role_id varchar2(33)
);

create table sys_role_permission(
 role_id varchar2(33),
 permission_id varchar2(33)
);

INSERT INTO tb_user_info (id,username,name,password,salt,state) VALUES ('1', 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 0);
INSERT INTO tb_sys_permission (id,available,name,parent_id,parent_ids,permission,resource_type,url) VALUES (1,0,'用户管理',0,'0/','userInfo:view','menu','userInfo/userList');
INSERT INTO tb_sys_permission (id,available,name,parent_id,parent_ids,permission,resource_type,url) VALUES (2,0,'用户添加',1,'0/1','userInfo:add','button','userInfo/userAdd');
INSERT INTO tb_sys_permission (id,available,name,parent_id,parent_ids,permission,resource_type,url) VALUES (3,0,'用户删除',1,'0/1','userInfo:del','button','userInfo/userDel');
INSERT INTO tb_sys_role (id,available,description,role) VALUES (1,0,'管理员','admin');
INSERT INTO tb_sys_role (id,available,description,role) VALUES (2,0,'VIP会员','vip');
INSERT INTO tb_sys_role (id,available,description,role) VALUES (3,1,'test','test');
INSERT INTO sys_role_permission VALUES ('1', '1');
INSERT INTO sys_role_permission (permission_id,role_id) VALUES (1,1);
INSERT INTO sys_role_permission (permission_id,role_id) VALUES (2,1);
INSERT INTO sys_role_permission (permission_id,role_id) VALUES (3,2);
INSERT INTO sys_user_role (role_id,uid) VALUES (1,1);


create table tarm_privilege.t_group (id  serial not null, deleted INTEGER DEFAULT 0 not null, groupid varchar(255), groupname varchar(255), grouppriority varchar(255), groupstatus varchar(255), primary key (id));
create table tarm_privilege.t_role (id  serial not null, deleted INTEGER DEFAULT 0 not null, groupid varchar(255), roledeviceid varchar(255), roledevicetype varchar(255), roleid varchar(255), rolepagenum varchar(255), roleservicecode varchar(255), roleuserid varchar(255), roleusertype varchar(255), primary key (id));
create table tarm_privilege.t_group (id  serial not null, deleted INTEGER DEFAULT 0 not null, groupid varchar(255), groupname varchar(255), grouppriority varchar(255), groupstatus varchar(255), primary key (id));
create table tarm_privilege.t_role (id  serial not null, deleted INTEGER DEFAULT 0 not null, groupid varchar(255), roledeviceid varchar(255), roledevicetype varchar(255), roleid varchar(255), rolepagenum varchar(255), roleservicecode varchar(255), roleuserid varchar(255), roleusertype varchar(255), primary key (id));
create table tarm_privilege.t_group (id  serial not null, deleted INTEGER DEFAULT 0 not null, groupid varchar(255), groupname varchar(255), grouppriority varchar(255), groupstatus varchar(255), primary key (id));
create table tarm_privilege.t_role (id  serial not null, deleted INTEGER DEFAULT 0 not null, groupid varchar(255), roledeviceid varchar(255), roledevicetype varchar(255), roleid varchar(255), rolepagenum varchar(255), roleservicecode varchar(255), roleuserid varchar(255), roleusertype varchar(255), primary key (id));
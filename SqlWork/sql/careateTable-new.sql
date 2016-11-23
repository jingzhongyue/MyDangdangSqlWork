/******************** Add Table: open_console_url S ************************/
DROP TABLE IF EXISTS `open_console_url_0`;
CREATE TABLE open_console_url_0
(
id VARCHAR(45)
COMMENT 'XXX' NOT NULL,
type TINYINT
COMMENT '0:car,1:clue' NULL,
merchant_no VARCHAR(45) NOT NULL,
http_type TINYINT DEFAULT 1
COMMENT '接收形式 0:get 1:post' NOT NULL,
url VARCHAR(255) NOT NULL,
create_time TIMESTAMP
COMMENT '创建时间' NOT NULL,
update_time TIMESTAMP
COMMENT '修改时间' NOT NULL,
app_id VARCHAR(45)
COMMENT 'app_id 例如:jianfa' NULL,
username VARCHAR(45)
COMMENT 'username 例如:jianfa' NULL
);
ALTER TABLE open_console_url_0 ADD CONSTRAINT pkopen_console_url
PRIMARY KEY (id);

DROP TABLE IF EXISTS `open_console_url_1`;
CREATE TABLE open_console_url_1
(
id VARCHAR(45)
COMMENT 'XXX' NOT NULL,
type TINYINT
COMMENT '0:car,1:clue' NULL,
merchant_no VARCHAR(45) NOT NULL,
http_type TINYINT DEFAULT 1
COMMENT '接收形式 0:get 1:post' NOT NULL,
url VARCHAR(255) NOT NULL,
create_time TIMESTAMP
COMMENT '创建时间' NOT NULL,
update_time TIMESTAMP
COMMENT '修改时间' NOT NULL,
app_id VARCHAR(45)
COMMENT 'app_id 例如:jianfa' NULL,
username VARCHAR(45)
COMMENT 'username 例如:jianfa' NULL
);
ALTER TABLE open_console_url_1 ADD CONSTRAINT pkopen_console_url
PRIMARY KEY (id);

DROP TABLE IF EXISTS `open_console_url_2`;
CREATE TABLE open_console_url_2
(
id VARCHAR(45)
COMMENT 'XXX' NOT NULL,
type TINYINT
COMMENT '0:car,1:clue' NULL,
merchant_no VARCHAR(45) NOT NULL,
http_type TINYINT DEFAULT 1
COMMENT '接收形式 0:get 1:post' NOT NULL,
url VARCHAR(255) NOT NULL,
create_time TIMESTAMP
COMMENT '创建时间' NOT NULL,
update_time TIMESTAMP
COMMENT '修改时间' NOT NULL,
app_id VARCHAR(45)
COMMENT 'app_id 例如:jianfa' NULL,
username VARCHAR(45)
COMMENT 'username 例如:jianfa' NULL
);
ALTER TABLE open_console_url_2 ADD CONSTRAINT pkopen_console_url
PRIMARY KEY (id);

DROP TABLE IF EXISTS `open_console_url_3`;
CREATE TABLE open_console_url_3
(
id VARCHAR(45)
COMMENT 'XXX' NOT NULL,
type TINYINT
COMMENT '0:car,1:clue' NULL,
merchant_no VARCHAR(45) NOT NULL,
http_type TINYINT DEFAULT 1
COMMENT '接收形式 0:get 1:post' NOT NULL,
url VARCHAR(255) NOT NULL,
create_time TIMESTAMP
COMMENT '创建时间' NOT NULL,
update_time TIMESTAMP
COMMENT '修改时间' NOT NULL,
app_id VARCHAR(45)
COMMENT 'app_id 例如:jianfa' NULL,
username VARCHAR(45)
COMMENT 'username 例如:jianfa' NULL
);
ALTER TABLE open_console_url_3 ADD CONSTRAINT pkopen_console_url
PRIMARY KEY (id);

/******************** Add Table: open_console_url E ************************/



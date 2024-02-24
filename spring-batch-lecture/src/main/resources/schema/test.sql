CREATE TABLE IF NOT EXISTS my_job_test
(
    id              SERIAL PRIMARY KEY     comment 'PK',
    `name`          varchar(100)  not null comment 'name',
    created_at      DATETIME      not null COMMENT '생성일',
    created_by      VARCHAR(100)  not null COMMENT '생성자'
) comment 'my job table';
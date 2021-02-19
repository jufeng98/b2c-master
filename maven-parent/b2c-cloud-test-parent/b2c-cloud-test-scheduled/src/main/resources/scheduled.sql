drop table if exists `spring_scheduled_cron`;
create table `spring_scheduled_cron`
(
    `cron_id`         int auto_increment primary key,
    `cron_key`        varchar(30) not null unique,
    `cron_expression` varchar(30) not null,
    `task_explain`    varchar(50),
    `status`          tinyint comment '0:正常;1:暂停'
);
insert into `spring_scheduled_cron`
values (1, 'ignoreTask', '* * 2 * * ?', null, 0);
insert into `spring_scheduled_cron`
values (2, 'DbPrintTask', '*/5 * * * * ?', 'demo', 0);
insert into `spring_scheduled_cron`
values (3, 'DbPrintTask1', '*/5 * * * * ?', 'demo1', 0);
insert into `spring_scheduled_cron`
values (4, 'com.javamaster.b2c.cloud.test.scheduled.dbtask.DbPrintTas2', '*/5 * * * * ?', 'demo2', 0);
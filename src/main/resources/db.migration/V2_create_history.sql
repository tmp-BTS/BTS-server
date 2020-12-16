create table history (
        id bigint unsigned not null auto_increment primary key,
        store_id bigint unsigned not null,
        temperature varchar(100),
        enter_time varchar(100),
        FOREIGN KEY(`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table user(
)

create table place(
    id bigint unsigned not null auto_increment primary key,
    title varchar(100),
    location varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
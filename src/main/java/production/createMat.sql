CREATE TABLE IF NOT EXISTS Material (
id int unsigned not null auto_increment,
nume varchar(40) not null,
quantity int ,
type varchar(40) not null,
price int not null,
primary key (id)
)auto_increment=1;
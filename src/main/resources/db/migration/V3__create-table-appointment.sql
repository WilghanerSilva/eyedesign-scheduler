CREATE TABLE appointment (
    id varchar(100) primary key,
    day_date date,
    confirmed tinyint(1),
    userID varchar(100) not null,
    foreign key(userID) references users(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    timeID varchar(100) not null,
    foreign key(timeID) references time(id)
    ON DELETE CASCADE ON UPDATE CASCADE
)
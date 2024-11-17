
create table movies (
	id int auto_increment primary key,
	code int unique not null,
	title varchar(150) not null,
	details varchar(100),
	release_date date,
	production_year int,
	synopsys text,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) comment '영화' charset = utf8mb4;

create table movie_daily_stats (
	id int auto_increment primary key,
	movie_code int not null,
	ranking int not null,
	revenue int not null,
	audience int not null,
	date date not null,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	foreign key (movie_code) references movies(code)
) comment '일일 영화 정보' charset = utf8mb4;

create table directors (
	id int auto_increment primary key,
	code int unique not null,
	name varchar(30) not null,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) comment '감독' charset = utf8mb4;

create table leadactors (
	id int auto_increment primary key,
	code int unique not null,
	name varchar(30) not null,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) comment '주연배우' charset = utf8mb4;

create table movie_director (
	id BIGINT auto_increment PRIMARY KEY,
    movie_code INT NOT NULL,
    director_code INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key (movie_code) references movies(code),
    foreign key (director_code) references directors(code)
) comment '영화-영화감독' charset = utf8mb4;

create table movie_leadactor (
	id BIGINT auto_increment primary key,
	movie_code int not null,
	leadactor_code int not null,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key (movie_code) references movies(code),
    foreign key (leadactor_code) references leadactors(code)
) comment '영화-주연배우' charset = utf8mb4;

CREATE TABLE movie_ratings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    code int NOT NULL,
    rating DOUBLE CHECK (rating >= 0.5 AND rating <= 10.0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (movie_code) REFERENCES movies(code)
);
DROP TABLE IF EXISTS GAME;
DROP TABLE IF EXISTS TYPES;
DROP TABLE IF EXISTS CONNECT_GAME_TYPE;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS FOLLOWED_USERS;
DROP TABLE IF EXISTS REVIEW;
DROP TABLE IF EXISTS FOLLOWED_GAMES;


CREATE TABLE IF NOT EXISTS GAME (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    imageURL VARCHAR (150),
    description VARCHAR(500) NOT NULL,
    author VARCHAR(80) NOT NULL
);

CREATE TABLE IF NOT EXISTS TYPES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS CONNECT_GAME_TYPE(
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    id_Game BIGINT,
    id_Type BIGINT,
    CONSTRAINT FK_GAME_CONNECT FOREIGN KEY (id_Game) REFERENCES GAME(id),
    CONSTRAINT FK_TYPE_CONNECT FOREIGN KEY (id_Type) REFERENCES TYPES(id)
);

CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_Name VARCHAR(30),
    last_Name VARCHAR(30),
    password VARCHAR(70) NOT NULL ,
    nick VARCHAR(30) NOT NULL ,
    email VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS FOLLOWED_USERS (
    id_User BIGINT NOT NULL,
    id_Followed_User BIGINT  NOT NULL,
    CONSTRAINT whoIsTracking FOREIGN KEY (id_User) REFERENCES USERS(id),
    CONSTRAINT whoIsFollowing FOREIGN KEY (id_Followed_User) REFERENCES USERS(id)
);

CREATE TABLE IF NOT EXISTS REVIEW (
    id_Review BIGINT AUTO_INCREMENT PRIMARY KEY ,
    id_User BIGINT NOT NULL ,
    id_Game BIGINT NOT NULL ,
    mark double NOT NULL ,
    date TIMESTAMP NOT NULL ,
    description VARCHAR (500) NOT NULL ,
    CONSTRAINT userId FOREIGN KEY (id_User) REFERENCES USERS(id),
    CONSTRAINT gameId FOREIGN KEY (id_Game) REFERENCES GAME(id)
);

CREATE TABLE IF NOT EXISTS FOLLOWED_GAMES (
    id_User BIGINT NOT NULL ,
    followed_Id_Game BIGINT NOT NULL ,
    CONSTRAINT userIdFollow FOREIGN KEY (id_User) REFERENCES USERS(id),
    CONSTRAINT gameIdFollow FOREIGN KEY (followed_Id_Game) REFERENCES GAME(id)
);



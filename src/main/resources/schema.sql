CREATE TABLE IF NOT EXISTS HARDWARE(
    CODE IDENTITY NOT NULL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    PRICE DECIMAL(15,2) NOT NULL,
    TYPE VARCHAR(20) NOT NULL,
    AMOUNT INT NOT NULL
);

CREATE TABLE IF NOT EXISTS REVIEW(
    CODE IDENTITY NOT NULL PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    RATING INT NOT NULL,
    HARDWAREID INT NOT NULL,
    FOREIGN KEY (HARDWAREID) REFERENCES HARDWARE(CODE)
);

CREATE TABLE IF NOT EXISTS USER(
    ID IDENTITY NOT NULL PRIMARY KEY,
    USERNAME VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD VARCHAR(1000) NOT NULL
);

CREATE TABLE IF NOT EXISTS AUTHORITY(
    ID IDENTITY NOT NULL PRIMARY KEY,
    AUTHORITY_NAME VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS USER_AUTHORITY(
    USER_ID BIGINT NOT NULL,
    AUTHORITY_ID BIGINT NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USER(ID),
    FOREIGN KEY (AUTHORITY_ID) REFERENCES AUTHORITY(ID)
);
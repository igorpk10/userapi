CREATE TABLE TB_USERS(
    user_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    full_name varchar(255) NOT NULL,
    user_name varchar(50) NOT NULL,
    user_password varchar(100) NOT NULL,

    UNIQUE KEY unique_user_name (user_name)
);
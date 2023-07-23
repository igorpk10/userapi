CREATE TABLE TB_CELLPHONES(
    phone_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cell_phone varchar(20) NOT NULL,
    user_id int,

   UNIQUE KEY unique_cell_phone (cell_phone),
   CONSTRAINT TB_CELLPHONES_fk FOREIGN KEY (user_id) REFERENCES TB_USERS(user_id)
);
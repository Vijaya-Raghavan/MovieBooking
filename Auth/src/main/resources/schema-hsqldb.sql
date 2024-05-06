CREATE TABLE user (id BIGINT, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, gender VARCHAR(1) NOT NULL, email VARCHAR(60) NOT NULL, phone VARCHAR(15) NOT NULL, password VARCHAR(255) NOT NULL);
ALTER TABLE user ADD CONSTRAINT user_pk PRIMARY KEY (id);
ALTER TABLE user ADD CONSTRAINT user_email_uq UNIQUE (email);
ALTER TABLE user ADD CONSTRAINT user_phone_uq UNIQUE (phone);
CREATE SEQUENCE user_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE role (id BIGINT, name VARCHAR(50) NOT NULL, description VARCHAR(255) NOT NULL);
ALTER TABLE role ADD CONSTRAINT role_pk PRIMARY KEY (id);
ALTER TABLE role ADD CONSTRAINT role_name_uq UNIQUE (name);
CREATE SEQUENCE role_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE user_role (id BIGINT, user_id BIGINT NOT NULL, role_id BIGINT NOT NULL);
ALTER TABLE user_role ADD CONSTRAINT user_role_pk PRIMARY KEY (id);
ALTER TABLE user_role ADD CONSTRAINT user_email_uq UNIQUE (role_name);
ALTER TABLE user_role ADD CONSTRAINT user_role_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE user_role ADD CONSTRAINT user_role_role_id_fk FOREIGN KEY (role_id) REFERENCES role (id);
ALTER TABLE user_role ADD CONSTRAINT user_role_uq UNIQUE (user_id, role_id);
CREATE SEQUENCE user_role_id_seq START WITH 1 INCREMENT BY 1;



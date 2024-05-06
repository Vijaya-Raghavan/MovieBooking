INSERT INTO user (id, first_name, last_name, gender, email, phone, password) VALUES (user_id_seq.nextval, 'vijayaraghavan', 'n', 'M', 'vijayaraghavan1805@gmail.com', '+919940643165', 'cb64b85910ffb1cac69e59d2a349a98deba06cd4bd88be1dc7f1cb3704030d21bb42befd9b79f845582b664134193424');

INSERT INTO role (id, name, description) VALUES (role_id_seq.nextval, 'ADMIN', 'Admin role');

INSERT INTO user_role (id, name, description) VALUES (user_role_id_seq.nextval, 1, 1);

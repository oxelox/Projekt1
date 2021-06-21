SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `app_user`;
DROP TABLE IF EXISTS `app_user_details`;
DROP TABLE IF EXISTS `verification_token`;
DROP TABLE IF EXISTS `pizza`;
DROP TABLE IF EXISTS `ingredient`;
DROP TABLE IF EXISTS `pizza_ingredients`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `car`;
DROP TABLE IF EXISTS `deliverer`;
DROP TABLE IF EXISTS `complaint`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `app_user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `is_enabled` BOOLEAN NOT NULL,
    `role` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `app_user_details` (
                                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                                    `app_user_id` BIGINT NOT NULL,
                                    `city` VARCHAR(100) NOT NULL,
                                    `street` VARCHAR(100) NOT NULL,
                                    `number` INT NOT NULL,
                                    PRIMARY KEY (`id`),
                                    CONSTRAINT `fk_user_details`
                                        FOREIGN KEY (`app_user_id`)
                                            REFERENCES `app_user` (`id`)
);

CREATE TABLE `verification_token` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `app_user_id` BIGINT NOT NULL,
    `token` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_token`
        FOREIGN KEY (`app_user_id`)
            REFERENCES `app_user` (`id`)
);
CREATE TABLE `pizza` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(100) NULL,
                           `description` VARCHAR(800) NULL,
                           `price` DECIMAL(5,2),
                           PRIMARY KEY (`id`)
);
CREATE TABLE `ingredient` (
                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                              `name` VARCHAR(100) NULL,
                              PRIMARY KEY (`id`)
);
CREATE TABLE `pizza_ingredients`(
                                    `pizza_id` BIGINT NOT NULL,
                                    `ingredient_id` BIGINT NOT NULL,
                                    PRIMARY KEY (`pizza_id`, `ingredient_id`),
                                    CONSTRAINT `fk_pizza_ingredient_pizza_id`
                                        FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`id`),
                                    CONSTRAINT `fk_ingredient_pizza_ingredient_id`
                                        FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`)
);
CREATE TABLE `car` (
                       `id` BIGINT NOT NULL AUTO_INCREMENT,
                       `brand` VARCHAR(100),
                       `model` VARCHAR(100),
                       `oc_expire` DATE,
                       PRIMARY KEY (`id`)
);
CREATE TABLE `deliverer` (
                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                             `car_id` BIGINT NOT NULL,
                             `first_name` VARCHAR(200),
                             `last_name` VARCHAR(200),
                             PRIMARY KEY (`id`),
                             CONSTRAINT `fk_deliverer_car`
                                FOREIGN KEY (`car_id`)
                                REFERENCES `car` (`id`)

);

CREATE TABLE `orders` (
                                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                                    `app_user_id` BIGINT NOT NULL,
                                    `deliverer_id` BIGINT NOT NULL,
                                    `order_date` DATETIME,
                                    `price` DECIMAL(5,2),
                                    `status` VARCHAR(20),
                                    `description` VARCHAR(200),
                                    PRIMARY KEY (`id`),
                                    CONSTRAINT `fk_user_orders`
                                        FOREIGN KEY (`app_user_id`)
                                            REFERENCES `app_user` (`id`),
                                    CONSTRAINT `fk_deliverer_orders`
                                            FOREIGN KEY (`deliverer_id`)
                                            REFERENCES `deliverer` (`id`)
);

CREATE TABLE `complaint` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `app_user_id` BIGINT NOT NULL,
    `description` VARCHAR(200),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_complaint_user`
                         FOREIGN KEY (`app_user_id`)
                         REFERENCES app_user (`id`)

);

INSERT INTO `app_user` (id, username, password, email, is_enabled, role) VALUES
('1','user','$2a$10$VbqHwpgv/H4m1uasfTpktuXu29fOdEegTPdBCpyEG8OUjqhqqVN6O','test123@gmail.com',TRUE,'ROLE_USER'),
('2','service','$2a$10$PbSkmUkGjisGJIa3phonYukaXU.KrpVmzMrphhdAYu5TCSCBPIihG','test321@gmail.com',TRUE,'ROLE_SERVICE');

INSERT INTO `app_user_details` (id, app_user_id, city, street, number) VALUES
('1', '1', 'Warszawa', 'Fabryczna', 32),
('2', '2', 'Warszawa', 'Pulawska', 32);

INSERT INTO `pizza` (id, name, description, price) VALUES
('1', 'MARGHERITA', 'Klasyk', 21.90),
('2', 'PEPPERONI', 'DLA FANOW PEPPERONI', 36.50),
('3', 'HAWAJSKA', 'DLA FANOW ANANASA', 34.50),
('4', 'VESUVIO', 'Proste polaczenie', 32.50);

INSERT INTO `ingredient` (id, name) VALUES
('1', 'sos pomidorowy'),
('2', 'mozzarella'),
('3', 'pepperoni'),
('4', 'ananas'),
('5', 'szynka');

INSERT INTO `pizza_ingredients` (pizza_id, ingredient_id) VALUES
('1','1'),
('1','2'),
('2','1'),
('2','2'),
('2','3'),
('3','1'),
('3','2'),
('3','4'),
('3','5'),
('4','1'),
('4','2'),
('4','5');



INSERT INTO `car` (id, brand, model, oc_expire) VALUES
('1', 'Opel', 'Astra', '2022-06-21'),
('2', 'Reno', 'Laguna', '2022-06-21'),
('3', 'Opel', 'Zefira', '2022-06-21');

INSERT INTO `deliverer` (id, car_id, first_name, last_name) VALUES
('1', '1', 'Wladimir', 'Kulowicz'),
('2', '2', 'Sasza', 'Piotrowicz'),
('3', '3', 'Daniel', 'Kowalski');

INSERT INTO `orders` (id, app_user_id, deliverer_id, order_date, price, status, description) VALUES
('1','1', '1', '2021-05-06', 35.5, 'REALIZACJA', 'Opis 1'),
('2','1', '3', '2021-05-06', 35.5, 'DOSTAWA', 'Opis 2'),
('3','1', '2', '2021-05-06', 35.5, 'KONIEC', 'Opis 3');
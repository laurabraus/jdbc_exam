# jdbc_exam

Create an aplication that interacts with a SQL Database using JDBC

The application must allow to:

1. see all cars
2. add a car
3. update a car
4. delete a car

>>>To create the table, you can use the following script:

CREATE TABLE `test`.`car` (
  `id_car` INT NOT NULL AUTO_INCREMENT,
  `license_plate` VARCHAR(45) NULL,
  `make` VARCHAR(45) NULL,
  `model` VARCHAR(45) NULL,
  `price` DECIMAL(2) NULL,
  PRIMARY KEY (`id_car`));
  
>>>Example of a car

license_plate: 1234BBB
make: Alfa Romeo
model: Giulietta
price: 30000
  

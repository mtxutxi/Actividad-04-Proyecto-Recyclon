INSERT INTO usuario (nombre, apellidos, telefono, f_nacimiento, dni, email, contrasena_hash, dir_envio, dir_facturacion, is_admin) 
VALUES 
('Belén','BB',612345678,'1990-07-22','12345678A','florenciabelen.barbas.bogado@zabalburu.org','$2a$12$1BqbQeYqcK8zvudtUn0D7.A.8d9ZG/OH.JTzumyiCJxJvCMQDSDuq','Calle Mayor 12, Madrid','Calle Mayor 12, Madrid',0),
('Txutxi','Martínez González',698765432,'1982-09-30','23456789B','jesusalejandro.martinez.gonzalez@zabalburu.org','$2a$12$1BqbQeYqcK8zvudtUn0D7.A.8d9ZG/OH.JTzumyiCJxJvCMQDSDuq','Av. Libertad 45, Bilbao','Av. Libertad 45, Bilbao',0),
('Sheila','Roca Garrido',677889900,'1993-06-12','45678901D','sheila.roca.garrido@zabalburu.org','$2a$12$1BqbQeYqcK8zvudtUn0D7.A.8d9ZG/OH.JTzumyiCJxJvCMQDSDuq','Calle Luna 23, Valencia', 'Calle Río 5, Zaragoza', 0),
('Admin',null,null,null,null,'admin','$2a$12$EeNM7Po0zjLL5Q.mhoSpPuItOz6jdaYo3SiFz1Yjtpvbhb61XlzSi',null,null,1);
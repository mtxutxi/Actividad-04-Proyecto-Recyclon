package org.zabalburu.recyclon.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {
	
	public static String hashPassword(String password) {
		return BCrypt.withDefaults().hashToString(12, password.toCharArray());
		//BCrypt.withDefaults() -> Crea una instancia de BCrypt con configuracion por defecto
		//.hashToString(12, ...) -> Genera el hash en formato String para poder guardarlo en la BBDD
			//12 -> Coste de trabajo (cuanto mayor el nº mas seguro el hash)
			//.password.toCharArray() -> Crea un Array de la contraseña ['c','o','n','t','r','a']
	}
	
	public static boolean verifyPassword(String password, String hashedPassword) {
		BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
		//BCrypt.Result -> Es una clase que contiene el metodo "verify()"
		//BCrypt.verifyer() -> Es un metodo que devuelve un verificador, sirve para comparar contraseñas con hash
		//.verify(password.toCharArray(), hashedPassword) -> verifica que el Array de la contraseña coincide con el hash
		return result.verified; //Devuelve la verificación de la contraseña como true(coincide) o false(no coincide)
	}
	
//	public static void main(String[] args) {
	//Para crear la contraseña hash
//		System.out.println(PasswordUtil.hashPassword("usuario")); 
//		System.out.println(PasswordUtil.hashPassword("admin"));
	//Para verificar la contraseña hash
//		System.out.println(PasswordUtil.verifyPassword("usuario", "$2a$12$1BqbQeYqcK8zvudtUn0D7.A.8d9ZG/OH.JTzumyiCJxJvCMQDSDuq"));
//		System.out.println(PasswordUtil.verifyPassword("admin", "$2a$12$EeNM7Po0zjLL5Q.mhoSpPuItOz6jdaYo3SiFz1Yjtpvbhb61XlzSi"));
//	}
}

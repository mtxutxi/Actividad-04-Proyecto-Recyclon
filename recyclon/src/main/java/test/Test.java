package test;

import org.zabalburu.recyclon.util.PasswordUtil;

public class Test {
	
	 public static void main(String[] args) {
	        System.out.println("=== TEST DE PASSWORDS ===\n");
	        
	        // 1️⃣ Crear hash de una contraseña
	        String password = "miPassword123";
	        String hash = PasswordUtil.hashPassword(password);
	        System.out.println("Password original: " + password);
	        System.out.println("Hash generado: " + hash);
	        System.out.println();
	        
	        // 2️⃣ Verificar password correcta
	        boolean correcto = PasswordUtil.verifyPassword("miPassword123", hash);
	        System.out.println("¿Password 'miPassword123' es correcta? " + correcto);
	        
	        // 3️⃣ Verificar password incorrecta
	        boolean incorrecto = PasswordUtil.verifyPassword("passwordIncorrecta", hash);
	        System.out.println("¿Password 'passwordIncorrecta' es correcta? " + incorrecto);
	        System.out.println();
	        
	        // 4️⃣ Generar hash para admin (para insertar en BD)
	        String adminHash = PasswordUtil.hashPassword("admin123");
	        System.out.println("Hash para admin123: " + adminHash);
	        
	        // 5️⃣ Generar hash para usuario (para insertar en BD)
	        String userHash = PasswordUtil.hashPassword("usuario123");
	        System.out.println("Hash para usuario123: " + userHash);
	    }

}

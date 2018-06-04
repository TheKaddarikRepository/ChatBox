package fr.afpa.security;

import java.security.KeyPairGenerator;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class UserPermission {

	
	public boolean password() {
		String  originalPassword = "password";
        String generatedSecuredPasswordHash = Cipher.getInstance("AES").init();
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(2048);
        KeyGenerator.getInstance("AES").generateKey();
        System.out.println(generatedSecuredPasswordHash);
         
        
        KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");

        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted =
            cipher.doFinal("This is just an example".getBytes());
        
        return BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
	}
}

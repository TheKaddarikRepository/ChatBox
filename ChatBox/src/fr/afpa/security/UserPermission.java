package fr.afpa.security;

public class UserPermission {

	
	public boolean password() {
		String  originalPassword = "password";
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        System.out.println(generatedSecuredPasswordHash);
         
        return BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
	}
}

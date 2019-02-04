package javaee.loja.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.security.Base64Encoder;

public class PassGenerator {	
	
	public String generate(String senha){
		String retorno = "";
		try {
			byte[] senhaCriptografada = MessageDigest.getInstance("sha-256").digest(senha.getBytes());
			retorno =  Base64Encoder.encode(senhaCriptografada);
		} catch (NoSuchAlgorithmException | IOException e) {
			new RuntimeException(e);
		}		
		return retorno;
	}
	

}

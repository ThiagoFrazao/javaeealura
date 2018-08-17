package main;

import utils.ConexaoHTTP;
import utils.LerArquivo;
import utils.Resposta;

public class Main {
	
	public static void main(String[] args){
		
		Resposta retorno = ConexaoHTTP.get("");
		
		System.out.println(retorno.getMensagem());
		String msg = LerArquivo.lerArquivo("C:/Users/E514189/documentoBase64.txt");
		retorno = ConexaoHTTP.post("/teste",msg);
		System.out.println(retorno.getMensagem());
	}

}

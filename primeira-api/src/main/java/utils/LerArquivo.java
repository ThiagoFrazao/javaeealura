package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class LerArquivo {

	public static String lerArquivo(String caminho) {
		
		String texto = "";
		try{
			FileReader arq = new FileReader(caminho);
		    BufferedReader lerArq = new BufferedReader(arq);
		    
		    String linha = lerArq.readLine();
		    texto = linha;
		    while(linha!= null){
		    	
		    	linha = lerArq.readLine();
		    	if(StringUtils.isNotBlank(linha)){
		    		texto = texto + linha;
		    	}
		    	
		    }		    
		    arq.close();
		} catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	                e.getMessage());	          
		}		
		return StringUtils.deleteWhitespace(texto);		
	}
	
	
}

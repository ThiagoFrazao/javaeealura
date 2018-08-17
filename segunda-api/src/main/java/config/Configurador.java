package config;

import org.glassfish.jersey.server.ResourceConfig;

public class Configurador extends ResourceConfig {
	
	public Configurador(){
		register(new MeuBinder());
		//packages(true, "config.RestApplication");
	}
	
}

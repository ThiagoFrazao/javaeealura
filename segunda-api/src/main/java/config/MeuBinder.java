package config;

import org.glassfish.jersey.internal.inject.AbstractBinder;

import services.LojaService;

public class MeuBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(LojaService.class).to(LojaService.class);		
	}

}

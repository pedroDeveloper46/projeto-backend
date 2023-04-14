package br.com.pedro.testeFinal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.pedro.testeFinal.dto.ConvertDto;

@Configuration
public class ConvertDtoConfig {
	
	@Bean
	public ConvertDto convertDto() {
		return new ConvertDto();
	}

}

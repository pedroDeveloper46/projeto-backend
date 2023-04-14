package br.com.pedro.testeFinal.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import lombok.Data;

@Embeddable
@Data
public class Endereco {
	
	@NotBlank(message = "A rua não pode ser vazia")
	private String rua;
	
	@NotNull(message = "O número da residência não pode ser nulo")
	private Integer numero;
	
	@NotBlank(message = "O cep não pode ser vazio")
	@Pattern(regexp="[0-9]{8}", message = "O CEP possui formato inválido") 
	private String cep;

}

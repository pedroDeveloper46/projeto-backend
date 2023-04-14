package br.com.pedro.testeFinal.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.pedro.testeFinal.model.Endereco;
import lombok.Data;

@Data
public class ClienteDto {

	private Long id;

	@NotBlank(message = "O Nome não pode ser vazio")
	private String nome;

	@NotBlank(message = "O CPF não pode ser vazio")
	@Pattern(regexp = "[0-9]{11}", message = "O CPF possui formato inválido")
	private String cpf;

	@NotBlank(message = "O telefone não pode ser vazio")
	@Pattern(regexp = "[0-9]{10,11}", message = "O formato do telefone é inválido")
	private String telefone;

	@Valid
	private Endereco endereco;

	@NotNull(message = "O rendimento mensal não pode ser nulo")
	private BigDecimal rendimentoMensal;
}

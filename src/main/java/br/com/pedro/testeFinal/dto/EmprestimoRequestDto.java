package br.com.pedro.testeFinal.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.pedro.testeFinal.enums.Relacionamento;
import lombok.Data;

@Data
public class EmprestimoRequestDto {
	
	@NotNull(message = "O valor inicial não pode ser nulo")
	private BigDecimal valorInicial;

	@NotNull(message = "A data Inicial não pode ser nula")
	private String dataInicial;
	
	
	@NotNull(message = "A data final não pode ser nula")
	private String dataFinal;
	
	@NotNull(message = "O tipo de Relacionamento não pode ser nulo")
	@Enumerated(EnumType.STRING)
	private Relacionamento relacionamento;

}

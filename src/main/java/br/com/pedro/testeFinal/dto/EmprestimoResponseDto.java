package br.com.pedro.testeFinal.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import br.com.pedro.testeFinal.enums.Relacionamento;

import lombok.Data;

@Data
public class EmprestimoResponseDto {
	
	
	private Long id;
	
	private ClienteDto cliente; 
	
	
	private BigDecimal valorInicial;
	
	
	private BigDecimal valorFinal;
	
	
	private LocalDateTime dataInicial;
	
	private LocalDateTime dataFinal;
	
	private Relacionamento relacionamento;

}

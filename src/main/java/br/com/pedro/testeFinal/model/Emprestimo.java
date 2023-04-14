package br.com.pedro.testeFinal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.pedro.testeFinal.enums.Relacionamento;
import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name = "emprestimo")
@Data
public class Emprestimo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O cliente não pode ser nulo")
	@ManyToOne
	@JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
	private Cliente cliente; 
	
	@NotNull(message = "O valor inicial não pode ser nulo")
	private BigDecimal valorInicial;
	
	@NotNull
	private BigDecimal valorFinal;
	
	@NotNull(message = "A data Inicial não pode ser nula")
	private LocalDateTime dataInicial;
	
	
	@NotNull(message = "A data final não pode ser nula")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(pattern =  "dd-MM-yyyy")
	private LocalDateTime dataFinal;
	
	@NotNull(message = "O tipo de Relacionamento não pode ser nulo")
	@Enumerated(EnumType.STRING)
	private Relacionamento relacionamento;
	
	public void calculaValorFinal() {
		this.valorFinal = this.relacionamento.calculaValorFinal(getValorInicial());
	}
	
	

}

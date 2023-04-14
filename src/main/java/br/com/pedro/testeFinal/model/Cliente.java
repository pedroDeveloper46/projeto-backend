package br.com.pedro.testeFinal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import lombok.Data;
import lombok.EqualsAndHashCode;


@SuppressWarnings("serial")
@Entity
@Table(name = "cliente")
@Data
public class Cliente implements Serializable  {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O Nome não pode ser vazio")
	@Column(nullable = false)
	private String nome;
	
	
	@NotBlank(message = "O CPF não pode ser vazio")
    @Pattern(regexp = "[0-9]{11}", message = "O CPF possui formato inválido")
    @Column(length =11, nullable = false, unique=true)
	private String cpf;
	
	@NotBlank(message = "O telefone não pode ser vazio")
	@Pattern(regexp="[0-9]{10,11}", message = "O formato do telefone é inválido")
	@Column(length = 11, nullable = false)
	private String telefone;
	
	@Embedded
	private Endereco endereco;
	
	@NotNull(message = "O rendimento mensal não pode ser nulo")
	private BigDecimal rendimentoMensal;
	
	
	@OneToMany(mappedBy = "cliente")
	private Set<Emprestimo> emprestimos = new HashSet<>(0);
	
	public boolean verificaSomaEmprestimo(BigDecimal soma) {
		
		BigDecimal multiplicacao = rendimentoMensal.multiply(new BigDecimal(10), MathContext.DECIMAL32);
		
		if (multiplicacao.doubleValue() >= soma.doubleValue()) {
			return true;
		}
		
		return false;
		
	}
}

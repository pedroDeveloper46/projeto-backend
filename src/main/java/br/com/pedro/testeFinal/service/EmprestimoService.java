package br.com.pedro.testeFinal.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedro.testeFinal.dto.ConvertDto;
import br.com.pedro.testeFinal.dto.EmprestimoRequestDto;
import br.com.pedro.testeFinal.dto.EmprestimoResponseDto;
import br.com.pedro.testeFinal.enums.Relacionamento;
import br.com.pedro.testeFinal.exceptions.ClienteNotFoundException;
import br.com.pedro.testeFinal.exceptions.EmprestimoNotFoundException;
import br.com.pedro.testeFinal.model.Cliente;
import br.com.pedro.testeFinal.model.Emprestimo;
import br.com.pedro.testeFinal.repository.ClienteRepository;
import br.com.pedro.testeFinal.repository.EmprestimoRepository;
import br.com.pedro.testeFinal.utils.StringUtils;

@Service
public class EmprestimoService {
	
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ConvertDto convertDto;
	
	public EmprestimoResponseDto salvaEmprestimo(EmprestimoRequestDto emprestimo, String cpf) throws ClienteNotFoundException {
		
		
		Cliente cliente = buscaClientePeloCpf(cpf);
		
		if (cliente == null) {
			throw new ClienteNotFoundException(cpf);
		}
		
		if (verificaPossibilidadeEmprestimo(cliente)) {
			
			DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			
			LocalDateTime dateFinal = LocalDate.parse(emprestimo.getDataFinal(), parser).atStartOfDay();
			
			LocalDateTime dataInicial = LocalDate.parse(emprestimo.getDataInicial(), parser).atStartOfDay();
			
			Emprestimo emprestimoModel = convertDto.toEmprestimo(emprestimo);
			
			emprestimoModel.setCliente(cliente);
			
			emprestimoModel.setDataFinal(dateFinal);
			
			emprestimoModel.setDataInicial(dataInicial);
			
			if (emprestimoModel.getRelacionamento() == Relacionamento.Ouro) {
				Integer quantidadeEmprestimos = emprestimoRepository.countEmprestimos(emprestimoModel.getCliente().getCpf());
				
				emprestimoModel.getRelacionamento().setQuantidadeEmprestimo(quantidadeEmprestimos);
				
				
			}
			
			emprestimoModel.calculaValorFinal();
			
			return convertDto.toEmprestimoResponseDto(emprestimoRepository.save(emprestimoModel));
			
		}else {
			throw new ValidationException("Não será possível realizar mais um emprestímo para esse cliente");
		}
		
		
		
	}
	
	public void deletarEmprestimo(Long id) throws EmprestimoNotFoundException {
		
		Emprestimo emprestimo = convertDto.toEmprestimo(buscaEmprestimo(id));
		
		emprestimoRepository.delete(emprestimo);
		
	}
	
	public EmprestimoResponseDto buscaEmprestimo(Long id) {
		
		Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow();
		
		return convertDto.toEmprestimoResponseDto(emprestimo);
		
	}
	
	public boolean verificaPossibilidadeEmprestimo(Cliente cliente) {
		
		BigDecimal somaEmprestimos = somaEmprestimos(cliente.getCpf());
		
		return cliente.verificaSomaEmprestimo(somaEmprestimos);
		
	}
	
	public BigDecimal somaEmprestimos(String cpf) {
		
		Double soma = 0.0;
		
		List<Emprestimo> emprestimos = emprestimoRepository.findByCliente_Cpf(cpf);
		
		for (Emprestimo emprestimo : emprestimos) {
			soma =+ emprestimo.getValorInicial().doubleValue();
		}
		
		return new BigDecimal(soma);
		
		
	}
	
	public List<EmprestimoResponseDto> buscaEmprestimosDoCliente(String cpf) throws ClienteNotFoundException{
		
		Cliente cliente = buscaClientePeloCpf(cpf);
		
		if (cliente == null) {
			throw new ClienteNotFoundException(cpf);
		}
		
		List<Emprestimo> emprestimos = emprestimoRepository.findByCliente_Cpf(cpf);
		
		if (emprestimos.size() == 0) {
			throw new ValidationException("O Cliente do CPF " + cpf + " ainda não pediu nenhum emprestimo");
		}
		
		return convertDto.toListEmprestimoResponseDto(emprestimos);
		
	}
	
	public Cliente buscaClientePeloCpf(String cpf) {
		
		if (!StringUtils.isCPF(cpf)) {
			throw new ValidationException(cpf);	
		}
		
		Cliente cliente = clienteRepository.findByCpf(cpf);
		
		if(cliente != null) {
			return cliente;
		}
		
		return null;
		
	}
	
	

}

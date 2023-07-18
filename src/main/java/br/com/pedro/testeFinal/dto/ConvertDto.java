package br.com.pedro.testeFinal.dto;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pedro.testeFinal.model.Cliente;
import br.com.pedro.testeFinal.model.Emprestimo;

public class ConvertDto {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteDto toClienteDto(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDto.class);
	}
	
	public Cliente toClienteModel(ClienteDto clienteDto) {
		return modelMapper.map(clienteDto, Cliente.class);
	}
	
	public List<ClienteDto> listAll(List<Cliente> clientes){
		return clientes.stream()
				       .map(cliente -> modelMapper.map(cliente, ClienteDto.class))
				       .toList();
	}
	
	public EmprestimoResponseDto toEmprestimoResponseDto(Emprestimo emprestimo) {
		return modelMapper.map(emprestimo, EmprestimoResponseDto.class);
	}
	
	public Emprestimo toEmprestimo(EmprestimoRequestDto emprestimo) {
		return modelMapper.map(emprestimo, Emprestimo.class);
	}
	
	public Emprestimo toEmprestimo(EmprestimoResponseDto emprestimo) {
		return modelMapper.map(emprestimo, Emprestimo.class);
	}
	
	public List<EmprestimoResponseDto> toListEmprestimoResponseDto(List<Emprestimo> emprestimos) {
		
		return emprestimos.stream()
				          .map(e -> modelMapper.map(e, EmprestimoResponseDto.class))
				          .toList();
		
	}
	
	
	
	

}

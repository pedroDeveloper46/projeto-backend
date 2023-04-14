package br.com.pedro.testeFinal.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedro.testeFinal.dto.ClienteDto;
import br.com.pedro.testeFinal.dto.ConvertDto;
import br.com.pedro.testeFinal.exceptions.ClienteNotFoundException;
import br.com.pedro.testeFinal.model.Cliente;
import br.com.pedro.testeFinal.model.Endereco;
import br.com.pedro.testeFinal.repository.ClienteRepository;
import br.com.pedro.testeFinal.utils.StringUtils;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ConvertDto convertDto;
	
	public ClienteDto saveCliente(ClienteDto cliente) throws ValidationException {
		
		if (StringUtils.isCPF(cliente.getCpf())) {
			
			if (!validateCPF(cliente.getCpf(), cliente.getId())) {
				throw new ValidationException("Erro de validação: CPF já cadastrado");
			}
			

		}else {
			throw new ValidationException("Erro de validação: CPF inválido");
		}
		
		Cliente clienteModel = convertDto.toClienteModel(cliente);
		
		clienteModel = clienteRepository.save(clienteModel);
		
		return convertDto.toClienteDto(clienteModel);
			
	}
	
	public ClienteDto updateCliente(String cpf, ClienteDto cliente) throws ClienteNotFoundException{
		
		if (!StringUtils.isCPF(cpf)) {
			throw new ValidationException("CPF inválido");
		}
		
		Cliente clienteModel = clienteRepository.findByCpf(cpf);
		
		if (clienteModel != null) {
			cliente.setId(clienteModel.getId());
			return saveCliente(cliente);
		}
		
		throw new ClienteNotFoundException(cpf);
		
	}
	
	public List<ClienteDto> listAll(){
		List<Cliente> clientes = clienteRepository.findAll();
		
		return convertDto.listAll(clientes);
	}
	
	public ClienteDto findCliente(String cpf) throws ClienteNotFoundException {
		
		if (!StringUtils.isCPF(cpf)) {
			throw new ValidationException("Erro de validação: CPF inválido");
		}
		
		Cliente cliente = clienteRepository.findByCpf(cpf);
		
		if (cliente != null) {
			ClienteDto clienteDto = convertDto.toClienteDto(cliente);
			return clienteDto;
		}
		
		throw new ClienteNotFoundException(cpf);
		
	}
	
	public String deleteCliente(String cpf) throws ClienteNotFoundException {
		
		if (!StringUtils.isCPF(cpf)) {
			throw new ValidationException("CPF inválido");
		}
		
		Cliente cliente = clienteRepository.findByCpf(cpf);
		
		if (cliente != null) {
			clienteRepository.delete(cliente);
			return "Cliente deletado com Sucesso!";
		}
		
		throw new ClienteNotFoundException(cpf);
		
	}
	
	
	private boolean validateCPF(String cpf, Long id) {
		
		Cliente cliente = clienteRepository.findByCpf(cpf);
		
		if (cliente != null) {
			if (id == null) {
				return false;
			}
			
			if (!cliente.getId().equals(id)) {
				return false;
			}
		}
		
		return true;
		
	}
	
	

}

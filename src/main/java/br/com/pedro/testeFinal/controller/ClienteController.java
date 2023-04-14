package br.com.pedro.testeFinal.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.testeFinal.dto.ClienteDto;
import br.com.pedro.testeFinal.dto.EmprestimoRequestDto;
import br.com.pedro.testeFinal.dto.EmprestimoResponseDto;
import br.com.pedro.testeFinal.exceptions.ClienteNotFoundException;
import br.com.pedro.testeFinal.model.Cliente;
import br.com.pedro.testeFinal.model.Endereco;
import br.com.pedro.testeFinal.service.ClienteService;
import br.com.pedro.testeFinal.service.EmprestimoService;

@RestController
@RequestMapping(path="/clientes")
public class ClienteController {
	
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmprestimoService emprestimoService;
	
	@GetMapping("")
	public List<ClienteDto> listAll(){
		return clienteService.listAll();
	}
	
	@GetMapping("/{cpf}")
	public ClienteDto getCliente(@PathVariable String cpf) throws ClienteNotFoundException {
		return clienteService.findCliente(cpf);
	}

	
	@PostMapping("/cadastrar")
	public ClienteDto saveCliente(@RequestBody @Valid ClienteDto cliente, Errors erros) {
		
		if (erros.hasErrors()) {
			throw new ValidationException("Erro de validação: " + erros.getFieldError().getDefaultMessage());
		}
		
	
		return clienteService.saveCliente(cliente);
	}
	
	@PutMapping("/atualizar/{cpf}")
	public ClienteDto updateCliente(@PathVariable String cpf, @RequestBody @Valid ClienteDto cliente, Errors erros) throws ClienteNotFoundException {
		
		if (erros.hasErrors()) {
			throw new ValidationException("Erro de validação: " + erros.getFieldError().getDefaultMessage());
		}
		
		return clienteService.updateCliente(cpf, cliente);
	}
	
	@GetMapping("/deletar/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public void deletarCliente(@PathVariable String cpf) throws ClienteNotFoundException {
		
		clienteService.deleteCliente(cpf);
		
		
	}
	
	
	@PostMapping("/{cpf}/emprestimos")
	public EmprestimoResponseDto saveEmprestimo(@PathVariable String cpf, @RequestBody @Valid EmprestimoRequestDto emprestimoRequestDto, Errors erros) throws ClienteNotFoundException {
		
		if (erros.hasErrors()) {
			throw new ValidationException("Erro de validação:" +erros.getFieldError().getDefaultMessage());
		}
		
		return emprestimoService.salvaEmprestimo(emprestimoRequestDto, cpf);
		
	}
	
	
	

}

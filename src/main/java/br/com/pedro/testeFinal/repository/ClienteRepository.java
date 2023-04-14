package br.com.pedro.testeFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedro.testeFinal.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Cliente findByCpf(String cpf);

}

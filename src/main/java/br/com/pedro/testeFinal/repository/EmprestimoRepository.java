package br.com.pedro.testeFinal.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.pedro.testeFinal.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
	
	
	
	@Query("SELECT count(cliente_cpf) FROM Emprestimo WHERE cliente_cpf = ?1")
	public Integer countEmprestimos(String cpf);
	
	public List<Emprestimo> findByCliente_Cpf(String cpf);

}

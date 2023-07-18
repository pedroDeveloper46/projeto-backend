package br.com.pedro.testeFinal.exceptions;

@SuppressWarnings("serial")
public class EmprestimoNotFoundException extends Exception {
	
	public EmprestimoNotFoundException(Long id) {
		super("Emprestimo com identificador " + id+ " não foi encontrado");
	}

}

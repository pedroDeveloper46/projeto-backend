package br.com.pedro.testeFinal.exceptions;

@SuppressWarnings("serial")
public class ClienteNotFoundException extends Exception {
	
	
	
	public ClienteNotFoundException(String cpf) {
		super("CPF " + cpf + " não encontrado");
	}

}

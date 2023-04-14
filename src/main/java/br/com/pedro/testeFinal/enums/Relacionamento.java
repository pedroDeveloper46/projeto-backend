package br.com.pedro.testeFinal.enums;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Relacionamento {
	
	Ouro(1){
		
		@Override
		public BigDecimal calculaValorFinal(BigDecimal valorInicial) {
			// TODO Auto-generated method stub
			
			BigDecimal fatorMultiplicador;
			
			if (getQuantidadeEmprestimo() == 1) {
				fatorMultiplicador = new BigDecimal(1.2);
				return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
			}
			
			fatorMultiplicador = new BigDecimal(1.3);
			return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
			
		}
		
	},
	Prata(2){
		@Override
		public BigDecimal calculaValorFinal(BigDecimal valorInicial) {
			// TODO Auto-generated method stub
			
			BigDecimal fatorMultiplicador;
			
			if (valorInicial.doubleValue() > 5000 ) {
				fatorMultiplicador = new BigDecimal(1.40);
				return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
			}
			
			fatorMultiplicador = new BigDecimal(1.60);
			return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	},
	
	Bronze(3){
		@Override
		public BigDecimal calculaValorFinal(BigDecimal valorInicial) {
			// TODO Auto-generated method stub
			BigDecimal fatorMultiplicador = new BigDecimal(1.80);
			return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	};
	
	private int codigo;
	
	private int quantidadeEmprestimo;
	 
    private Relacionamento(int codigo) {
 
        this.codigo = codigo;
    }
 
    public int getCodigo() { 
        return this.codigo;
    }
    
    public int getQuantidadeEmprestimo() { 
        return this.quantidadeEmprestimo;
    }
    
    public void setQuantidadeEmprestimo(int quantidadeEmprestimo) {
    	this.quantidadeEmprestimo = quantidadeEmprestimo;
    }
    
    public abstract BigDecimal calculaValorFinal(BigDecimal valorInicial);
	
	

}

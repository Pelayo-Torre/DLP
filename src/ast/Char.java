package ast;

import visitor.Visitor;

public class Char extends AbstractExpresion{

	private String valor;
	
	public Char(int line, int column, String valor) {
		super(line, column);
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Char [valor=" + valor + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

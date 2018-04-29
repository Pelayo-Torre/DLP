package ast;

import visitor.Visitor;

public class ConstanteEntera extends AbstractExpresion{

	private int valor;
	
	public ConstanteEntera(int line, int column, int valor) {
		super(line, column);
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ConstanteEntera [valor=" + valor + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

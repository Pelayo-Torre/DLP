package ast;

import visitor.Visitor;

public class ConstanteReal extends AbstractExpresion{

	private float valor;
	
	public ConstanteReal(int line, int column, float valor) {
		super(line, column);
		this.valor = valor;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ConstanteReal [valor=" + valor + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

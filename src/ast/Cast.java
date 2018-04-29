package ast;

import tipo.Tipo;
import visitor.Visitor;

public class Cast extends AbstractExpresion{

	private Tipo tipo;
	private Expresion expr;
	
	public Cast(int line, int column, Tipo tipo, Expresion expr) {
		super(line, column);
		this.tipo = tipo;
		this.expr = expr;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Expresion getExpr() {
		return expr;
	}

	public void setExpr(Expresion expr) {
		this.expr = expr;
	}

	@Override
	public String toString() {
		return "Cast [tipo=" + tipo + ", expr=" + expr + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

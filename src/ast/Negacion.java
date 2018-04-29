package ast;

import visitor.Visitor;

public class Negacion extends AbstractExpresion{

	private Expresion expr;
	
	public Negacion(int line, int column, Expresion expr) {
		super(line, column);
		this.expr = expr;
	}

	public Expresion getExpr() {
		return expr;
	}

	public void setExpr(Expresion expr) {
		this.expr = expr;
	}

	@Override
	public String toString() {
		return "Negacion [expr=" + expr + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

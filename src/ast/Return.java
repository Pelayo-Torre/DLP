package ast;

import visitor.Visitor;

public class Return extends AbstractSentencia{
	
	private Expresion expr;

	public Return(int line, int column, Expresion expr) {
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
		return "Return [expr=" + expr + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

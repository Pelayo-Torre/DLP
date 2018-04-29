package ast;

import java.util.List;

import visitor.Visitor;

public class While extends AbstractSentencia{
	
	private Expresion expr;
	private List<Sentencia> sentencias;
	
	public While(int line, int column, Expresion expr, List<Sentencia> sentencias) {
		super(line, column);
		this.expr = expr;
		this.sentencias = sentencias;
	}

	public Expresion getExpr() {
		return expr;
	}

	public void setExpr(Expresion expr) {
		this.expr = expr;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "While [expr=" + expr + ", sentencias=" + sentencias + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

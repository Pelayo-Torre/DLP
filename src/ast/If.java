package ast;

import java.util.List;

import visitor.Visitor;

public class If extends AbstractSentencia{

	private Expresion expr;
	private List<Sentencia> sentenciasIf;
	private List<Sentencia> sentenciasElse;
	
	public If(int line, int column, Expresion expr, List<Sentencia> sentenciasIf, List<Sentencia> sentenciasElse) {
		super(line, column);
		this.expr = expr;
		this.sentenciasIf = sentenciasIf;
		this.sentenciasElse = sentenciasElse;
	}

	public Expresion getExpr() {
		return expr;
	}

	public void setExpr(Expresion expr) {
		this.expr = expr;
	}

	public List<Sentencia> getSentenciasIf() {
		return sentenciasIf;
	}

	public void setSentenciasIf(List<Sentencia> sentenciasIf) {
		this.sentenciasIf = sentenciasIf;
	}

	public List<Sentencia> getSentenciasElse() {
		return sentenciasElse;
	}

	public void setSentenciasElse(List<Sentencia> sentenciasElse) {
		this.sentenciasElse = sentenciasElse;
	}

	@Override
	public String toString() {
		return "If [expr=" + expr + ", sentenciasIf=" + sentenciasIf + ", sentenciasElse=" + sentenciasElse + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	

}

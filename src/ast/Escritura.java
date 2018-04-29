package ast;

import java.util.List;

import visitor.Visitor;

public class Escritura extends AbstractSentencia{
	
	private List<Expresion> expresiones;

	public Escritura(int line, int column, List<Expresion> expresiones) {
		super(line, column);
		this.expresiones = expresiones;
	}

	public List<Expresion> getExpresiones() {
		return expresiones;
	}

	public void setExpresiones(List<Expresion> expresiones) {
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Escritura [expresiones=" + expresiones + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

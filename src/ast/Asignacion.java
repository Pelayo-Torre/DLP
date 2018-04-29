package ast;

import visitor.Visitor;

public class Asignacion extends AbstractSentencia{
	
	private Expresion izquierda;
	private Expresion derecha;
	
	public Asignacion(int line, int column, Expresion izquierda, Expresion derecha) {
		super(line, column);
		this.izquierda = izquierda;
		this.derecha = derecha;
	}

	public Expresion getIzquierda() {
		return izquierda;
	}

	public Expresion getDerecha() {
		return derecha;
	}

	public void setIzquierda(Expresion izquierda) {
		this.izquierda = izquierda;
	}

	public void setDerecha(Expresion derecha) {
		this.derecha = derecha;
	}

	@Override
	public String toString() {
		return "Asignacion [izquierda=" + izquierda + ", derecha=" + derecha + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

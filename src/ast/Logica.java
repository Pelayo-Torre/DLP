package ast;

import visitor.Visitor;

public class Logica extends AbstractExpresion{

	private Expresion izquierda;
	private Expresion derecha;
	private String operador;
	
	public Logica(int line, int column, Expresion izquierda, String operador, Expresion derecha) {
		super(line, column);
		this.izquierda = izquierda;
		this.derecha = derecha;
		this.operador = operador;
	}

	public Expresion getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Expresion izquierda) {
		this.izquierda = izquierda;
	}

	public Expresion getDerecha() {
		return derecha;
	}

	public void setDerecha(Expresion derecha) {
		this.derecha = derecha;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "Logica [izquierda=" + izquierda + ", derecha=" + derecha + ", operador=" + operador + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

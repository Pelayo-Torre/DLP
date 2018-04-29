package ast;

import visitor.Visitor;

public class AccesoCampo extends AbstractExpresion{
	
	private Expresion izquierda;
	private String nombre;
		
	public AccesoCampo(int line, int column, Expresion izquierda, String nombre) {
		super(line, column);
		this.izquierda = izquierda;
		this.nombre = nombre;
	}

	public Expresion getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Expresion izquierda) {
		this.izquierda = izquierda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "AccesoCampo [izquierda=" + izquierda + ", nombre=" + nombre + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

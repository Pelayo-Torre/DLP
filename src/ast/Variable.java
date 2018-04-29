package ast;

import visitor.Visitor;

public class Variable extends AbstractExpresion{
	
	private String nombre;
	private Definicion definicion;

	public Variable(int line, int column, String nombre) {
		super(line, column);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Definicion getDefinicion() {
		return definicion;
	}

	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}

	@Override
	public String toString() {
		return "Variable [nombre=" + nombre + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
	

	
}

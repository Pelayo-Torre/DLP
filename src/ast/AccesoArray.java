package ast;

import visitor.Visitor;

public class AccesoArray extends AbstractExpresion{

	private Expresion nombre;
	private Expresion posicion;
	
	public AccesoArray(int line, int column, Expresion nombre, Expresion posicion) {
		super(line, column);
		this.nombre = nombre;
		this.posicion = posicion;
	}

	public Expresion getNombre() {
		return nombre;
	}

	public void setNombre(Expresion nombre) {
		this.nombre = nombre;
	}

	public Expresion getPosicion() {
		return posicion;
	}

	public void setPosicion(Expresion posicion) {
		this.posicion = posicion;
	}

	@Override
	public String toString() {
		return "AccesoArray [nombre=" + nombre + ", posicion=" + posicion + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}

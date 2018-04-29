package ast;

import tipo.Tipo;
import visitor.Visitor;

public class DefVariable extends AbstractDefinicion implements Sentencia{
	
	private String nombre;
	private Tipo tipo;
	
	private int ambito;	//Ámbito de la variable
	private int offset;	//Para almacenar la dirección de memoria.

	public DefVariable(int line, int column, String nombre, Tipo tipo) {
		super(line, column);
		this.nombre = nombre;
		this.tipo = tipo;
		this.ambito = 0;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getAmbito() {
		return ambito;
	}

	public void setAmbito(int ambito) {
		this.ambito = ambito;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "DefVariable [nombre=" + nombre + ", tipo=" + tipo + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}

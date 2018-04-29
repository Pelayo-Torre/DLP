package ast;

import java.util.List;

import visitor.Visitor;

public class Procedimiento extends AbstractSentencia{
	
	private Variable nombre;
	private List<Expresion> expresiones;
	
	public Procedimiento(int line, int column, Variable nombre, List<Expresion>expresiones) {
		super(line, column);
		this.expresiones = expresiones;
		this.nombre = nombre;
	}

	public Variable getNombre() {
		return nombre;
	}

	public void setNombre(Variable nombre) {
		this.nombre = nombre;
	}

	public List<Expresion> getExpresiones() {
		return expresiones;
	}

	public void setExpresiones(List<Expresion> expresiones) {
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Procedimiento [nombre=" + nombre + ", expresiones=" + expresiones + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}

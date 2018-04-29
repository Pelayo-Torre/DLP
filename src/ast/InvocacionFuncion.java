package ast;

import java.util.List;

import visitor.Visitor;

public class InvocacionFuncion extends AbstractExpresion{

	private Variable nombre;
	private List<Expresion> argumentos;
	
	public InvocacionFuncion(int line, int column, Variable nombre, List<Expresion> argumentos) {
		super(line, column);
		this.nombre = nombre;
		this.argumentos = argumentos;
	}

	public Variable getNombre() {
		return nombre;
	}

	public void setNombre(Variable nombre) {
		this.nombre = nombre;
	}

	public List<Expresion> getArgumentos() {
		return argumentos;
	}

	public void setArgumentos(List<Expresion> argumentos) {
		this.argumentos = argumentos;
	}

	@Override
	public String toString() {
		return "InvocacionFuncion [nombre=" + nombre + ", argumentos=" + argumentos + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

}

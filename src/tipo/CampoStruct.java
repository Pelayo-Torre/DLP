package tipo;

import ast.AbstractNodoAST;
import visitor.Visitor;

public class CampoStruct extends AbstractNodoAST{

	private String nombre;
	private Tipo tipo;
	
	private int offset;		//Para almacenar direcciones de memoria.
	
	public CampoStruct(int line, int column, String nombre, Tipo tipo) {
		super(line, column);
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "CampoStruct [nombre=" + nombre + ", tipo=" + tipo + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
}

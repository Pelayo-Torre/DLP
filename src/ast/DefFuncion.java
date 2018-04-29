package ast;

import java.util.List;

import tipo.Tipo;
import tipo.TipoFuncion;
import visitor.Visitor;

public class DefFuncion extends AbstractDefinicion {
	
	private String nombre;
	private TipoFuncion tipoFuncion;
	private List<Sentencia> sentencias;
	
	public DefFuncion(int line, int column, String nombre, TipoFuncion tipoFuncion, List<Sentencia> sentencias) {
		super(line, column);
		this.nombre = nombre;
		this.tipoFuncion = tipoFuncion;
		this.sentencias = sentencias;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "DefFuncion [nombre=" + nombre + ", tipoFuncion=" + tipoFuncion + ", sentencias=" + sentencias + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public Tipo getTipo() {
		return tipoFuncion;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipoFuncion = (TipoFuncion) tipo;
		
	}

}

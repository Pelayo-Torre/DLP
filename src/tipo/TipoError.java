package tipo;

import manejadorerrores.ME;
import visitor.Visitor;

public class TipoError extends AbstractTipo{

	private int columna;
	private int line;
	private String mensaje;
	
	public TipoError(int line, int column, String mensaje) {
		super(line, column);
		this.line = line;
		this.columna = column;
		this.mensaje = mensaje;
		ME.getME().añadirErrores(this);
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Error detectado en linea: " + line + " y columna: " + columna + " \nEl mensaje que se muestra es "
				+ "el siguiente: " + mensaje;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public Tipo promocionaA(Tipo tipo) {
		if(tipo.getClass() == this.getClass())
			return this;
		return null;
	}
	
	@Override
	public Tipo negacion() {
		return this;
	}

	@Override
	public int numeroBytes() {
		throw new IllegalArgumentException("No se puede calcular el nº de bytes de un error");
	}
	
	
}

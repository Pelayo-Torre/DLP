package tipo;

import visitor.Visitor;

public class TipoArray extends AbstractTipo{

	private int tamaño;
	private Tipo tipo;
	
	public TipoArray(int line, int column, int tamaño, Tipo tipo) {
		super(line, column);
		this.tamaño = tamaño;
		this.tipo = tipo;
	}

	public int getTamaño() {
		return tamaño;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tamaño=" + tamaño + ", tipo=" + tipo + "]";
	}
	
	@Override
	public Tipo corchetes(Tipo tipo) {
		if(tipo instanceof TipoEntero)
			return this.tipo;
		return null;
	}
	
	@Override
	public Tipo promocionaA(Tipo tipo) {
		if(tipo.getClass() == this.getClass())
			return this;
		return null;
	}
	
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public int numeroBytes() {
		return (tamaño * tipo.numeroBytes());
	}

}

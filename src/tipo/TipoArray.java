package tipo;

import visitor.Visitor;

public class TipoArray extends AbstractTipo{

	private int tama�o;
	private Tipo tipo;
	
	public TipoArray(int line, int column, int tama�o, Tipo tipo) {
		super(line, column);
		this.tama�o = tama�o;
		this.tipo = tipo;
	}

	public int getTama�o() {
		return tama�o;
	}

	public void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tama�o=" + tama�o + ", tipo=" + tipo + "]";
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
		return (tama�o * tipo.numeroBytes());
	}

}

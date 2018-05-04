package tipo;

import visitor.Visitor;

public class TipoEntero extends AbstractTipo{
	
	public TipoEntero(int line, int column) {
		super(line, column);
	}

	public String getNombre() {
		return "int";
	}
	
	@Override
	public boolean esLogico() {
		return true;
	}
	
	@Override
	public Tipo aritmetica() {
		return this;
	}
	
	@Override
	public Tipo aritmetica(Tipo tipo) {
		if(tipo.getClass().equals(this.getClass()))
			return this;
		return super.aritmetica(tipo);
	}
	
	@Override
	public boolean esBasico() {
		return true;
	}
	
	@Override
	public Tipo cast(Tipo tipo) {
		if(esBasico() == true)
			return tipo;
		return null;
	}

	@Override
	public String toString() {
		return "int";
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
		return 2;
	}
	
	@Override
	public char suffix() {
		return 'i';
	}
}

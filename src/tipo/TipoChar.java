package tipo;

import visitor.Visitor;

public class TipoChar extends AbstractTipo{

	public TipoChar(int line, int column) {
		super(line, column);
	}
	
	public String getNombre() {
		return "char";
	}

	@Override
	public String toString() {
		return "char";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	
	@Override
	public boolean esBasico() {
		return true;
	}
	
	@Override
	public boolean isCaracter() {
		return true;
	}
	
	@Override
	public Tipo aritmetica(Tipo tipo) {
		if(tipo.getClass().equals(this.getClass()))
			return new TipoEntero(0,0);
		return super.aritmetica(tipo);
	}
	
	@Override
	public Tipo cast(Tipo tipo) {
		if(esBasico() == true)
			return tipo;
		return null;
	}
	
	@Override
	public Tipo promocionaA(Tipo tipo) {
		if(tipo.getClass() == this.getClass())
			return this;
		return null;
	}

	@Override
	public int numeroBytes() {
		return 1;
	}
	
	@Override
	public char suffix() {
		return 'b';
	}
}

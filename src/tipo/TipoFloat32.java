package tipo;

import visitor.Visitor;

public class TipoFloat32 extends AbstractTipo{

	public TipoFloat32(int line, int column) {
		super(line, column);
	}
	
	public String getNombre() {
		return "float32";
	}
	
	@Override
	public String toString() {
		return "float32";
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
		return 4;
	}

	@Override
	public char suffix() {
		return 'f';
	}
}

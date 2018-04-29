package tipo;

import visitor.Visitor;

public class TipoVoid extends AbstractTipo{

	public TipoVoid(int line, int column) {
		super(line, column);
	}

	@Override
	public String toString() {
		return "void";
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


}

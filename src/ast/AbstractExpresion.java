package ast;

import tipo.Tipo;

public abstract class AbstractExpresion extends AbstractNodoAST implements Expresion{

	boolean LValue = false;
	Tipo tipo;
	
	public AbstractExpresion(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean getLValue() {
		return LValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		LValue = lValue;
	}
	
	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public Tipo getTipo() {
		return tipo;
	}

}

package ast;

import tipo.Tipo;

public interface Expresion extends NodoAST{

	boolean getLValue();
	void setLValue(boolean LValue);
	
	Tipo getTipo();
	void setTipo(Tipo tipo);
	
}

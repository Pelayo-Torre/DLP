package ast;

import tipo.Tipo;

public interface Definicion extends NodoAST{
	
	String getNombre();
	void setNombre(String nombre);
	
	Tipo getTipo();
	void setTipo(Tipo tipo);

}

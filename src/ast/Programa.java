package ast;

import java.util.ArrayList;
import java.util.List;

import tipo.TipoFuncion;
import tipo.TipoVoid;
import visitor.Visitor;

public class Programa extends AbstractNodoAST{

	//List<Sentencia> sentencias = new ArrayList<Sentencia>();
	List<Definicion> definiciones = new ArrayList<Definicion>();
	
	public Programa(int line, int column, List<Definicion> definiciones, List<Sentencia>sentencias) {
		super(line, column);
		this.definiciones = definiciones;		
		DefFuncion main = new DefFuncion(0, 0, "main", new TipoFuncion(0, 0, new ArrayList<DefVariable>(),new TipoVoid(0,0)), sentencias);
		this.definiciones.add(main);
		
		
	}

	
	
	public List<Definicion> getDefiniciones() {
		return definiciones;
	}

	
	public void setDefiniciones(List<Definicion> definiciones) {
		this.definiciones = definiciones;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	

}

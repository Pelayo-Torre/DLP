package tipo;

import java.util.List;

import ast.DefVariable;
import visitor.Visitor;

public class TipoFuncion extends AbstractTipo{

	private Tipo tipoRetorno;
	private List<DefVariable> argumentos;
	
	public TipoFuncion(int line, int column, List<DefVariable> argumentos, Tipo tipoRetorno) {
		super(line, column);
		this.tipoRetorno = tipoRetorno;
		this.argumentos = argumentos;
	}

	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(Tipo tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public List<DefVariable> getArgumentos() {
		return argumentos;
	}

	public void setArgumentos(List<DefVariable> argumentos) {
		this.argumentos = argumentos;
	}

	@Override
	public String toString() {
		return "TipoFuncion [tipoRetorno=" + tipoRetorno + ", argumentos=" + argumentos + "]";
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
	public Tipo parentesis(List<Tipo> tipos) {
		//Comprobamos que el nº de parámetros sea el mismo que se le pasa
		if(tipos.size() != argumentos.size()) {
			return null;
		}
		for(int i=0; i<argumentos.size() ; i++) {
			if(!(argumentos.get(i).getTipo().getClass().equals(tipos.get(i).getClass()))) {
				return null;
			}
		}
		return tipoRetorno;
	}

	@Override
	public int numeroBytes() {
		return tipoRetorno.numeroBytes();
	}
	

}

package semantico;

import ast.DefFuncion;
import ast.DefVariable;
import ast.Definicion;
import ast.Expresion;
import ast.InvocacionFuncion;
import ast.Procedimiento;
import ast.Sentencia;
import ast.Variable;
import tablasimbolos.TablaSimbolos;
import tipo.TipoError;

public class VisitorIdentificacion extends VisitorPorDefecto{

	TablaSimbolos ts = new TablaSimbolos();

	@Override
	public Object visit(DefVariable defVariable, Object param) {
		if(ts.insertar(defVariable) == false) {	//Si no se puede insertar, ya está insertada
			defVariable.setTipo(new TipoError(defVariable.getLine(), defVariable.getColumn(), 
					"La variable --" + defVariable.getNombre() + "-- ya está declarada"));
		}
		defVariable.getTipo().accept(this, param);
		return null;
	}

	@Override
	public Object visit(DefFuncion defFuncion, Object param) {
		if(ts.insertar(defFuncion) == false) { //Si no es null ya está definida
			new TipoError(defFuncion.getLine(), defFuncion.getColumn(), 
					"La función --" + defFuncion.getNombre() + "-- ya está declarada");
		}
		ts.set();
		defFuncion.getTipo().accept(this, param);
	
		for(Sentencia s : defFuncion.getSentencias()) {
			s.accept(this, param);
		}
		ts.reset();
		return null;
	}
	
	@Override
	public Object visit(Variable variable, Object param) {
		//Primero se busca las definciones definidas localmente.
		Definicion def = ts.buscarAmbitoActual(variable.getNombre());
		
		//Si no se han encontrado loclmente, se busca en variables globales.
		if(def == null)
			def = ts.buscar(variable.getNombre());
		
		//Si sigue siendo null, no existe la definición.
		if(def == null) { 
			variable.setTipo(new TipoError(variable.getLine(), variable.getColumn(), 
					"La variable --" + variable.getNombre() + "-- no se ha definido"));
		}
		else {
			//if(def instanceof DefVariable)
				variable.setDefinicion(def);
		}
		
		return null;
	}
	
	@Override
	public Object visit(Procedimiento procedimiento, Object param) {
		Definicion def = ts.buscar(procedimiento.getNombre().getNombre());
		if(!(def != null && def instanceof DefFuncion)) { //Si es distinto de null y es una función
			new TipoError(procedimiento.getLine(), procedimiento.getColumn(), 
					"La función --" + procedimiento.getNombre().getNombre() + "-- no está definida");
		}
		procedimiento.getNombre().setDefinicion(def);
		for(Expresion expr : procedimiento.getExpresiones()) {
			expr.accept(this, null);
		}
		return null;
	}
	
	@Override
	public Object visit(InvocacionFuncion invocacion, Object param) {
		Definicion def = ts.buscar(invocacion.getNombre().getNombre());
		if(!(def != null && def instanceof DefFuncion)) { //Si es distinto de null y es una función
			new TipoError(invocacion.getLine(), invocacion.getColumn(), 
					"La función --" + invocacion.getNombre().getNombre() + "-- no está definida");
		}
		invocacion.getNombre().setDefinicion(def);
		for(Expresion expr : invocacion.getArgumentos()) {
			expr.accept(this, null);
		}
		return null;
	}
	
}

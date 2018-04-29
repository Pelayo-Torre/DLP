package semantico;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Asignacion;
import ast.Expresion;
import ast.Lectura;
import ast.Variable;
import tipo.TipoError;

public class VisitorLValue extends VisitorPorDefecto{
	
	@Override
	public Object visit(Asignacion asignacion, Object param) {
		asignacion.getDerecha().accept(this, null);
		asignacion.getIzquierda().accept(this, null);
		
		if(asignacion.getIzquierda().getLValue() == false) {
			new TipoError(asignacion.getLine(), asignacion.getColumn(), "Valor incorrecto a "
					+ "la izquierda de la asignación");
		}
		
		return null;
	}
	
	@Override
	public Object visit(Lectura lectura, Object param) {
		for(Expresion expr : lectura.getExpresiones()) {
			expr.accept(this, null);
			
			if(expr.getLValue() == false) {
				new TipoError(expr.getLine(), expr.getColumn(), "Valor incorrecto en la "
						+ "expresión de la sentencia read");
			}
			
		}
		return null;
	}
	
	@Override
	public Object visit(AccesoArray accesoArray, Object param) {
		accesoArray.getNombre().accept(this, null);
		accesoArray.getPosicion().accept(this, null);
		accesoArray.setLValue(true);
		return null;
	}
	
	@Override
	public Object visit(Variable variable, Object param) {
		variable.setLValue(true);
		return null;
	}
	
	@Override
	public Object visit(AccesoCampo accesoCampo, Object param) {
		accesoCampo.getIzquierda().accept(this, null);
		accesoCampo.setLValue(true);
		return null;
	}


}

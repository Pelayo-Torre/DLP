package semantico;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Asignacion;
import ast.Cast;
import ast.Char;
import ast.Comparacion;
import ast.ConstanteEntera;
import ast.ConstanteReal;
import ast.DefFuncion;
import ast.DefVariable;
import ast.Definicion;
import ast.Escritura;
import ast.Expresion;
import ast.If;
import ast.InvocacionFuncion;
import ast.Lectura;
import ast.Logica;
import ast.MenosUnario;
import ast.Negacion;
import ast.Procedimiento;
import ast.Programa;
import ast.Return;
import ast.Sentencia;
import ast.Variable;
import ast.While;
import tipo.CampoStruct;
import tipo.TipoArray;
import tipo.TipoChar;
import tipo.TipoEntero;
import tipo.TipoError;
import tipo.TipoFloat32;
import tipo.TipoFuncion;
import tipo.TipoStruct;
import tipo.TipoVoid;
import visitor.Visitor;

public abstract class VisitorPorDefecto implements Visitor{

	@Override
	public Object visit(ConstanteEntera constanteEntera, Object param) {
		return null;
	}

	@Override
	public Object visit(ConstanteReal constanteReal, Object param) {
		return null;
	}

	@Override
	public Object visit(Char constanteChar, Object param) {
		return null;
	}

	@Override
	public Object visit(Logica logica, Object param) {
		logica.getDerecha().accept(this, null);
		logica.getIzquierda().accept(this, null);
		return null;
	}

	@Override
	public Object visit(Comparacion comparacion, Object param) {
		comparacion.getDerecha().accept(this, null);
		comparacion.getIzquierda().accept(this, null);
		return null;
	}

	@Override
	public Object visit(Aritmetica aritmetica, Object param) {
		aritmetica.getDerecha().accept(this, null);
		aritmetica.getIzquierda().accept(this, null);
		return null;
	}

	@Override
	public Object visit(AccesoArray accesoArray, Object param) {
		accesoArray.getNombre().accept(this, null);
		accesoArray.getPosicion().accept(this, null);		
		return null;
	}

	@Override
	public Object visit(AccesoCampo accesoCampo, Object param) {
		accesoCampo.getIzquierda().accept(this, null);
		return null;
	}

	@Override
	public Object visit(MenosUnario menosUnario, Object param) {
		menosUnario.getExpr().accept(this, null);
		return null;
	}

	@Override
	public Object visit(Negacion negacion, Object param) {
		negacion.getExpr().accept(this, null);
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		//variable.getDefinicion().accept(this, null);
		return null;
	}

	@Override
	public Object visit(InvocacionFuncion invocacionFuncion, Object param) {
		invocacionFuncion.getNombre().accept(this, null);
		for(Expresion expr : invocacionFuncion.getArgumentos()) {
			expr.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {
		cast.getExpr().accept(this, null);
		cast.getTipo().accept(this, null);
		return null;
	}

	@Override
	public Object visit(While sentenciaWhile, Object param) {
		sentenciaWhile.getExpr().accept(this, null);
		for(Sentencia s : sentenciaWhile.getSentencias()) {
			s.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Escritura escritura, Object param) {
		for(Expresion expr : escritura.getExpresiones()) {
			expr.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Lectura lectura, Object param) {
		for(Expresion expr : lectura.getExpresiones()) {
			expr.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Asignacion asignacion, Object param) {
		asignacion.getDerecha().accept(this, null);
		asignacion.getIzquierda().accept(this, null);
		return null;
	}

	@Override
	public Object visit(Return sentenciaReturn, Object param) {
		sentenciaReturn.getExpr().accept(this, null);
		return null;
	}

	@Override
	public Object visit(If sentenciaIf, Object param) {
		sentenciaIf.getExpr().accept(this, null);
		for(Sentencia s : sentenciaIf.getSentenciasIf()) {
			s.accept(this, null);
		}
		
		if(sentenciaIf.getSentenciasElse() != null) {
			for(Sentencia s : sentenciaIf.getSentenciasElse()) {
				s.accept(this, null);
			}
		}
		
		return null;
	}

	@Override
	public Object visit(Procedimiento procedimiento, Object param) {
		procedimiento.getNombre().accept(this, null);
		for(Expresion expr : procedimiento.getExpresiones()) {
			expr.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(TipoArray tipoArray, Object param) {
		tipoArray.getTipo().accept(this, null);
		return null;
	}

	@Override
	public Object visit(TipoChar tipoChar, Object param) {
		return null;
	}

	@Override
	public Object visit(TipoFuncion tipoFuncion, Object param) {
		tipoFuncion.getTipoRetorno().accept(this, null);
		for(DefVariable def : tipoFuncion.getArgumentos()) {
			def.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(TipoStruct tipoStruct, Object param) {
		for(CampoStruct campo : tipoStruct.getCampos()) {
			campo.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(CampoStruct campoStruct, Object param) {
		campoStruct.getTipo().accept(this, null);
		return null;
	}

	@Override
	public Object visit(TipoError tipoError, Object param) {
		return null;
	}

	@Override
	public Object visit(TipoEntero tipoEntero, Object param) {
		return null;
	}

	@Override
	public Object visit(TipoFloat32 tipoFloat32, Object param) {
		return null;
	}

	@Override
	public Object visit(TipoVoid tipoVoid, Object param) {
		return null;
	}

	@Override
	public Object visit(DefVariable defVariable, Object param) {
		defVariable.getTipo().accept(this, null);
		return null;
	}

	@Override
	public Object visit(DefFuncion defFuncion, Object param) {
		defFuncion.getTipo().accept(this, null);
		for(Sentencia s : defFuncion.getSentencias()) {
			s.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Programa programa, Object param) {
		//En la visita del programa se deben visitar todos los nodos hijos.
		for(Definicion def : programa.getDefiniciones()) {
			def.accept(this, param);
		}
		
//		for(Sentencia s : programa.getSentencias()) {
//			s.accept(this, param);
//		}
		
		return null;
	}

}

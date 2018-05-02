package semantico;

import java.util.ArrayList;
import java.util.List;

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
import ast.Expresion;
import ast.If;
import ast.InvocacionFuncion;
import ast.Logica;
import ast.MenosUnario;
import ast.Negacion;
import ast.Procedimiento;
import ast.Return;
import ast.Sentencia;
import ast.Variable;
import ast.While;
import tipo.Tipo;
import tipo.TipoChar;
import tipo.TipoEntero;
import tipo.TipoError;
import tipo.TipoFloat32;
import tipo.TipoFuncion;

public class VisitorComprobacionTipos extends VisitorPorDefecto {

	@Override
	public Object visit(ConstanteEntera constanteEntera, Object param) {
		constanteEntera.setTipo(new TipoEntero(constanteEntera.getLine(), constanteEntera.getColumn()));

		return null;
	}

	@Override
	public Object visit(ConstanteReal constanteReal, Object param) {
		constanteReal.setTipo(new TipoFloat32(constanteReal.getLine(), constanteReal.getColumn()));

		return null;
	}

	@Override
	public Object visit(Char constanteChar, Object param) {
		constanteChar.setTipo(new TipoChar(constanteChar.getLine(), constanteChar.getColumn()));

		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		if (variable.getDefinicion() != null)
			variable.setTipo(variable.getDefinicion().getTipo());
		return null;
	}

	@Override
	public Object visit(While sentenciaWhile, Object param) {
		sentenciaWhile.getExpr().accept(this, null);

		// if(sentenciaWhile.getExpr().getTipo() != null) {
		if (sentenciaWhile.getExpr().getTipo().esLogico() == false) {
			sentenciaWhile.getExpr().setTipo(new TipoError(sentenciaWhile.getLine(), sentenciaWhile.getColumn(),
					"El tipo de la condición while es " + sentenciaWhile.getExpr().getTipo() + " y debe ser ENTERO"));
		}
		// }

		for (Sentencia s : sentenciaWhile.getSentencias()) {
			s.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Aritmetica aritmetica, Object param) {
		aritmetica.getDerecha().accept(this, null);
		aritmetica.getIzquierda().accept(this, null);

		// if(aritmetica.getIzquierda().getTipo() != null &&
		// aritmetica.getDerecha().getTipo() != null) {

		aritmetica.setTipo(aritmetica.getIzquierda().getTipo().aritmetica(aritmetica.getDerecha().getTipo()));

		if (aritmetica.getTipo() == null)
			aritmetica.setTipo(new TipoError(aritmetica.getLine(), aritmetica.getColumn(),
					"No se puede realizar la operación aritmética de tipos " + aritmetica.getIzquierda().getTipo()
							+ " y " + aritmetica.getDerecha().getTipo()));
		// }

		return null;
	}

	@Override
	public Object visit(MenosUnario menosUnario, Object param) {
		menosUnario.getExpr().accept(this, null);

		// if(menosUnario.getExpr().getTipo() != null) {

		menosUnario.setTipo(menosUnario.getExpr().getTipo().aritmetica());
		if (menosUnario.getTipo() == null)
			menosUnario.setTipo(new TipoError(menosUnario.getLine(), menosUnario.getColumn(),
					"El tipo del menos unario debe ser ENTERO o REAL."));

		// }

		return null;
	}

	@Override
	public Object visit(AccesoCampo accesoCampo, Object param) {
		accesoCampo.getIzquierda().accept(this, null);

		// if(accesoCampo.getIzquierda().getTipo() != null) {

		accesoCampo.setTipo(accesoCampo.getIzquierda().getTipo().punto(accesoCampo.getNombre()));
		if (accesoCampo.getTipo() == null)
			accesoCampo.setTipo(new TipoError(accesoCampo.getLine(), accesoCampo.getColumn(),
					"No existe el campo especificado: " + accesoCampo.getNombre()));

		// }

		return null;
	}

	@Override
	public Object visit(AccesoArray accesoArray, Object param) {
		accesoArray.getNombre().accept(this, null);
		accesoArray.getPosicion().accept(this, null);

		// if(accesoArray.getNombre().getTipo() != null &&
		// accesoArray.getPosicion().getTipo() != null) {

		accesoArray.setTipo(accesoArray.getNombre().getTipo().corchetes(accesoArray.getPosicion().getTipo()));
		if (accesoArray.getTipo() == null) {
			accesoArray.setTipo(new TipoError(accesoArray.getLine(), accesoArray.getColumn(),
					"El tipo del acceso a array no es correcto"));
		}
		// }

		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {
		cast.getExpr().accept(this, null);
		cast.getTipo().accept(this, null);

		// if(cast.getExpr().getTipo() != null) {

		cast.setTipo(cast.getExpr().getTipo().cast(cast.getTipo()));
		if (cast.getTipo() == null) {
			cast.setTipo(new TipoError(cast.getLine(), cast.getColumn(), "El cast solo se hace con tipos simples"));
		}
		// }

		return null;
	}

	@Override
	public Object visit(Asignacion asignacion, Object param) {
		asignacion.getDerecha().accept(this, null);
		asignacion.getIzquierda().accept(this, null);

		// if(asignacion.getIzquierda().getTipo() != null &&
		// asignacion.getDerecha().getTipo() != null) {

		if (asignacion.getIzquierda().getTipo().promocionaA(asignacion.getDerecha().getTipo()) == null) {
			new TipoError(asignacion.getLine(), asignacion.getColumn(),
					"Los tipos de la asignación deben coincidir. Izquierda: " + asignacion.getIzquierda().getTipo()
							+ " Derecha: " + asignacion.getDerecha().getTipo());
		}
		// }

		return null;
	}

	@Override
	public Object visit(Negacion negacion, Object param) {
		negacion.getExpr().accept(this, null);

		// if(negacion.getExpr().getTipo() != null) {

		negacion.setTipo(negacion.getExpr().getTipo().negacion());
		if (negacion.getTipo() == null)
			negacion.setTipo(new TipoError(negacion.getLine(), negacion.getColumn(),
					"No se puede negar un tipo " + negacion.getExpr().getTipo()));

		// }

		return null;
	}

	@Override
	public Object visit(Comparacion comparacion, Object param) {
		comparacion.getDerecha().accept(this, null);
		comparacion.getIzquierda().accept(this, null);

		comparacion.setTipo(comparacion.getIzquierda().getTipo().comparacion(comparacion.getDerecha().getTipo()));

		if (comparacion.getTipo() == null)
			comparacion.setTipo(new TipoError(comparacion.getLine(), comparacion.getColumn(),
					"No se puede realizar la comparación de tipos " + comparacion.getIzquierda().getTipo() + " y "
							+ comparacion.getDerecha().getTipo()));

		return null;
	}

	@Override
	public Object visit(Logica logica, Object param) {
		logica.getDerecha().accept(this, null);
		logica.getIzquierda().accept(this, null);

		logica.setTipo(logica.getIzquierda().getTipo().logica(logica.getDerecha().getTipo()));
		if (logica.getTipo() == null)
			logica.setTipo(new TipoError(logica.getLine(), logica.getColumn(),
					"No se puede realizar la operación lógica " + logica.getIzquierda().getTipo() + " "
							+ logica.getOperador() + " " + logica.getDerecha().getTipo()));

		return null;
	}

	@Override
	public Object visit(If sentenciaIf, Object param) {
		sentenciaIf.getExpr().accept(this, null);
		
		if (sentenciaIf.getExpr().getTipo().esLogico() == false) {
			sentenciaIf.getExpr().setTipo(new TipoError(sentenciaIf.getExpr().getLine(),
					sentenciaIf.getExpr().getColumn(),
					"La condición del IF es " + sentenciaIf.getExpr().getTipo() + " y debe ser lógico(entero)"));
		}

		if (sentenciaIf.getSentenciasIf() != null) {
			for (Sentencia s : sentenciaIf.getSentenciasIf()) {
				s.accept(this, null);
			}
		}

		if (sentenciaIf.getSentenciasElse() != null) {
			for (Sentencia s : sentenciaIf.getSentenciasElse()) {
				s.accept(this, null);
			}
		}

		return null;
	}

	@Override
	public Object visit(Procedimiento procedimiento, Object param) {
		// Lista auxiliar para los tipos.
		List<Tipo> tipos = new ArrayList<Tipo>();

		procedimiento.getNombre().accept(this, null);
		for (Expresion expr : procedimiento.getExpresiones()) {
			expr.accept(this, null);
			tipos.add(expr.getTipo());
		}

		if (procedimiento.getNombre().getDefinicion() != null) {
			if (procedimiento.getNombre().getDefinicion().getTipo().parentesis(tipos) == null) {
				new TipoError(procedimiento.getLine(), procedimiento.getColumn(),
						"Los argumentos pasados en la invocación no son correctos");
			}
		}

		return null;
	}

	@Override
	public Object visit(InvocacionFuncion invocacionFuncion, Object param) {
		// Lista auxiliar para los tipos.
		List<Tipo> tipos = new ArrayList<Tipo>();

		invocacionFuncion.getNombre().accept(this, null);
		for (Expresion expr : invocacionFuncion.getArgumentos()) {
			expr.accept(this, null);
			tipos.add(expr.getTipo());
		}

		if (invocacionFuncion.getNombre().getDefinicion() != null) {
			invocacionFuncion.setTipo(invocacionFuncion.getNombre().getDefinicion().getTipo().parentesis(tipos));
			if (invocacionFuncion.getTipo() == null) {
				invocacionFuncion.setTipo(new TipoError(invocacionFuncion.getLine(), invocacionFuncion.getColumn(),
						"Los argumentos pasados en la invocación no son correctos"));
			}
		}

		return null;
	}

	@Override
	public Object visit(DefFuncion defFuncion, Object param) {
		boolean hayRetorno = false;

		defFuncion.getTipo().accept(this, defFuncion.getTipo()); // Le pasamos el tipo de retorno de la función.

		for (Sentencia s : defFuncion.getSentencias()) {
			s.accept(this, defFuncion.getTipo());

			if (s instanceof Return)
				hayRetorno = true;
		}

		// Si la función tiene declarado el tipoRetorno pero no hay sentencia return,
		// error
		if (hayRetorno == false && ((TipoFuncion) defFuncion.getTipo()).getTipoRetorno().esBasico() == true)
			new TipoError(defFuncion.getLine(), defFuncion.getColumn(),
					"La función --" + defFuncion.getNombre() + "-- no tiene sentencia RETURN");

		return null;
	}

	@Override
	public Object visit(Return sentenciaReturn, Object param) {
		sentenciaReturn.getExpr().accept(this, null);

		if (sentenciaReturn.getExpr().getTipo().getClass() != ((TipoFuncion) param).getTipoRetorno().getClass())
			sentenciaReturn.getExpr()
					.setTipo(new TipoError(sentenciaReturn.getLine(), sentenciaReturn.getColumn(),
							"El tipo de retorno es --" + sentenciaReturn.getExpr().getTipo() + "-- y debe ser --"
									+ ((TipoFuncion) param).getTipoRetorno() + "--"));

		return null;
	}

}

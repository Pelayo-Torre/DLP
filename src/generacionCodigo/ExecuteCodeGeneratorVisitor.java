package generacionCodigo;

import java.io.IOException;

import ast.Asignacion;
import ast.DefFuncion;
import ast.DefVariable;
import ast.Definicion;
import ast.Escritura;
import ast.Expresion;
import ast.If;
import ast.InvocacionFuncion;
import ast.Lectura;
import ast.Procedimiento;
import ast.Programa;
import ast.Return;
import ast.Sentencia;
import ast.While;
import tipo.TipoFuncion;
import tipo.TipoVoid;

public class ExecuteCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	CodeGenerator cg;
	ValueCodeGeneratorVisitor vcgv;
	AddresCodeGeneratorVisitor acgv;

	public ExecuteCodeGeneratorVisitor(CodeGenerator cg, ValueCodeGeneratorVisitor vcgv,
			AddresCodeGeneratorVisitor acgv) {
		this.cg = cg;
		this.vcgv = vcgv;
		this.acgv = acgv;
	}

	// En el acept, se le pasa el visitor que corresponda.

	@Override
	public Object visit(Programa programa, Object param) {
		for (Definicion def : programa.getDefiniciones()) {
			if (def instanceof DefVariable)
				def.accept(this, param);
		}

		try {
			cg.saltoLinea();
			cg.comentario("Invocation to the main function");
			cg.call("main");
			cg.halt();
			cg.saltoLinea();
			cg.saltoLinea();

		} catch (IOException e) {
			System.err.println("Error al ejecutar el programa");
		}

		for (Definicion def : programa.getDefiniciones()) {
			if (def instanceof DefFuncion) {
				def.accept(this, param);
			}
		}

		return null;
	}

	@Override
	public Object visit(Escritura escritura, Object param) {
		for (Expresion e : escritura.getExpresiones()) {
			e.accept(vcgv, param);
			try {
				cg.out(e.getTipo().suffix());
			} catch (IOException e1) {
				System.err.println("Error al ejecutar visit (E) de escritura");
			}
		}
		return null;
	}

	@Override
	public Object visit(Lectura lectura, Object param) {
		for (Expresion e : lectura.getExpresiones()) {
			e.accept(acgv, param);
			try {
				cg.in(e.getTipo().suffix());
				cg.store(e.getTipo().suffix());
			} catch (IOException e1) {
				System.err.println("Error al ejecutar visit (E) de lectura");
			}
		}
		return null;
	}

	@Override
	public Object visit(Asignacion asignacion, Object param) {
		asignacion.getIzquierda().accept(acgv, param);
		asignacion.getDerecha().accept(vcgv, param);
		try {
			cg.store(asignacion.getIzquierda().getTipo().suffix());
		} catch (IOException e) {
			System.err.println("Error al ejecutar visit (E) de asignación");
		}
		return null;
	}

	@Override
	public Object visit(DefVariable defVariable, Object param) {
		try {
			cg.comentarioTabulado("* var " + defVariable.getNombre() + " " + defVariable.getTipo() + " (offset "
					+ defVariable.getOffset() + ")");
		} catch (IOException e) {
			System.err.println("Error al ejecutar visit (E) de defVariable");
		}
		
		return null;
	}
	
	@Override
	public Object visit(DefFuncion defFuncion, Object param) {
		//Array que le envío a la sentencia retorno para hacer el RET
		int[] necesarioRetorno = new int [3];
		necesarioRetorno[0] = defFuncion.getTipo().numeroBytes();
		try {
			cg.func(defFuncion.getNombre());
			cg.comentarioTabulado("* Parameters");
			
			int tamParametros = 0;
			//Se recorren los parámetros en primer lugar
			for(DefVariable dv : ((TipoFuncion)defFuncion.getTipo()).getArgumentos()) {
				dv.accept(this, param);
				tamParametros += dv.getTipo().numeroBytes();
			}
			necesarioRetorno[2] = tamParametros;
			cg.comentarioTabulado("* Local variables");
			
			int tamLocales = 0;
			//Recorremos las variables locales para calcular su tamaño para el ENTER
			for(Sentencia s : defFuncion.getSentencias()) {
				if(s instanceof DefVariable) {
					s.accept(this, param);
					tamLocales += ((DefVariable)s).getTipo().numeroBytes();
				}
			}
			necesarioRetorno[1] = tamLocales;
			
			cg.enter(tamLocales);
			//Recorremos las sentencias de la función que no sean defVariables.
			for(Sentencia s : defFuncion.getSentencias()) {
				if(!(s instanceof DefVariable)) {
					cg.line(s.getLine());
					s.accept(this, necesarioRetorno);
					cg.saltoLinea();
				}
			}
			
			//Comprobamos que no sea de tipo VOID y ejecutamos el RET
			if(((TipoFuncion)defFuncion.getTipo()).getTipoRetorno() instanceof TipoVoid)
				cg.ret(0, tamLocales, tamParametros);
			
		} catch (IOException e) {
			System.err.println("Error al ejecutar visit (E) de defFuncion");
		}
		
		return null;
	}
	
	@Override
	public Object visit(Procedimiento proc, Object param) {
		for(Expresion e : proc.getExpresiones())
			e.accept(vcgv, param);
		try {
			cg.call(proc.getNombre().getNombre());
			if(!(((TipoFuncion)proc.getNombre().getDefinicion().getTipo()).getTipoRetorno() instanceof TipoVoid))
				cg.pop(((TipoFuncion)proc.getNombre().getDefinicion().getTipo()).getTipoRetorno().suffix());
		} catch (IOException e) {
			System.err.println("Error al hacer una llamada a la función VALUE. Línea: "+proc.getNombre().getLine());
		}
		return null;
	}
	
	@Override
	public Object visit(Return sentenciaReturn, Object param) {
		sentenciaReturn.getExpr().accept(vcgv, param);
		try {
			cg.ret(((int[])param)[0], ((int[])param)[1], ((int[])param)[2]);
		} catch (IOException e) {
			System.err.println("Error al hacer la sentencia Return EXECUTE. Línea: "+sentenciaReturn.getLine());
		}
		return null;
	}
	
	@Override
	public Object visit(If sentenciaIf, Object param) {
		int label = cg.getLabel(2);
		//throw new IllegalArgumentException("No se puede aplicar " + sentenciaIf.toString() + " esta plantilla");
		sentenciaIf.getExpr().accept(vcgv, param);
		try {
			cg.jz("label_"+label);
			for(Sentencia a : sentenciaIf.getSentenciasIf())
				a.accept(this, param);
			cg.jmp("label_"+(label+1));
			cg.etiqueta("label_"+label+":");
			for(Sentencia b : sentenciaIf.getSentenciasElse())
				b.accept(this, param);
			cg.etiqueta("label_"+(label+1)+":");
		} catch (IOException e) {
			System.err.println("Error al ejecutar senetencia IF EXECUTE. Línea: "+sentenciaIf.getExpr().getLine());
		}
		return null;
	}
	
	@Override
	public Object visit(While sentenciaWhile, Object param) {
		
		int label = cg.getLabel(2);
		try {
			cg.etiqueta("label_"+label+":");
			sentenciaWhile.getExpr().accept(vcgv, param);
			cg.jz("label_"+(label+1));
			for(Sentencia a : sentenciaWhile.getSentencias()) 
				a.accept(this, param);
			cg.jmp("label_"+label);
			cg.etiqueta("label_"+(label+1)+":");
		} catch (IOException e) {
			
		}
		
		return null;
	}

}

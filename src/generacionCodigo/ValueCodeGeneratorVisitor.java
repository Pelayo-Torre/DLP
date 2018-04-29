package generacionCodigo;

import java.io.IOException;

import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Cast;
import ast.Char;
import ast.Comparacion;
import ast.ConstanteEntera;
import ast.ConstanteReal;
import ast.Logica;
import ast.Negacion;
import ast.Variable;
import tipo.TipoChar;
import tipo.TipoFloat32;

public class ValueCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	CodeGenerator cg;
	AddresCodeGeneratorVisitor acg;

	public ValueCodeGeneratorVisitor(CodeGenerator cg, AddresCodeGeneratorVisitor acg) {
		this.cg = cg;
		this.acg = acg;
	}

	@Override
	public Object visit(ConstanteEntera constanteEntera, Object param) {
		try {
			cg.pushi(constanteEntera.getValor());
		} catch (IOException e) {
			System.err.println("Error al cargar constante entera en plantilla VALUE. L�nea: " + constanteEntera.getLine());
		}
		return null;
	}

	@Override
	public Object visit(ConstanteReal constanteReal, Object param) {
		try {
			cg.pushf(constanteReal.getValor());
		} catch (IOException e) {
			System.err.println("Error al cargar constante real en plantilla VALUE. L�nea: " + constanteReal.getLine());
		}
		return null;
	}

	@Override
	public Object visit(Char constanteChar, Object param) {
		try {
			//Cogemos el primer caracter para saber si es un \algo
			char value = constanteChar.getValor().charAt(0);
			if (value == '\\') {
				switch (constanteChar.getValor().charAt(1)) {
					case 'n':
						value = '\n';
						break;
					case 't':
						value = '\t';
						break;
					default:
						value = (char)Integer.parseInt(constanteChar.getValor().substring(1));
						break;
				}
			}
			
			cg.pushb((int)value);
		} catch (IOException e) {
			System.err.println("Error al cargar constante char en plantilla VALUE. L�nea: "+constanteChar.getLine());
		}
		return null;
	}

	@Override
	public Object visit(Aritmetica aritmetica, Object param) {
		aritmetica.getIzquierda().accept(this, param);
		aritmetica.getDerecha().accept(this, param);

		try {
			cg.aritmetica(aritmetica.getOperador(), aritmetica.getIzquierda().getTipo().suffix());
		} catch (IOException e) {
			System.err.println("Error al realizar la operaci�n aritm�tica en plantilla VALUE. L�nea: "+aritmetica.getIzquierda().getLine());
		}
		return null;
	}

	@Override
	public Object visit(Logica logica, Object param) {
		logica.getIzquierda().accept(this, param);
		logica.getDerecha().accept(this, param);

		try {
			cg.logica(logica.getOperador());
		} catch (IOException e) {
			System.err.println("Error al realizar la operaci�n l�gica en plantilla VALUE. L�nea: "+logica.getIzquierda().getLine());
		}
		return null;
	}

	@Override
	public Object visit(Comparacion comparacion, Object param) {
		comparacion.getIzquierda().accept(this, param);
		comparacion.getDerecha().accept(this, param);

		try {
			cg.comparacion(comparacion.getOperador(), comparacion.getIzquierda().getTipo().suffix());
		} catch (IOException e) {
			System.err.println("Error al realizar la comparaci�n en plantilla VALUE. L�nea: "+comparacion.getIzquierda().getLine());
		}
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		variable.accept(acg, param);
		try {
			cg.load(variable.getDefinicion().getTipo().suffix());
		} catch (IOException e) {
			System.err.println("Error al cargar la variable en plantilla VALUE. L�nea: "+variable.getLine());
		}
		return null;
	}
	
	@Override
	public Object visit(Cast cast, Object param) {
		try {
			cast.getExpr().accept(this, param);
			
			if(cast.getExpr().getTipo() instanceof TipoFloat32 && cast.getTipo() instanceof TipoChar) {
				cg.cast('f', 'i');
				cg.cast('i', 'b');
			}
			else if(cast.getExpr().getTipo() instanceof TipoChar && cast.getTipo() instanceof TipoFloat32) {
				cg.cast('b', 'i');
				cg.cast('i', 'f');
			}
			else
				cg.cast(cast.getExpr().getTipo().suffix(), cast.getTipo().suffix());
			
		} catch (IOException e) {
			System.err.println("Error al hacer el cast en plantilla VALUE. L�nea: "+cast.getLine());
		}
		return null;
	}
	
	@Override
	public Object visit(Negacion negacion, Object param) {
		try {
			negacion.getExpr().accept(this, param);
			cg.logica("!");
		} catch (IOException e) {
			System.err.println("Error al hacer la negaci�n en plantilla VALUE. L�nea: "+negacion.getLine());
		}
		return null;
	}
	
	@Override
	public Object visit(AccesoCampo accesoCampo, Object param) {
		accesoCampo.accept(acg, param);
		try {
			cg.load(accesoCampo.getTipo().suffix());
		} catch (IOException e) {
			System.err.println("Error al acceder a campo en plantilla VALUE. L�nea: "+accesoCampo.getLine());
		}
		return null;
	}

}

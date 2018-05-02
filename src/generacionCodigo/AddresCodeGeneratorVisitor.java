package generacionCodigo;

import java.io.IOException;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.DefVariable;
import ast.Variable;

public class AddresCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	CodeGenerator cg;
	ValueCodeGeneratorVisitor vcg;

	public AddresCodeGeneratorVisitor(CodeGenerator cg) {
		this.cg = cg;
	}
	
	public void setValueCodeGenerator(ValueCodeGeneratorVisitor valueCodeGenerator) {
		this.vcg = valueCodeGenerator;
	}

	@Override
	public Object visit(Variable variable, Object param) {

		// Si el �mbito es 0 se trata de una variable global.
		if (((DefVariable) variable.getDefinicion()).getAmbito() == 0)
			try {
				cg.pusha(String.valueOf(((DefVariable) variable.getDefinicion()).getOffset()));
			} catch (IOException e) {
				System.err.println("Error al calcular la direcci�n de la variable " + variable.getNombre()
						+ "   L�nea: " + variable.getLine());
			}
		else {
			try {
				cg.pusha("bp");
				cg.push(((DefVariable) variable.getDefinicion()).getOffset());
				cg.add('i');
			} catch (IOException e) {
				System.err.println("Error al calcualar la direcci�n de la variable " + variable.getNombre()
						+ "   L�nea: " + variable.getLine());
			}
		}

		return null;
	}

	@Override
	public Object visit(AccesoCampo accesoCampo, Object param) {
		accesoCampo.getIzquierda().accept(this, param);
		try {
			cg.push(accesoCampo.getIzquierda().getTipo().getCampo(accesoCampo.getNombre()).getOffset());
			cg.add('i');
		} catch (IOException e) {
			System.err.println("Error al acceder a campo en plantilla ADDRESS. L�nea: " + accesoCampo.getLine());
		}
		return null;
	}
	
	@Override
	public Object visit(AccesoArray accesoArray, Object param) {
		
		accesoArray.getNombre().accept(this, param);
		accesoArray.getPosicion().accept(vcg, param);
		
		try {
			cg.push(accesoArray.getTipo().numeroBytes());
			cg.mul('i');
			cg.add('i');
		} catch (IOException e) {
			System.err.println("Error al acceder a array en plantilla ADDRESS. L�nea: " + accesoArray.getLine());
		}
		
		return null;
	}

}

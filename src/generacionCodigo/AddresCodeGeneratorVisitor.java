package generacionCodigo;

import java.io.IOException;

import ast.AccesoCampo;
import ast.DefVariable;
import ast.Variable;

public class AddresCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	CodeGenerator cg;

	public AddresCodeGeneratorVisitor(CodeGenerator cg) {
		this.cg = cg;
	}

	@Override
	public Object visit(Variable variable, Object param) {

		// Si el ámbito es 0 se trata de una variable global.
		if (((DefVariable) variable.getDefinicion()).getAmbito() == 0)
			try {
				cg.pusha(String.valueOf(((DefVariable) variable.getDefinicion()).getOffset()));
			} catch (IOException e) {
				System.err.println("Error al calcular la dirección de la variable " + variable.getNombre()
						+ "   Línea: " + variable.getLine());
			}
		else {
			try {
				cg.pusha("bp");
				cg.push(((DefVariable) variable.getDefinicion()).getOffset());
				cg.add('i');
			} catch (IOException e) {
				System.err.println("Error al calcualar la dirección de la variable " + variable.getNombre()
						+ "   Línea: " + variable.getLine());
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
			System.err.println("Error al acceder a campo en plantilla ADDRESS. Línea: " + accesoCampo.getLine());
		}
		return null;
	}

}

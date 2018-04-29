package generacionCodigo;

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
import ast.Escritura;
import ast.If;
import ast.InvocacionFuncion;
import ast.Lectura;
import ast.Logica;
import ast.MenosUnario;
import ast.Negacion;
import ast.Procedimiento;
import ast.Programa;
import ast.Return;
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

public abstract class AbstractCodeGeneratorVisitor implements Visitor{
	
	@Override
	public Object visit(ConstanteEntera constanteEntera, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + constanteEntera.toString() + " esta plantilla");
	}

	@Override
	public Object visit(ConstanteReal constanteReal, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + constanteReal.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Char constanteChar, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + constanteChar.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Logica logica, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + logica.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Comparacion comparacion, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + comparacion.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Aritmetica aritmetica, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + aritmetica.toString() + " esta plantilla");
	}

	@Override
	public Object visit(AccesoArray accesoArray, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + accesoArray.toString() + " esta plantilla");
	}

	@Override
	public Object visit(AccesoCampo accesoCampo, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + accesoCampo.toString() + " esta plantilla");
	}

	@Override
	public Object visit(MenosUnario menosUnario, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + menosUnario.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Negacion negacion, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + negacion.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Variable variable, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + variable.toString() + " esta plantilla");
	}

	@Override
	public Object visit(InvocacionFuncion invocacionFuncion, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + invocacionFuncion.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Cast cast, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + cast.toString() + " esta plantilla");
	}

	@Override
	public Object visit(While sentenciaWhile, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + sentenciaWhile.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Escritura escritura, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + escritura.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Lectura lectura, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + lectura.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Asignacion asignacion, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + asignacion.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Return sentenciaReturn, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + sentenciaReturn.toString() + " esta plantilla");
	}

	@Override
	public Object visit(If sentenciaIf, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + sentenciaIf.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Procedimiento procedimiento, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + procedimiento.toString() + " esta plantilla");
	}

	@Override
	public Object visit(TipoArray tipoArray, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + tipoArray.toString() + " esta plantilla");
	}

	@Override
	public Object visit(TipoChar tipoChar, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + tipoChar.toString() + " esta plantilla");
	}

	@Override
	public Object visit(TipoFuncion tipoFuncion, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + tipoFuncion.toString() + " esta plantilla");
	}

	@Override
	public Object visit(TipoStruct tipoStruct, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + tipoStruct.toString() + " esta plantilla");
	}

	@Override
	public Object visit(CampoStruct campoStruct, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + campoStruct.toString() + " esta plantilla");
	}

	@Override
	public Object visit(TipoError tipoError, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + tipoError.toString() + " esta plantilla");
	}

	@Override
	public Object visit(TipoEntero tipoEntero, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + tipoEntero.toString() + " esta plantilla");
	}

	@Override
	public Object visit(TipoFloat32 tipoFloat32, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + tipoFloat32.toString() + " esta plantilla");
	}

	@Override
	public Object visit(TipoVoid tipoVoid, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + tipoVoid.toString() + " esta plantilla");
	}

	@Override
	public Object visit(DefVariable defVariable, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + defVariable.toString() + " esta plantilla");
	}

	@Override
	public Object visit(DefFuncion defFuncion, Object param) {
		throw new IllegalArgumentException("No se puede aplicar " + defFuncion.toString() + " esta plantilla");
	}

	@Override
	public Object visit(Programa programa, Object param) {
		throw new IllegalArgumentException("No se puede aplicar programa a esta plantilla");
	}

}

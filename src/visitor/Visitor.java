package visitor;
import ast.*;
import tipo.*;

public interface Visitor {
	
	//Constantes
	public Object visit(ConstanteEntera constanteEntera, Object param);
	public Object visit(ConstanteReal constanteReal, Object param);
	public Object visit(Char constanteChar, Object param);
	
	//Expresiones binarias
	public Object visit(Logica logica, Object param);
	public Object visit(Comparacion comparacion, Object param);
	public Object visit(Aritmetica aritmetica, Object param);
	public Object visit(AccesoArray accesoArray, Object param);
	public Object visit(AccesoCampo accesoCampo, Object param);
	
	//Expresiones unarias
	public Object visit(MenosUnario menosUnario, Object param);
	public Object visit(Negacion negacion, Object param);
	
	//Otras expresiones
	public Object visit(Variable variable, Object param);
	public Object visit(InvocacionFuncion invocacionFuncion, Object param);
	public Object visit(Cast cast, Object param);
	
	//Sentencias
	public Object visit(While sentenciaWhile, Object param);
	public Object visit(Escritura escritura, Object param);
	public Object visit(Lectura lectura, Object param);
	public Object visit(Asignacion asignacion, Object param);
	public Object visit(Return sentenciaReturn, Object param);
	public Object visit(If sentenciaIf, Object param);
	public Object visit(Procedimiento procedimiento, Object param);

	//Tipos
	public Object visit(TipoArray tipoArray, Object param);
	public Object visit(TipoChar tipoChar, Object param);
	public Object visit(TipoFuncion tipoFuncion, Object param);
	public Object visit(TipoStruct tipoStruct, Object param);
	public Object visit(CampoStruct campoStruct, Object param);
	public Object visit(TipoError tipoError, Object param);
	public Object visit(TipoEntero tipoEntero, Object param);
	public Object visit(TipoFloat32 tipoFloat32, Object param);
	public Object visit(TipoVoid tipoVoid, Object param);
	
	//Definiciones
	public Object visit(DefVariable defVariable, Object param);
	public Object visit(DefFuncion defFuncion, Object param);
	
	//Programa
	public Object visit(Programa programa, Object param);
	
}

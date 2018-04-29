package generacionCodigo;

import ast.DefFuncion;
import ast.DefVariable;
import ast.Sentencia;
import semantico.VisitorPorDefecto;
import tipo.CampoStruct;
import tipo.TipoFuncion;
import tipo.TipoStruct;

public class VisitorOffset extends VisitorPorDefecto{
	
	//Tipo INT --> 1 byte.
	//Tipo FLOAT32 --> 4 bytes
	//Tipo CHAR --> 2 bytes
	
	int offsetGlobal = 0;
	int offsetLocal = 0;
	int offsetParametro = 0;
	int offsetCampo = 0;
	
	@Override
	public Object visit(DefVariable defVariable, Object param) {
		defVariable.getTipo().accept(this, null);
		
		if(defVariable.getAmbito() == 0) //Variable global
		{	
			defVariable.setOffset(offsetGlobal);
			offsetGlobal += defVariable.getTipo().numeroBytes();
			//System.out.println("Offset de la variable global --" + defVariable.getNombre() + "-- es: " + defVariable.getOffset());
		}
		else if((boolean) param == true)	//Variable local
		{
			defVariable.setOffset( -(offsetLocal + defVariable.getTipo().numeroBytes()) );
			offsetLocal = defVariable.getOffset() * (-1);
			//System.out.println("Offset de la variable local --" + defVariable.getNombre() + "-- es: " + defVariable.getOffset());
			
		}
		else	//Parámetro
		{
			defVariable.setOffset( 4 + offsetParametro );
			offsetParametro += defVariable.getTipo().numeroBytes();
			//System.out.println("Offset del parámetro --" + defVariable.getNombre() + "-- es: " + defVariable.getOffset());
		}
		
		return null;
	}
	
	@Override
	public Object visit(DefFuncion defFuncion, Object param) {
		defFuncion.getTipo().accept(this, null);
		for(Sentencia s : defFuncion.getSentencias()) {
			//Le pasamos true, para indicar que es variable local, ya que las variables locales se definen en la DefFuncion.
			s.accept(this, true);	
		}
		
		//Reseteamos el offset de las variables locales
		this.offsetLocal = 0;
		
		return null;
	}
	
	@Override
	public Object visit(TipoFuncion tipoFuncion, Object param) {
		tipoFuncion.getTipoRetorno().accept(this, null);
		
		for(int i = tipoFuncion.getArgumentos().size()-1; i>=0 ; i--) {
			 //Le pasamos false para indicar que son parámetros, ya que los parámetros se definen en TipoFuncion
			tipoFuncion.getArgumentos().get(i).accept(this, false);
		}
		
		//Reseteamos el offset del parámetro.
		this.offsetParametro = 0;
		
		return null;
	}

	@Override
	public Object visit(TipoStruct tipoStruct, Object param) {
		for(CampoStruct campo : tipoStruct.getCampos()) {
			campo.accept(this, null);
			campo.setOffset(offsetCampo);
			offsetCampo += campo.getTipo().numeroBytes();
			//System.out.println("Offset del campo --" + campo.getNombre() + "-- es: " + campo.getOffset());
		}
		
		//Reseteamos el offset de campos:
		this.offsetCampo = 0;
		
		return null;
	}
}

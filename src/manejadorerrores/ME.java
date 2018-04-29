package manejadorerrores;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import tipo.TipoError;

public class ME {
	
	private static final ME instance = new ME();
	private List<TipoError> errores = new ArrayList<TipoError>();;
	
	private ME() {
		 
	}
	
	public static ME getME() {
		return instance;
	}

	public boolean huboErrores() {
		if(errores.size() != 0)
			return true;
		return false;
	}

	public void mostrarErrores(PrintStream err) {
		for(TipoError error : errores) {
			err.println(error.toString());
		}
	}
	
	public void añadirErrores(TipoError te) {
		errores.add(te);
	}
	
	

}

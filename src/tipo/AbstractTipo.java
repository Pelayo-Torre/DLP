package tipo;

import java.util.List;

import ast.AbstractNodoAST;

public abstract class AbstractTipo extends AbstractNodoAST implements Tipo {

	public AbstractTipo(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean esLogico() {
		return false;
	}

	@Override
	public Tipo aritmetica(Tipo tipo) {
		if(tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public Tipo aritmetica() {
		return null;
	}

	@Override
	public Tipo punto(String acceso) {
		return null;
	}

	@Override
	public Tipo corchetes(Tipo tipo) {
		return null;
	}

	@Override
	public boolean esBasico() {
		return false;
	}

	@Override
	public Tipo cast(Tipo tipo) {
		if (esBasico() == true)
			return tipo;
		return null;
	}
	
	@Override
	public boolean isCaracter() {
		return false;
	}

	@Override
	public Tipo parentesis(List<Tipo> tipos) {
		return null;
	}

	@Override
	public Tipo comparacion(Tipo tipo) {
		if ((tipo instanceof TipoEntero || tipo instanceof TipoFloat32 
				|| tipo instanceof TipoChar) && tipo.getClass().equals(getClass()))
			return new TipoEntero(tipo.getLine(), tipo.getColumn());
		else if(tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public Tipo negacion() {
		return null;
	}

	@Override
	public Tipo logica(Tipo tipo) {
		if ((tipo instanceof TipoEntero && tipo.getClass().equals(getClass()))) {
			return tipo;
		}
		else if(tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public int numeroBytes() {
		return 0;
	}
	
	@Override
	public char suffix() {
		return 0;
	}
	
	@Override
	public CampoStruct getCampo(String nombreCampo) {
		return null;
	}

}

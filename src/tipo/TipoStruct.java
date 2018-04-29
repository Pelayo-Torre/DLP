package tipo;

import java.util.List;

import visitor.Visitor;

public class TipoStruct extends AbstractTipo{

	private List<CampoStruct> campos;
	
	public TipoStruct(int line, int column, List<CampoStruct> campos) {
		super(line, column);
		this.campos = campos;
	}

	public List<CampoStruct> getCampos() {
		return campos;
	}

	public void setCampos(List<CampoStruct> campos) {
		this.campos = campos;
	}

	@Override
	public String toString() {
		return "TipoStruct [campos=" + campos + "]";
	}
	
	@Override
	public Tipo punto(String acceso) {
		for(CampoStruct campo : campos){
			if(campo.getNombre().equals(acceso))
				return campo.getTipo();
		}
		return null;
	}

	@Override
	public Tipo promocionaA(Tipo tipo) {
		if(tipo.getClass() == this.getClass())
			return this;
		return null;
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public int numeroBytes() {
		int bytesStruct = 0;
		for(CampoStruct campo : campos)
			bytesStruct += campo.getTipo().numeroBytes();
		return bytesStruct;
	}
	
	@Override
	public CampoStruct getCampo(String nombreCampo) {
		for(CampoStruct c : campos)
			if(c.getNombre().equals(nombreCampo))
				return c;
		return null;
	}
		
}

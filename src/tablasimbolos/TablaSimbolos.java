package tablasimbolos;

import java.util.*;

import ast.DefVariable;
import ast.Definicion;

public class TablaSimbolos {
	
	private int ambito=0;
	private List<Map<String,Definicion>> tabla;
	
	public TablaSimbolos()  {
		tabla = new ArrayList<Map<String,Definicion>>();
		Map<String, Definicion> mapa = new HashMap<String, Definicion>();
		tabla.add(mapa);
	}

	/**
	 * 	Crea una nueva tabla HASH con Scope(ámbito) +1
	 */
	public void set() {
		Map<String, Definicion> mapa = new HashMap<>();
		tabla.add(mapa);
		ambito++;
	}
	
	/**
	 * Elimina el último tabla HASH creada y se decrementa el ámbito.
	 */
	public void reset() {
		tabla.remove(tabla.get(tabla.size()-1));
		ambito--;
	}
	
	/**
	 * Si consigue meter retorna true sino false
	 * @param simbolo
	 * @return
	 */
	public boolean insertar(Definicion simbolo) {
		if(simbolo instanceof DefVariable) {
			((DefVariable)simbolo).setAmbito(ambito);
		}
		if(buscarAmbitoActual(simbolo.getNombre()) != null) 
			return false;
		
		else {
			tabla.get(tabla.size()-1).put(simbolo.getNombre(), simbolo);
			return true;
		}
		
	}
	
	/**
	 * Busca GLOBALMENTE y LOCALMENTE por ID y devuelve la definición. 
	 * Si no la encuentra retorna null.
	 * @param id
	 * @return
	 */
	public Definicion buscar(String id) {
		for(int i=0; i<tabla.size(); i++) {
			if(tabla.get(i).containsKey(id)) {
				return tabla.get(i).get(id);
			}
		}
		return null;
	}

	/**
	 * Busca que en el último map introducido, no se encuentre la definición cuya 
	 * clave se le pasa como parámetro
	 * @param id
	 * @return
	 */
	public Definicion buscarAmbitoActual(String id) {		
		for(Map.Entry<String, Definicion> i : tabla.get(tabla.size()-1).entrySet()) {
			if(i.getKey().equals(id))
				return i.getValue();		
		}
		return null;
	}
}

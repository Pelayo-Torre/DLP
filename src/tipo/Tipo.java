package tipo;

import java.util.List;

import ast.NodoAST;

public interface Tipo extends NodoAST{
	
	/**
	 * Comprueba que el tipo sea Entero o no, devolviendo null
	 * en caso de que no sea l�gico.
	 * @return
	 */
	boolean esLogico();	//Comprueba si es l�gico o no.
	
	/**
	 * Comprueba que el tipo pasado como par�metro es ENTERO, FLOAT32, CHAR
	 * o TIPOERROR, y adem�s, comprueba que el par�metro y la clase que lo 
	 * invoca sean del mismo tipo.
	 * @param tipo
	 * @return
	 */
	Tipo aritmetica(Tipo tipo);	//Devuelve el tipo de la expresi�n de la derecha
	
	/**
	 * Retorna null si el tipo no es ENTERO o FLOAT32. Se utiliza para comprobar
	 * que solo se pueda negar un entero o flotante.
	 * @return
	 */
	Tipo aritmetica();	//Menos unario. Solo piede ser Entero o real
	
	Tipo punto(String acceso);	//Acceso a campo.
	
	Tipo corchetes(Tipo tipo);	//Acceso a arrays
	
	boolean isCaracter();
	
	Tipo cast(Tipo tipo);	//Cast de tipos
	
	boolean esBasico(); //Si es INT, FLOAT32 O CHAR
	
	Tipo promocionaA(Tipo tipo); //
	
	Tipo parentesis(List<Tipo> tipos);
	
	Tipo comparacion(Tipo tipo); //Comparacion entero > entero
	
	Tipo negacion();	//La negaci�n solo se hace sobre el tipo l�gico.
	
	Tipo logica(Tipo tipo);	//Condiciones
	
	int numeroBytes();	//Indica el n�mero de bytes que ocupar� en memoria. --> Tama�o
	
	/**
	 * Devuelve f, si es flotante, i si es enterp y b si es flotante.
	 * @return
	 */
	char suffix();
	
	/**
	 * Devuelve el campo de un struct dado su nombre
	 * @param nombreCampo
	 * @return
	 */
	CampoStruct getCampo(String nombreCampo);
}

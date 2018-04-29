package test;

import introspector.view.IntrospectorTree;
import introspector.model.IntrospectorModel;

import java.util.*;

import ast.Aritmetica;
import ast.Asignacion;
import ast.ConstanteEntera;
import ast.Escritura;
import ast.Expresion;
import ast.Lectura;
import ast.MenosUnario;
import ast.NodoAST;
import ast.Sentencia;
import ast.Variable;

/**
 * Peque�o programa de prueba.<br/>
 * Dise�o de Lenguajes de Programaci�n<br/>
 * Escuela de Ingenier�a Inform�tica<br/>
 * Universidad de Oviedo<br/>
 * 
 * @author Francisco Ortin
 */
public class ASTTest {

	/**
	 * Prueba de creaci�n de un AST.
	 * El programa de entrada es: 
	 *   read a,b; 
	 *   a = (-b+3)*c/2; 
	 *   write a,c*2;
	 */
	private static NodoAST crearArbol() {
		List<Sentencia> sentencias = new ArrayList<Sentencia>();
		List<Expresion> expresiones = new ArrayList<Expresion>();
		// * Primera linea
		Expresion expresion = new Variable(1, 6, "a");
		expresiones.add(expresion);
		sentencias.add(new Lectura(1, 6, expresiones));
		
		expresion = new Variable(1, 8, "b");
		sentencias.add(new Lectura(1, 8, expresiones));
		// * Segunda L�nea
		Sentencia sentencia = new Asignacion(2, 3, 
				new Variable(2, 1, "a"),
				new Aritmetica(2, 11,
						new Aritmetica(2, 8, 
								new MenosUnario(2, 6, 
										new Variable(2, 7, "b")
										),
								"+",
								new ConstanteEntera(2, 9, 3)
								),
						"*",
						new Aritmetica(2, 13,
								new Variable(2, 12, "c"),
								"/",
								new ConstanteEntera(2, 14, 2)
								)
						)
				);
		sentencias.add(sentencia);
		
		// * Tercera L�nea
		expresion = new Variable(3, 7, "a");
		sentencias.add(new Escritura(3, 7, expresiones));
		
		expresion = new Aritmetica(3, 10, 
				new Variable(3, 9, "c"),
				"*",
				new ConstanteEntera(3, 11, 2)
				);
		sentencias.add(new Escritura(3, 9, expresiones));
		
		// * Construimos y devolvemos el �rbol
		//return new Programa(1, 1, sentencias);		
		return null;
	}

	public static void main(String[] args) {
		IntrospectorModel modelo = new IntrospectorModel("Programa",
				crearArbol());
		new IntrospectorTree("Introspector", modelo);
	}
}

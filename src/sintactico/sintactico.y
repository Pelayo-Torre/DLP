%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexico.Lexico;
import ast.*;
import tipo.*;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

%}

// * Declaraciones Yacc
%token CTE_ENTERA
%token WRITE
%token STRUCT
%token AND
%token CHAR
%token CTE_REAL
%token READ
%token MAYORIGUAL
%token MAIN
%token IGUALDAD
%token WHILE
%token ELSE
%token DISTINTO
%token OR
%token IDENTIFICADOR
%token VOID
%token INT
%token VAR
%token MENORIGUAL
%token RETURN
%token FLOAT32
%token IF
%token FUNC

%left '='
%left OR AND
%left IGUALDAD DISTINTO MENORIGUAL MAYORIGUAL '<' '>'
%left '+' '-'
%left '/' '*' '%'
%left '!'
%left '[' ']'
%left '.'
%left '(' ')'

%%
// * Gramática y acciones Yacc

programa: definiciones FUNC MAIN '(' ')' VOID '{' sentenciasCuerpo '}' ;	{ raiz = new Programa(lexico.getLinea(), lexico.getColumna(), (ArrayList<Definicion>)$1, (ArrayList<Sentencia>)$8) ; }								

definiciones: definiciones definicion						{ List<Definicion> lista = (List<Definicion>)$1;
																for(int i=0; i<((List<Definicion>)$2).size(); i++){
																	lista.add(((List<Definicion>)$2).get(i));
															   } 
															   $$ = lista ;  }									
			|												{ $$ = new ArrayList<Definicion>() ; }																					
			;

definicion: defFuncion										{ $$ = $1 ; }
		  | defVariable										{ $$ = $1 ; }
		  ;

defFuncion: FUNC IDENTIFICADOR tipoFuncion '{' sentenciasCuerpo '}' ;	{ ArrayList<DefFuncion> lista = new ArrayList<DefFuncion>();
 																		lista.add(new DefFuncion(lexico.getLinea(), lexico.getColumna(), (String)$2, (TipoFuncion)$3, (ArrayList<Sentencia>)$5));
 																		$$ = lista ; }

tipoFuncion: '(' parametrosFuncion ')' tipoRetorno			{ $$ = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (ArrayList<DefVariable>)$2, (Tipo)$4) ; }
		   ;
		   
tipoRetorno: INT											{ $$ = new TipoEntero(lexico.getLinea(), lexico.getColumna()) ; }
		   | FLOAT32										{ $$ = new TipoFloat32(lexico.getLinea(), lexico.getColumna()) ; }
		   | CHAR											{ $$ = new TipoChar(lexico.getLinea(), lexico.getColumna()) ; }
		   | VOID											{ $$ = new TipoVoid(lexico.getLinea(), lexico.getColumna()) ; }
		   ;

parametrosFuncion: parametros								{ $$ = $1 ; }									
		  		 |											{ $$ = new ArrayList<DefVariable>() ; }									
		 		 ;
		 		 
parametros: parametro										{ $$ = new ArrayList<DefVariable>() ; ((ArrayList<DefVariable>)$$).add((DefVariable)$1) ; }
		  | parametros ',' parametro						{ $$ = (ArrayList<DefVariable>)$1 ; ((ArrayList)$$).add((DefVariable)$3) ; } 
		  ;
		  
parametro: IDENTIFICADOR tipoSimples;						{ $$ = new DefVariable(lexico.getLinea(), lexico.getColumna(), (String)$1, (Tipo)$2) ; }									

defVariablesLocales: defVariablesLocales defVariable		{ $$ = (ArrayList<DefVariable>)$1 ; for(int i=0; i<((ArrayList<DefVariable>)$2).size(); i++) {((ArrayList<DefVariable>)$$).add(((ArrayList<DefVariable>)$2).get(i)) ;}}										
				   | 										{ $$ = new ArrayList<DefVariable>() ; }
				   ;

defVariable: VAR identificadores tipo ';'					  { ArrayList<DefVariable> lista = new ArrayList<DefVariable>() ; 
																ArrayList<String> aux = new ArrayList<String>();
			;													for(int i=0; i<((ArrayList<String>)$2).size(); i++) {
																	if(aux.contains(((ArrayList<String>)$2).get(i)))
																	{
																		new TipoError(lexico.getLinea(), lexico.getColumna(), "Nombre de variable repetida");
																	} 
																	aux.add(((ArrayList<String>)$2).get(i));
																	lista.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), ((ArrayList<String>)$2).get(i), (Tipo)$3));
																}
																$$ = lista ; }																									
		   

identificadores: IDENTIFICADOR								{ $$ = new ArrayList<String>() ; ((ArrayList<String>)$$).add((String)$1) ; }								
			   | identificadores ',' IDENTIFICADOR			{ $$ = (ArrayList<String>)$1 ; ((ArrayList<String>)$$).add((String)$3) ; }								
			   ;

sentenciasCuerpo: defVariablesLocales sentencias;			{ ArrayList<Sentencia> lista = new ArrayList<Sentencia>();
															  lista.addAll((ArrayList<DefVariable>)$1);
															  lista.addAll((ArrayList<Sentencia>)$2) ;
															  $$ = lista ; }

sentencias: sentencias sentencia							{ $$ = (ArrayList<Sentencia>)$1 ; ((ArrayList<Sentencia>)$$).add((Sentencia)$2) ; }
		  |													{ $$ = new ArrayList<Sentencia>() ; }		
		  ;
		  
sentencia: write											{ $$ = $1 ; }									
		 | read												{ $$ = $1 ; }									
		 | return											{ $$ = $1 ; }									
		 | if												{ $$ = $1 ; }									
		 | while											{ $$ = $1 ; }									
		 | asignacion										{ $$ = $1 ; }									
		 | procedimiento									{ $$ = $1 ; }
		 ;

write: WRITE '(' expresiones ')' ';'						{ $$ = new Escritura(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)$3) ; }									
	 ;
	 
read: READ '(' expresiones ')' ';'							{ $$ = new Lectura(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)$3) ; }									
	;
	
return: RETURN expresion ';'								{ $$ = new Return(lexico.getLinea(), lexico.getColumna(), (Expresion)$2) ; }									
	  ;
	  
if: IF expresion '{' sentencias '}' ELSE '{' sentencias '}'				{ $$ = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)$2, (ArrayList<Sentencia>)$4, (ArrayList<Sentencia>)$8) ; }						
  | IF expresion '{' sentencias '}'										{ $$ = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)$2, (ArrayList<Sentencia>)$4, null) ; }						
  ;

while: WHILE expresion '{' sentencias '}'					{ $$ = new While(lexico.getLinea(), lexico.getColumna(), (Expresion)$2, (ArrayList<Sentencia>)$4) ; }				
	 ;
	
asignacion: expresion "=" expresion ';'						{ $$ = new Asignacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3) ; }					
		  ;
		  
procedimiento: IDENTIFICADOR '(' expresionesInvocacion ')' ';'			{ $$ = new Procedimiento(lexico.getLinea(), lexico.getColumna(), new Variable(lexico.getLinea(), lexico.getColumna(), (String)$1), (ArrayList<Expresion>)$3) ; }					

funcionExpresion: IDENTIFICADOR '(' expresionesInvocacion ')'			{ $$ = new InvocacionFuncion(lexico.getLinea(), lexico.getColumna(), new Variable(lexico.getLinea(), lexico.getColumna(), (String)$1), (ArrayList<Expresion>)$3) ; }						

expresionesInvocacion: expresiones							{ $$ = $1 ; }									
					 |										{ $$ = new ArrayList<Expresion>() ; }						
					 ;											
	 
expresiones: expresion										{ $$ = new ArrayList<Expresion>() ; ((ArrayList<Expresion>)$$).add((Expresion)$1) ; }									
		   | expresiones ',' expresion						{ $$ = (ArrayList<Expresion>)$1 ; ((ArrayList<Expresion>)$$).add((Expresion)$3) ; }									
		   ;
		   
expresion: expresion '+' expresion							{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }										
		 | expresion '-' expresion							{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }										
         | expresion '*' expresion							{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }										
         | expresion '/' expresion							{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }										
         | '(' expresion ')'								{ $$ = $2 ; }									
         | expresion '[' expresion ']'						{ $$ = new AccesoArray(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3) ; }						
         | expresion '.' IDENTIFICADOR						{ $$ = new AccesoCampo(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$3) ; }									
         | IDENTIFICADOR									{ $$ = new Variable(lexico.getLinea(), lexico.getColumna(), (String)$1) ; }
         | '-' expresion									{ $$ = new MenosUnario(lexico.getLinea(), lexico.getColumna(), (Expresion)$2) ; }							
         | '!' expresion									{ $$ = new Negacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$2) ; }
         | expresion '%' expresion							{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }									
         | expresion '>' expresion							{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }									
         | expresion '<' expresion							{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }									
         | expresion MAYORIGUAL expresion					{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }									
         | expresion MENORIGUAL expresion					{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }									
         | expresion DISTINTO expresion						{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }										
         | expresion IGUALDAD expresion						{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }										
         | expresion AND expresion							{ $$ = new Logica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }							
         | expresion OR expresion							{ $$ = new Logica(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$2, (Expresion)$3) ; }								
         | tipoSimples '(' expresion ')'					{ $$ = new Cast(lexico.getLinea(), lexico.getColumna(), (Tipo)$1, (Expresion)$3) ; }									
         | CTE_ENTERA										{ $$ = new ConstanteEntera(lexico.getLinea(), lexico.getColumna(), (int)$1) ; }								
         | CTE_REAL											{ $$ = new ConstanteReal(lexico.getLinea(), lexico.getColumna(), (float)$1) ; }								
         | CHAR												{ $$ = new Char(lexico.getLinea(), lexico.getColumna(), (String)$1) ; }									
         | funcionExpresion									{ $$ = $1 ; }								
         ;
  
tipoSimples: CHAR											{ $$ = new TipoChar(lexico.getLinea(), lexico.getColumna()) ; }
		   | INT											{ $$ = new TipoEntero(lexico.getLinea(), lexico.getColumna()) ; }
		   | FLOAT32										{ $$ = new TipoFloat32(lexico.getLinea(), lexico.getColumna()) ; }		
		   ;
       
tipo: CHAR													{ $$ = new TipoChar(lexico.getLinea(), lexico.getColumna()) ; }						
	| FLOAT32												{ $$ = new TipoFloat32(lexico.getLinea(), lexico.getColumna()) ; }									
	| INT													{ $$ = new TipoEntero(lexico.getLinea(), lexico.getColumna()) ; }									
	| '[' CTE_ENTERA ']' tipo								{ $$ = new TipoArray(lexico.getLinea(), lexico.getColumna(), (int)$2, (Tipo)$4) ; }
	| STRUCT '{' campos '}' 								{ $$ = new TipoStruct(lexico.getLinea(), lexico.getColumna(), (ArrayList<CampoStruct>)$3) ; 
																ArrayList<String> aux = new ArrayList<String>();
																for(int i=0; i<((ArrayList<CampoStruct>)$3).size(); i++){
															 	 	if(aux.contains(((ArrayList<CampoStruct>)$3).get(i).getNombre()))
																	{
																		new TipoError(lexico.getLinea(), lexico.getColumna(),  "El campo --" + ((ArrayList<CampoStruct>)$3).get(i).getNombre() + "-- está repetido");
																	} 
																	aux.add(((ArrayList<CampoStruct>)$3).get(i).getNombre());
																}
																
																}																
	;
	
campos: campo												{ $$ = new ArrayList<CampoStruct>()	; ((ArrayList<CampoStruct>)$$).addAll((ArrayList<CampoStruct>)$1) ; }		
	  | campos campo										{ ((ArrayList<CampoStruct>)$1).addAll((ArrayList<CampoStruct>)$2) ; 
	  															$$ = (ArrayList<CampoStruct>)$1 ; }
	  ;
	
campo: identificadores tipo ';'								{ ArrayList<CampoStruct> lista = new ArrayList<CampoStruct>() ;
															 	 for(int i=0; i<((ArrayList<String>)$1).size(); i++){
																	lista.add(new CampoStruct(lexico.getLinea(), lexico.getColumna(), ((ArrayList<String>)$1).get(i), (Tipo)$2));
															 	 }	
															 	 $$ = lista ;
															}		
	  ;														
	  

%%

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Lexico lexico;
private NodoAST raiz;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
		token=lexico.yylex(); 	
		this.yylval = lexico.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Error Sintáctico en línea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
}

public NodoAST getAST() {
	return raiz;
}

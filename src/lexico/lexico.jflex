// ************  Código a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************
// * Para acceder al número de línea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}

%}

// ************  Patrones (macros) ********************

PalabraReservadaVar = "var"
PalabraReservadaFunc = "func"
PalabraReservadaStruct = "struct"
PalabraReservadaReturn = "return"
PalabraReservadaWhile = "while"
PalabraReservadaIf = "if"
PalabraReservadaElse = "else"
PalabraReservadaInt = "int"
PalabraReservadaFloat32 = "float32"
PalabraReservadaChar = "char"
PalabraReservadaWrite = "write"
PalabraReservadaRead = "read"
PalabraReservadaVoid = "void"
PalabraReservadaMain = "main"

AndLogico = "&&"
OrLogico = "||"
MayorIgual = ">="
MenorIgual = "<="
Igualdad = "=="
Distinto = "!="

Suma = "+"
Resta = "-"
Multiplicacion = "*"
Division = "/"
Porcentaje = "%"
Mayor = ">"
Menor = "<"
Igual = "="
ParentesisIzquierdo = "("
ParentesisDerecho = ")"
CorcheteDerecho = "["
CorcheteIzquierdo = "]"
Exclamacion = "!"
Punto = "."
DelimitadorIzquierdo = "{"
DelimitadorDerecho = "}"
PuntoYComa = ";"
Coma = ","

ConstanteEntera = [0-9]+
ConstanteReal = [0-9]+( ("."[0-9]*) | ("."[0-9]+[e|E]"-"?[0-9]+) | ([e|E]"-"?[0-9]+) )		
Identificador = [a-zA-ZñÑáéíóúÁÉÍÓÚ][a-zA-ZñÑáéíóúÁÉÍÓÚ0-9_]*
CharConMasDeUnCaracter = ('.')|('\\[0-9]{1,3}')|('\\n')|('\\t')

Comentario = "//".*
ComentarioGrande = "/*"~"*/"

SaltoDeLinea = \n
RetornoDeCarro = \r
Tabulador = \t
Espacio = " "


%%
// ************  Acciones ********************

// * Constante Entera
{ConstanteEntera}	{ this.yylval = new Integer(yytext());
         			  return Parser.CTE_ENTERA;  }
 
 // * Constante Real
 {ConstanteReal}	{ this.yylval = new Float(yytext());
 					  return Parser.CTE_REAL;}				  
 					  
 					
 		//************** PALABRAS RESERVADAS *************			
 					  
 					  
 // * Palabra Reservada var
 {PalabraReservadaVar}	{ this.yylval = new String(yytext());
 					  	  return Parser.VAR;}
 					  	  
 // * Palabra Reservada func
 {PalabraReservadaFunc}	{ this.yylval = new String(yytext());
 					  	  return Parser.FUNC;}
 					  	  
 // * Palabra Reservada struct
 {PalabraReservadaStruct}	{ this.yylval = new String(yytext());
 					  	  return Parser.STRUCT;}
 					  	  
// * Palabra Reservada return 
 {PalabraReservadaReturn}	{ this.yylval = new String(yytext());
 					  	  return Parser.RETURN;}
 					  	  
 // * Palabra Reservada while
 {PalabraReservadaWhile}	{ this.yylval = new String(yytext());
 					  	  return Parser.WHILE;}
 					  	  
 // * Palabra Reservada if
 {PalabraReservadaIf}	{ this.yylval = new String(yytext());
 					  	  return Parser.IF;} 	
 					  	  
 // * Palabra Reservada else
 {PalabraReservadaElse}	{ this.yylval = new String(yytext());
 					  	  return Parser.ELSE;}
 					  	  
 // * Palabra Reservada int
 {PalabraReservadaInt}	{ this.yylval = new String(yytext());
 					  	  return Parser.INT;}
 					  	  
 // * Palabra Reservada float32
 {PalabraReservadaFloat32}	{ this.yylval = new String(yytext());
 					  	  return Parser.FLOAT32;}	
 					  	  
 // * Palabra Reservada char
 {PalabraReservadaChar}	{ this.yylval = new String(yytext());
 					  	  return Parser.CHAR;}
 					  	  
 // * Palabra Reservada write
 {PalabraReservadaWrite}	{ this.yylval = new String(yytext());
 					  	  return Parser.WRITE;}
 					  	  
 // * Palabra Reservada read
 {PalabraReservadaRead}	{ this.yylval = new String(yytext());
 					  	  return Parser.READ;}	
 					  	  
 // * Palabra Reservada void
 {PalabraReservadaVoid}	{ this.yylval = new String(yytext());
 					  	  return Parser.VOID;}
 					  	  
 // * Palabra Reservada main
 {PalabraReservadaMain}	{ this.yylval = new String(yytext());
 					  	  return Parser.MAIN;} 					  	  		  	  						  	    				  	  
 	
 					  	  
 		//************** OPERADORES LÓGICOS ***********			  	  
 
 
 // * Operador lógico AND
 {AndLogico}				{ this.yylval = new String(yytext());
 					  	  return Parser.AND;}
 					  	  
 // * Operador lógico OR
 {OrLogico}				{ this.yylval = new String(yytext());
 					  	  return Parser.OR;}							  	  			  	  
 					  	  			  	  		
 // * Operación lógica >=
 {MayorIgual}				{ this.yylval = new String(yytext());
 					  	  return Parser.MAYORIGUAL;}	
 					  	  
 // * Operación lógica <=
 {MenorIgual}				{ this.yylval = new String(yytext());
 					  	  return Parser.MENORIGUAL;}	
 					  	  
 // * Operación lógica ==
 {Igualdad}				{ this.yylval = new String(yytext());
 					  	  return Parser.IGUALDAD;} 	
 					  	  
 // * Operación lógica !=
 {Distinto}				{ this.yylval = new String(yytext());
 					  	  return Parser.DISTINTO;} 		
 					  	  
 					  	  
 		//************** UN CARACTER *************
 		
 
 {Suma}						{ this.yylval = yytext(); 	return '+';}
 {Resta}					{ this.yylval = yytext(); 	return '-';}
 {Multiplicacion}			{ this.yylval = yytext(); 	return '*';}
 {Division}					{ this.yylval = yytext(); 	return '/';}
 {Porcentaje}				{ this.yylval = yytext(); 	return '%';}
 {Mayor}					{ this.yylval = yytext(); 	return '>';}
 {Menor}					{ this.yylval = yytext(); 	return '<';}
 {Igual}					{ this.yylval = yytext(); 	return '=';}
 {ParentesisIzquierdo}		{ this.yylval = yytext(); 	return '(';}
 {ParentesisDerecho}		{ this.yylval = yytext(); 	return ')';}
 {CorcheteDerecho}			{ this.yylval = yytext(); 	return '[';}
 {CorcheteIzquierdo}		{ this.yylval = yytext(); 	return ']';}
 {Exclamacion}				{ this.yylval = yytext(); 	return '!';}
 {Punto}					{ this.yylval = yytext(); 	return '.';}
 {DelimitadorIzquierdo}		{ this.yylval = yytext(); 	return '{';}
 {DelimitadorDerecho}		{ this.yylval = yytext(); 	return '}';}
 {PuntoYComa}				{ this.yylval = yytext(); 	return ';';}
 {Coma}						{ this.yylval = yytext(); 	return ',';}
 
 
 
 		//************** IDENTIFICADORES ***************
 		

// * Identificador
 {Identificador}		{this.yylval = new String(yytext());
 					  	  return Parser.IDENTIFICADOR;} 		  		
 			  	  			  	  				  	  		
// * Char de más de un caracter
 {CharConMasDeUnCaracter}		{this.yylval = yytext().substring(1, yytext().length()-1);
 					  	  return Parser.CHAR;}  			  	  						  	  				  	  				  	  				  	  			  	  				  	  				  	    	  
 					  	  
		//************** OBVIAR ************ 	
						  	  
 					  	  
 // * Salto de línea
 {SaltoDeLinea}		{}	
 
 // * Retorno de carro
 {RetornoDeCarro}	{}
 
 // * Tabulador
 {Tabulador}		{}
 
 // * Espacio
 {Espacio}			{}				  	  
 					  
 // * Comentario
 {Comentario}		{}
 
 // * Comentario Grande
 {ComentarioGrande} {}
 
 
 
 


//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package sintactico;



//#line 2 "../../src/sintactico/sintactico.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexico.Lexico;
import ast.*;
import tipo.*;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

//#line 28 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short WRITE=258;
public final static short STRUCT=259;
public final static short AND=260;
public final static short CHAR=261;
public final static short CTE_REAL=262;
public final static short READ=263;
public final static short MAYORIGUAL=264;
public final static short MAIN=265;
public final static short IGUALDAD=266;
public final static short WHILE=267;
public final static short ELSE=268;
public final static short DISTINTO=269;
public final static short OR=270;
public final static short IDENTIFICADOR=271;
public final static short VOID=272;
public final static short INT=273;
public final static short VAR=274;
public final static short MENORIGUAL=275;
public final static short RETURN=276;
public final static short FLOAT32=277;
public final static short IF=278;
public final static short FUNC=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    4,    6,    8,    8,    8,
    8,    7,    7,    9,    9,   10,   12,   12,    5,   13,
   13,    2,   15,   15,   16,   16,   16,   16,   16,   16,
   16,   17,   18,   19,   20,   20,   21,   22,   23,   27,
   26,   26,   24,   24,   25,   25,   25,   25,   25,   25,
   25,   25,   25,   25,   25,   25,   25,   25,   25,   25,
   25,   25,   25,   25,   25,   25,   25,   25,   11,   11,
   11,   14,   14,   14,   14,   14,   28,   28,   29,
};
final static short yylen[] = {                            2,
    9,    2,    0,    1,    1,    6,    4,    1,    1,    1,
    1,    1,    0,    1,    3,    2,    2,    0,    4,    1,
    3,    2,    2,    0,    1,    1,    1,    1,    1,    1,
    1,    5,    5,    3,    9,    5,    5,    4,    5,    4,
    1,    0,    1,    3,    3,    3,    3,    3,    3,    4,
    3,    1,    2,    2,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    4,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    4,    4,    1,    2,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    0,    2,    4,    5,   20,    0,    0,
    0,    0,   72,   74,   73,    0,    0,    0,    0,    0,
    0,    0,    0,   21,   19,    0,    0,    0,    0,   14,
   18,    0,    0,   77,    0,    0,   69,   70,   71,   16,
    0,    0,    0,    0,    0,   76,   78,   75,   18,   10,
   11,    8,    9,    7,   15,    6,   17,    0,   79,    0,
   65,    0,    0,   66,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   23,   25,   26,   27,   28,   29,   30,
   31,    0,   68,    1,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   24,    0,    0,   34,   24,
   49,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   51,    0,    0,
    0,    0,    0,    0,    0,   64,   38,   50,   32,    0,
   33,   40,   37,   39,    0,    0,   24,    0,   35,
};
final static short yydgoto[] = {                          1,
    2,   43,    5,    6,    7,   21,   28,   54,   29,   30,
   73,   44,   32,   18,   58,   74,   75,   76,   77,   78,
   79,   80,   81,  117,   82,  118,   83,   33,   34,
};
final static short yysindex[] = {                         0,
    0, -270, -243, -233,    0,    0,    0,    0,  -41,    7,
    9,  -66,    0,    0,    0, -198, -210,    4,   30, -197,
  -48, -243,  -17,    0,    0, -195, -250,   38,   36,    0,
    0,  -41, -123,    0,  -83,  -42,    0,    0,    0,    0,
 -247, -197,  -43, -191,   25,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   77,    0,  -39,
    0,   47,    0,    0,   49,   85,   50,   85,   85,   85,
   85,   85,   51,    0,    0,    0,    0,    0,    0,    0,
    0,  440,    0,    0,   85,   85,   53,  478,   85,  504,
  511,    6,  -45,  537,   85,   85,   85,   85,   85,   85,
   85,   85,   85,   85,   85,   85,   85,   85,   85,   85,
 -175,  -20,  806,   14,   85,    0,   54,   58,    0,    0,
    0,  544,  -27,   -6,   -6,   -6,  -27,   -6,  626,   -6,
   -6,    6,    6,  -45,  -45,  -45,  694,    0,   43,   85,
   44,   64,  -33,   48,  -11,    0,    0,    0,    0,  806,
    0,    0,    0,    0, -162,  -15,    0,   11,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   68,
    0,    0,    0,    0,    0,    0,    0,    0,   70,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   33,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -13,    0,    0,
    0,    0,  100,    0,    0,    0,  778,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  126,    0,   72,    0,
    0,  813,  361,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   24,    0,   72,    0,   38,    0,    0,    0,
    0,    0,    1,  866,  873,  912,   60,  939,    0,  968,
 1052,  820,  859,  387,  414,  452,    0,    0,    0,    0,
    0,    0,    0,  785,    0,    0,    0,    0,    0,   26,
    0,    0,    0,    0,   55,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   66,    0,    0,   76,    0,    0,    0,    0,   74,
   96,    0,  123,   37, -103,    0,    0,    0,    0,    0,
    0,    0,    0,  -80, 1150,   12,    0,    0,   95,
};
final static int YYTABLESIZE=1327;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         71,
  111,   46,   17,    3,  112,  114,   72,   16,    4,  109,
   37,   70,  143,   50,  108,  105,  145,  106,  111,  107,
  139,   71,   38,  140,   51,   52,   39,    8,   72,   53,
  109,   10,  103,   70,  104,  108,  105,   11,  106,  111,
  107,   62,  109,   71,   62,  110,   19,  108,   20,   16,
   72,  111,  107,  158,  141,   70,   22,  140,   23,   62,
   24,   62,   25,  110,   43,   24,   44,   43,   45,   44,
   26,   48,   24,   27,   31,   35,   36,   24,   41,   42,
   49,   56,    3,   59,  110,   84,   85,   36,   86,   89,
   95,  153,  115,   62,   36,  138,  110,  140,  144,   36,
   63,  149,  151,   63,  152,  156,  154,  157,   13,   71,
   12,   22,   42,  155,   60,   55,   72,   71,   63,   57,
   63,   70,   40,   62,   72,    9,  142,   47,    0,   70,
    0,    0,    0,    0,    0,  159,   67,    0,    0,   69,
   67,   67,   67,   67,   67,   67,   67,    8,    0,    0,
    0,    0,   63,    0,    0,    0,    0,   24,   67,   67,
   67,   67,   52,    0,    0,    0,   52,   52,   52,   52,
   52,   52,   52,    0,    0,   12,    0,   13,    0,   36,
    0,    0,   63,    0,   52,   52,   52,   52,    0,   14,
   67,    0,   67,   15,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   52,   12,   52,   13,
    0,    0,   67,   61,   62,    0,    0,   63,   64,   65,
    0,   14,    0,   66,    0,   15,   97,   67,   98,   38,
    0,   99,   68,   39,   69,   61,   62,  101,   52,   63,
   64,   65,    0,    0,    0,   66,    0,    0,    0,   67,
   62,   38,    0,    0,   68,   39,   69,   61,   62,    0,
   62,   63,   64,   65,    0,    0,    0,   66,    0,    0,
    0,   67,    0,   38,    0,    0,   68,   39,   69,   24,
   24,    0,    0,   24,   24,   24,    0,    0,    0,   24,
    0,    0,    0,   24,    0,   24,    0,    0,   24,   24,
   24,   36,   36,    0,    0,   36,   36,   36,    0,   63,
    0,   36,    0,    0,    0,   36,    0,   36,    0,   63,
   36,   36,   36,   61,   62,    0,    0,   63,   64,   65,
    0,   61,    0,   66,    0,   63,   64,   67,    0,   38,
    0,    0,   68,   39,   69,   87,    0,   38,    0,   67,
    0,   39,    0,   67,    0,   67,    0,    0,   67,   67,
    0,    0,    0,    0,   67,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   52,    0,    0,    0,   52,
    0,   52,    0,    0,   52,   52,    0,   54,    0,    0,
   52,   54,   54,   54,   54,   54,    0,   54,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   54,
   54,   54,   54,   48,    0,    0,    0,   48,   48,   48,
   48,   48,    0,   48,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   48,   48,   48,   48,    0,
   47,    0,    0,   54,   47,   47,   47,   47,   47,    0,
   47,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   47,   47,   47,   47,  109,    0,    0,   48,
    0,  108,  105,   54,  106,  111,  107,    0,   55,    0,
    0,    0,   55,   55,   55,   55,   55,    0,   55,  103,
  102,  104,    0,    0,    0,    0,   47,    0,    0,   48,
   55,   55,   55,   55,  109,    0,    0,    0,    0,  108,
  105,    0,  106,  111,  107,    0,    0,    0,    0,    0,
  110,    0,    0,    0,    0,    0,   47,  103,    0,  104,
  109,    0,    0,    0,   55,  108,  105,  109,  106,  111,
  107,    0,  108,  105,    0,  106,  111,  107,    0,    0,
    0,    0,  119,  103,    0,  104,    0,    0,  110,    0,
  103,    0,  104,  109,   55,    0,    0,  121,  108,  105,
  109,  106,  111,  107,  146,  108,  105,    0,  106,  111,
  107,    0,    0,    0,  110,    0,  103,    0,  104,    0,
  116,  110,    0,  103,    0,  104,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   54,    0,    0,    0,   54,    0,   54,  110,    0,   54,
   54,    0,    0,  120,  110,   54,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   48,    0,    0,    0,
   48,    0,   48,    0,    0,   48,   48,    0,    0,    0,
    0,   48,  109,    0,    0,    0,    0,  108,  105,    0,
  106,  111,  107,   47,    0,    0,    0,   47,    0,   47,
    0,    0,   47,   47,  147,  103,    0,  104,   47,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   96,
    0,    0,    0,   97,    0,   98,    0,    0,   99,  100,
    0,   55,    0,    0,  101,   55,  110,   55,    0,    0,
   55,   55,    0,    0,    0,    0,   55,    0,    0,    0,
  109,    0,    0,    0,    0,  108,  105,   96,  106,  111,
  107,   97,    0,   98,    0,    0,   99,  100,    0,    0,
    0,    0,  101,  103,    0,  104,    0,    0,    0,    0,
    0,    0,    0,   96,    0,    0,    0,   97,    0,   98,
   96,    0,   99,  100,   97,    0,   98,    0,  101,   99,
  100,    0,    0,    0,  110,  101,  148,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   96,    0,    0,    0,
   97,    0,   98,   96,    0,   99,  100,   97,    0,   98,
    0,  101,   99,  100,   52,    0,    0,    0,  101,   52,
   52,   40,   52,   52,   52,    0,   40,   40,    0,   40,
   40,   40,    0,    0,    0,    0,    0,   52,   52,   52,
    0,    0,  109,    0,   40,   40,   40,  108,  105,    0,
  106,  111,  107,   53,    0,   53,   53,   53,    0,    0,
   45,    0,   45,   45,   45,  103,    0,  104,   52,    0,
    0,   53,   53,   53,   53,   40,    0,    0,   45,   45,
   45,   45,    0,    0,    0,   96,    0,    0,    0,   97,
    0,   98,    0,    0,   99,  100,  110,    0,    0,   46,
  101,   46,   46,   46,    0,   53,   58,    0,    0,   58,
    0,    0,   45,   61,    0,    0,   61,   46,   46,   46,
   46,    0,    0,    0,   58,   58,   58,   58,    0,    0,
    0,   61,   61,   61,   61,   53,    0,    0,    0,    0,
    0,    0,   45,    0,    0,    0,    0,    0,    0,    0,
    0,   46,   60,   96,    0,   60,    0,   97,   58,   98,
    0,    0,   99,  100,    0,   61,    0,    0,  101,    0,
   60,   60,   60,   60,    0,    0,    0,    0,    0,   59,
    0,   46,   59,    0,    0,    0,    0,    0,   58,    0,
    0,    0,    0,    0,    0,   61,    0,   59,   59,   59,
   59,    0,    0,    0,   60,    0,    0,    0,   57,    0,
    0,   57,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   57,   57,   57,   57,
    0,   59,    0,    0,   60,    0,    0,   52,    0,    0,
    0,   52,    0,   52,   40,    0,   52,   52,   40,    0,
   40,    0,   52,   40,   40,    0,    0,    0,    0,   40,
   57,   59,    0,    0,    0,   96,    0,    0,    0,   97,
    0,   98,   53,    0,   99,  100,   53,    0,   53,   45,
  101,   53,   53,   45,    0,   45,    0,   53,   45,   45,
   57,    0,   56,    0,   45,   56,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   56,   56,   56,   56,    0,    0,    0,    0,   46,    0,
    0,    0,   46,    0,   46,   58,    0,   46,   46,   58,
    0,   58,   61,   46,   58,   58,   61,    0,   61,    0,
   58,   61,   61,    0,   56,    0,    0,   61,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   60,    0,    0,   56,   60,    0,   60,    0,    0,
   60,   60,    0,    0,    0,    0,   60,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   59,    0,
    0,    0,   59,    0,   59,    0,    0,   59,   59,    0,
    0,    0,    0,   59,    0,   88,    0,   90,   91,   92,
   93,   94,    0,    0,    0,    0,    0,   57,    0,    0,
    0,   57,    0,   57,  113,  113,   57,   57,  113,    0,
    0,    0,   57,    0,  122,  123,  124,  125,  126,  127,
  128,  129,  130,  131,  132,  133,  134,  135,  136,  137,
    0,    0,    0,    0,  113,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  150,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   56,    0,    0,    0,   56,    0,   56,    0,    0,
   56,   56,    0,    0,    0,    0,   56,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   46,  125,   44,  274,   85,   86,   40,   91,  279,   37,
  261,   45,  116,  261,   42,   43,  120,   45,   46,   47,
   41,   33,  273,   44,  272,  273,  277,  271,   40,  277,
   37,  265,   60,   45,   62,   42,   43,  271,   45,   46,
   47,   41,   37,   33,   44,   91,   40,   42,   40,   91,
   40,   46,   47,  157,   41,   45,  123,   44,  257,   59,
  271,   61,   59,   91,   41,   33,   41,   44,   32,   44,
   41,   35,   40,  271,  123,   93,  272,   45,   41,   44,
  123,  125,  274,   59,   91,  125,   40,   33,   40,   40,
   40,  125,   40,   93,   40,  271,   91,   44,   41,   45,
   41,   59,   59,   44,   41,  268,   59,  123,   41,   33,
   41,  125,   41,  125,   49,   42,   40,   33,   59,   44,
   61,   45,   27,  123,   40,    3,  115,   33,   -1,   45,
   -1,   -1,   -1,   -1,   -1,  125,   37,   -1,   -1,   40,
   41,   42,   43,   44,   45,   46,   47,  271,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,  125,   59,   60,
   61,   62,   37,   -1,   -1,   -1,   41,   42,   43,   44,
   45,   46,   47,   -1,   -1,  259,   -1,  261,   -1,  125,
   -1,   -1,  123,   -1,   59,   60,   61,   62,   -1,  273,
   91,   -1,   93,  277,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,  259,   93,  261,
   -1,   -1,  123,  257,  258,   -1,   -1,  261,  262,  263,
   -1,  273,   -1,  267,   -1,  277,  264,  271,  266,  273,
   -1,  269,  276,  277,  278,  257,  258,  275,  123,  261,
  262,  263,   -1,   -1,   -1,  267,   -1,   -1,   -1,  271,
  260,  273,   -1,   -1,  276,  277,  278,  257,  258,   -1,
  270,  261,  262,  263,   -1,   -1,   -1,  267,   -1,   -1,
   -1,  271,   -1,  273,   -1,   -1,  276,  277,  278,  257,
  258,   -1,   -1,  261,  262,  263,   -1,   -1,   -1,  267,
   -1,   -1,   -1,  271,   -1,  273,   -1,   -1,  276,  277,
  278,  257,  258,   -1,   -1,  261,  262,  263,   -1,  260,
   -1,  267,   -1,   -1,   -1,  271,   -1,  273,   -1,  270,
  276,  277,  278,  257,  258,   -1,   -1,  261,  262,  263,
   -1,  257,   -1,  267,   -1,  261,  262,  271,   -1,  273,
   -1,   -1,  276,  277,  278,  271,   -1,  273,   -1,  260,
   -1,  277,   -1,  264,   -1,  266,   -1,   -1,  269,  270,
   -1,   -1,   -1,   -1,  275,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  260,   -1,   -1,   -1,  264,
   -1,  266,   -1,   -1,  269,  270,   -1,   37,   -1,   -1,
  275,   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   60,   61,   62,   37,   -1,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,
   37,   -1,   -1,   93,   41,   42,   43,   44,   45,   -1,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   37,   -1,   -1,   93,
   -1,   42,   43,  123,   45,   46,   47,   -1,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   60,
   61,   62,   -1,   -1,   -1,   -1,   93,   -1,   -1,  123,
   59,   60,   61,   62,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   91,   -1,   -1,   -1,   -1,   -1,  123,   60,   -1,   62,
   37,   -1,   -1,   -1,   93,   42,   43,   37,   45,   46,
   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   91,   -1,
   60,   -1,   62,   37,  123,   -1,   -1,   41,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   91,   -1,   60,   -1,   62,   -1,
  123,   91,   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  260,   -1,   -1,   -1,  264,   -1,  266,   91,   -1,  269,
  270,   -1,   -1,  123,   91,  275,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  260,   -1,   -1,   -1,
  264,   -1,  266,   -1,   -1,  269,  270,   -1,   -1,   -1,
   -1,  275,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,  260,   -1,   -1,   -1,  264,   -1,  266,
   -1,   -1,  269,  270,   59,   60,   -1,   62,  275,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  260,
   -1,   -1,   -1,  264,   -1,  266,   -1,   -1,  269,  270,
   -1,  260,   -1,   -1,  275,  264,   91,  266,   -1,   -1,
  269,  270,   -1,   -1,   -1,   -1,  275,   -1,   -1,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,  260,   45,   46,
   47,  264,   -1,  266,   -1,   -1,  269,  270,   -1,   -1,
   -1,   -1,  275,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  260,   -1,   -1,   -1,  264,   -1,  266,
  260,   -1,  269,  270,  264,   -1,  266,   -1,  275,  269,
  270,   -1,   -1,   -1,   91,  275,   93,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  260,   -1,   -1,   -1,
  264,   -1,  266,  260,   -1,  269,  270,  264,   -1,  266,
   -1,  275,  269,  270,   37,   -1,   -1,   -1,  275,   42,
   43,   37,   45,   46,   47,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   60,   61,   62,
   -1,   -1,   37,   -1,   60,   61,   62,   42,   43,   -1,
   45,   46,   47,   41,   -1,   43,   44,   45,   -1,   -1,
   41,   -1,   43,   44,   45,   60,   -1,   62,   91,   -1,
   -1,   59,   60,   61,   62,   91,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,  260,   -1,   -1,   -1,  264,
   -1,  266,   -1,   -1,  269,  270,   91,   -1,   -1,   41,
  275,   43,   44,   45,   -1,   93,   41,   -1,   -1,   44,
   -1,   -1,   93,   41,   -1,   -1,   44,   59,   60,   61,
   62,   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,
   -1,   59,   60,   61,   62,  123,   -1,   -1,   -1,   -1,
   -1,   -1,  123,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   41,  260,   -1,   44,   -1,  264,   93,  266,
   -1,   -1,  269,  270,   -1,   93,   -1,   -1,  275,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   41,
   -1,  123,   44,   -1,   -1,   -1,   -1,   -1,  123,   -1,
   -1,   -1,   -1,   -1,   -1,  123,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   93,   -1,   -1,   -1,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   61,   62,
   -1,   93,   -1,   -1,  123,   -1,   -1,  260,   -1,   -1,
   -1,  264,   -1,  266,  260,   -1,  269,  270,  264,   -1,
  266,   -1,  275,  269,  270,   -1,   -1,   -1,   -1,  275,
   93,  123,   -1,   -1,   -1,  260,   -1,   -1,   -1,  264,
   -1,  266,  260,   -1,  269,  270,  264,   -1,  266,  260,
  275,  269,  270,  264,   -1,  266,   -1,  275,  269,  270,
  123,   -1,   41,   -1,  275,   44,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,  260,   -1,
   -1,   -1,  264,   -1,  266,  260,   -1,  269,  270,  264,
   -1,  266,  260,  275,  269,  270,  264,   -1,  266,   -1,
  275,  269,  270,   -1,   93,   -1,   -1,  275,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  260,   -1,   -1,  123,  264,   -1,  266,   -1,   -1,
  269,  270,   -1,   -1,   -1,   -1,  275,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  260,   -1,
   -1,   -1,  264,   -1,  266,   -1,   -1,  269,  270,   -1,
   -1,   -1,   -1,  275,   -1,   66,   -1,   68,   69,   70,
   71,   72,   -1,   -1,   -1,   -1,   -1,  260,   -1,   -1,
   -1,  264,   -1,  266,   85,   86,  269,  270,   89,   -1,
   -1,   -1,  275,   -1,   95,   96,   97,   98,   99,  100,
  101,  102,  103,  104,  105,  106,  107,  108,  109,  110,
   -1,   -1,   -1,   -1,  115,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  140,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  260,   -1,   -1,   -1,  264,   -1,  266,   -1,   -1,
  269,  270,   -1,   -1,   -1,   -1,  275,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CTE_ENTERA","WRITE","STRUCT",
"AND","CHAR","CTE_REAL","READ","MAYORIGUAL","MAIN","IGUALDAD","WHILE","ELSE",
"DISTINTO","OR","IDENTIFICADOR","VOID","INT","VAR","MENORIGUAL","RETURN",
"FLOAT32","IF","FUNC",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones FUNC MAIN '(' ')' VOID '{' sentenciasCuerpo '}'",
"definiciones : definiciones definicion",
"definiciones :",
"definicion : defFuncion",
"definicion : defVariable",
"defFuncion : FUNC IDENTIFICADOR tipoFuncion '{' sentenciasCuerpo '}'",
"tipoFuncion : '(' parametrosFuncion ')' tipoRetorno",
"tipoRetorno : INT",
"tipoRetorno : FLOAT32",
"tipoRetorno : CHAR",
"tipoRetorno : VOID",
"parametrosFuncion : parametros",
"parametrosFuncion :",
"parametros : parametro",
"parametros : parametros ',' parametro",
"parametro : IDENTIFICADOR tipoSimples",
"defVariablesLocales : defVariablesLocales defVariable",
"defVariablesLocales :",
"defVariable : VAR identificadores tipo ';'",
"identificadores : IDENTIFICADOR",
"identificadores : identificadores ',' IDENTIFICADOR",
"sentenciasCuerpo : defVariablesLocales sentencias",
"sentencias : sentencias sentencia",
"sentencias :",
"sentencia : write",
"sentencia : read",
"sentencia : return",
"sentencia : if",
"sentencia : while",
"sentencia : asignacion",
"sentencia : procedimiento",
"write : WRITE '(' expresiones ')' ';'",
"read : READ '(' expresiones ')' ';'",
"return : RETURN expresion ';'",
"if : IF expresion '{' sentencias '}' ELSE '{' sentencias '}'",
"if : IF expresion '{' sentencias '}'",
"while : WHILE expresion '{' sentencias '}'",
"asignacion : expresion '=' expresion ';'",
"procedimiento : IDENTIFICADOR '(' expresionesInvocacion ')' ';'",
"funcionExpresion : IDENTIFICADOR '(' expresionesInvocacion ')'",
"expresionesInvocacion : expresiones",
"expresionesInvocacion :",
"expresiones : expresion",
"expresiones : expresiones ',' expresion",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : '(' expresion ')'",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '.' IDENTIFICADOR",
"expresion : IDENTIFICADOR",
"expresion : '-' expresion",
"expresion : '!' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion '>' expresion",
"expresion : expresion '<' expresion",
"expresion : expresion MAYORIGUAL expresion",
"expresion : expresion MENORIGUAL expresion",
"expresion : expresion DISTINTO expresion",
"expresion : expresion IGUALDAD expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"expresion : tipoSimples '(' expresion ')'",
"expresion : CTE_ENTERA",
"expresion : CTE_REAL",
"expresion : CHAR",
"expresion : funcionExpresion",
"tipoSimples : CHAR",
"tipoSimples : INT",
"tipoSimples : FLOAT32",
"tipo : CHAR",
"tipo : FLOAT32",
"tipo : INT",
"tipo : '[' CTE_ENTERA ']' tipo",
"tipo : STRUCT '{' campos '}'",
"campos : campo",
"campos : campos campo",
"campo : identificadores tipo ';'",
};

//#line 222 "../../src/sintactico/sintactico.y"

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
//#line 634 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 52 "../../src/sintactico/sintactico.y"
{ raiz = new Programa(lexico.getLinea(), lexico.getColumna(), (ArrayList<Definicion>)val_peek(8), (ArrayList<Sentencia>)val_peek(1)) ; }
break;
case 2:
//#line 54 "../../src/sintactico/sintactico.y"
{ List<Definicion> lista = (List<Definicion>)val_peek(1);
																for(int i=0; i<((List<Definicion>)val_peek(0)).size(); i++){
																	lista.add(((List<Definicion>)val_peek(0)).get(i));
															   } 
															   yyval = lista ;  }
break;
case 3:
//#line 59 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Definicion>() ; }
break;
case 4:
//#line 62 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 5:
//#line 63 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 6:
//#line 66 "../../src/sintactico/sintactico.y"
{ ArrayList<DefFuncion> lista = new ArrayList<DefFuncion>();
 																		lista.add(new DefFuncion(lexico.getLinea(), lexico.getColumna(), (String)val_peek(4), (TipoFuncion)val_peek(3), (ArrayList<Sentencia>)val_peek(1)));
 																		yyval = lista ; }
break;
case 7:
//#line 70 "../../src/sintactico/sintactico.y"
{ yyval = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (ArrayList<DefVariable>)val_peek(2), (Tipo)val_peek(0)) ; }
break;
case 8:
//#line 73 "../../src/sintactico/sintactico.y"
{ yyval = new TipoEntero(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 9:
//#line 74 "../../src/sintactico/sintactico.y"
{ yyval = new TipoFloat32(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 10:
//#line 75 "../../src/sintactico/sintactico.y"
{ yyval = new TipoChar(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 11:
//#line 76 "../../src/sintactico/sintactico.y"
{ yyval = new TipoVoid(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 12:
//#line 79 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 13:
//#line 80 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>() ; }
break;
case 14:
//#line 83 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>() ; ((ArrayList<DefVariable>)yyval).add((DefVariable)val_peek(0)) ; }
break;
case 15:
//#line 84 "../../src/sintactico/sintactico.y"
{ yyval = (ArrayList<DefVariable>)val_peek(2) ; ((ArrayList)yyval).add((DefVariable)val_peek(0)) ; }
break;
case 16:
//#line 87 "../../src/sintactico/sintactico.y"
{ yyval = new DefVariable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(1), (Tipo)val_peek(0)) ; }
break;
case 17:
//#line 89 "../../src/sintactico/sintactico.y"
{ yyval = (ArrayList<DefVariable>)val_peek(1) ; for(int i=0; i<((ArrayList<DefVariable>)val_peek(0)).size(); i++) {((ArrayList<DefVariable>)yyval).add(((ArrayList<DefVariable>)val_peek(0)).get(i)) ;}}
break;
case 18:
//#line 90 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>() ; }
break;
case 19:
//#line 93 "../../src/sintactico/sintactico.y"
{ ArrayList<DefVariable> lista = new ArrayList<DefVariable>() ; 
																ArrayList<String> aux = new ArrayList<String>();
			;													for(int i=0; i<((ArrayList<String>)val_peek(2)).size(); i++) {
																	if(aux.contains(((ArrayList<String>)val_peek(2)).get(i)))
																	{
																		new TipoError(lexico.getLinea(), lexico.getColumna(), "Nombre de variable repetida");
																	} 
																	aux.add(((ArrayList<String>)val_peek(2)).get(i));
																	lista.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), ((ArrayList<String>)val_peek(2)).get(i), (Tipo)val_peek(1)));
																}
																yyval = lista ; }
break;
case 20:
//#line 106 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<String>() ; ((ArrayList<String>)yyval).add((String)val_peek(0)) ; }
break;
case 21:
//#line 107 "../../src/sintactico/sintactico.y"
{ yyval = (ArrayList<String>)val_peek(2) ; ((ArrayList<String>)yyval).add((String)val_peek(0)) ; }
break;
case 22:
//#line 110 "../../src/sintactico/sintactico.y"
{ ArrayList<Sentencia> lista = new ArrayList<Sentencia>();
															  lista.addAll((ArrayList<DefVariable>)val_peek(1));
															  lista.addAll((ArrayList<Sentencia>)val_peek(0)) ;
															  yyval = lista ; }
break;
case 23:
//#line 115 "../../src/sintactico/sintactico.y"
{ yyval = (ArrayList<Sentencia>)val_peek(1) ; ((ArrayList<Sentencia>)yyval).add((Sentencia)val_peek(0)) ; }
break;
case 24:
//#line 116 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>() ; }
break;
case 25:
//#line 119 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 26:
//#line 120 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 27:
//#line 121 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 28:
//#line 122 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 29:
//#line 123 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 30:
//#line 124 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 31:
//#line 125 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 32:
//#line 128 "../../src/sintactico/sintactico.y"
{ yyval = new Escritura(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)val_peek(2)) ; }
break;
case 33:
//#line 131 "../../src/sintactico/sintactico.y"
{ yyval = new Lectura(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)val_peek(2)) ; }
break;
case 34:
//#line 134 "../../src/sintactico/sintactico.y"
{ yyval = new Return(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(1)) ; }
break;
case 35:
//#line 137 "../../src/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(7), (ArrayList<Sentencia>)val_peek(5), (ArrayList<Sentencia>)val_peek(1)) ; }
break;
case 36:
//#line 138 "../../src/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), (ArrayList<Sentencia>)val_peek(1), null) ; }
break;
case 37:
//#line 141 "../../src/sintactico/sintactico.y"
{ yyval = new While(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), (ArrayList<Sentencia>)val_peek(1)) ; }
break;
case 38:
//#line 144 "../../src/sintactico/sintactico.y"
{ yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), (Expresion)val_peek(1)) ; }
break;
case 39:
//#line 147 "../../src/sintactico/sintactico.y"
{ yyval = new Procedimiento(lexico.getLinea(), lexico.getColumna(), new Variable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(4)), (ArrayList<Expresion>)val_peek(2)) ; }
break;
case 40:
//#line 149 "../../src/sintactico/sintactico.y"
{ yyval = new InvocacionFuncion(lexico.getLinea(), lexico.getColumna(), new Variable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(3)), (ArrayList<Expresion>)val_peek(1)) ; }
break;
case 41:
//#line 151 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 42:
//#line 152 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>() ; }
break;
case 43:
//#line 155 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>() ; ((ArrayList<Expresion>)yyval).add((Expresion)val_peek(0)) ; }
break;
case 44:
//#line 156 "../../src/sintactico/sintactico.y"
{ yyval = (ArrayList<Expresion>)val_peek(2) ; ((ArrayList<Expresion>)yyval).add((Expresion)val_peek(0)) ; }
break;
case 45:
//#line 159 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 46:
//#line 160 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 47:
//#line 161 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 48:
//#line 162 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 49:
//#line 163 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1) ; }
break;
case 50:
//#line 164 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoArray(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), (Expresion)val_peek(1)) ; }
break;
case 51:
//#line 165 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoCampo(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(0)) ; }
break;
case 52:
//#line 166 "../../src/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0)) ; }
break;
case 53:
//#line 167 "../../src/sintactico/sintactico.y"
{ yyval = new MenosUnario(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(0)) ; }
break;
case 54:
//#line 168 "../../src/sintactico/sintactico.y"
{ yyval = new Negacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(0)) ; }
break;
case 55:
//#line 169 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 56:
//#line 170 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 57:
//#line 171 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 58:
//#line 172 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 59:
//#line 173 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 60:
//#line 174 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 61:
//#line 175 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 62:
//#line 176 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 63:
//#line 177 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(1), (Expresion)val_peek(0)) ; }
break;
case 64:
//#line 178 "../../src/sintactico/sintactico.y"
{ yyval = new Cast(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(3), (Expresion)val_peek(1)) ; }
break;
case 65:
//#line 179 "../../src/sintactico/sintactico.y"
{ yyval = new ConstanteEntera(lexico.getLinea(), lexico.getColumna(), (int)val_peek(0)) ; }
break;
case 66:
//#line 180 "../../src/sintactico/sintactico.y"
{ yyval = new ConstanteReal(lexico.getLinea(), lexico.getColumna(), (float)val_peek(0)) ; }
break;
case 67:
//#line 181 "../../src/sintactico/sintactico.y"
{ yyval = new Char(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0)) ; }
break;
case 68:
//#line 182 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0) ; }
break;
case 69:
//#line 185 "../../src/sintactico/sintactico.y"
{ yyval = new TipoChar(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 70:
//#line 186 "../../src/sintactico/sintactico.y"
{ yyval = new TipoEntero(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 71:
//#line 187 "../../src/sintactico/sintactico.y"
{ yyval = new TipoFloat32(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 72:
//#line 190 "../../src/sintactico/sintactico.y"
{ yyval = new TipoChar(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 73:
//#line 191 "../../src/sintactico/sintactico.y"
{ yyval = new TipoFloat32(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 74:
//#line 192 "../../src/sintactico/sintactico.y"
{ yyval = new TipoEntero(lexico.getLinea(), lexico.getColumna()) ; }
break;
case 75:
//#line 193 "../../src/sintactico/sintactico.y"
{ yyval = new TipoArray(lexico.getLinea(), lexico.getColumna(), (int)val_peek(2), (Tipo)val_peek(0)) ; }
break;
case 76:
//#line 194 "../../src/sintactico/sintactico.y"
{ yyval = new TipoStruct(lexico.getLinea(), lexico.getColumna(), (ArrayList<CampoStruct>)val_peek(1)) ; 
																ArrayList<String> aux = new ArrayList<String>();
																for(int i=0; i<((ArrayList<CampoStruct>)val_peek(1)).size(); i++){
															 	 	if(aux.contains(((ArrayList<CampoStruct>)val_peek(1)).get(i).getNombre()))
																	{
																		new TipoError(lexico.getLinea(), lexico.getColumna(),  "El campo --" + ((ArrayList<CampoStruct>)val_peek(1)).get(i).getNombre() + "-- está repetido");
																	} 
																	aux.add(((ArrayList<CampoStruct>)val_peek(1)).get(i).getNombre());
																}
																
																}
break;
case 77:
//#line 207 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<CampoStruct>()	; ((ArrayList<CampoStruct>)yyval).addAll((ArrayList<CampoStruct>)val_peek(0)) ; }
break;
case 78:
//#line 208 "../../src/sintactico/sintactico.y"
{ ((ArrayList<CampoStruct>)val_peek(1)).addAll((ArrayList<CampoStruct>)val_peek(0)) ; 
	  															yyval = (ArrayList<CampoStruct>)val_peek(1) ; }
break;
case 79:
//#line 212 "../../src/sintactico/sintactico.y"
{ ArrayList<CampoStruct> lista = new ArrayList<CampoStruct>() ;
															 	 for(int i=0; i<((ArrayList<String>)val_peek(2)).size(); i++){
																	lista.add(new CampoStruct(lexico.getLinea(), lexico.getColumna(), ((ArrayList<String>)val_peek(2)).get(i), (Tipo)val_peek(1)));
															 	 }	
															 	 yyval = lista ;
															}
break;
//#line 1134 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################

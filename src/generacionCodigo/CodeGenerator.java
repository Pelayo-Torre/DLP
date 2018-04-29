package generacionCodigo;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerator {

	FileWriter out;

	// ****************INSTRUCCIONES PUSH*********************//

	public CodeGenerator(FileWriter fw) {
		this.out = fw;
	}

	public void pushb(int c) throws IOException {
		out.write("\tpushb	" + c + "\n");
	}

	public void pushi(int c) throws IOException {
		out.write("\tpush	" + c + "\n");
	}

	public void pushf(float c) throws IOException {
		out.write("\tpushf	" + c + "\n");
	}

	public void pusha(String c) throws IOException {
		out.write("\tpusha	" + c + "\n");
	}

	public void push(int c) throws IOException {
		out.write("\tpush	" + c + "\n");
	}

	// ****************INSTRUCCIONES LOAD Y STORE**********************//

	public void load(char c) throws IOException {
		out.write("\tload" + c + "\n");
	}

	public void store(char c) throws IOException {
		out.write("\tstore" + c + "\n");
	}

	// *********************INSTRUCCIONES POP Y DUP********************//

	public void pop(char c) throws IOException {
		out.write("\tpop" + c + "\n");
	}

	public void dup(char c) throws IOException {
		out.write("\tdup" + c + "\n");
	}

	// ********************INSTRUCCIONES ARITMÉTICAS*****************//

	public void aritmetica(String operador, char suffix) throws IOException {
		if (operador.equals("+"))
			out.write("\tadd" + suffix + "\n");
		if (operador.equals("-"))
			out.write("\tsub" + suffix + "\n");
		if (operador.equals("*"))
			out.write("\tmul" + suffix + "\n");
		if (operador.equals("/"))
			out.write("\tdiv" + suffix + "\n");
		if (operador.equals("%"))
			out.write("\tmod" + suffix + "\n");
	}

	public void add(char suffix) throws IOException {
		out.write("\tadd" + suffix + "\n");
	}

	public void sub(char suffix) throws IOException {
		out.write("\tsub" + suffix + "\n");
	}

	public void mul(char suffix) throws IOException {
		out.write("\tmul" + suffix + "\n");
	}

	public void div(char suffix) throws IOException {
		out.write("\tdiv" + suffix + "\n");
	}

	// ********************INSTRUCCIONES DE COPARACIÓN****************//

	public void comparacion(String operador, char suffix) throws IOException {
		if (operador.equals(">"))
			out.write("\tgt" + suffix + "\n");
		if (operador.equals("<"))
			out.write("\tlt" + suffix + "\n");
		if (operador.equals(">="))
			out.write("\tge" + suffix + "\n");
		if (operador.equals("<="))
			out.write("\tle" + suffix + "\n");
		if (operador.equals("=="))
			out.write("\teq" + suffix + "\n");
		if (operador.equals("!="))
			out.write("\tne" + suffix + "\n");
	}

	// **********************INSTRUCCIONES LÓGICAS*************************//

	public void logica(String operador) throws IOException {
		if (operador.equals("&&"))
			out.write("\tand" + "\n");
		if (operador.equals("||"))
			out.write("\tor" + "\n");
		if (operador.equals("!"))
			out.write("\tnot" + "\n");
	}

	// **********************INSTRUCCIONES INPUY Y OUTPUT******************//

	public void out(char c) throws IOException {
		out.write("\tout" + c + "\n");
	}

	public void in(char c) throws IOException {
		out.write("\tin" + c + "\n");
	}

	// *********************INSTRUCCIONES DE CONVERSIÓN*******************//

	public void cast(char tipoIzq, char tipoDcha) throws IOException {
		out.write(tipoIzq + "2" + tipoDcha + "\n");
	}

	// ******************INSTRUCCIONES DE SALTO***************************//

	public void id() throws IOException {
		out.write("<id>:" + "\n");
	}

	public void jmp(String salto) throws IOException {
		out.write("\tjmp <" + salto + ">" + "\n");
	}

	public void jz(String salto) throws IOException {
		out.write("\tjz <" + salto + ">" + "\n");
	}

	public void jnz(String salto) throws IOException {
		out.write("\tjnz <" + salto + ">" + "\n");
	}

	// **********************FUNCIONES**************************************//

	public void call(String func) throws IOException {
		out.write("call <" + func + ">" + "\n");
	}

	public void enter(int cte) throws IOException {
		out.write("\tenter " + cte + "\n");
	}

	public void ret(int cte1, int cte2, int cte3) throws IOException {
		out.write("\tret " + cte1 + ", " + cte2 + ", " + cte3 + "\n");
	}

	// *******************FINALIZACIÓN DEL PROGRAMA************************//

	public void halt() throws IOException {
		out.write("halt" + "\n");
	}

	// ****************INFORMACIÓN DE DEBUG******************************//

	public void source(String cte) throws IOException {
		out.write("#source " + cte + "\n");
	}

	public void line(int cte) throws IOException {
		out.write("#line\t" + cte + "\n");
	}

	// *********************COMENTARIOS***************************************//
	public void comentarioTabulado(String comentario) throws IOException {
		out.write("\t'  " + comentario + "\n");
	}
	
	public void comentario(String comentario) throws IOException {
		out.write("'  " + comentario + "\n");
	}
	
	public void saltoLinea() throws IOException {
		out.write("\n");
	}

}

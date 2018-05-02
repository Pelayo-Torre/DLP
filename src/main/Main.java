package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ast.NodoAST;
import generacionCodigo.AddresCodeGeneratorVisitor;
import generacionCodigo.CodeGenerator;
import generacionCodigo.ExecuteCodeGeneratorVisitor;
import generacionCodigo.ValueCodeGeneratorVisitor;
import generacionCodigo.VisitorOffset;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import lexico.Lexico;
import sintactico.Parser;
import visitor.Visitor;
import manejadorerrores.ME;
import semantico.VisitorComprobacionTipos;
import semantico.VisitorIdentificacion;
import semantico.VisitorLValue;

public class Main {
	public static void main(String args[]) throws IOException {
		if (args.length < 1) {
			System.err.println("Necesito el archivo de entrada.");
			return;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(args[0]);
		} catch (IOException io) {
			System.err.println("El archivo " + args[0] + " no se ha podido abrir.");
			return;
		}

		// * Creamos léxico y sintáctico
		Lexico lexico = new Lexico(fr);
		Parser parser = new Parser(lexico);
		// * "Parseamos"
		parser.run();

		// Visitor v = new VisitorPorDefecto();
		Visitor videntificacion = new VisitorIdentificacion();
		Visitor vlvalue = new VisitorLValue();
		Visitor vcomprobacionTipos = new VisitorComprobacionTipos();
		Visitor voffset = new VisitorOffset();

		FileWriter fw = new FileWriter("salida.txt");
		CodeGenerator cg = new CodeGenerator(fw);
		cg.source("programa");
		cg.saltoLinea();

		AddresCodeGeneratorVisitor addresCodeGenerator = new AddresCodeGeneratorVisitor(cg);
		ValueCodeGeneratorVisitor valueCodeGenerator = new ValueCodeGeneratorVisitor(cg);
		
		addresCodeGenerator.setValueCodeGenerator(valueCodeGenerator);
		valueCodeGenerator.setAddresCodeGenerator(addresCodeGenerator);
		
		ExecuteCodeGeneratorVisitor executeCodeGenerator = new ExecuteCodeGeneratorVisitor(cg, valueCodeGenerator,
				addresCodeGenerator);

		NodoAST programa = parser.getAST();
		programa.accept(videntificacion, null);
		programa.accept(vlvalue, null);
		programa.accept(vcomprobacionTipos, null);

		// * Comprobamos si hubo errores
		if (ME.getME().huboErrores()) {
			// * Mostramos los errores
			ME.getME().mostrarErrores(System.err);
		} else {
			// Calculamos direcciones de memoria
			programa.accept(voffset, null);
			programa.accept(executeCodeGenerator, null);
			fw.close();
			// * Mostramos el AST
			IntrospectorModel modelo = new IntrospectorModel("Programa", parser.getAST());
			new IntrospectorTree("Introspector", modelo);
			System.out.println("Introspector lanzado con éxito");
		}
	}

}
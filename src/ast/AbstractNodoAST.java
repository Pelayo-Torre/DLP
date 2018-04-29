package ast;

public abstract class AbstractNodoAST implements NodoAST{
	
	private int line;
	private int column;
	
	public AbstractNodoAST(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	@Override
	public int getLine() {
		return line;
	}
	
	@Override
	public int getColumn() {
		return column;
	}

}

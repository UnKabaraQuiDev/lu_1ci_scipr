package tokens;

public abstract class LiteralToken<T> extends Token {
	
	public LiteralToken(TokenType type, int line, int column) {
		super(type, line, column);
	}
	
	public abstract T getValue();
	public abstract ValueType getValueType();
	
}

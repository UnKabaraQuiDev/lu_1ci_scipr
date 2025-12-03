package tokens;


public enum TokenType {

	TRUE("true"), FALSE("false"),

	NUM_LIT(), DEC_NUM_LIT(NUM_LIT), HEX_NUM_LIT(NUM_LIT), BIN_NUM_LIT(NUM_LIT), CHAR_LIT(NUM_LIT),

	IDENT(),

	COMMA(','), DOT('.'), COLON(':'), SEMICOLON(';'), DOLLAR('$'),

	ARROW("->"),

	PAREN_OPEN('('), PAREN_CLOSE(')'), BRACKET_OPEN('['), BRACKET_CLOSE(']'), CURLY_OPEN('{'), CURLY_CLOSE('}'),

	STRING_LIT(),

	COMMENT("//"),

	IF("if"), ELSE("else"),

	SWITCH("switch"), CASE("case"), DEFAULT("default"),

	FOR("for"), WHILE("while"), BREAK("break"),

	DO("do"), FINALLY("finally"),

	GOTO("goto"), YIELD("yield"),

	ASSIGN(), STRICT_ASSIGN(ASSIGN, '='),

	OR("||"), AND("&&"), NOT('!'), XOR("^^"),

	PLUS('+'), MINUS('-'), MUL('*'), DIV('/'), MODULO('%'),

	EQUALS("=="), NOT_EQUALS("!="),

	LESS('<'), LESS_EQUALS("<="),

	GREATER('>'), GREATER_EQUALS(">="),
	
	// CUSTOM
	MOVE("move"), UP("up"), DOWN("down"), ROTATE("rotate");

	private TokenType parent;
	private boolean fixed = false;
	private boolean string = false;
	private String stringValue;
	private char charValue;

	private TokenType() {
		this.fixed = false;
	}

	private TokenType(TokenType parent) {
		this.fixed = false;
		this.parent = parent;
	}

	private TokenType(char cha) {
		this.fixed = true;
		this.string = false;
		this.charValue = cha;
	}

	private TokenType(TokenType parent, char cha) {
		this.fixed = true;
		this.string = false;
		this.charValue = cha;
		this.parent = parent;
	}

	private TokenType(String str) {
		this.fixed = true;
		this.string = true;
		this.stringValue = str;
	}

	private TokenType(TokenType parent, String str) {
		this.fixed = true;
		this.string = true;
		this.stringValue = str;
		this.parent = parent;
	}

	public boolean matches(TokenType type) {
		return this.equals(type) || (parent != null ? parent.matches(type) : false);
	}

	public boolean isFixed() {
		return fixed;
	}

	public boolean isString() {
		return string;
	}

	public String getStringValue() {
		return stringValue;
	}

	public char getCharValue() {
		return charValue;
	}

	public Object getValue() {
		return !fixed ? name() : (string ? stringValue : charValue);
	}

	@Override
	public String toString() {
		if (fixed && string) {
			return TokenType.class.getSimpleName() + "[" + name() + ", fixed=" + fixed + ", string=" + string + ", stringValue=" + stringValue + "]";
		} else if (fixed && !string) {
			return TokenType.class.getSimpleName() + "[" + name() + ", fixed=" + fixed + ", string=" + string + ", charValue=" + charValue + "]";
		} else {
			return TokenType.class.getSimpleName() + "[" + name() + ", fixed=" + fixed + ", string=" + string + "]";
		}
	}

	public String toShortString() {
		return TokenType.class.getSimpleName() + "[" + name() + "]";
	}

}

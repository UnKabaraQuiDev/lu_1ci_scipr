package tokens;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Parser {

	private ParentStatement root = new ProgramStatement();
	private int index = 0;
	private List<Token> input;

	public Parser(Reader reader) throws IOException, LexerException {
		final L3Lexer lexer = new L3Lexer(reader);
		lexer.lexe();
		this.input = lexer.getTokens();
	}

	private void parseStmt(ParentStatement parent) {
		final Token t = consume(TokenType.move)
	}
	
	private boolean hasNext() {
		return index < input.size();
	}

	private boolean hasNext(int x) {
		return index + x < input.size();
	}

	private Token consume() throws ParserException {
		return consume(1);
	}

	private Token consume(int i) throws ParserException {
		end();
		Token c = input.get(index);
		index += i;
		return c;
	}

	private Token consume(TokenType t) throws ParserException {
		if (!hasNext())
			throw new ParserException("Expected %s but got end of input at: " + peek(-1).getPosition() + "->" + peek().getPosition(), t);

		if (peek(t)) {
			return consume();
		} else {
			throw new ParserException(peek(), t);
		}
	}

	private Token consume(TokenType... types) throws ParserException {
		Token peek = peek();
		if (Arrays.stream(types).filter(peek.getType()::matches).collect(Collectors.counting()) > 0)
			return consume();
		else
			throw new ParserException(peek, types);
	}

	private void end() throws ParserException {
		if (!hasNext())
			throw new ParserException("Unexpected end of input.");
	}

	private Token peek() {
		return peek(0);
	}

	private Token peek(int i) {
		return input.get(index + i);
	}

	private boolean peek(TokenType type) {
		if (!hasNext())
			return false;
		return peek().getType().matches(type);
	}

	private boolean peek(int x, TokenType type) {
		if (!hasNext(x))
			return false;
		return peek(x).getType().matches(type);
	}

	private boolean peek(TokenType... types) {
		if (!hasNext())
			return false;
		TokenType peek = peek().getType();
		return Arrays.stream(types).map(peek::matches).collect(Collectors.reducing((a, b) -> a || b)).orElse(false);
	}

	private boolean peek(int x, TokenType... types) {
		TokenType peek = peek(x).getType();
		return Arrays.stream(types).map(peek::matches).collect(Collectors.reducing((a, b) -> a || b)).orElse(false);
	}

}

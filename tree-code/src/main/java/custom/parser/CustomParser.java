package custom.parser;

import java.io.IOException;
import java.io.Reader;

import custom.Parser;
import custom.lexer.CustomLexer;
import custom.lexer.CustomTokenTypes;
import custom.stmts.LoopStatement;
import custom.stmts.MoveStatement;
import custom.stmts.ParentStatement;
import custom.stmts.PenStateStatement;
import custom.stmts.ProgramStatement;
import custom.stmts.RotateStatement;
import l3.lexer.LexerException;
import l3.lexer.TokenTypes;
import l3.parser.ParserException;

public class CustomParser extends Parser {

	protected ParentStatement root = new ProgramStatement();

	public CustomParser(Reader reader) throws IOException, LexerException, ParserException {
		super(new CustomLexer(reader));
		// super.input.forEach(System.out::println);
		while (hasNext()) {
			parseStmt(root);
		}
	}

	private void parseStmt(ParentStatement parent) throws ParserException {
		if (peek(CustomTokenTypes.MOVE)) {
			parseMove(parent);
		} else if (peek(CustomTokenTypes.ROTATE)) {
			parseRotate(parent);
		} else if (peek(CustomTokenTypes.UP, CustomTokenTypes.DOWN)) {
			parseUpDown(parent);
		} else if (peek(CustomTokenTypes.LOOP)) {
			parseLoop(parent);
		} else if (peek(TokenTypes.COMMENT)) {
			consume();
		} else {
			throw new UnsupportedOperationException("Unexpected token: " + peek());
		}
	}

	private void parseMove(ParentStatement parent) throws ParserException {
		consume(CustomTokenTypes.MOVE);
		parent.add(new MoveStatement(consumeNumericLiteral()));
	}

	private void parseRotate(ParentStatement parent) throws ParserException {
		consume(CustomTokenTypes.ROTATE);
		parent.add(new RotateStatement(consumeNumericLiteral()));
	}

	private void parseUpDown(ParentStatement parent) throws ParserException {
		parent.add(new PenStateStatement(
				consume(CustomTokenTypes.DOWN, CustomTokenTypes.UP).getType() == CustomTokenTypes.DOWN));
	}

	private void parseLoop(ParentStatement parent) throws ParserException {
		consume(CustomTokenTypes.LOOP);
		final ParentStatement newParent = new LoopStatement(consumeNumericLiteral());
		consume(TokenTypes.CURLY_OPEN);
		while (!peek(TokenTypes.CURLY_CLOSE)) {
			parseStmt(newParent);
		}
		consume(TokenTypes.CURLY_CLOSE);
		parent.add(newParent);
	}

	public ParentStatement getRoot() {
		return root;
	}

}

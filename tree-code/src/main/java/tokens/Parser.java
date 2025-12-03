package tokens;
import java.io.InputStream;
import java.util.Scanner;


public class Parser {

	private Scanner scanner;
	private ParentStatement root = new ProgramStatement();

	public Parser(InputStream reader) {
		this.scanner = new Scanner(reader);

		while (scanner.hasNextLine()) {
			parseStmt(root);
		}
	}

	private void parseStmt(ParentStatement parent) {
		if (!scanner.hasNextLine()) {
			return;
		}
		final String line = scanner.nextLine().trim().strip().toLowerCase();
		if (line.isEmpty()) {
			return;
		}

		final String[] tokens = line.split("\\s+");
		switch (tokens[0]) {
		case "move":
			parent.add(new MoveStatement(Integer.parseInt(tokens[1])));
			break;
		case "rotate":
			parent.add(new RotateStatement(Integer.parseInt(tokens[1])));
			break;

		default:
			if(Direction.ALL.contains(tokens[0].toUpperCase())) {
				parent.add(new DirectionStatement(Enum.valueOf(Direction.class, tokens[0].toUpperCase())));
			}
			break;
		}
	}

}

package tokens;
import java.util.ArrayList;
import java.util.List;

public class ParentStatement extends Statement {

	private List<Statement> children = new ArrayList<>();

	public boolean add(Statement arg0) {
		return children.add(arg0);
	}

}

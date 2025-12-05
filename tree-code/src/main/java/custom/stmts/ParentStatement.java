package custom.stmts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lu.pcy113.pclib.PCUtils;

public class ParentStatement extends Statement {

	private List<Statement> children = new ArrayList<>();

	public boolean add(Statement arg0) {
		return children.add(arg0);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{\n" + children.parallelStream()
				.map(c -> PCUtils.leftPadLine(c.toString(), "\t")).collect(Collectors.joining("\n")) + "\n}";
	}

}

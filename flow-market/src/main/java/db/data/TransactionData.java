package db.data;
import db.TransactionType;
import db.table.CashierTable;
import lu.pcy113.pclib.db.autobuild.column.AutoIncrement;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.Nullable;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class TransactionData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@AutoIncrement
	protected int id;
	@Column
	protected TransactionType type;
	@Column(length = 20)
	@Nullable
	protected String transactionTrace;
	@Column
	@ForeignKey(table = CashierTable.class)
	protected int cashierId;

}

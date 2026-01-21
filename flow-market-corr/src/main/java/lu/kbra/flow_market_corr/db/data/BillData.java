package lu.kbra.flow_market_corr.db.data;
import java.sql.Timestamp;

import lu.kbra.flow_market_corr.db.TransactionType;
import lu.kbra.flow_market_corr.db.table.CashierTable;
import lu.pcy113.pclib.db.autobuild.column.AutoIncrement;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.DefaultValue;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class BillData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@AutoIncrement
	protected long id;
	@Column(length = 20)
	protected TransactionType payementMethod;
	@Column
	@ForeignKey(table = CashierTable.class)
	protected long cashierId;
	@Column
	@DefaultValue("CURRENT_TIMESTAMP")
	protected Timestamp timestamp;

}

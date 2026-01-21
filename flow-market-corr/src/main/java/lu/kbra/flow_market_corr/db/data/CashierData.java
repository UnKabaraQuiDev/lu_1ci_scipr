package lu.kbra.flow_market_corr.db.data;

import lu.pcy113.pclib.db.autobuild.column.AutoIncrement;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.autobuild.column.Unique;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class CashierData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@AutoIncrement
	protected long id;
	@Column(length = 20)
	@Unique
	protected String ip;

	public CashierData() {
	}

	public CashierData(String ip) {
		this.ip = ip;
	}

}

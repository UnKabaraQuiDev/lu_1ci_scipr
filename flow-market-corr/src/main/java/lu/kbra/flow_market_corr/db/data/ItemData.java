package lu.kbra.flow_market_corr.db.data;

import lu.kbra.flow_market_corr.db.table.BillTable;
import lu.kbra.flow_market_corr.db.table.VendorTable;
import lu.pcy113.pclib.db.autobuild.column.Check;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class ItemData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@ForeignKey(table = BillTable.class)
	protected long transactionId;
	@Column(length = 6)
	@PrimaryKey
	@ForeignKey(table = VendorTable.class)
	protected String vendorCode;
	@Column
	@Check("%NAME% > 0")
	protected float price;

}

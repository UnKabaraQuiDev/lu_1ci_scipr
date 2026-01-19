package db.data;

import db.table.ProductTable;
import db.table.TransactionTable;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class TransactionProductData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@ForeignKey(table = TransactionTable.class)
	protected int transactionCode;
	@Column(length = 8)
	@PrimaryKey
	@ForeignKey(table = ProductTable.class)
	protected String productCode;

}

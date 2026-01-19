package db.data;

import db.table.VendorTable;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class ProductData implements DataBaseEntry {

	@Column(length = 8)
	@PrimaryKey
	protected String productCode;
	@Column
	protected String name;
	@Column(length = 6)
	@ForeignKey(table = VendorTable.class)
	protected String vendorCode;
	@Column
	protected float price;

}

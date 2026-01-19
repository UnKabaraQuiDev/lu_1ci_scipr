package lu.kbra.flow_market.db.data;
import lu.pcy113.pclib.db.autobuild.column.Check;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class VendorData implements DataBaseEntry {

	@Column(length = 6)
	@PrimaryKey
	protected String vendorCode;
	@Column
	@Check("%NAME% BETWEEN 0 AND 1")
	protected float percentage;

}

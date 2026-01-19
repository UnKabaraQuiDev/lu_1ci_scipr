package db.view;

import db.data.VendorData;
import db.table.ProductTable;
import db.table.TransactionProductTable;
import db.table.VendorTable;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseView;
import lu.pcy113.pclib.db.annotations.view.DB_View;
import lu.pcy113.pclib.db.annotations.view.ViewColumn;
import lu.pcy113.pclib.db.annotations.view.ViewTable;
import lu.pcy113.pclib.db.annotations.view.ViewTable.Type;

@DB_View(
		name = "vendor_profit",
		tables = {
				@ViewTable(typeName = TransactionProductTable.class),
				@ViewTable(
						typeName = ProductTable.class,
						join = Type.LEFT,
						on = "product.product_code = transaction_product.product_code",
						columns = {
								@ViewColumn(func = "SUM(price)", asName = "price"),
								@ViewColumn(func = "SUM(PRICE) * percentage", asName = "price for vendor"),
								@ViewColumn(func = "SUM(PRICE) * (1-percentage)", asName = "price for orga") }
				),
				@ViewTable(
						typeName = VendorTable.class,
						join = Type.LEFT,
						on = "vendor.vendor_code = product.vendor_code",
						columns = { @ViewColumn(name = "vendor_code") }
				) },
		groupBy = { "vendor_code" }
)
public class VendorProfitView extends DataBaseView<VendorData> {

	public VendorProfitView(DataBase dataBase) {
		super(dataBase);
	}

}

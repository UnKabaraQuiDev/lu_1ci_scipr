package db;

import db.table.CashierTable;
import db.table.ProductTable;
import db.table.TransactionProductTable;
import db.table.TransactionTable;
import db.table.VendorTable;
import db.view.VendorProfitView;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseConnector;

public class Main {

	public static final void main(String[] args) {
		final DataBaseConnector conn = new DataBaseConnector("user", "pass", "localhost", 3306);
		final DataBase db = new DataBase(conn, "scipr_flow_market");
		db.create().run();
		final CashierTable cashiers = new CashierTable(db);
		cashiers.create().run();
		final VendorTable vendors = new VendorTable(db);
		vendors.create().run();
		final ProductTable products = new ProductTable(db);
		products.create().run();
		final TransactionTable transactions = new TransactionTable(db);
		transactions.create().run();
		final TransactionProductTable transactionProducts = new TransactionProductTable(db);
		transactionProducts.create().run();
		final VendorProfitView vendorProfit = new VendorProfitView(db);
		vendorProfit.create().run();
	}

}

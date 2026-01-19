package lu.kbra.flow_market;

import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseConnector;

import lu.kbra.flow_market.db.table.CashierTable;
import lu.kbra.flow_market.db.table.ProductTable;
import lu.kbra.flow_market.db.table.TransactionProductTable;
import lu.kbra.flow_market.db.table.TransactionSupplementTable;
import lu.kbra.flow_market.db.table.TransactionTable;
import lu.kbra.flow_market.db.table.VendorTable;
import lu.kbra.flow_market.db.view.VendorProfitView;

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
		final TransactionSupplementTable transactionSupplements = new TransactionSupplementTable(db);
		transactionSupplements.create().run();

		final VendorProfitView vendorProfit = new VendorProfitView(db);
//		System.out.println(vendorProfit.getCreateSQL());
//		vendorProfit.exists().thenCompose(f -> f ? vendorProfit.drop() : NextTask.empty()).run();
		vendorProfit.create().run();
//		final CashierProfitView cashierProfit = new CashierProfitView(db);
//		cashierProfit.create().run();

		vendorProfit.all().forEachRemaining(System.out::println);
	}

}

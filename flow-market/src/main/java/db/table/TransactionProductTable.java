package db.table;

import db.data.TransactionProductData;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;

public class TransactionProductTable extends DataBaseTable<TransactionProductData> {

	public TransactionProductTable(DataBase dataBase) {
		super(dataBase);
	}

}

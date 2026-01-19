package db.table;
import db.data.TransactionData;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;

public class TransactionTable extends DataBaseTable<TransactionData> {

	public TransactionTable(DataBase dataBase) {
		super(dataBase);
	}

}

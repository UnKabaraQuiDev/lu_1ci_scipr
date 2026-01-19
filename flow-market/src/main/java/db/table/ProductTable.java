package db.table;
import db.data.ProductData;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;

public class ProductTable extends DataBaseTable<ProductData> {

	public ProductTable(DataBase dataBase) {
		super(dataBase);
	}

}

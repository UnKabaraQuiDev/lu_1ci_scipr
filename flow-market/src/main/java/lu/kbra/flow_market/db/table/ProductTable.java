package lu.kbra.flow_market.db.table;

import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;

import lu.kbra.flow_market.db.data.ProductData;

public class ProductTable extends DataBaseTable<ProductData> {

	public ProductTable(DataBase dataBase) {
		super(dataBase);
	}

}

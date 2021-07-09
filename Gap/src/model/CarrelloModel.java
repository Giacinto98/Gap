package model;

import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

public class CarrelloModel implements InterfacciaDAO <CarrelloBean>  {
	private DataSource ds = null;
	
	public CarrelloModel (DataSource ds)
	{
		this.ds = ds;
	}

	@Override
	public CarrelloBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CarrelloBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(CarrelloBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(CarrelloBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(CarrelloBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
}

package model;
//interfaccia generica per operare su un oggetto del database
import java.sql.SQLException;
import java.util.Collection;

public interface InterfacciaDAO<T> {
	public T doRetrieveByKey (String code) throws SQLException;
	
	public Collection <T> doRetriveAll (String order) throws SQLException;
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(T item) throws SQLException;
	
	public void doDelete(T item) throws SQLException;
}
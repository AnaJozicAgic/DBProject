package muzej;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MuzejDAOInterface {
	// method to get all exhibits
	public ArrayList<MuzejDB> getMuzejDB() throws SQLException;

	// method to get a specific exhibit
	public MuzejDB getMuzejDB(int ID) throws SQLException;
	// method to get a specific exhibit
	public MuzejDB getMuzejDBname(String  naziv) throws SQLException;
	// method to get a specific exhibit
	public MuzejDB getMuzejDBplace(String  lokalitet) throws SQLException;
	// method to get a specific exhibit
	public MuzejDB getMuzejDBage(int age) throws SQLException;

	// method to update a specific exhibit
	public void updateMuzejDB(MuzejDB exhibit) throws SQLException;

	// method to delete a specific exhibit
	public void deleteMuzejDB(MuzejDB exhibit) throws SQLException;
	
	//method to add a exhibit
	public void addMuzejDB() throws SQLException;

	// method to print a specific exhibit
	public void printMuzejDB(MuzejDB exhibit);
}

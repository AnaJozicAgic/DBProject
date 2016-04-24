package muzej;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users {
	private String username;
	private String pasword;
	private String wright;

	// constructors
	public Users() {

	}

	public Users(String username, String pasword, String wright) {
		this.wright = wright;
		this.username = username;
		this.pasword = pasword;
	}

	// getters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getWright() {
		return wright;
	}

	public void setWright(String wright) {
		this.wright = wright;
	}

	public static String getUser(String username, String password) {
		Connection conn = ConectionManager.getInstance().getConnection();
		String query = "select * from users_wrights;";
		ResultSet rs = null;
		String wright1="";
		try (
				// java.sql.Statement
				Statement statement = conn.createStatement();) {

			// execute the query
			rs = statement.executeQuery(query);

			// check for users 

			while (rs.next()) {
//				int id = rs.getInt("id");
				String usernameDB = rs.getString("username");
				String passwordDB = rs.getString("password");
				String wrightDB = rs.getString("wright");
				if (username.equals(usernameDB) && password.equals(passwordDB)) {
					wright1 = wrightDB;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return wright1;
	}


}

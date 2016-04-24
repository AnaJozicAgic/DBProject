package muzej;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MuzejDAOConcrete implements MuzejDAOInterface, AutoCloseable {
	Scanner input = new Scanner(System.in);
	// connect to the database
	Connection connection = ConectionManager.getInstance().getConnection();

	@Override
	public ArrayList<MuzejDB> getMuzejDB() throws SQLException {

		// create an array list to hold exhibits
		ArrayList<MuzejDB> exhibits = new ArrayList<>();

		// create an SELECT SQL query
		String query = "SELECT * FROM muzej";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				Statement statement = connection.createStatement();) {

			// execute the query
			rs = statement.executeQuery(query);

			// add students to the arrayList
			while (rs.next()) {
				exhibits.add(new MuzejDB(rs.getInt("ID"), rs.getString("naziv"), rs.getString("opis"),
						rs.getInt("procjenjenaStarost"), rs.getString("lokalitet")));

				System.out.println("Exhibit : " + rs.getObject("naziv") + " / " + rs.getString("opis") + " / "
						+ rs.getInt("procjenjenaStarost") + " / " + rs.getString("lokalitet"));
			}

		}

		return exhibits;
	}

	@Override
	public MuzejDB getMuzejDB(int ID) throws SQLException {

		// null student
		MuzejDB exhibit = null;

		// create an SELECT SQL query
		String query = "SELECT * FROM muzej WHERE ID = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			statement.setInt(1, ID);

			// execute the query
			rs = statement.executeQuery();

			// set the cursor
			if (rs.next()) {

				// populate exhibit
				exhibit = new MuzejDB(rs.getInt("ID"), rs.getString("naziv"), rs.getString("opis"),
						rs.getInt("procjenjenaStarost"), rs.getString("lokalitet"));

				// close the ResultSet
				rs.close();
			}
		}

		return exhibit;
	}

	@Override
	public void updateMuzejDB(MuzejDB exhibit) throws SQLException {
		if (exhibit != null) {

			// create an SELECT SQL query
			String query = "UPDATE muzej SET naziv = ?, opis = ?, procjenjenaStarost = ?, lokalitet = ? WHERE ID = ?";

			System.out.print("Set a new name for exhibit (current: " + exhibit.getNaziv() + " ): ");
			String name = input.next();

			System.out.print("Set a new description for exhibit (current: " + exhibit.getOpis() + " ): ");
			String description = input.next();

			System.out.print("Set a new period for exhibit (current: " + exhibit.getProcjenjenaStarost() + " ): ");
			int age = input.nextInt();

			System.out.print("Set a new location for exhibit (current: " + exhibit.getLokalitet() + " ): ");
			String lokalitet = input.next();

			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				// fill in the placeholders/parameters
				statement.setString(1, name);
				statement.setString(2, description);
				statement.setInt(3, age);
				statement.setString(4, lokalitet);
				statement.setInt(5, exhibit.getID());

				// execute the query
				statement.executeUpdate();

				System.out.println("Exibit updated in the database.");
			}
		}
	}

	@Override
	public void deleteMuzejDB(MuzejDB exhibit) throws SQLException {
		if (exhibit != null) {
			// create an SELECT SQL query
			String query = "DELETE FROM muzej WHERE ID = ?";

			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				// fill in the placeholders/parameters
				statement.setInt(1, exhibit.getID());

				// execute the query
				statement.executeUpdate();

				System.out.println("Exhibit deleted from the database.");
			}
		}
	}

	@Override
	public void addMuzejDB() throws SQLException {

		// create an SELECT SQL query
		String query = "INSERT INTO muzej(naziv, opis, procjenjenaStarost, lokalitet) VALUES (?, ?, ?, ?)";

		System.out.print("Enter exhibit name: ");
		String name = input.next();

		System.out.print("Enter exhibits description: ");
		String description = input.next();

		System.out.print("Enter exhibit dated time: ");
		int age = input.nextInt();

		System.out.print("Enter exhibits locality: ");
		String lokalitet = input.next();

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			statement.setString(1, name);
			statement.setString(2, description);
			statement.setInt(3, age);
			statement.setString(4, lokalitet);

			// execute the query
			statement.executeUpdate();

			System.out.println("Exhibit added to the database.");
		}

	}

	@Override
	public void printMuzejDB(MuzejDB exhibit) {
		if (exhibit != null) {
			System.out.println(
					"ID: " + exhibit.getID() + ", name: " + exhibit.getNaziv() + ", description: " + exhibit.getOpis()
							+ ", age: " + exhibit.getProcjenjenaStarost() + ", locality: " + exhibit.getLokalitet());
		} else {
			System.out.println("No exhibits to print.");
		}
	}

	@Override
	public void close() throws Exception {
		
		input.close();
	}

	@Override
	public MuzejDB getMuzejDBname(String naziv) throws SQLException {
		// null exhibit
		MuzejDB exhibit0 = null;

		// create an SELECT SQL query
		String query = "SELECT * FROM muzej WHERE naziv = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			statement.setString(1, naziv);

			// execute the query
			rs = statement.executeQuery();

			// set the cursor
			if (rs.next()) {

				// populate exhibit
				exhibit0 = new MuzejDB(rs.getInt("ID"), rs.getString("naziv"), rs.getString("opis"),
						rs.getInt("procjenjenaStarost"), rs.getString("lokalitet"));

				// close the ResultSet
				rs.close();
			}
		}

		return exhibit0;

	}

	@Override
	public MuzejDB getMuzejDBplace(String lokalitet) throws SQLException {
		// null exhibit
		MuzejDB exhibit = null;

		// create an SELECT SQL query
		String query = "SELECT * FROM muzej WHERE lokalitet = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			statement.setString(1, lokalitet);

			// execute the query
			rs = statement.executeQuery();

			// set the cursor
			if (rs.next()) {

				// populate exhibit
				exhibit = new MuzejDB(rs.getInt("ID"), rs.getString("naziv"), rs.getString("opis"),
						rs.getInt("procjenjenaStarost"), rs.getString("lokalitet"));

				// close the ResultSet
				rs.close();
			}
		}

		return exhibit;
	}

	@Override
	public MuzejDB getMuzejDBage(int age) throws SQLException {
		// null exhibit
		MuzejDB exhibit = null;

		// create an SELECT SQL query
		String query = "SELECT * FROM muzej WHERE procjenjenaStarost = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			statement.setInt(1, age);

			// execute the query
			rs = statement.executeQuery();

			// set the cursor
			if (rs.next()) {

				// populate exhibit
				exhibit = new MuzejDB(rs.getInt("ID"), rs.getString("naziv"), rs.getString("opis"),
						rs.getInt("procjenjenaStarost"), rs.getString("lokalitet"));

				// close the ResultSet
				rs.close();
			}
		}

		return exhibit;
	}
}

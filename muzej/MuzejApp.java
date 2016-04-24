package muzej;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MuzejApp {
	public static boolean checkDBExists(String dbName) {

		try {

			// open a connection
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "root");
			ResultSet resultSet = conn.getMetaData().getCatalogs();

			while (resultSet.next()) {

				String databaseName = resultSet.getString(1);
				if (databaseName.equals(dbName)) {
					return true;
				}
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static void create() {
		String query = "create database muzej";
		try (Connection conection = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "root");
				Statement statement = conection.createStatement();) {
			statement.executeUpdate(query);

		} catch (SQLException e) {

		}

		String query2 = "create table muzej " + "(ID integer primary key not null auto_increment,"
				+ "naziv varchar (70)," + "opis varchar (70)," + "procjenjenaStarost integer,"
				+ "lokalitet varchar(70));";
		try (Connection conection = ConectionManager.getInstance().getConnection();
				Statement statement = conection.createStatement();) {
			statement.executeUpdate(query2);

		} catch (SQLException e) {

		}
	}

	public static void admin(String[] args) {
		// CRUD
		// Create
		// Update
		// Reed
		// Delete

	}

	public static void user(String[] args) {
		// izlistati bazu

		// Pretrazivati bazu
		java.util.Scanner input = new java.util.Scanner(System.in);
		MuzejDAOConcrete user = new MuzejDAOConcrete();
		System.out.println(
				"1 - Unesite za izlistavanje cijele baze.\n2 - Unesite za pretrazivanje baze. \n3 - Unesite za logout.");
		int check = input.nextInt();
		switch (check) {
		case 1:
			try {
				user.getMuzejDB();
				System.out.println();
			} catch (SQLException e) {
				System.out.println("Greska.");
			}
			break;
		case 2:
			System.out.println(
					"1 unesite za pretrazivanje po id-u,\n2 unesite za pretrazivanje po nazivu \n3 unesite za pretrazivanje po lokalitetu \n4 unesite za pretrazivanje po procjenjenoj starosti. ");
			int provjera = input.nextInt();
			switch (provjera) {
			case 1:
				System.out.println("Unesite id zeljenog exponata: ");
				int id = input.nextInt();
				try {
					System.out.println(user.getMuzejDB(id));

				} catch (SQLException e1) {

				}

				break;
			case 2:
				System.out.println("Unesite naziv zeljenog exponata: ");
				String naziv1 = input.next();

				try {
					System.out.println(user.getMuzejDBname(naziv1));
				} catch (SQLException e) {
					System.out.println("");
				}
				break;
			case 3:
				System.out.println("Unesite lokalitet :");
				String lokalitet = input.next();
				try {
					System.out.println(user.getMuzejDBplace(lokalitet));
				} catch (SQLException e) {

				}
				break;
			case 4:
				System.out.println("Unesite procjenjenu starost : ");
				int starost = input.nextInt();
				try {
					System.out.println(user.getMuzejDBage(starost));
				} catch (SQLException e) {
					System.out.println("-");
				}
				break;
			default:
				break;
			}
			break;
		case 3:
			try {
				main(args);
			} catch (SQLException e2) {
				System.out.println("Greska.");
			}
			break;
		default:
			break;
		}

	}

	public static boolean login(String username, String pasword, String[] args) {
		if (Users.getUser(username, pasword).equals("administrator")) {
			admin(args);
			return true;
		} else if (Users.getUser(username, pasword).equals("user")) {
			user(args);
			return true;
		} else {

			return false;
		}

	}

	public static void main(String[] args) throws SQLException {
		if (!checkDBExists("muzej")) {
			create();
		}
		String username = "", pasword = "";
		java.util.Scanner input = new java.util.Scanner(System.in);
		while (!login(username, pasword, args)) {

			System.out.println("Enter username: ");
			username = input.next();
			System.out.println("Enter password: ");
			pasword = input.next();
			if (!login(username, pasword, args)) {

				System.out.println("You entered incorect walues. ");
			}
		}
		input.close();
	}

}

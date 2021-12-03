package info5.BDD;

import java.util.ArrayList;
import java.sql.*;
import info5.BDD.Database_tool;
import java.time.LocalDateTime;

public class Database {
	/**
	 * Classe de gestion des base de donnees
	 * 
	 */
	private ArrayList<Table> tables;
	private String url;

	/**
	 * Connect an create the database
	 *
	 * @param name the database file name
	 */
	public Database(String name, String path) {
		super();
		this.tables = new ArrayList<Table>();
		this.url = "jdbc:sqlite:" + path + name + ".db";
	}

	public void CreateDatabase() {

		try (Connection conn = this.connect()) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection connect() {
		// SQLite connection string
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public void AddTable(Table table) {
		tables.add(table);

		// SQLite connection string

		// SQL statement for creating a new table
		String sql = "CREATE TABLE IF NOT EXISTS " + table.getName() + "(\n" + table.getForm_Table() + ");";
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insert_data(Table table, String[] data) {

		String comp_sql = "(";
		String value = "VALUES(";
		ArrayList<String> columns = table.getColumns();
		for (int i = 0; i < data.length; i++) {
			if (i + 1 == data.length) {
				comp_sql = comp_sql + columns.get(i + 1) + ") ";
				value = value + "?)";
			} else {
				comp_sql = comp_sql + columns.get(i + 1) + ", ";
				value = value + "?,";
			}

		}
		ArrayList<String> types = table.getTypes();

		String sql = "INSERT INTO " + table.getName() + comp_sql + value;// + "(name,capacity) VALUES(?,?)";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			for (int i = 0; i < data.length; i++) {

				Database_tool.setObject(pstmt, i + 1, data[i], types.get(i + 1));

			}

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void select_all(Table table) {
		String sql = "SELECT * FROM " + table.getName();
		ArrayList<String> column = table.getColumns();
		ArrayList<String> types = table.getTypes();

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				String res_query = "";
				for (int i = 0; i < column.size(); i++) {

					res_query = res_query + Database_tool.getObject(rs, types.get(i), column.get(i)) + "   ";

				}
				System.out.println(res_query);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<String> selectCheckUser(String user) {
		String sql = "SELECT login, password, token  FROM user where login like '" + user + "'";
		ArrayList<String> res_query = new ArrayList<String>();
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			// loop through the result set
			while (rs.next()) {
				String row = "";
				for (int i = 0; i < 3; i++) {
					row = rs.getString(i + 1);
					res_query.add(row);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res_query;
	}
	
	public void createAccount(String username, String password, String token) {
		String sql= "INSERT INTO user(login, password, token, created_at) VALUES(?,?,?,?)";
		
		try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            if (!token.isBlank()) {
            	preparedStatement.setString(3,token);
            }else {
            	preparedStatement.setNull(3, java.sql.Types.NULL);;
			}
            System.out.println(Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row); //1

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

package fr.tse.info5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database_tool {
	
	public static String getObject(ResultSet rs, String type, String column) throws SQLException {
		if(type== "integer") {
			return Integer.toString(rs.getInt(column));
		}
		else {
			if(type == "float") {
				return Float.toString(rs.getFloat(column));
			}
			else{
				return rs.getString(column);
			}
		}
	}
	
	
	public static void setObject(PreparedStatement pstmt,int column, String data, String type) throws NumberFormatException, SQLException {
		if(type== "integer") {
			pstmt.setInt(column, Integer.parseInt(data));
		}
		else {
			if(type== "float") {
				pstmt.setFloat(column, Float.parseFloat(data));
			}
			else {
				pstmt.setString(column, data);
			}
		}
	}

}

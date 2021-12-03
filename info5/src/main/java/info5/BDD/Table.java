package info5.BDD;

import java.util.ArrayList;

public class Table {
	private String name;
	private ArrayList<String> columns;
	private ArrayList<String> types;
	private ArrayList<String> indicator_null;
	
	public Table(String name) {
		/*
		 * Create the basis of the Table of the database
		 */
		super();
		this.name = name;
		columns = new ArrayList<String>();
		types = new ArrayList<String>();
		indicator_null = new ArrayList<String>();
		
		/*
		 * creating the primary key column 
		 */
		columns.add("id");
		types.add("integer");
		indicator_null.add("PRIMARY KEY");
	}
	
	public void addColumn(String column_name,String column_type, String column_indicator) {
		/*
		 * allow to add a column with a indicator that is not NULL
		 */
		columns.add(column_name);
		types.add(column_type);
		indicator_null.add(column_indicator);
	}
	
	public void addColumn(String column_name,String column_type) {
		/*
		 * allow to add a column with a indicator that is not NULL
		 */
		columns.add(column_name);
		types.add(column_type);
		indicator_null.add("NULL");
	}
	
	
	

	public String getName() {
		return name;
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public ArrayList<String> getIndicator_null() {
		return indicator_null;
	}
	
	public String getForm_Table() {
		/*
		 * create a string to allow the creation of the table in the database
		 */
		String form_table ="";
		
		String backline = ",\n"; // separator between every columns
		for (int i = 0; i < columns.size(); i++) {
			if((i+1)==columns.size()){
				backline= "\n"; // at the last column the "," is not needed
			}
			
			
			if(indicator_null.get(i)!="NULL") {
				form_table= form_table
						+columns.get(i)+" "
						+types.get(i)+" "
						+indicator_null.get(i)+backline;
			}
			else {
				form_table= form_table+columns.get(i)+" "
						+types.get(i)+backline;
			}
		}
		
		
		return form_table;
	}
	
	
	
	
	
	
	
	
	
}

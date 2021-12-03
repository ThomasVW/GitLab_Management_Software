package fr.tse.info5.database;


import java.util.ArrayList;
import java.sql.*;

public class Database {
	/** Classe de gestion des base de donnees
	 * 
	 */
	private ArrayList<Table> tables; // pour plus tard
	private String url; //premet de savoir ou creer la BDD
	 
	


	/**
     * Connect an create the database
     *
     * @param name the database file name
     */
	public Database(String name, String path) {
		/*
		 * let the user to give a name and the path where the DB should be
		 */
		super();
		this.tables = new ArrayList<Table>(); 
		this.url="jdbc:sqlite:"+path+name+".db";
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
            conn = DriverManager.getConnection(this.url); //tentative de connection a la BDD
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public void AddTable(Table table) {
		/*
		 * allow to create a new table in the DB
		 */
		
		tables.add(table);
		
        
        String sql = "CREATE TABLE IF NOT EXISTS "+table.getName()+"(\n"
        			 +table.getForm_Table()+ ");"; //prepation de la requete de creation 
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
    public void insert_data(Table table, String[] data) {
        /*
         * permet d'inserer des donnes dans la table
         */
    	// creation de la requete
        String comp_sql="(";
        String value="VALUES(";
        ArrayList<String> columns = table.getColumns();
        for (int i = 0; i < data.length; i++) {
        	if(i+1==data.length) {
        		comp_sql=comp_sql+columns.get(i+1)+") ";
        		value= value+"?)";
        	}
        	else {
        		comp_sql=comp_sql+columns.get(i+1)+", ";
        		value= value+"?,";
        	}
			
		}
        ArrayList<String> types = table.getTypes();
        
        String sql = "INSERT INTO "+table.getName()+comp_sql+value;
        // injection dans la table
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < data.length ; i++) {
            	//on injecte les donnees colonne par colonne
				Database_tool.setObject(pstmt, i+1, data[i], types.get(i+1));
				
			}
        	
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
    public void select_all(Table table){
    	/*
    	 * permet de recuperer les donnees de toute la table retour a modifier
    	 */
        String sql = "SELECT * FROM "+ table.getName(); // creation de la requete
        ArrayList<String> column = table.getColumns();
        ArrayList<String> types = table.getTypes();
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	String res_query = "";
            	for (int i = 0; i < column.size(); i++) {
            		
            		res_query= res_query+ Database_tool.getObject(rs,types.get(i),column.get(i))+"   ";
    				
            	}
            	System.out.println(res_query);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void select(Table table, ArrayList<String> column){
    	/*
    	 * a modifier retourne le resultat d'une requete uniquement pour les colonnes souhaitees
    	 */
        int n =column.size();// economie du temps de calcul
    	String sql = "SELECT";
        for (int i = 0; i < n; i++) {
        	if(i==n-1) {
        		sql=sql+column.get(i)+" ";
        	}
        	else {
        		sql=sql+column.get(i)+", ";
        	}
        }
        
        sql=sql+"FROM "+ table.getName();
        ArrayList<String> types = table.getTypes();
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	String res_query = "";
            	for (int i = 0; i < n; i++) {
            		Integer index= table.get_index(column.get(i));
            		res_query= res_query+ Database_tool.getObject(rs,types.get(index),column.get(index))+"   ";
    				
            	}
            	System.out.println(res_query);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public ArrayList<String> select_where(Table table, ArrayList<String> columns, String column, String condition){
        /*
         * permet de recuperer une donnee precise si la condition est unique
         */
    	int n =columns.size();
    	//creation de la requete
    	String sql = "SELECT ";
        for (int i = 0; i < n; i++) {
        	if(i==n-1) {
        		sql=sql+columns.get(i)+" ";
        	}
        	else {
        		sql=sql+columns.get(i)+", ";
        	}
        }
        
        sql=sql+"FROM "+ table.getName()+" WHERE"+column+" = "+condition;
        
        
        ArrayList<String> types = table.getTypes(); //permet de choisir le bon type lors de la recuperation
        
        ArrayList<String> resultat= new ArrayList<String>();// permet de recuperer le resultat de la requete
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	String res_query = "";
            	for (int i = 0; i < n; i++) {
            		Integer index= table.get_index(columns.get(i));
            		if(i+1==n) {
            			res_query= res_query+ Database_tool.getObject(rs,types.get(index),columns.get(index));
            		}
            		else {
            			res_query= res_query+ Database_tool.getObject(rs,types.get(index),columns.get(index))+";";// on a repris un format type csv pour la ligne
            		}
            	}
            	resultat.add(res_query);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }
    
    public String select_where(Table table,String column_cond, String column, String condition){
        /*
         * permet de recuperer une donnee precise si la condition est unique
         */

    	//creation de la requete
    	String sql = "SELECT "+column+" FROM "+ table.getName()+" WHERE "+column_cond+" = "+"'"+condition+"'"+';';
        
    	ArrayList<String> columns= table.getColumns();
        ArrayList<String> types = table.getTypes(); //permet de choisir le bon type lors de la recuperation
        
        String resultat= "";// permet de recuperer le resultat de la requete
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
        	
        	String res_query ="";
            // loop through the result set
            while (rs.next()) {
            	Integer index = table.get_index(column);
            	res_query=  Database_tool.getObject(rs,types.get(index),columns.get(index));
            		
            	}
            	resultat= resultat+res_query+"\n";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }
    
    
    
    
    
    
    
    
}

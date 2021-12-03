package fr.tse.info5.database;

public class Main {
    
    /**
     * @param args the command line arguments
     * à refaire
     */
    public static void main(String[] args) {
       Database DB = new Database("app", "../database/");
       Password pass = new Password();
       DB.connect();
 //      DB.CreateDatabase();
      
        Table user = new Table("user");
        user.addColumn("login", "text", "NOT NULL");
        user.addColumn("password", "text", "NOT NULL");
        
        
        
        
        String[] data_brut = {"test","motdepasse"};
        String password = pass.encrypt(data_brut[0], data_brut[1]);
        String[] data = {"",""};
        data[0]= data_brut[0];
        data[1]=password;
//        DB.insert_data(user,data);
        

        try {
			Boolean test =pass.verification(DB, user, data_brut[1], data_brut[0], "password", "login");
			System.out.println(test);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			Boolean test =pass.verification(DB, user, "bonjourhacke", data_brut[0], "password", "login");
			System.out.println(test);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}

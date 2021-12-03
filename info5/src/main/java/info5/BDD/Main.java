package info5.BDD;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database DB = new Database("app", "./src/main/java/info5/assets/");
        DB.connect();
        DB.CreateDatabase();
        
        Table user = new Table("user");
        user.addColumn("login", "varchar", "NOT NULL");
        user.addColumn("password", "varchar", "NOT NULL");
        user.addColumn("token", "varchar");
        user.addColumn("created_at", "timestamp");
        
        Table worker = new Table("worker");
        worker.addColumn("lastname", "CHAR(30)", "NOT NULL");
        worker.addColumn("firstname", "CHAR(30)", "NOT NULL");
        worker.addColumn("mail", "varchar", "NOT NULL");
        worker.addColumn("id_group", "integer");
        worker.addColumn("id_user", "integer");
        worker.addColumn("id_module", "integer");
        worker.addColumn("created_at", "timestamp");
        
        DB.AddTable(user);
        DB.AddTable(worker);
        
        String[] data = {"test","test"};
        String[] data2 = {"Last","First","test@mail.com","5"};
        
        DB.insert_data(user, data);
        DB.insert_data(worker, data2);
        DB.select_all(user);
        DB.selectCheckUser("Test");
        
        
    }
}

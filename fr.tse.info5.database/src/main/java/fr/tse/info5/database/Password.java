package fr.tse.info5.database;

import java.net.UnknownHostException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Password {
	private static java.lang.Exception Userdoesnotexist;
	//Class to create an encrypted password
	java.net.InetAddress localMachine;
	private BCryptPasswordEncoder encoder;  //clef de cryptage
	
	
	public Password() {
		super();
		this.encoder=new BCryptPasswordEncoder(16); // valeur a mettre dans un fichier conf.txt pour plus de securite
		/*
		 * cette partie recupere l'id de la machine pour complexifier le mdp
		 */
		this.localMachine = null;
		try {
			localMachine = java.net.InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			this.localMachine = null;
		}
	}

	

	public String encrypt(String login, String password) {
		/*
		 * methode de chiffrement du mot de passe
		 */
		
		
    	 
       /*
        * partie de chiffrement
        */
		String chain;
		if(this.localMachine != null) {
			chain = login+password+localMachine.getHostName();
		}
		else {
			chain = login+password;
		}
		String crypt=encoder.encode(chain);
		
		
		return crypt;// on recupere le mot passe chiffre
	}
	
	public Boolean verification(Database DB, Table table, String password, String login, String column, String column_cond) throws Exception {
		
		String DBpassword= DB.select_where(table, column_cond, column , login);
		DBpassword = DBpassword.replace("\n", ""); //normalement le login est unique
		if(DBpassword.isBlank()) {
			throw  Userdoesnotexist;
		}
		String passwd="";
		if(this.localMachine != null) {
			passwd = login+password+localMachine.getHostName();
		}
		else {
			passwd = login+password;
		}

		Boolean res = encoder.matches(passwd, DBpassword);
		return res;
	};
	

}

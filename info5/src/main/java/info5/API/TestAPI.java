package lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.SystemUtils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TestAPI {
	public static void main(String[] args) throws UnsupportedEncodingException, UnirestException {
		// Host url
		String host = "https://code.telecomste.fr/api/v4/projects/";
		String charset = "UTF-8";
		// Headers for a request
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter token");
		String token = scan.nextLine();// Type here your key

		int option = 0;
		while ((option < 1) || (option > 7)) {
			System.out.println("Select option");
			System.out.println("1. Projects");
			System.out.println("2. Projects and statistics");
			System.out.println("3. Projects owned");
			System.out.println("4. Delete project");
			System.out.println("5. Create project");
			System.out.println("6. Add member to a project");
			System.out.println("7. Add member to a group");
			option = scan.nextInt();
		}

		String id_project = "";
		String id_group = "";
		String user_id = "";
		int permission = 0;
		HttpResponse<JsonNode> response = null;
		switch (option) {
		case 1:
			response = Unirest.get(host).header("PRIVATE-TOKEN", token).asJson();
			break;
		case 2:
			response = Unirest.get(host + "?statistics=true").header("PRIVATE-TOKEN", token).asJson();
			break;
		case 3:
			response = Unirest.get(host + "?owned=true").header("PRIVATE-TOKEN", token).asJson();
			break;
		case 4:
			Scanner scan1 = new Scanner(System.in);
			System.out.println("Please type id of the project");
			host += scan1.nextLine();
			response = Unirest.delete(host).header("PRIVATE-TOKEN", token).asJson();
			break;
		case 5:
			Scanner scan2 = new Scanner(System.in);
			System.out.println("Please type name of the project");
			String name = scan2.nextLine();
			host += "?name=" + name;
			response = Unirest.post(host).header("PRIVATE-TOKEN", token).asJson();
			break;
		case 6:
			Scanner scan3 = new Scanner(System.in);
			System.out.println("Please type id of the project");
			id_project = scan3.nextLine();
			System.out.println("Please type id of the member");
			user_id = scan3.nextLine();
			while ((permission < 1) || (permission > 6)) {
				System.out.println("Please select permission level");
				System.out.println("1. No access");
				System.out.println("2. Minimal access");
				System.out.println("3. Guest");
				System.out.println("4. Reporter");
				System.out.println("5. Developer");
				System.out.println("6. Maintainer");
				permission = scan3.nextInt();
			}
			switch (permission) {
			case 1:
				permission = 0;
				break;
			case 2:
				permission = 5;
				break;
			case 3:
				permission = 10;
				break;
			case 4:
				permission = 20;
				break;
			case 5:
				permission = 30;
				break;
			case 6:
				permission = 40;
				break;
			};
			response = Unirest.post(host+id_project+"/members").header("PRIVATE-TOKEN", token).field("user_id", user_id).field("access_level", permission).asJson();
			break;
		case 7:
			Scanner scan4 = new Scanner(System.in);
			System.out.println("Please type id of the group");
			id_group = scan4.nextLine();
			System.out.println("Please type id of the member");
			user_id = scan4.nextLine();
			while ((permission < 1) || (permission > 6)) {
				System.out.println("Please select permission level");
				System.out.println("1. No access");
				System.out.println("2. Minimal access");
				System.out.println("3. Guest");
				System.out.println("4. Reporter");
				System.out.println("5. Developer");
				System.out.println("6. Maintainer");
				permission = scan4.nextInt();
			}
			switch (permission) {
			case 1:
				permission = 0;
				break;
			case 2:
				permission = 5;
				break;
			case 3:
				permission = 10;
				break;
			case 4:
				permission = 20;
				break;
			case 5:
				permission = 30;
				break;
			case 6:
				permission = 40;
				break;
			};
			response = Unirest.post(host+id_group+"/members").header("PRIVATE-TOKEN", token).field("user_id", user_id).field("access_level", permission).asJson();
			break;
		}
		// Prettifying
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response.getBody().toString());
		String prettyJsonString = gson.toJson(je);
		System.out.println(prettyJsonString);
	}

}

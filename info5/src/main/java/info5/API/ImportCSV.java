package info5.API;

import java.awt.Taskbar.State;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.commons.csv.*;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.Pager;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

//Import for API
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class ImportCSV {

	public static ArrayList <String[]> ImportProjectsMembers(String path,GitLabApi gitLabApi,String token, String host ) {
		if(host==null) {
			host ="https://code.telecomste.fr";
		}
		//Header of the CSV File
		String[] Headers = { "Groupes","Adresses mail"};
		ArrayList <String[]> status=new ArrayList <String[]>();
		try {
			status=givenCSVFile_whenRead_thenContentsAsExpected(path, Headers, gitLabApi,token,host);
			//Add import done at the end should be displayed as a popup
			String[] stat=new String[1];
			stat[0]="Import terminé pas d'erreur majeure";
			status.add(stat);
		} catch (IOException e) {
			//Add the error message at the end should be displayed as a popup
			String[] stat=new String[1];
			stat[0]=e.getMessage();
			status.add(stat);
		}
		return status;
	}

	public static ArrayList <String[]> givenCSVFile_whenRead_thenContentsAsExpected(String path, String[] Headers,GitLabApi gitLabApi,String token,String host) throws IOException {
		
		Reader in = new FileReader(path);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(';').parse(in);
		int iteration = 0;
		// Variables used for the API
		String user_id = "";
		String project_id = "";
		String project_name = "";
		int permission = 0;
		HttpResponse<JsonNode> response=null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		String requestAnswers = "";
		JsonElement je = null;
		//List that will contain all the informations
		ArrayList <String[]> status=new ArrayList <String[]>();
				
		Project projects = null;
		for (CSVRecord record : records) {
			int recordSize = record.size();
			// Check if the line has something in it (A group with only a space will be
			// valid)
			if (record.get(0).toString() != "" || record.size() > 1) {
				String group = record.get(0).toString();
				String mailAdress = record.get(1).toString();
				if (iteration == 0) {// Check the titles
					String groupesH = Headers[0].trim();
					String mailAdressH = Headers[1].trim();
					if (!group.trim().equals(Headers[0].trim()) || !mailAdress.trim().equals(Headers[1].trim())) {
						throw new IOException("Les titres ne sont pas valides.");
					}
				} else {
					String[] stat=new String[4];
					String[] stat1=new String[4];
					if (group != "" && mailAdress.trim()!="") {
						
						project_id = "";// Put to void to avoid adding wrong members to a previous project
						project_name = "";
						project_name = group;
						stat1[0]=project_name;//Add the name of the project in the list
						stat1[1]="Création du groupe";
						//If provided a token
						if(token!=null) {
							// Try to add that person to the group
							// Create group with the following name		
							String state="";
							String ok_nok="";
							try {
								requestAnswers=requestAnswers+"Création du projet "+project_name+" avec le message"+createProjectUnirest(host,token,project_name)+"/n";
								state="Fini";
								ok_nok="OK";
							} catch (IOException e) {
								StringWriter errors = new StringWriter();
								e.printStackTrace(new PrintWriter(errors));
								state=errors.toString();
								ok_nok="NOK";
							}
							catch(UnirestException e) {
								StringWriter errors = new StringWriter();
								e.printStackTrace(new PrintWriter(errors));
								state=state +"|-------Unirest-------|"+errors.toString();
								ok_nok="NOK";
							}
							stat1[2]=state;
							stat1[3]=ok_nok;
							
						}
						//With the GitLabAPI, the user Username and password
						else {
							try {
								gitLabApi.getProjectApi().createProject(project_name,"");
								stat1[2]="Fini";
								stat1[3]="OK";
							} catch (GitLabApiException e) {
								// TODO Auto-generated catch block
								StringWriter errors = new StringWriter();
								e.printStackTrace(new PrintWriter(errors));
								stat1[2]=errors.toString();
								stat1[3]="NOK";
							}
						}				
						status.add(stat1);
						
						// Add members to the project
						String state="";
						String ok_nok="";
						stat[0]="Ajout membre au projet";//Add the name of the project in the list
						stat[1]=mailAdress;
						try {
							addMemberProject(gitLabApi,mailAdress.trim(), project_name);
							state="Fini";
							ok_nok="OK";
						} catch (IOException e) {
							StringWriter errors = new StringWriter();
							e.printStackTrace(new PrintWriter(errors));
							state=errors.toString();
							ok_nok="NOK";
						}
						catch(GitLabApiException e) {
							StringWriter errors = new StringWriter();
							e.printStackTrace(new PrintWriter(errors));
							state=state +"|-------GitLabApiExepection-------|"+errors.toString();
							ok_nok="NOK";
						}
							
						stat[2]=state;
						stat[3]=ok_nok;
						status.add(stat);

						} else {
						if (mailAdress.trim() != "") {
							// Add members to the project
							String state="";
							String ok_nok="";
							stat[0]="Ajout membre au projet";//Add the name of the project in the list
							stat[1]=mailAdress;
							try {
								addMemberProject(gitLabApi,mailAdress.trim(), project_name);
								state="Fini";
								ok_nok="OK";
							} catch (IOException e) {
								StringWriter errors = new StringWriter();
								e.printStackTrace(new PrintWriter(errors));
								state=errors.toString();
								ok_nok="NOK";
							}
							catch(GitLabApiException e) {
								StringWriter errors = new StringWriter();
								e.printStackTrace(new PrintWriter(errors));
								state=state +"|-------GitLabApiExepection-------|"+errors.toString();
								ok_nok="NOK";
							}

							stat[2]=state;
							stat[3]=ok_nok;
							status.add(stat);

						} else {
							throw new IOException("Membre d'un groupe manquant");
						}						
					}
				}
				iteration = iteration + 1;
			} else {
				throw new IOException("Ligne vide");
			}
			System.out.println(requestAnswers);
		}
		return status;
	}
	//Check if the given email is associated to a GitLab account
	//Return true if yes 
	//False if no
	public static Boolean CheckEmailAssociated(GitLabApi gitLabApi,String email ) throws IOException, GitLabApiException {
		Boolean emailAssociated=false;
		User user=null;	
		user=gitLabApi.getUserApi().getUserByEmail(email);
		if(user==null) {
			throw new IOException("L'email "+  email +" n'est associé à aucun compte GitLab");
		}else {
			emailAssociated=true;
		}
		return emailAssociated;
	}
	//Get the id of the user associated to a given email	
	public static int getMemberId(GitLabApi gitLabApi,String email ) throws GitLabApiException, IOException {
		int id=0;
		if(CheckEmailAssociated(gitLabApi,email)) {
			id=gitLabApi.getUserApi().getUserByEmail(email).getId();
		}
		return id;
	}
	//Create a project	
	//Return a string which tell the status of the request if a success or not
		public static String createProjectUnirest(String host,String token,String project_name ) throws  IOException, UnirestException {
			String requestStatus="";
			HttpResponse<JsonNode> response=null;	
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = null;
			//Send the request
			response=Unirest.post(host+"/api/v4/projects/" + "?name=" + project_name).header("PRIVATE-TOKEN", token).asJson();
			//Get the result in a string
			if(response!=null) {
				je = jp.parse(response.getBody().toString());
				requestStatus = "Création du projet " + project_name + " : " + gson.toJson(je) + " /n ";
			}
			return requestStatus;
		}
	//Add a member to a project
		public static void addMemberProject(GitLabApi gitLabApi,String email,String project_name ) throws  IOException, GitLabApiException {
			int member_id = getMemberId(gitLabApi,email);//Get member id
			List<Project> matching_projects=gitLabApi.getProjectApi().getProjects(project_name);//Get all the matching project the the name provided
			Project current_project=null;
			System.out.println("Nombre de projet qui matchent: "+matching_projects.size());
			for(Project project : matching_projects) {
				if(project_name.equals(project.getName().toString()) && project.getOwner().getId().equals(gitLabApi.getUserApi().getCurrentUser().getId())) {
					current_project=project;//Get the project matching exactly with the name
				}
			}
			int project_id=current_project.getId();//Get project id
			gitLabApi.getProjectApi().addMember(project_id, member_id, AccessLevel.DEVELOPER);
		}
}

/*
 * //Replace all the separator possible by ";" to split it correctly public
 * static List<String> replaceSeparator(List<String> lines){ List<String>
 * LinesSemicollon = null; List<Character> separatorList= Arrays.asList(',','
 * '); for (String line : lines) { for(Character separator : separatorList) {
 * line.replace(separator, ';'); } } return LinesSemicollon; } //Check if the
 * file is not empty public static Boolean checkFileIntergrity(String csvPath) {
 * Path filePath = Paths.get(csvPath); List<String> lines = null; //null mean no
 * value by default try { lines = Files.readAllLines(filePath); } catch
 * (IOException e) { System.out.println("Impossible de lire le fichier"); return
 * false; } if (lines.size() < 2) {
 * System.out.println("Il n'y a pas de groupes dans le fichier"); return false;
 * } return true; } //Check if a student is missing public static Boolean
 * checkLineIntergrity(String[] line,int linenb) { if(line[1].trim()=="") {
 * System.out.println("La ligne "
 * +linenb+" est vide. Veuillez renseigner un étudiant dans le fichier" );
 * return false; } return true;
 * 
 * } //Read the CSV file, return true if success and false if failed public
 * static boolean read(String csvPath) { Path filePath = Paths.get(csvPath);
 * List<String> lines = null; //null mean no value by default
 * if(checkFileIntergrity(csvPath)) { try { lines =
 * Files.readAllLines(filePath); }catch (IOException e) {}
 * lines=replaceSeparator(lines); for (int i = 0; i < lines.size(); i++) {
 * String[] split = lines.get(i).split(";"); if(i==0) { if(split[0]!="Groupes"
 * || split[1]!="Adresses mail") { System.out.
 * println("Les titres sont manquant. Ajoutez 'Groupes' et 'Adresses mail'");
 * return false; } } else { if(checkLineIntergrity(split,i)) {
 * if(split[0].trim()!="") { System.out.println("Groupe :"+split[0]);
 * System.out.println("Mail :"+split[1]); } else {
 * System.out.println("Mail :"+split[1]); } } else { return false; } } } } else
 * { return false; } return true; } public static void main(String path) {
 * ImportCSV importCSV = new ImportCSV(); ImportCSV.read(path); }
 */

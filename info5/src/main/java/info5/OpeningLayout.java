package info5;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.border.SoftBevelBorder;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

import info5.BDD.Database;

public class OpeningLayout extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsernameLogin;
	private JPasswordField passwordFieldLogin;
	private JTextField textFieldUsernameCreate;
	private JPasswordField passwordFieldCreate;
	private String myToken = null;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningLayout frame = new OpeningLayout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OpeningLayout() {
		setTitle("GIT TSE");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.setBounds(0, 0, 729, 563);
		contentPane.add(tabbedPane);

		// REGION LOGIN
		JPanel loginPanel = new JPanel();
		tabbedPane.addTab("Login", null, loginPanel, null);
		tabbedPane.setEnabledAt(0, true);
		loginPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 200, 530);
		loginPanel.add(panel);
		panel.setLayout(null);

		JLabel lblUsernameLogin = new JLabel("USERNAME");
		lblUsernameLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsernameLogin.setBounds(260, 116, 103, 28);
		loginPanel.add(lblUsernameLogin);

		textFieldUsernameLogin = new JTextField();
		textFieldUsernameLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldUsernameLogin.setBounds(260, 154, 303, 35);
		loginPanel.add(textFieldUsernameLogin);
		textFieldUsernameLogin.setColumns(10);

		JLabel lblPasswordLogin = new JLabel("PASSWORD");
		lblPasswordLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPasswordLogin.setBounds(260, 209, 113, 28);
		loginPanel.add(lblPasswordLogin);

		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordFieldLogin.setBounds(260, 247, 303, 35);
		loginPanel.add(passwordFieldLogin);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(255, 153, 51));
		btnLogin.setBounds(260, 377, 303, 35);
		btnLogin.setBorder(BorderFactory.createEmptyBorder());
		btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				/*
				 * if (checkUserExists(textFieldUsernameLogin.getText(),
				 * String.valueOf(passwordFieldLogin.getPassword()))) { try {
				 * System.out.println("ok"); BtnLoginMouseClicked(evt); } catch
				 * (GitLabApiException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } } else { JOptionPane.showMessageDialog(contentPane,
				 * "Credentials not recognized", "Authentication Error",
				 * JOptionPane.ERROR_MESSAGE); }
				 */
				BtnMouseClicked(evt);
			}
		});
		loginPanel.add(btnLogin);

		JLabel lblTitleLogin = new JLabel("Login");
		lblTitleLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitleLogin.setBounds(260, 60, 206, 28);
		loginPanel.add(lblTitleLogin);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Show");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxNewCheckBox.setBounds(569, 256, 123, 21);
		chckbxNewCheckBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					passwordFieldLogin.setEchoChar((char) 0);
				} else {
					passwordFieldLogin.setEchoChar('*');
				}
			}
		});
		loginPanel.add(chckbxNewCheckBox);
		// END REGION LOGIN

		// REGION CREATE
		JPanel createPanel = new JPanel();
		tabbedPane.addTab("Create Account", null, createPanel, null);
		createPanel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(51, 0, 102));
		panel_1.setBounds(0, 0, 200, 530);
		createPanel.add(panel_1);

		JLabel lblUsernameCreate = new JLabel("USERNAME");
		lblUsernameCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsernameCreate.setBounds(260, 136, 103, 28);
		createPanel.add(lblUsernameCreate);

		textFieldUsernameCreate = new JTextField();
		textFieldUsernameCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldUsernameCreate.setColumns(10);
		textFieldUsernameCreate.setBounds(260, 174, 303, 35);
		createPanel.add(textFieldUsernameCreate);

		JLabel lblPasswordCreate = new JLabel("PASSWORD");
		lblPasswordCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPasswordCreate.setBounds(260, 251, 113, 28);
		createPanel.add(lblPasswordCreate);

		passwordFieldCreate = new JPasswordField();
		passwordFieldCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordFieldCreate.setBounds(260, 289, 303, 35);
		createPanel.add(passwordFieldCreate);

		JButton btnCreate = new JButton("Create Account");
		btnCreate.setBackground(new Color(255, 153, 0));
		btnCreate.setBounds(260, 390, 303, 35);
		btnCreate.setBorder(BorderFactory.createEmptyBorder());
		btnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				JTextField gitUsername = new JTextField();
				JTextField gitPass = new JPasswordField();
				Object[] message = { "Username:", gitUsername, "Password:", gitPass };

				int option = JOptionPane.showConfirmDialog(contentPane, message, "GitLab Login",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					GitLabApi gitLabApi;
					try {
						gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", gitUsername.getText(),
								gitPass.getText());
						myToken = gitLabApi.getAuthToken();
						System.out.println(myToken);
						String res = createUser(textFieldUsernameCreate.getText(),
								String.valueOf(passwordFieldCreate.getPassword()), myToken);
						if (res == "true") {
							BtnCreateMouseClicked(evt, gitLabApi);
						} else {
							JOptionPane.showMessageDialog(contentPane, res, "Creation Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (GitLabApiException e) {
						System.out.println("username or password incorrect");
					}

				} else {
					System.out.println("Gitlab credentials wrong");
				}

			}
		});
		createPanel.add(btnCreate);

		JLabel lblTitleCreate = new JLabel("Create an account");
		lblTitleCreate.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitleCreate.setBounds(260, 60, 206, 28);
		createPanel.add(lblTitleCreate);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Show");
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxNewCheckBox_1.setBounds(575, 297, 123, 21);
		chckbxNewCheckBox_1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					passwordFieldCreate.setEchoChar((char) 0);
				} else {
					passwordFieldCreate.setEchoChar('*');
				}
			}
		});
		createPanel.add(chckbxNewCheckBox_1);
		// END REGION CREATE
	}

	private void BtnLoginMouseClicked(java.awt.event.MouseEvent evt) throws GitLabApiException {
		// Create a GitLabApi instance to communicate with your GitLab server
		GitLabApi gitLabApi;
		try {
			gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", textFieldUsernameLogin.getText(),
					passwordFieldLogin.getPassword());
			this.setVisible(false);
			Home home = new Home(gitLabApi);
			home.setVisible(true);
		} catch (GitLabApiException e) {
			System.out.println("username or password incorrect");
		}
		try {
			gitLabApi = new GitLabApi("https://code.telecomste.fr", myToken);
			this.setVisible(false);
			Home home = new Home(gitLabApi);
			home.setVisible(true);
		} catch (Exception e) {
			System.out.println("connection failed");
		}
	}

	private void BtnMouseClicked(java.awt.event.MouseEvent evt) {
		// Create a GitLabApi instance to communicate with your GitLab server
		GitLabApi gitLabApi;
		if (myToken != null) {
			try {
				gitLabApi = new GitLabApi("https://code.telecomste.fr", myToken);
				this.setVisible(false);
				Home home = new Home(gitLabApi);
				home.setVisible(true);
			} catch (Exception e) {
				System.out.println("connection failed");
			}
		}
		try {
			gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", textFieldUsernameLogin.getText(),
					passwordFieldLogin.getPassword());
			this.setVisible(false);
			Home home = new Home(gitLabApi);
			home.setVisible(true);
		} catch (GitLabApiException e) {
			System.out.println("username or password incorrect");
		}
	}

//	un peu inutile voir à remplacer
	private void BtnCreateMouseClicked(java.awt.event.MouseEvent evt, GitLabApi gitLabApi) throws GitLabApiException {

		this.setVisible(false);
		Home home = new Home(gitLabApi);
		home.setVisible(true);
	}

	/*
	 * Function to test if credentials entered by user are valid (exists in the
	 * database)
	 * 
	 * @param String username, String password
	 * 
	 * @return Boolean
	 */

	private Boolean checkUserExists(String user, String password) {
		Database DB = new Database("app", "./src/main/java/info5/assets/");
		ArrayList<String> res = DB.selectCheckUser(user);
		if (user.equals(res.get(0).toString()) && password.equals(res.get(1).toString())) {
			if (!res.get(2).toString().isBlank()) {
				myToken = res.get(2).toString();
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	/*
	 * Function to create a new user for the application
	 * 
	 * @param String user, String password, string token
	 * 
	 * @return String
	 */

	private String createUser(String user, String password, String token) {
		Database DB = new Database("app", "./src/main/java/info5/assets/");
		ArrayList<String> res = DB.selectCheckUser(user);
		if (!user.equals(res.get(0))) {
			try {
				DB.createAccount(user, password, token);
				return "true";
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "Cannot create account.";
			}
		} else {
			return "Username already exists. Please chose another one.";
		}
	}
}

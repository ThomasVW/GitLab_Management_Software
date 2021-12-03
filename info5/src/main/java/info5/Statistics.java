package info5;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Visibility;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Statistics extends JFrame {

	private JPanel contentPane;
	static GitLabApi gitlabApi;
	static Stream<Project> projectStream;
	// Stream the visible projects printing out the project name.
	static ArrayList<String> projects = new ArrayList<String>();
    static List<Project> listproject;
    //static List<Commit> listcommit; // r�cuperer liste de commits d'un project -> probl�me tr�s lourd 
    static int count_commits = 0;
    static Date last_activity;
    static ArrayList<String> members = new ArrayList<String>();
    static int last_commit = 0;
    static int count_repositories = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gitlabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", "van-wynendaele.thomas","thomas118118");
					Statistics frame = new Statistics(gitlabApi);
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
	public Statistics(GitLabApi gitLabApi) {
		
		gitlabApi = gitLabApi;
		try {
			listproject = gitlabApi.getProjectApi().getProjects(false, Visibility.PRIVATE,Constants.ProjectOrderBy.NAME, Constants.SortOrder.ASC, null, false, false, true, false, true);
			//listcommit = gitlabApi.getCommitsApi().getCommits(listproject.get(2).getId()).;
	    	//System.out.println(listcommit);
			for( Project p : listproject) {
				projects.add(p.getName());
			}
			
		} catch (GitLabApiException e) {
			e.printStackTrace();
		}
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		Button1 = new javax.swing.JPanel();
		Button1.setBorder(null);
		Indicator1 = new javax.swing.JPanel();
		Indicator1.setBorder(new LineBorder(new Color(255, 255, 255)));
		jLabel1 = new javax.swing.JLabel();
        jLabel1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Button1MouseClicked(e);
        	}
        });
		Button2 = new javax.swing.JPanel();
		Button2.setBorder(null);
		Indicator2 = new javax.swing.JPanel();
		Indicator2.setBorder(new LineBorder(Color.WHITE));
		Indicator2.setForeground(Color.WHITE);
		jLabel2 = new javax.swing.JLabel();
        jLabel2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Button2MouseClicked(e);
        	}
        });
		Button3 = new javax.swing.JPanel();
		Button3.setBorder(null);
		Indicator3 = new javax.swing.JPanel();
		Indicator3.setBorder(new LineBorder(Color.BLACK));
		Indicator3.setForeground(Color.WHITE);
		jLabel3 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));

		jPanel1.setBackground(new java.awt.Color(41, 41, 97));
        
		Button1.setBackground(new java.awt.Color(208, 45, 26));
		Button1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Button1MouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Button1MouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Button1MouseExited(evt);
			}
		});

		Indicator1.setBackground(new java.awt.Color(255, 255, 255));
		Indicator1.setForeground(new java.awt.Color(255, 255, 255));

		javax.swing.GroupLayout Indicator1Layout = new javax.swing.GroupLayout(Indicator1);
		Indicator1.setLayout(Indicator1Layout);
		Indicator1Layout.setHorizontalGroup(Indicator1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 8, Short.MAX_VALUE));
		Indicator1Layout.setVerticalGroup(Indicator1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setText("Home");
		jLabel1.setToolTipText("");

		javax.swing.GroupLayout Button1Layout = new javax.swing.GroupLayout(Button1);
		Button1.setLayout(Button1Layout);
		Button1Layout.setHorizontalGroup(Button1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(Button1Layout.createSequentialGroup()
						.addComponent(Indicator1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 135, Short.MAX_VALUE)));
		Button1Layout
				.setVerticalGroup(Button1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								Button1Layout.createSequentialGroup().addContainerGap()
										.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 36,
												Short.MAX_VALUE)
										.addContainerGap())
						.addComponent(Indicator1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		Button2.setBackground(new java.awt.Color(208, 45, 26));
		Button2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Button2MouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Button2MouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Button2MouseExited(evt);
			}
		});

		Indicator2.setBackground(Color.WHITE);

		javax.swing.GroupLayout Indicator2Layout = new javax.swing.GroupLayout(Indicator2);
		Indicator2.setLayout(Indicator2Layout);
		Indicator2Layout.setHorizontalGroup(Indicator2Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 8, Short.MAX_VALUE));
		Indicator2Layout.setVerticalGroup(Indicator2Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel2.setForeground(new java.awt.Color(255, 255, 255));
		jLabel2.setText("Dashboard");
		jLabel2.setToolTipText("");

		javax.swing.GroupLayout Button2Layout = new javax.swing.GroupLayout(Button2);
		Button2Layout.setHorizontalGroup(Button2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(Button2Layout.createSequentialGroup()
						.addComponent(Indicator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(28).addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 92, Short.MAX_VALUE)));
		Button2Layout
				.setVerticalGroup(Button2Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, Button2Layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE).addContainerGap())
						.addComponent(Indicator2, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE));
		Button2.setLayout(Button2Layout);

		Button3.setBackground(new java.awt.Color(208, 45, 26));
		Button3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Button3MouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Button3MouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Button3MouseExited(evt);
			}
		});

		Indicator3.setBackground(Color.BLACK);

		javax.swing.GroupLayout Indicator3Layout = new javax.swing.GroupLayout(Indicator3);
		Indicator3.setLayout(Indicator3Layout);
		Indicator3Layout.setHorizontalGroup(Indicator3Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 8, Short.MAX_VALUE));
		Indicator3Layout.setVerticalGroup(Indicator3Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setText("Statistics");
		jLabel3.setToolTipText("");

		javax.swing.GroupLayout Button3Layout = new javax.swing.GroupLayout(Button3);
		Button3.setLayout(Button3Layout);
		Button3Layout.setHorizontalGroup(Button3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(Button3Layout.createSequentialGroup()
						.addComponent(Indicator3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		Button3Layout.setVerticalGroup(Button3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(Indicator3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						Button3Layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addContainerGap()));

		jLabel12.setIcon(new javax.swing.ImageIcon("src/main/java/info5/assets/logo.png")); // NOI18N

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(24).addComponent(jLabel12).addGap(0, 48,
						Short.MAX_VALUE))
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(Button1, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
								.addComponent(Button3, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(Button2,
						GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel1Layout
				.createSequentialGroup().addContainerGap(86, Short.MAX_VALUE).addComponent(jLabel12).addGap(59)
				.addComponent(Button1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(Button2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE).addGap(21)
				.addComponent(Button3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(158)));
		jPanel1.setLayout(jPanel1Layout);

		jPanel2.setBackground(new java.awt.Color(153, 204, 255));

		jLabel5 = new JLabel();
		jLabel5.setToolTipText("");
		jLabel5.setText("Your GitLab Statistics");
		jLabel5.setForeground(new Color(41, 41, 97));
		jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		JPanel panel = new JPanel();
		panel.setName("");
		
		panel_1 = new JPanel();
		
		panel_2 = new JPanel();
		
				
		JLabel lblArchived = new JLabel();
		lblArchived.setBounds(38, 10, 133, 25);
		lblArchived.setToolTipText("");
		lblArchived.setText("Last Commit");
		lblArchived.setForeground(new Color(41, 41, 97));
		lblArchived.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel_2.add(lblArchived);
		
		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setForeground(new Color(208, 45, 26));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(36, 41, 151, 33);
		panel_2.add(lblNewLabel_2);
		panel_1.setLayout(null);
		
		lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setForeground(new Color(208, 45, 26));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(38, 73, 149, 33);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblRepositories = new JLabel();
		lblRepositories.setBounds(28, 10, 142, 25);
		lblRepositories.setToolTipText("");
		lblRepositories.setText("Commits Counts");
		lblRepositories.setForeground(new Color(41, 41, 97));
		lblRepositories.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel_1.add(lblRepositories);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(61, 45, 67, 49);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(208, 45, 26));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel_1.add(lblNewLabel_1);
		panel.setLayout(null);
		
		JLabel lblGroups_1 = new JLabel();
		lblGroups_1.setBounds(47, 10, 86, 25);
		lblGroups_1.setToolTipText("");
		lblGroups_1.setText("Members");
		lblGroups_1.setForeground(new Color(41, 41, 97));
		lblGroups_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(lblGroups_1);
		
        DefaultMutableTreeNode Gitroot = new DefaultMutableTreeNode("Gitlab");
        DefaultTreeModel treeModel = new DefaultTreeModel(Gitroot);
        JTree tree = new JTree(treeModel);
        tree.setBackground(null);    
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		String[] array1 = projects.toArray(new String[projects.size()]);
		JComboBox list = new JComboBox();
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Project selectedproject = listproject.get(list.getSelectedIndex());
		        DefaultMutableTreeNode Gitroot = new DefaultMutableTreeNode("Gitlab");
				DefaultMutableTreeNode temp = new DefaultMutableTreeNode(selectedproject.getName());
				try {
					members = new ArrayList<String>();
					gitlabApi.getProjectApi().getMembers(selectedproject.getId()).forEach(item -> members.add(item.getName()));
					gitlabApi.getRepositoryApi().getTree(selectedproject.getId()).forEach(name -> temp.add(new DefaultMutableTreeNode(name.getName())));
					
				} catch (GitLabApiException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Gitroot.add(temp);
		        treeModel.setRoot(Gitroot);
                treeModel.reload();
                
				JList memberslist = new JList(members.toArray(new String[projects.size()]));
				memberslist.setBounds(10, 5, 150, 100);
				scrollPane.setBounds(10, 40, 180, 90);
		        scrollPane.setViewportView(memberslist);
				last_activity = selectedproject.getLastActivityAt();
				count_commits = (int) selectedproject.getStatistics().getCommitCount();
				lblNewLabel_1.setText(String.valueOf(count_commits));
				String[] date = last_activity.toLocaleString().split("�");
				lblNewLabel_2.setText(date[0]);
				lblNewLabel_3.setText(date[1]);
			}
		});
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( array1 );
		list.setModel( model );	
		
		JLabel lblProject = new JLabel();
		lblProject.setToolTipText("");
		lblProject.setText("Project :");
		lblProject.setForeground(new Color(41, 41, 97));
		lblProject.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JList list_1 = new JList();
		
		JLabel lblModule = new JLabel();
		lblModule.setToolTipText("");
		lblModule.setText("Module :");
		lblModule.setForeground(new Color(41, 41, 97));
		lblModule.setFont(new Font("Segoe UI", Font.BOLD, 18));
		

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addGap(24)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup()
									.addComponent(lblModule, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel2Layout.createSequentialGroup()
									.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(tree, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))))
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(lblProject, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		jPanel2Layout.setVerticalGroup(
			jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup()
									.addGap(28)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel2Layout.createSequentialGroup()
									.addGap(18)
									.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblModule, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblProject, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))))
							.addGap(48))
						.addGroup(jPanel2Layout.createSequentialGroup()
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(61)))
					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tree, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(331, Short.MAX_VALUE))
		);
		
		
		panel_2.setLayout(null);

		jPanel2.setLayout(jPanel2Layout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	private void Button3MouseExited(java.awt.event.MouseEvent evt) {

	}

	private void Button3MouseEntered(java.awt.event.MouseEvent evt) {
		onHover(Indicator3, jLabel3);
		onleaveHover(Indicator2, jLabel2);
		onleaveHover(Indicator1, jLabel1);
	}

	private void Button3MouseClicked(java.awt.event.MouseEvent evt) {

	}

	private void Button2MouseExited(java.awt.event.MouseEvent evt) {

	}

	private void Button2MouseEntered(java.awt.event.MouseEvent evt) {
		onHover(Indicator2, jLabel2);
		onleaveHover(Indicator1, jLabel1);
		onleaveHover(Indicator3, jLabel3);
	}

	private void Button2MouseClicked(java.awt.event.MouseEvent evt) {
		Dashboard dashboard = new Dashboard(gitlabApi);
		this.setVisible(false);
		dashboard.setVisible(true);
	}

	private void Button1MouseExited(java.awt.event.MouseEvent evt) {

	}

	private void Button1MouseEntered(java.awt.event.MouseEvent evt) {
		onHover(Indicator1, jLabel1);
		onleaveHover(Indicator3, jLabel3);
		onleaveHover(Indicator2, jLabel2);
	}
    
	private void Button1MouseClicked(java.awt.event.MouseEvent evt) {
		Home home = new Home(gitlabApi);
		this.setVisible(false);
		home.setVisible(true);
	}

	private void onHover(JPanel panel, JLabel label) {
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setForeground(new Color(0, 0, 0));
		label.setForeground(new Color(0, 0, 0));
	}

	private void onleaveHover(JPanel panel, JLabel label) {
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(255, 255, 255)));
		label.setForeground(new Color(255, 255, 255));
	}

	private void onClick(JPanel panel, JLabel label) {
		panel.setBackground(new Color(0, 0, 0));
		label.setForeground(new Color(0, 0, 0));
	}

	private void onleaveClick(JPanel panel, JLabel label) {
		panel.setBackground(new Color(255, 255, 255));
		label.setForeground(new Color(255, 255, 255));
	}

	private void hiddenpanel(JPanel panel) {
		panel.setVisible(false);
	}

	// Variables declaration - do not modify
	private javax.swing.JPanel Button1;
	private javax.swing.JPanel Button2;
	private javax.swing.JPanel Button3;
	private javax.swing.JPanel Indicator1;
	private javax.swing.JPanel Indicator2;
	private javax.swing.JPanel Indicator3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private JLabel jLabel5;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTree tree;
	private DefaultTreeModel treeModel;
}
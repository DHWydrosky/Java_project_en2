package en2dbproject;

import java.awt.BorderLayout;
import java.util.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import application.BDD;

public class Commande extends JFrame {

	private JPanel contentPane;
	private JTextField nom;
	private JTextField ville;
	private JTextField tel;
	private JTextField adresse;
	private JTextField compte;
	private JTextField article;
	private ResultSet rs;
	private ResultSet rd;
	private BDD db;
	private DefaultTableModel model = new DefaultTableModel();
	String[] rgb = new String[] {};
	String[] art = new String[] {};
	String[] pri =new String[] {};
	String lista = "";
	
	private JTable table;
	private JTable table_Client;
	/* Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commande frame = new Commande();
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
	public Commande() {
		db= new BDD();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		model.addColumn("Id");
		model.addColumn("Nom");
		
		JLabel lblNewLab = new JLabel("Passer une commande");
		lblNewLab.setBounds(250, 5, 160, 14);
		contentPane.add(lblNewLab);
		
		
		JScrollPane tableClient = new JScrollPane();
		tableClient.setBounds(10, 21, 661, 178);
		contentPane.add(tableClient);
		
		table_Client = new JTable();
		table_Client.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nom.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 0)));
				ville.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 1)));
			}
		});
		table_Client.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table_Client.setModel(model);
		tableClient.setViewportView(table_Client);
		
		JLabel lblNewLabl = new JLabel("Client");
		lblNewLabl.setBounds(60, 206, 86, 14);
		contentPane.add(lblNewLabl);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 230, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrnom = new JLabel("Nom");
		lblPrnom.setBounds(10, 261, 86, 14);
		contentPane.add(lblPrnom);
		
		
		
		nom = new JTextField();
		nom.setBounds(106, 224, 167, 20);
		contentPane.add(nom);
		nom.setColumns(10);
		
		ville = new JTextField();
		ville.setColumns(10);
		ville.setBounds(106, 255, 167, 20);
		contentPane.add(ville);
		
		JLabel lblNewLabel_2 = new JLabel("Recherche Article  ");
		lblNewLabel_2.setBounds(330, 237, 140, 14);
		contentPane.add(lblNewLabel_2);
		 { 
			 rs= db.executeQuery("select nom_article from article");
		 }
		
		
		try {
			while (rs.next()) {
				String[] rgb2 = new String[rgb.length + 1];
				System.arraycopy(rgb, 0, rgb2, 0, rgb.length);
				rgb2[rgb.length] =  rs.getString("nom_article");
				rgb = rgb2;	
			}
		} catch(Exception e1) {
			System.err.println(e1.getMessage());
		}
		
		JComboBox rechercherPar = new JComboBox();
		rechercherPar.setModel(new DefaultComboBoxModel(rgb));
		rechercherPar.setBounds(457, 233, 140, 22);
		contentPane.add(rechercherPar);
		
		JLabel lblNewLael_2 = new JLabel("Quantite");
		lblNewLael_2.setBounds(330, 267, 140, 14);
		contentPane.add(lblNewLael_2);
		
		article = new JTextField();
		article.setBounds(457, 265, 140, 20);
		contentPane.add(article);
		article.setColumns(10);
		
		JLabel lblEmail = new JLabel("Liste d'articles");
		lblEmail.setBounds(10, 340, 120, 14);
		contentPane.add(lblEmail);
		
		compte = new JTextField();
		compte.setColumns(10);
		compte.setBounds(130, 341, 400, 20);
		contentPane.add(compte);
		
		
		
		JButton btnajouter = new JButton("Ajouter");
		btnajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (article.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas entre la quantite");	
				}
				else {
					for (int i = 0; i < rgb.length; i++) {
						if (rechercherPar.getSelectedItem().toString().equals(rgb[i])) {
							String[] rgb2 = new String[art.length + 1];
							System.arraycopy(art, 0, rgb2, 0, art.length);
							rgb2[art.length] =  rgb[i];
							art = rgb2;
						}
					}
					{
						String[] rgb2 = new String[pri.length + 1];
						System.arraycopy(pri, 0, rgb2, 0, pri.length);
						rgb2[pri.length] =  article.getText();
						pri = rgb2;
					}

				}
				String bang="";
				for (int i = 0; i < art.length; i++) {
					
					bang = bang +" "+art[i]+ "("+pri[i]+"), ";
				}
				lista=bang;
				compte.setText(lista);
				System.out.println(Arrays.toString(art));
				System.out.println(Arrays.toString(pri));
				//actualiser();
			}
		});
		btnajouter.setBounds(457, 290, 140, 23);
		contentPane.add(btnajouter);
		
		
		actualiser();
		JButton btnNewButton = new JButton("Commander");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lista.equals("")) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas ajoute d'article");
				} else {
					Date daten = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					
					String [] nomColonne = {"date_commande", "num_client"};
					String [] contenu = {formatter.format(daten),nom.getText()};
					
				   // db.insert("commande", nomColonne, contenu);
					
					Date date = Calendar.getInstance().getTime();  
	                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
	                String strDate = dateFormat.format(date);  
	                System.out.println("Converted String: " + nom.getText()); 
				    
					/*String query="select num_commande from commande where date_commande= "+ strDate +" and num_client="+ nom.getText();
					rs = db.executeQuery(query) ;
					try {
						while(rs.next()) {
							String nom2 = rs.getString("num_commande"); 
							System.out.println(nom2);
						}
						
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}*/
				   
					
					for (int i = 0; i < art.length; i++) {
						 
						String qr="";
						if(art[i].equals("coca")) {
							qr= "insert into detail (num_article,num_commande,qte) values (1,5,"+ pri[i]+ ")" ;
							db.executeUpdate(qr);
						}else if(art[i].equals("splash")) {
							qr= "insert into detail (num_article,num_commande,qte) values (3,5,"+ pri[i]+ ")" ;
							db.executeUpdate(qr);
						}else if(art[i].equals("krik")) {
							qr= "insert into detail (num_article,num_commande,qte) values (4,5,"+ pri[i]+ ")" ;
							db.executeUpdate(qr);
						}else if(art[i].equals("jina")) {
							qr= "insert into detail (num_article,num_commande,qte) values (6,5,"+ pri[i]+ ")" ;
							db.executeUpdate(qr);
						}else if(art[i].equals("berejenn")) {
							qr= "insert into detail (num_article,num_commande,qte) values (8,5,"+ pri[i]+ ")" ;
							db.executeUpdate(qr);
						}else if(art[i].equals("militon")) {
							qr= "insert into detail (num_article,num_commande,qte) values (9,5,"+ pri[i]+ ")" ;
							db.executeUpdate(qr);
						}else if(art[i].equals("5rr")) {
							qr= "insert into detail (num_article,num_commande,qte) values (11,5,"+ pri[i]+ ")" ;
							db.executeUpdate(qr);
						}
						// rd= db.executeQuery(qury);
						// qury="insert into detail (num_article,num_commande,qte) values (" + rd.getInt("num_article")+ "," + rs.getInt("num_commande")+ "," +pri[i]+ ");";
						// db.executeQuery(qury);
					}
				
					//System.out.println("information");
					//actualiser();
					JOptionPane.showMessageDialog(null, "Vous avez passez une commande");
				}
			}
		});
		btnNewButton.setBounds(10, 430, 140, 23);
		contentPane.add(btnNewButton);
		
	}
	public void table() {
		model.setRowCount(0);
		rs = db.selectAll("client");
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getString("num_client"),
						rs.getString("nom_client"),
						rs.getString("ville"),
						rs.getString("adres_client"),
						rs.getInt("tlf"),
						rs.getFloat("compte")
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		table_Client.setModel(model);
	}
	
	public void actualiser () {
		table();
		nom.setText("");
		ville.setText("");
	}
}

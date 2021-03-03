package en2dbproject;


import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.util.Date;

import application.BDD;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Article extends JFrame {

	private JPanel contentPane;
	private JTextField nom;
	private JTextField libelle;
	private JTextField prix;
	private JTextField qte;
	private JTextField famart;
	
	
	private JTextField rechercher;
	private ResultSet rs;
	private ResultSet rd;
	private BDD db;
	private DefaultTableModel model = new DefaultTableModel();
	
	private JTable table;
	private JTable table_Article;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Article frame = new Article();
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
	public Article() {
		db= new BDD();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		model.addColumn("Id");
		model.addColumn("Nom");
		model.addColumn("Libelle");
		model.addColumn("Prix unitaire");
		model.addColumn("Quantite en stock");
		model.addColumn("Date de creation");
		model.addColumn("Famille d'article");
		
		JScrollPane tableArticle = new JScrollPane();
		tableArticle.setBounds(10, 11, 661, 178);
		contentPane.add(tableArticle);
		
		table_Article = new JTable();
		table_Article.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nom.setText(String.valueOf(table_Article.getValueAt(table_Article.getSelectedRow(), 1)));
				libelle.setText(String.valueOf(table_Article.getValueAt(table_Article.getSelectedRow(), 2)));
				prix.setText(String.valueOf(table_Article.getValueAt(table_Article.getSelectedRow(), 3)));
				qte.setText(String.valueOf(table_Article.getValueAt(table_Article.getSelectedRow(), 4)));
				//date_creation.setText(String.valueOf(table_Article.getValueAt(table_Article.getSelectedRow(), 5)));
			}
		});
		table_Article.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table_Article.setModel(model);
		tableArticle.setViewportView(table_Article);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(10, 206, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lbllibelle = new JLabel("Libelle");
		lbllibelle.setBounds(10, 237, 86, 14);
		contentPane.add(lbllibelle);
		
		JLabel lblprix = new JLabel("Prix unitaire");
		lblprix.setBounds(10, 299, 86, 14);
		contentPane.add(lblprix);
		
		JLabel lblqte = new JLabel("Quantite en stock");
		lblqte.setBounds(10, 268, 86, 14);
		contentPane.add(lblqte);
		/*
		JLabel lblEmail = new JLabel("Compte");
		lblEmail.setBounds(10, 327, 86, 14);
		contentPane.add(lblEmail);*/
		
		nom = new JTextField();
		nom.setBounds(106, 200, 167, 20);
		contentPane.add(nom);
		nom.setColumns(10);
		
		libelle = new JTextField();
		libelle.setColumns(10);
		libelle.setBounds(106, 231, 167, 20);
		contentPane.add(libelle);
		
		prix = new JTextField();
		prix.setColumns(10);
		prix.setBounds(106, 293, 167, 20);
		contentPane.add(prix);
		
		qte = new JTextField();
		qte.setColumns(10);
		qte.setBounds(106, 262, 167, 20);
		contentPane.add(qte);
		
		/*compte = new JTextField();
		compte.setColumns(10);
		compte.setBounds(106, 321, 167, 20);
		contentPane.add(compte);*/
		
		String query = "SELECT nom from famArt;";
		rs = db.executeQuery(query);
		 
		String [] fam= new String [] {};
		
		try {
			while (rs.next()) {
				fam = append2Array(fam, rs.getString("nom"));
			}
		} catch(Exception e1) {
			System.err.println(e1.getMessage());
		}
		
		JLabel lblFamille = new JLabel("Famille ");
		lblFamille.setBounds(10, 327, 86, 14);
		contentPane.add(lblFamille);
		{ 
			 rs= db.executeQuery("select nom from famArt");
		 }
		String[] rgb = new String[] {};
		
		try {
			while (rs.next()) {
				String[] rgb2 = new String[rgb.length + 1];
				System.arraycopy(rgb, 0, rgb2, 0, rgb.length);
				rgb2[rgb.length] =  rs.getString("nom");
				rgb = rgb2;	
			}
		} catch(Exception e1) {
			System.err.println(e1.getMessage());
		}
		JComboBox famille = new JComboBox();
		famille.setModel(new DefaultComboBoxModel(rgb));
		famille.setBounds(106, 321, 140, 22);
		contentPane.add(famille);
		
		actualiser();
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (libelle.getText().equals("") || prix.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "information incomplete");
				} else {
					//int id = 0;
					try {
						//rs = db.selectAll("article", "idFamille_Article = '" +familleNom.getSelectedItem().toString()+ "'");
						//id = rs.getInt("idFamille_Article");
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
					Date daten = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					
					String etat = "";
					if(famille.getSelectedItem().toString().equals("boisson")) {
						etat = "1";
					} else if(famille.getSelectedItem().toString().equals("legumes")){
						etat = "2";
					}
					
					
					String [] nomColonne = {"nom_article","libelle", "prix_unitaire", "date_creation", "qte_stock", "num_famille"};
					String [] contenu = {nom.getText(),libelle.getText(), prix.getText(),formatter.format(daten),qte.getText(),etat};
					db.insert("article", nomColonne, contenu);
					actualiser();
				}
			}
		});
		btnNewButton.setBounds(10, 430, 140, 23);
		contentPane.add(btnNewButton);
		
		JComboBox trierPar = new JComboBox();
		trierPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom", "Libelle", "Prix unitaire", "Date de creation", "Famille"}));
		trierPar.setBounds(457, 202, 140, 22);
		contentPane.add(trierPar);
		
		JLabel lblNewLabel_2 = new JLabel("Rechercher par  ");
		lblNewLabel_2.setBounds(340, 237, 120, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox rechercherPar = new JComboBox();
		rechercherPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom", "Libelle", "Prix unitaire","Quantite en stock", "Date de creation", "Famille"}));
		rechercherPar.setBounds(457, 233, 140, 22);
		contentPane.add(rechercherPar);
		
		rechercher = new JTextField();
		rechercher.setBounds(457, 265, 140, 20);
		contentPane.add(rechercher);
		rechercher.setColumns(10);
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rechercher.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "SVP entrer qq chose");
				} else {
					model.setRowCount(0);
					if (rechercherPar.getSelectedItem().toString().equals("Id")) {
						int etat = Integer.parseInt(rechercher.getText());
						String query = "SELECT * FROM article WHERE num_article LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);	
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_article"),
										rs.getString("nom_article"),
										rs.getString("libelle"),
										rs.getString("prix_unitaire"),
										rs.getString("qte_stock"),
										rs.getString("date_creation"),
										rs.getString("num_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Article.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Nom")) {
						String etat = rechercher.getText();
						String query = "SELECT * FROM article WHERE nom_article LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);		
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_article"),
										rs.getString("nom_article"),
										rs.getString("libelle"),
										rs.getString("prix_unitaire"),
										rs.getString("qte_stock"),
										rs.getString("date_creation"),
										rs.getString("num_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Article.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Libelle")) {
						String etat = rechercher.getText();
						String query = "SELECT * FROM article WHERE libelle LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);	
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_article"),
										rs.getString("nom_article"),
										rs.getString("libelle"),
										rs.getString("prix_unitaire"),
										rs.getString("qte_stock"),
										rs.getString("date_creation"),
										rs.getString("num_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Article.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Prix unitaire")) {
						String etat = rechercher.getText();
						String query = "SELECT * FROM article WHERE prix_unitaire LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);			
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_article"),
										rs.getString("nom_article"),
										rs.getString("libelle"),
										rs.getString("prix_unitaire"),
										rs.getString("qte_stock"),
										rs.getString("date_creation"),
										rs.getString("num_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Article.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Quantite en sctock")) {
						String etat = rechercher.getText();
						String query = "SELECT * FROM article WHERE qte_stock LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);			
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_article"),
										rs.getString("nom_article"),
										rs.getString("libelle"),
										rs.getString("prix_unitaire"),
										rs.getString("qte_stock"),
										rs.getString("date_creation"),
										rs.getString("num_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Article.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Date de creation")){
						String etat = rechercher.getText();
						String query = "SELECT * FROM article WHERE date_creation LIKE '%" +etat+ "%'";
						{
							rs = db.executeQuery(query);
						}				
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_article"),
										rs.getString("nom_article"),
										rs.getString("libelle"),
										rs.getString("prix_unitaire"),
										rs.getString("qte_stock"),
										rs.getString("date_creation"),
										rs.getString("num_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Article.setModel(model);
						actualiser();
					}else {
						String etat = rechercher.getText();
						String query = "SELECT * FROM article WHERE num_famille LIKE '%" +etat+ "%'";
						{
							rs = db.executeQuery(query);
						}				
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_article"),
										rs.getString("nom_article"),
										rs.getString("libelle"),
										rs.getString("prix_unitaire"),
										rs.getString("qte_stock"),
										rs.getString("date_creation"),
										rs.getString("num_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Article.setModel(model);
						actualiser();
					}
				}
			}
		});
		btnRecherche.setBounds(301, 264, 146, 23);
		contentPane.add(btnRecherche);
		
		JButton btnNewButton_1 = new JButton("Trier par");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String etat = "";
				if(trierPar.getSelectedItem().toString().equals("Id")) {
					etat = "num_article";
				} else if(trierPar.getSelectedItem().toString().equals("Nom")){
					etat = "nom_article";
				}
				else if(trierPar.getSelectedItem().toString().equals("Libelle")){
					etat = "libelle";
				}
				else if(trierPar.getSelectedItem().toString().equals("Quantite en stock")){
					etat = "qte_stock";
				}
				else if(trierPar.getSelectedItem().toString().equals("Date de creation")){
					etat = "date_creation";
				}else if(trierPar.getSelectedItem().toString().equals("Prix unitaire")){
					etat = "prix_unitaire";
				}
				else {
					etat = "num_famille";
				}
				model.setRowCount(0);
				String query = "SELECT * FROM article order by " +etat;
				{
					rs = db.executeQuery(query);
				}				
				try {
					while (rs.next()) {
						model.addRow(new Object [] { rs.getString("num_article"),
								rs.getString("nom_article"),
								rs.getString("libelle"),
								rs.getString("prix_unitaire"),
								rs.getString("qte_stock"),
								rs.getString("date_creation"),
								rs.getString("num_famille")
						});
					}
				} catch(Exception e1) {
					System.err.println(e1.getMessage());
				}
				table_Article.setModel(model);
			}
		});
		btnNewButton_1.setBounds(301, 202, 146, 23);
		contentPane.add(btnNewButton_1);
		
		table();
		
		
		JButton btnNewButton2 = new JButton("Refresh");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualiser();
			}
		});
		btnNewButton2.setBounds(540, 430, 140, 23);
		contentPane.add(btnNewButton2);
		
		
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nom.getText().equals("") || libelle.getText().equals("") || qte.getText().equals("") || prix.getText().equals("") /*|| compte.getText().equals("")*/) {
					JOptionPane.showMessageDialog(null, "information incomplete");
				} else {
					String [] nomColonne = {"nom_article", "libelle", "qte_stock", "prix_unitaire", /*"compte"*/};
					String [] contenu = {nom.getText(), libelle.getText(), qte.getText(), prix.getText(), /*compte.getText()*/};
					int i = Integer.parseInt(String.valueOf(table_Article.getValueAt(table_Article.getSelectedRow(), 0)));
					db.update("article", nomColonne, contenu, "num_article = '" +i+ "'");
					actualiser();
				}
			}
		});
		btnModifier.setBounds(191, 430, 140, 23);
		contentPane.add(btnModifier);
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(String.valueOf(table_Article.getValueAt(table_Article.getSelectedRow(), 0)));
				if (JOptionPane.showConfirmDialog(null, "Confirmer", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					db.delete("article", "num_article = " +i);	
				}
				else {
					return;
				}
				actualiser();
			}
		});
		btnSupprimer.setBounds(357, 430, 140, 23);
		contentPane.add(btnSupprimer);
	}
	
	
	private String[] append2Array(String[] fam, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void table() {
		model.setRowCount(0);
		rs = db.selectAll("article");
		
		try {
			while (rs.next()) {
				//String query= "select * from famArt where num_famille = " + rs.getInt("num_famille");
				//rd=db.executeQuery(query);
				
				model.addRow(new Object [] { rs.getString("num_article"),
						rs.getString("nom_article"),
						rs.getString("libelle"),
						rs.getString("prix_unitaire"),
						rs.getInt("qte_stock"),
						rs.getString("date_creation"),
						rs.getString("num_famille")
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		table_Article.setModel(model);
	}
	
	public void actualiser () {
		table();
		nom.setText("");
		libelle.setText("");
		prix.setText("");
		qte.setText("");
		//compte.setText("");
	}
}

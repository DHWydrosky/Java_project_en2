package en2dbproject;


import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import application.BDD;

import java.sql.ResultSet;

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


public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField nom;
	private JTextField ville;
	private JTextField tel;
	private JTextField adresse;
	private JTextField compte;
	private JTextField rechercher;
	private ResultSet rs;
	private BDD db;
	private DefaultTableModel model = new DefaultTableModel();
	
	private JTable table;
	private JTable table_Client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
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
	public Client() {
		db= new BDD();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		model.addColumn("Id");
		model.addColumn("Nom");
		model.addColumn("Adresse");
		model.addColumn("Ville");
		model.addColumn("Telephone");
		model.addColumn("Compte");
		
		JScrollPane tableClient = new JScrollPane();
		tableClient.setBounds(10, 11, 661, 178);
		contentPane.add(tableClient);
		
		table_Client = new JTable();
		table_Client.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nom.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 1)));
				ville.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 2)));
				tel.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 4)));
				adresse.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 3)));
				compte.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 5)));
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
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(10, 206, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrnom = new JLabel("Ville");
		lblPrnom.setBounds(10, 237, 86, 14);
		contentPane.add(lblPrnom);
		
		JLabel lblTlphone = new JLabel("T\u00E9l\u00E9phone");
		lblTlphone.setBounds(10, 299, 86, 14);
		contentPane.add(lblTlphone);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(10, 268, 86, 14);
		contentPane.add(lblAdresse);
		
		JLabel lblEmail = new JLabel("Compte");
		lblEmail.setBounds(10, 327, 86, 14);
		contentPane.add(lblEmail);
		
		nom = new JTextField();
		nom.setBounds(106, 200, 167, 20);
		contentPane.add(nom);
		nom.setColumns(10);
		
		ville = new JTextField();
		ville.setColumns(10);
		ville.setBounds(106, 231, 167, 20);
		contentPane.add(ville);
		
		tel = new JTextField();
		tel.setColumns(10);
		tel.setBounds(106, 293, 167, 20);
		contentPane.add(tel);
		
		adresse = new JTextField();
		adresse.setColumns(10);
		adresse.setBounds(106, 262, 167, 20);
		contentPane.add(adresse);
		
		compte = new JTextField();
		compte.setColumns(10);
		compte.setBounds(106, 321, 167, 20);
		contentPane.add(compte);
		
		actualiser();
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nom.getText().equals("") || ville.getText().equals("") || tel.getText().equals("") || adresse.getText().equals("") || compte.getText().equals("")) {
					System.out.println("information incomplete");
				} else {
					String [] nomColonne = {"nom_client", "ville", "tlf", "adres_client", "compte"};
					String [] contenu = {nom.getText(), ville.getText(), tel.getText(), adresse.getText(),compte.getText()};
					db.insert("client", nomColonne, contenu);
					actualiser();
				}
			}
		});
		btnNewButton.setBounds(10, 430, 140, 23);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nom.getText().equals("") || ville.getText().equals("") || tel.getText().equals("") || adresse.getText().equals("") || compte.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "information incomplete");
				} else {
					String [] nomColonne = {"nom_client", "ville", "tlf", "adres_client", "compte"};
					String [] contenu = {nom.getText(), ville.getText(), tel.getText(), adresse.getText(), compte.getText()};
					int i = Integer.parseInt(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 0)));
					db.update("client", nomColonne, contenu, "num_client = '" +i+ "'");
					actualiser();
				}
			}
		});
		btnModifier.setBounds(191, 430, 140, 23);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 0)));
				if (JOptionPane.showConfirmDialog(null, "Confirmer", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					db.delete("client", "num_client = " +i);	
				}
				else {
					return;
				}
				actualiser();
			}
		});
		btnSupprimer.setBounds(357, 430, 140, 23);
		contentPane.add(btnSupprimer);
		
		JButton btnNewButton2 = new JButton("Refresh");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualiser();
			}
		});
		btnNewButton2.setBounds(540, 430, 140, 23);
		contentPane.add(btnNewButton2);
		
		
		
		
		JComboBox trierPar = new JComboBox();
		trierPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom", "Ville", "T\u00E9l\u00E9phone", "Adresse\t", "Compte"}));
		trierPar.setBounds(457, 202, 140, 22);
		contentPane.add(trierPar);
		
		JLabel lblNewLabel_2 = new JLabel("Rechercher par  ");
		lblNewLabel_2.setBounds(340, 237, 120, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox rechercherPar = new JComboBox();
		rechercherPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom", "Ville", "T\u00E9l\u00E9phone", "Adresse\t", "Compte"}));
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
						String query = "SELECT * FROM client WHERE num_client LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);	
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_client"),
										rs.getString("nom_client"),
										rs.getString("ville"),
										rs.getString("tlf"),
										rs.getString("adres_client"),
										rs.getString("compte")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Client.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Nom")) {
						String etat = rechercher.getText();
						String query = "SELECT * FROM client WHERE nom_client LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);		
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_client"),
										rs.getString("nom_client"),
										rs.getString("ville"),
										rs.getString("tlf"),
										rs.getString("adres_client"),
										rs.getString("compte")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Client.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Ville")) {
						String etat = rechercher.getText();
						String query = "SELECT * FROM client WHERE ville LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);	
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_client"),
										rs.getString("nom_client"),
										rs.getString("ville"),
										rs.getString("tlf"),
										rs.getString("adres_client"),
										rs.getString("compte")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Client.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("T\\u00E9l\\u00E9phone")) {
						String etat = rechercher.getText();
						String query = "SELECT * FROM client WHERE tlf LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);			
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_client"),
										rs.getString("nom_client"),
										rs.getString("ville"),
										rs.getString("tlf"),
										rs.getString("adres_client"),
										rs.getString("compte")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Client.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Adresse")) {
						String etat = rechercher.getText();
						String query = "SELECT * FROM client WHERE adres_client LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);			
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_client"),
										rs.getString("nom_client"),
										rs.getString("ville"),
										rs.getString("tlf"),
										rs.getString("adres_client"),
										rs.getString("compte")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Client.setModel(model);
					} else {
						String etat = rechercher.getText();
						String query = "SELECT * FROM client WHERE compte LIKE '%" +etat+ "%'";
						{
							rs = db.executeQuery(query);
						}				
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("num_client"),
										rs.getString("nom_client"),
										rs.getString("ville"),
										rs.getString("tlf"),
										rs.getString("adres_client"),
										rs.getString("compte")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						table_Client.setModel(model);
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
					etat = "num_client";
				} else if(trierPar.getSelectedItem().toString().equals("Nom")){
					etat = "nom_client";
				}
				else if(trierPar.getSelectedItem().toString().equals("Ville")){
					etat = "ville";
				}
				else if(trierPar.getSelectedItem().toString().equals("T\\\\u00E9l\\\\u00E9phone")){
					etat = "tlf";
				}
				else if(trierPar.getSelectedItem().toString().equals("Adresse")){
					etat = "adres_client";
				}
				else {
					etat = "compte";
				}
				model.setRowCount(0);
				String query = "SELECT * FROM client order by " +etat;
				{
					rs = db.executeQuery(query);
				}				
				try {
					while (rs.next()) {
						model.addRow(new Object [] { rs.getString("num_client"),
								rs.getString("nom_client"),
								rs.getString("ville"),
								rs.getString("tlf"),
								rs.getString("adres_client"),
								rs.getString("compte"),
						});
					}
				} catch(Exception e1) {
					System.err.println(e1.getMessage());
				}
				table_Client.setModel(model);
			}
		});
		btnNewButton_1.setBounds(301, 202, 146, 23);
		contentPane.add(btnNewButton_1);
		
		table();
		
		
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
		tel.setText("");
		adresse.setText("");
		compte.setText("");
	}
}

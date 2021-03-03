package en2dbproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Bon extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bon frame = new Bon();
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
	public Bon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JFrame fenetre2 = new JFrame();
		fenetre2.setSize(1000,800);
		fenetre2.setTitle(" Client ");
		fenetre2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre2.setResizable(false);
		//ImageIcon image = new ImageIcon("C:\\Users\\hp\\Desktop\\PROJETS INFORMATIQUES\\image2.png");

		
		//fenetre2.setIconImage(image.getImage());
		fenetre2.setLayout(null);
			
		JLabel label1 = new JLabel();
		label1.setText("Entreprise EDGARD ETIENNE S.A");
		label1.setFont(new Font("consolas",Font.PLAIN,25));
		label1.setForeground(new Color(0x123456));
		label1.setBackground(new Color(0xffffff));
		label1.setOpaque(true);
		label1.setBounds(300,20,425,30);
		

		/*JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1000, 800);
		panel.setBackground(Color.CYAN);
		//panel.setImage();
		fenetre2.setContentPane(panel);*/
		
		JButton bouton1 = new JButton();
	    bouton1.setText("CLIENT");
	    bouton1.setFocusable(false);
	    bouton1.setBounds(90,260,150,40);
	    bouton1.setFont(new Font("consolas",Font.CENTER_BASELINE,20));
	    contentPane.add(bouton1);
	    bouton1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{ 
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Client frame = new Client();
							frame.setVisible(true);
							fenetre2.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			
			}
			
			
		});
		
	    
	    JButton bouton2 = new JButton();
	    bouton2.setText("ARTICLE");
	    bouton2.setFocusable(false);
	    bouton2.setBounds(340,260,150,40);
	    bouton2.setFont(new Font("consolas",Font.CENTER_BASELINE,20));
	    contentPane.add(bouton2);
	    bouton2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{ 
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Article frame = new Article();
							frame.setVisible(true);
							contentPane.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			
			}
			
			
		});
	    
	    JButton bouton3 = new JButton();
	    bouton3.setText("COMMANDE");
	    bouton3.setFocusable(false);
	    bouton3.setBounds(560,260,250,40);
	    bouton3.setFont(new Font("consolas",Font.CENTER_BASELINE,20));
	    contentPane.add(bouton3);
	    bouton3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{ 
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Commande frame = new Commande();
							frame.setVisible(true);
							contentPane.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
			
			
			
		});
	    
	   
	  
		
		contentPane.add(label1);
		contentPane.setVisible(true); 
		
		
		
		
	}

}

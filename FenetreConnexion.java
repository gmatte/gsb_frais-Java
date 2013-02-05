package gsb_frais;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.security.*;
import java.util.ArrayList;

import javax.swing.*;

public class FenetreConnexion extends JFrame implements ActionListener {
	private JButton connexion;
	private JButton quitter;
	private JLabel titre;
	private JLabel login;
	private JLabel mdp;
	private JPanel panel;
	private JPanel pSaisie;
	private JPanel pConnexion;
	private JPanel logo;
	private JTextField tfLogin;
	private JTextField tfMdp;
//	private JOptionPane error;
//	private JOptionPane bravo;
	
	public FenetreConnexion(){
		
		this.connexion = new JButton("connexion");
		this.quitter = new JButton("Quitter l'application");
		this.titre = new JLabel("Connexion");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.login = new JLabel("Login : ");
		this.login.setHorizontalAlignment(SwingConstants.RIGHT);
		this.mdp = new JLabel("Mot de passe : ");
		this.mdp.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.panel = new JPanel();
		this.pSaisie = new JPanel();
		this.pConnexion = new JPanel();
		
		
		this.tfLogin = new JTextField();
		this.tfMdp = new JPasswordField();
		
		this.setTitle("Application Gsb_Frais");
		this.setSize(350, 280);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	
		panel.setLayout(new GridLayout(3,1,2,2));
		pSaisie.setLayout(new GridLayout(2,2,20,20));
		
		this.pSaisie.add(login);
		this.pSaisie.add(tfLogin);
		this.pSaisie.add(mdp);
		this.pSaisie.add(tfMdp);
		this.pSaisie.setBackground(Color.orange);
		
		this.pConnexion.add(connexion);
		this.pConnexion.add(quitter);	
		this.pConnexion.setBackground(Color.orange);
		
		this.panel.add(titre);
		this.panel.add(pSaisie);
		this.panel.add(pConnexion);
		this.panel.setBackground(Color.orange);
		
		connexion.addActionListener(this);
		quitter.addActionListener(this);
		
		this.setContentPane(panel);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		boolean trouve= false;
		if(event.getSource() == connexion){
			try {
				String Lelogin = tfLogin.getText();
				String mdpMd5 = MD5.getEncodedPassword(tfMdp.getText());
								
				ArrayList<Visiteur>lesComptables = Passerelle.chargerLesComptables();

				for(Visiteur unVisiteur : lesComptables){
					
					if(unVisiteur.getLogin().contains(Lelogin) && unVisiteur.getMdp().contains(mdpMd5)){
						String nom = unVisiteur.getNom();
						String prenom = unVisiteur.getPrenom();
						JOptionPane.showMessageDialog(null, "Bienvenue "+prenom+" "+nom, "Succès de la connexion", JOptionPane.INFORMATION_MESSAGE);
						FenetreComptable compta = new FenetreComptable(nom, prenom);
						dispose();
						trouve = true;
					}

				}
				if(!trouve){
					JOptionPane.showMessageDialog(null, "Mauvais login ou mauvais mot de passe", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
				}
			} 
			catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			catch(IndexOutOfBoundsException e1){
				e1.printStackTrace();
			}
		}
		
		if(event.getSource() == quitter){
			JOptionPane.showMessageDialog(null, "Au revoir", "Fermeture de l'application", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

}

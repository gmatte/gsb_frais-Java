package gsb_frais;

import java.awt.*;
import java.awt.event.*;
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
	private JTextField tfLogin;
	private JTextField tfMdp;
//	private JOptionPane error;
//	private JOptionPane bravo;
	
	public static String getEncodedPassword(String key) throws NoSuchAlgorithmException {
		byte[] uniqueKey = key.getBytes();
		byte[] hash = null;
		hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		StringBuffer hashString = new StringBuffer();
		for ( int i = 0; i<hash.length; ++i ) {
			String hex = Integer.toHexString(hash[i]);
			if ( hex.length() == 1 ) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length()-1));
			} 
			else {
				hashString.append(hex.substring(hex.length()-2));
			}
		}
		return hashString.toString();
	}		

	public FenetreConnexion(){
		this.connexion = new JButton("connexion");
		this.quitter = new JButton("Quitter l'application");
		this.titre = new JLabel("Connexion");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.login = new JLabel("Login");
		this.mdp = new JLabel("Mot de passe");
		
		this.panel = new JPanel();
		this.pSaisie = new JPanel();
		this.pConnexion = new JPanel();
		
		this.tfLogin = new JTextField();
		this.tfMdp = new JPasswordField();
		
		this.setTitle("Connexion");
		this.setSize(320,240);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		panel.setLayout(new GridLayout(3,1,2,2));
		pSaisie.setLayout(new GridLayout(2,2,20,20));
		this.panel.add(titre);
		this.pSaisie.add(login);
		this.pSaisie.add(tfLogin);
		this.pSaisie.add(mdp);
		this.pSaisie.add(tfMdp);
		this.panel.add(pSaisie);
		this.pConnexion.add(connexion);
		this.pConnexion.add(quitter);
		this.panel.add(pConnexion);
		
		connexion.addActionListener(this);
		quitter.addActionListener(this);
		
		this.setContentPane(panel);
		//this.pack();
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		boolean trouve= false;
		if(event.getSource() == connexion){
			try {
				String Lelogin = tfLogin.getText();
				String mdpMd5 = getEncodedPassword(tfMdp.getText());
								
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

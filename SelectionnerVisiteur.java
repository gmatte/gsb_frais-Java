package gsb_frais;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class SelectionnerVisiteur extends JFrame implements ActionListener {
	private JPanel panelGlobal;
	private JPanel panelLabel;
	private JPanel panelListe;
	
	private JLabel chooseVisiteur;
	private JButton valider;
	private JComboBox listeVisiteurs;
	private JComboBox listeMois;
	
	private JButton retour;
	
	private String nomComptable;
	private String prenomComptable;
	

	public SelectionnerVisiteur(String nomComptable, String prenomComptable) {
		this.nomComptable = nomComptable;
		this.prenomComptable = prenomComptable;
		panelListe = new JPanel();
		panelGlobal = new JPanel();
		panelLabel = new JPanel();
		chooseVisiteur = new JLabel(prenomComptable+" "+nomComptable+", veuillez choisir un visiteur : ");
		valider = new JButton("Valider");
		retour = new JButton("Retour");
		
		retour.addActionListener(this);
		valider.addActionListener(this);
		listeVisiteurs = new JComboBox();
		listeMois = new JComboBox();
		
		
		ArrayList<Visiteur>lesVisiteurs = Passerelle.chargerLesVisiteurs();
		
		for(Visiteur unVisiteur : lesVisiteurs){
			String id = unVisiteur.getId();
			String nom = unVisiteur.getNom();
			String prenom = unVisiteur.getPrenom();
			listeVisiteurs.addItem(new Visiteur(id, nom, prenom));
			
			
		}
		
		this.setTitle("Sélectionner un visiteur");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		panelLabel.setLayout(new FlowLayout());
		panelLabel.add(chooseVisiteur);
		
		panelListe.setLayout(new FlowLayout());
		panelListe.add(listeVisiteurs);
		panelListe.add(valider);
		
		panelGlobal.setLayout(new BorderLayout());
		panelGlobal.add(panelLabel, BorderLayout.NORTH);
		panelGlobal.add(panelListe, BorderLayout.CENTER);
		
		this.getContentPane().add(panelGlobal);
		
		this.setVisible(true);
	}



	

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == valider){
				String idVisiteur = ((Visiteur) listeVisiteurs.getSelectedItem()).getId();
				SelectionnerMois mois = new SelectionnerMois(idVisiteur, nomComptable, prenomComptable);
				dispose();
			}
			
		}
}

package gsb_frais;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectionnerVisiteur extends JFrame implements ActionListener {
	private JPanel panelGlobal;
	private JPanel panelLabel;
	private JPanel panelListe;
	private JPanel panelRetour;
	
	private JLabel chooseVisiteur;
	private JComboBox listeVisiteurs;
	private JComboBox listeMois;
	
	private JButton retour;
	private JButton valider;
	
	private String nomComptable;
	private String prenomComptable;
	

	public SelectionnerVisiteur(String nomComptable, String prenomComptable) {
		this.nomComptable = nomComptable;
		this.prenomComptable = prenomComptable;
		panelListe = new JPanel();
		panelGlobal = new JPanel();
		panelLabel = new JPanel();
		panelRetour = new JPanel();
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
		
		panelRetour.setLayout(new FlowLayout());
		panelRetour.add(retour);
		
		panelGlobal.setLayout(new BorderLayout());
		panelGlobal.add(panelLabel, BorderLayout.NORTH);
		panelGlobal.add(panelListe, BorderLayout.CENTER);
		panelGlobal.add(panelRetour, BorderLayout.SOUTH);
		
		this.getContentPane().add(panelGlobal);
		
		this.setVisible(true);
	}
	

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == valider){
				String idVisiteur = ((Visiteur) listeVisiteurs.getSelectedItem()).getId();
				String nomVisiteur = ((Visiteur) listeVisiteurs.getSelectedItem()).getNom();
				String prenomVisiteur = ((Visiteur) listeVisiteurs.getSelectedItem()).getPrenom();
				SelectionnerMois mois = new SelectionnerMois(idVisiteur, nomComptable, prenomComptable, nomVisiteur, prenomVisiteur);
				dispose();
			}
			
			if(event.getSource() == retour){
				FenetreComptable fen = new FenetreComptable(nomComptable, prenomComptable);
				dispose();
			}
			
		}
}

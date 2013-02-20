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

public class SelectionnerMois extends JFrame implements ActionListener {
	
	private JPanel panelGlobal;
	private JPanel panelLabel;
	private JPanel panelListe;
	private JPanel panelRetour;
	
	private JLabel chooseMois;
	private JComboBox listeMois;
	
	private JButton valider;
	private JButton retour;
	
	private String nomComptable;
	private String prenomComptable;
	private String nomVisiteur;
	private String prenomVisiteur;
	private String idVisiteur;
	
	public SelectionnerMois(String idVisiteur, String nomComptable, String prenomComptable, String nomVisiteur, String prenomVisiteur){
		this.idVisiteur = idVisiteur;
		this.nomComptable = nomComptable;
		this.prenomComptable = prenomComptable;
		this.nomVisiteur = nomVisiteur;
		this.prenomVisiteur = prenomVisiteur;
		
		panelGlobal = new JPanel();
		panelLabel = new JPanel();
		panelListe = new JPanel();
		panelRetour = new JPanel();
		
		chooseMois = new JLabel(prenomComptable+" "+nomComptable+", veuillez sélectionner le mois pour les fiches de frais");
		listeMois = new JComboBox();
		
		valider = new JButton("Valider");
		valider.addActionListener(this);
		retour = new JButton("Retour");
		retour.addActionListener(this);
		
		ArrayList<Mois>lesMois = Passerelle.chargerLesMois(idVisiteur);
		for(Mois unMois : lesMois){
			String mois = unMois.getMois();
			listeMois.addItem(new Mois(mois));
		}
		
		this.setTitle("Choisir une période pour les fiches frais du visiteur "+prenomVisiteur+" "+nomVisiteur);
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		panelLabel.setLayout(new FlowLayout());
		panelLabel.add(chooseMois);
		
		panelListe.setLayout(new FlowLayout());
		panelListe.add(listeMois);
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
			String leMois = ((Mois) listeMois.getSelectedItem()).getMois();
			FenetreFiche fen = new FenetreFiche(idVisiteur, leMois, nomComptable, prenomComptable, nomVisiteur, prenomVisiteur);
			dispose();
		}
		
		if(event.getSource() == retour){
			SelectionnerVisiteur fen = new SelectionnerVisiteur(nomComptable, prenomComptable);
			dispose();
		}
	}

}

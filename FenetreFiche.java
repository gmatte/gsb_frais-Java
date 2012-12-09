package gsb_frais;

import java.util.ArrayList;

import javax.swing.*;

public class FenetreFiche extends JFrame {
	private JPanel panel;
	private JLabel libelleFrais;
	private JTextField quantiteFrais;
	private JButton valider;
	
	private String idVisiteur;
	private String mois;
	private String nomComptable;
	private String prenomComptable;
	private String nomVisiteur;
	private String prenomVisiteur;

	public FenetreFiche(String idVisiteur, String mois, String nomComptable, String prenomComptable, String nomVisiteur, String prenomVisiteur){
		this.idVisiteur = idVisiteur;
		this.mois = mois;
		this.nomComptable = nomComptable;
		this.prenomComptable = prenomComptable;
		this.nomVisiteur = nomVisiteur;
		this.prenomVisiteur = prenomVisiteur;
		
		panel = new JPanel();
		libelleFrais = new JLabel();
		quantiteFrais = new JTextField();
		valider = new JButton("Valider");
		
		ArrayList<FraisForfait>lesFraisForfait = Passerelle.chargerLesFichefraisForfait(idVisiteur, mois);
		System.out.println(lesFraisForfait.size());
		for(FraisForfait unFraisForfait : lesFraisForfait){
			System.out.println(unFraisForfait.getLibelleFrais()+" "+unFraisForfait.getQuantite());
		}
		
		this.setVisible(true);
	}

}

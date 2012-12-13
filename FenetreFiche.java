package gsb_frais;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

public class FenetreFiche extends JFrame {
	private JPanel panelGlobal;
	private JPanel panelLabel;
	private JPanel panelTableFraisForfait;
	private JLabel libelleFrais;
	private JTable tableFraisForfait;
	private JScrollPane scroll;
	private JButton valider;
	
	private String idVisiteur;
	private String mois;
	private String mmaaaa;
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
		this.mmaaaa = mois.substring(4)+"/"+mois.substring(0, 4);
		
		panelGlobal = new JPanel();
		panelLabel = new JPanel();
		panelTableFraisForfait = new JPanel();
		libelleFrais = new JLabel("Frais forfaitisés pour la période "+mmaaaa);
		valider = new JButton("Valider");
		
		this.setTitle("Fiche de frais du visiteur "+nomVisiteur+" "+prenomVisiteur);
		this.setSize(980, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		ArrayList<FraisForfait>lesFraisForfait = Passerelle.chargerLesFichefraisForfait(idVisiteur, mois);
		int taille = lesFraisForfait.size();
		String title[] = {"Libellés des frais", "Quantité"};
		String[][]data = new String[taille][2];
		for(FraisForfait unFraisForfait : lesFraisForfait){
			String libelle = unFraisForfait.getLibelleFrais();
			int quantite = unFraisForfait.getQuantite();
			int index = lesFraisForfait.indexOf(unFraisForfait);
			data[index][0] = libelle;
			data[index][1] = quantite+"";			
		}
		tableFraisForfait = new JTable(data, title);
		scroll = new JScrollPane(tableFraisForfait);
		
		panelLabel.setLayout(new FlowLayout());
		panelLabel.add(libelleFrais);
		
		panelTableFraisForfait.setLayout(new BorderLayout());
		panelTableFraisForfait.add(tableFraisForfait.getTableHeader(), BorderLayout.NORTH);
		panelTableFraisForfait.add(tableFraisForfait, BorderLayout.CENTER);
		
		panelGlobal.setLayout(new BorderLayout());
		panelGlobal.add(panelLabel, BorderLayout.NORTH);
		panelGlobal.add(panelTableFraisForfait, BorderLayout.CENTER);
		
		this.getContentPane().add(panelGlobal);
		
		this.setVisible(true);
	}

}

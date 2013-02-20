package gsb_frais;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FenetreFiche extends JFrame implements ActionListener {
	private boolean DEBUG = false;
	
	private java.sql.Date sDate;
	private java.util.Date uDate;

	private JPanel panelGlobal;
	private JPanel panelGlobalForfait;
	private JPanel panelGlobalHorsForfait;
	private JPanel panelGlobalHorsClassification;
	private JPanel panelGlobalFicheFrais;
	private JPanel panelBouton;

	// Variables pour les frais forfait
	private JPanel panelLabelFraisForfait;
	private JPanel panelTableFraisForfait;
	private JLabel libelleTableForfait;
	private JTable tableFraisForfait;
	private JScrollPane scrollFraisForfait;

	// Variables pour les frais hors forfait
	private JPanel panelLabelHorsForfait;
	private JPanel panelTableHorsForfait;
	private JLabel libelleTableHorsForfait;
	private JTable tableHorsForfait;
	private JScrollPane scrollHorsForfait;

	// Variables pour les hors classification
	private JPanel panelLabelHorsClassification;
	private JPanel panelTableHorsClassification;
	private JLabel libelleHorsClassification;
	private JTable tableHorsClassification;
	private JScrollPane scrollHorsClassification;

	/* ------------------------------------------- */

	private JButton validerFiche;
	private JButton retour;

	private String idVisiteur;
	private String mois;
	private String mmaaaa;
	private String nomComptable;
	private String prenomComptable;
	private String nomVisiteur;
	private String prenomVisiteur;
	
	private JComboBox listeEtatFraisForfait = new JComboBox();
	private JComboBox listeEtatFraisHorsForfait = new JComboBox();
	private JComboBox listeEtatFraisHorsClassification = new JComboBox();
	
	/* -------------------------------------------- */
	private JPanel panelEtatFraisForfait;
	private JPanel panelEtatFraisHorsClassification;
	/* -------------------------------------------- */

	public FenetreFiche(String idVisiteur, String mois, String nomComptable, String prenomComptable, String nomVisiteur, String prenomVisiteur){
		this.idVisiteur = idVisiteur;
		this.mois = mois;
		this.nomComptable = nomComptable;
		this.prenomComptable = prenomComptable;
		this.nomVisiteur = nomVisiteur;
		this.prenomVisiteur = prenomVisiteur;
		this.mmaaaa = mois.substring(4)+"/"+mois.substring(0, 4);

		this.sDate = new java.sql.Date(System.currentTimeMillis());
		this.uDate = new java.util.Date();
		/*
		 * Conversion entre les 2 types de dates
		 */
		sDate = new java.sql.Date(uDate.getTime());
		
		
		ArrayList<EtatFrais>lesEtatsFrais = Passerelle.chargerLesEtatsFrais();
				
		for(EtatFrais unEtatFrais : lesEtatsFrais){
			listeEtatFraisForfait.addItem(unEtatFrais.getLibelleEtat());
			listeEtatFraisHorsForfait.addItem(unEtatFrais.getLibelleEtat());
			listeEtatFraisHorsClassification.addItem(unEtatFrais.getLibelleEtat());
		}
		
		
		// Instanciation des panels globaux et du panel pour le bouton valider
		panelGlobal = new JPanel();
		panelGlobalForfait = new JPanel();
		panelGlobalHorsForfait = new JPanel();
		panelGlobalHorsClassification = new JPanel();
		panelGlobalFicheFrais = new JPanel();
		panelBouton = new JPanel();

		// Instanciation des panels et label des frais forfait
		panelLabelFraisForfait = new JPanel();
		panelLabelFraisForfait.setBackground(Color.orange);
		panelLabelFraisForfait.setBorder(BorderFactory.createLineBorder(Color.black));
		panelTableFraisForfait = new JPanel();
		libelleTableForfait = new JLabel("Frais forfaitisés");

		// Instanciation des panels et labels des hors forfait
		panelLabelHorsForfait = new JPanel();
		panelLabelHorsForfait.setBackground(Color.orange);
		panelLabelHorsForfait.setBorder(BorderFactory.createLineBorder(Color.black));
		panelTableHorsForfait = new JPanel();
		libelleTableHorsForfait = new JLabel("Frais Hors Forfait");

		// Instanciation des panels et label des hors classification
		panelLabelHorsClassification = new JPanel();
		panelLabelHorsClassification.setBackground(Color.orange);
		panelLabelHorsClassification.setBorder(BorderFactory.createLineBorder(Color.black));
		panelTableHorsClassification = new JPanel();
		libelleHorsClassification = new JLabel("Hors classification");

		validerFiche = new JButton("Valider");
		validerFiche.addActionListener(this);
		retour = new JButton("Retour");
		retour.addActionListener(this);

		this.setTitle("Fiche de frais du visiteur "+nomVisiteur+" "+prenomVisiteur+" pour la période "+mmaaaa);
		this.setSize(900, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);

		// Création du tableau des frais forfaitisés
		ArrayList<FraisForfait>lesFraisForfait = Passerelle.chargerLesFichefraisForfait(idVisiteur, mois);
		int tailleForfait = lesFraisForfait.size();
		String titleForfait[] = {"Libellés des frais", "Quantité"};
		String[][]dataForfait = new String[tailleForfait][2];
		@SuppressWarnings("unused")
		double montantTotal = 0;
		for(FraisForfait unFraisForfait : lesFraisForfait){
			String libelle = unFraisForfait.getLibelleFrais();
			int quantite = unFraisForfait.getQuantite();
			int indexForfait = lesFraisForfait.indexOf(unFraisForfait);
			dataForfait[indexForfait][0] = libelle;
			dataForfait[indexForfait][1] = quantite+"";
			montantTotal += unFraisForfait.calcMontantTotal();
		}
		tableFraisForfait = new JTable(dataForfait, titleForfait);
		tableFraisForfait.getTableHeader().setBackground(new Color(91, 111, 192));
		tableFraisForfait.getTableHeader().setForeground(Color.white);
		scrollFraisForfait = new JScrollPane(tableFraisForfait);

		// Création du tableau des frais hors forfait
		ArrayList<FraisHorsForfait>lesHorsForfait = Passerelle.chargerLesFicheFraisHorsForfait(idVisiteur, mois);
		int tailleHorsForfait = lesHorsForfait.size();
		String titleHorsForfait[] = {"Date", "Libellé", "Montant", "Situation actuelle", "Nouvelle situation"};
		String[][]dataHorsForfait = new String[tailleHorsForfait][5];
		for(FraisHorsForfait unHorsForfait : lesHorsForfait){
			Date uneDate = unHorsForfait.getDateFraisHorsForfait();
			String unLibelle = unHorsForfait.getLibelleHorsForfait();
			double unMontant = unHorsForfait.getMontant();
			String libelleEtat = unHorsForfait.getLibelleEtat();
			int indexHorsForfait = lesHorsForfait.indexOf(unHorsForfait);
			dataHorsForfait[indexHorsForfait][0] = uneDate+"";
			dataHorsForfait[indexHorsForfait][1] = unLibelle;
			dataHorsForfait[indexHorsForfait][2] = unMontant+"";
			dataHorsForfait[indexHorsForfait][3] = libelleEtat;
			//dataHorsForfait[indexHorsForfait][4] = listeEtatFrais+"";
		}
		
		tableHorsForfait = new JTable(dataHorsForfait, titleHorsForfait);
		tableHorsForfait.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(listeEtatFraisHorsForfait));
		tableHorsForfait.getTableHeader().setBackground(new Color(91, 111, 192));
		tableHorsForfait.getTableHeader().setForeground(Color.white);

		scrollHorsForfait = new JScrollPane(tableHorsForfait);

		// Création du tableau des frais hors classification
		ArrayList<HorsClassification>lesHorsClassification = Passerelle.chargerLesFichesHorsClassification(idVisiteur, mois);
		int tailleHorsClassification = lesHorsClassification.size();
		String titleHorsClassification[] = {"Nombre de justificatifs", "Montant Valide", "Date de modification"};
		String[][]dataHorsClassification = new String[tailleHorsClassification][3];
		for(HorsClassification unHorsClassification : lesHorsClassification){
			int nbJustificatifs = unHorsClassification.getNbJustificatifs();
			double montantValide = unHorsClassification.getMontantValide();
			Date dateModif = unHorsClassification.getDateModif();
			int indexHorsClassification = lesHorsClassification.indexOf(unHorsClassification);
			dataHorsClassification[indexHorsClassification][0] = nbJustificatifs+"";
			dataHorsClassification[indexHorsClassification][1] = montantValide+"";
			dataHorsClassification[indexHorsClassification][2] = dateModif+"";
		}
		tableHorsClassification = new JTable(dataHorsClassification, titleHorsClassification);
		tableHorsClassification.getTableHeader().setBackground(new Color(91, 111, 192));
		tableHorsClassification.getTableHeader().setForeground(Color.white);
		scrollHorsClassification = new JScrollPane(tableHorsClassification);

		// Instanciation de l'affichage des frais forfait
		panelLabelFraisForfait.setLayout(new FlowLayout());
		panelLabelFraisForfait.add(libelleTableForfait);

		panelTableFraisForfait.setLayout(new BorderLayout());
		panelTableFraisForfait.add(tableFraisForfait.getTableHeader(), BorderLayout.NORTH);
		panelTableFraisForfait.add(tableFraisForfait, BorderLayout.CENTER);

		panelEtatFraisForfait = new JPanel();
		JLabel etatFraisForfait = new JLabel(lesFraisForfait.get(0).getLibelleEtat());
		
		panelEtatFraisForfait.setLayout(new FlowLayout());
		panelEtatFraisForfait.add(etatFraisForfait);
		panelEtatFraisForfait.add(listeEtatFraisForfait);
		
		panelGlobalForfait.setLayout(new BorderLayout());
		panelGlobalForfait.add(panelLabelFraisForfait, BorderLayout.NORTH);
		panelGlobalForfait.add(panelTableFraisForfait, BorderLayout.CENTER);
		panelGlobalForfait.add(panelEtatFraisForfait, BorderLayout.SOUTH);

		// Instanciation de l'affichage des frais hors forfait
		panelLabelHorsForfait.setLayout(new FlowLayout());
		panelLabelHorsForfait.add(libelleTableHorsForfait);

		panelTableHorsForfait.setLayout(new BorderLayout());
		panelTableHorsForfait.add(tableHorsForfait.getTableHeader(), BorderLayout.NORTH);
		panelTableHorsForfait.add(tableHorsForfait, BorderLayout.CENTER);

		panelGlobalHorsForfait.setLayout(new BorderLayout());
		panelGlobalHorsForfait.add(panelLabelHorsForfait, BorderLayout.NORTH);
		panelGlobalHorsForfait.add(panelTableHorsForfait, BorderLayout.CENTER);

		// Instanciation de l'affichage des hors classification
		panelLabelHorsClassification.setLayout(new FlowLayout());
		panelLabelHorsClassification.add(libelleHorsClassification);

		panelTableHorsClassification.setLayout(new BorderLayout());
		panelTableHorsClassification.add(tableHorsClassification.getTableHeader(), BorderLayout.NORTH);
		panelTableHorsClassification.add(tableHorsClassification, BorderLayout.CENTER);
		
		panelEtatFraisHorsClassification = new JPanel();
		JLabel etatFraisForfaitHorsClassification = new JLabel(lesHorsClassification.get(0).getLibelleEtat());
		
		panelEtatFraisHorsClassification.setLayout(new FlowLayout());
		panelEtatFraisHorsClassification.add(etatFraisForfaitHorsClassification);
		panelEtatFraisHorsClassification.add(listeEtatFraisHorsClassification);

		panelGlobalHorsClassification.setLayout(new BorderLayout());
		panelGlobalHorsClassification.add(panelLabelHorsClassification, BorderLayout.NORTH);
		panelGlobalHorsClassification.add(panelTableHorsClassification, BorderLayout.CENTER);
		panelGlobalHorsClassification.add(panelEtatFraisHorsClassification, BorderLayout.SOUTH);

		// Instanciation de l'affichage du bouton
		panelBouton.setLayout(new FlowLayout());
		panelBouton.add(validerFiche);
		panelBouton.add(retour);

		panelGlobalFicheFrais.setLayout(new BorderLayout(20, 20));
		panelGlobalFicheFrais.add(panelGlobalForfait, BorderLayout.NORTH);
		panelGlobalFicheFrais.add(panelGlobalHorsForfait, BorderLayout.CENTER);
		panelGlobalFicheFrais.add(panelGlobalHorsClassification, BorderLayout.SOUTH);

		panelGlobal.setLayout(new BorderLayout());
		panelGlobal.add(panelGlobalFicheFrais, BorderLayout.NORTH);
		panelGlobal.add(panelBouton, BorderLayout.CENTER);

		this.getContentPane().add(panelGlobal);

		this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == retour){
			SelectionnerMois lesMois = new SelectionnerMois(idVisiteur, nomComptable, prenomComptable, nomVisiteur, prenomVisiteur);
			dispose();
		}
		
		if(event.getSource() == validerFiche){
			ArrayList<FraisHorsForfait>validHorsForfait = new ArrayList<FraisHorsForfait>();
			//System.out.println(tableHorsForfait.getValueAt(0, 2));
			for(int i = 0 ; i < tableHorsForfait.getRowCount() ; i++){
				for(int j = 0 ; j < tableHorsForfait.getColumnCount() ; j++){
					
				}
				String dateHorsForfait = (String) tableHorsForfait.getValueAt(i, 0);
				String libelleHorsForfait = (String) tableHorsForfait.getValueAt(i, 1);
				String montantHorsForfait = (String) tableHorsForfait.getValueAt(i, 2);
				String newSituationHorsForfait = (String) tableHorsForfait.getValueAt(i, 4);
				System.out.println(dateHorsForfait+" "+libelleHorsForfait+" "+montantHorsForfait+" "+newSituationHorsForfait);
				System.out.println("\n");
			}
		}
		
		
	}

}
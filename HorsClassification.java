package gsb_frais;

import java.util.Date;
import java.sql.*;

public class HorsClassification {
	private String idVisiteur;
	private String mois;
	private int nbJustificatifs;
	private double montantValide;
	private Date dateModif;
	private String idEtat;
	private String libelleEtat;
	
	public HorsClassification(String unIdVisiteur, 
							  String unMois, 
							  int unNbJustificatifs, 
							  double unMontantValide, 
							  Date uneDateModif, 
							  String unIdEtat,
							  String unLibelleEtat){
		this.idVisiteur = unIdVisiteur;
		this.mois = unMois;
		this.nbJustificatifs = unNbJustificatifs;
		this.montantValide = unMontantValide;
		this.dateModif = uneDateModif;
		this.idEtat = unIdEtat;
		this.libelleEtat = unLibelleEtat;
	}

	public String getLibelleEtat() {
		return libelleEtat;
	}

	public double getMontantValide() {
		return montantValide;
	}

	public void setMontantValide(double montantValide) {
		this.montantValide = montantValide;
	}

	public Date getDateModif() {
		return dateModif;
	}

	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}

	public String getIdVisiteur() {
		return idVisiteur;
	}

	public String getMois() {
		return mois;
	}

	public int getNbJustificatifs() {
		return nbJustificatifs;
	}

	public String getIdEtat() {
		return idEtat;
	}
}

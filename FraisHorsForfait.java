package gsb_frais;

import java.util.Date;

public class FraisHorsForfait {
	private int idFraisHorsForfait;
	private String idVisiteur;
	private String mois;
	private String libelleHorsForfait;
	private Date dateFraisHorsForfait;
	private double montant;
	private String idEtat;
	private String libelleEtat;
	
	
	public FraisHorsForfait(int idFraisHorsForfait, String idVisiteur, String mois,
							String libelleHorsForfait, Date dateFraisHorsForfait,
							double montant, String idEtat, String unLibelleEtat){
		this.idFraisHorsForfait = idFraisHorsForfait;
		this.idVisiteur = idVisiteur;
		this.mois = mois;
		this.libelleHorsForfait = libelleHorsForfait;
		this.dateFraisHorsForfait = dateFraisHorsForfait;
		this.montant = montant;
		this.idEtat = idEtat;
		this.libelleEtat = unLibelleEtat;
	}


	public String getLibelleEtat() {
		return libelleEtat;
	}


	public void setIdEtat(String idEtat) {
		this.idEtat = idEtat;
	}


	public int getIdFraisHorsForfait() {
		return idFraisHorsForfait;
	}


	public String getIdVisiteur() {
		return idVisiteur;
	}


	public String getMois() {
		return mois;
	}


	public String getLibelleHorsForfait() {
		return libelleHorsForfait;
	}


	public Date getDateFraisHorsForfait() {
		return dateFraisHorsForfait;
	}


	public double getMontant() {
		return montant;
	}


	public String getIdEtat() {
		return idEtat;
	}
	

	

}

package gsb_frais;

public class FraisForfait {
	private String idFrais;
	private String libelleFrais;
	private int quantite;
	private String libelleEtat;
	private double montant;
	private String idVisiteur;
	private String mois;

	public FraisForfait(String unIdFrais, String unLibelleFrais, int uneQuantite, String unLibelleEtat, double unMontant, String unIdVisiteur, String unMois){
		this.idFrais = unIdFrais;
		this.libelleFrais = unLibelleFrais;
		this.quantite = uneQuantite;
		this.libelleEtat = unLibelleEtat;
		this.montant = unMontant;
		this.idVisiteur = unIdVisiteur;
		this.mois = unMois;
	}

	public String getIdFrais() {
		return idFrais;
	}

	public String getLibelleFrais() {
		return libelleFrais;
	}

	public int getQuantite() {
		return quantite;
	}

	public String getLibelleEtat() {
		return libelleEtat;
	}

	public double getMontant() {
		return montant;
	}

	public String getIdVisiteur() {
		return idVisiteur;
	}

	public String getMois() {
		return mois;
	}
}

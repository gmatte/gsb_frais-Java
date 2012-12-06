package gsb_frais;


public class Visiteur {
	private String id;
	private String nom;
	private String prenom;
	private String login;
	private String mdp;	
	private String typeUtilisateur;
	
	public String getTypeUtilisateur() {
		return typeUtilisateur;
	}

	public void setTypeUtilisateur(String typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
	}

	public Visiteur(String id, String nom, String prenom, String login, String mdp, String typeUtilisateur) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
		this.typeUtilisateur = typeUtilisateur;
	}
	 public Visiteur(String id,String nom, String prenom){
			this.id = id;
			this.nom = nom;
			this.prenom = prenom;
	 }

	public String getLogin() {
		return login;
	}

	public String getMdp() {
		return mdp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String toString() {
		return nom+" "+prenom; 
	}
	


}

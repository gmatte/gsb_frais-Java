package gsb_frais;

public class Mois {
	private String mois;
	
	public Mois(String unMois){
		mois = unMois;
	}
	
	public String getMois() {
		return this.mois;
	}
	
	public String toString(){
		String mm = mois.substring(4);
		String aaaa = mois.substring(0, 4);
		return mm+"/"+aaaa;
	}
	
	
}
package gsb_frais;

import javax.swing.table.DefaultTableCellRenderer;

public class EtatFrais extends DefaultTableCellRenderer {
	private String idEtat;
	private String libelleEtat;
	
	public EtatFrais(String unIdEtat, String unLibelleEtat){
		this.idEtat = unIdEtat;
		this.libelleEtat = unLibelleEtat;
	}

	public String getIdEtat() {
		return idEtat;
	}

	public String getLibelleEtat() {
		return libelleEtat;
	}
	
	public String toString(){
		return libelleEtat;
	}

}

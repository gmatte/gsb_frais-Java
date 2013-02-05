package gsb_frais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


public class Passerelle {
	
	public static ArrayList<Visiteur>chargerLesComptables(){
		
		ArrayList<Visiteur>lesComptables = new ArrayList<Visiteur>();
		Connection c = null;
		try { 
		       Class.forName("com.mysql.jdbc.Driver") ; 
		       c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", ""); 
		       Statement st = c.createStatement();
		       ResultSet rs = st.executeQuery("SELECT * FROM Visiteur where typeUtilisateur = 'Comptable'");
		       while(rs.next()){
					lesComptables.add(new Visiteur(rs.getString("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mdp"), rs.getString("typeutilisateur")));
		       }
		       rs.close();
		       c.close();
		} 
		catch (ClassNotFoundException erreur) {
		       System.out.println ("Driver non chargé !" + erreur); 
		} 
		catch (SQLException erreur) { 
			   System.out.println("Erreur SQL ! " + erreur);
		}
		return lesComptables;		
	}
	
	public static ArrayList<EtatFrais>chargerLesEtatsFrais(){
		ArrayList<EtatFrais>lesEtatsFrais = new ArrayList<EtatFrais>();
		Connection c = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", "");
			Statement st = c.createStatement(); 
		    ResultSet rs = st.executeQuery("SELECT * FROM Etat WHERE id NOT IN ('CL', 'CR')");
		    while(rs.next()){
		    	String idEtat = rs.getString("id");
		    	String libelleEtat = rs.getString("libelle");
		    	lesEtatsFrais.add(new EtatFrais(idEtat, libelleEtat));
		    }
		    rs.close();
		    c.close();
		} 
		catch (ClassNotFoundException erreur) {
		       System.out.println ("Driver non chargé !" + erreur); 
		} 
		catch (SQLException erreur) { 
			   System.out.println("Erreur SQL ! " + erreur);
		}
		return lesEtatsFrais;
	}

	public static ArrayList<Visiteur>chargerLesVisiteurs(){
		ArrayList<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
		Connection c = null;
		try { 
		       Class.forName("com.mysql.jdbc.Driver") ; 
		       c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", ""); 
		       Statement st = c.createStatement ();
		       ResultSet rs = st.executeQuery ("SELECT visiteur.id AS id, visiteur.nom AS nom, visiteur.prenom AS prenom"+
		    		   						   " FROM visiteur"+ 
		    		   						   " WHERE typeUtilisateur='Visiteur'"+
		    		   						   " AND visiteur.id"+
		    		   						   " IN(SELECT DISTINCT fichefrais.idVisiteur"+
		    		   						   	  " FROM fichefrais)");
		       while(rs.next()){
		    	   lesVisiteurs.add(new Visiteur(rs.getString(1), rs.getString(2), rs.getString(3)));
		       }
		       rs.close();
		       c.close ();
		} 
		catch (ClassNotFoundException erreur) {
		       System.out.println ("Driver non chargé !" + erreur); 
		} 
		catch (SQLException erreur) { 
			   System.out.println("Erreur SQL ! " + erreur);
		}
		return lesVisiteurs;	
	}
	
	public static ArrayList<Mois>chargerLesMois(String unVisiteur){
		
		ArrayList<Mois>lesMois = new ArrayList<Mois>();
		Connection c = null;
		try { 
		       Class.forName("com.mysql.jdbc.Driver") ; 
		       c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", ""); 
		       Statement st = c.createStatement ();
		       ResultSet rs = st.executeQuery ("SELECT fichefrais.mois as mois FROM Fichefrais WHERE fichefrais.idVisiteur = '"+unVisiteur+"' ORDER BY fichefrais.mois DESC");
		       while(rs.next()){
		    	   lesMois.add(new Mois(rs.getString("mois")));
		       }
		       rs.close();
		       c.close ();
		} 
		catch (ClassNotFoundException erreur) {
		       System.out.println ("Driver non chargé !" + erreur); 
		} 
		catch (SQLException erreur) { 
			   System.out.println("Erreur SQL ! " + erreur);
		}
		return lesMois;
		
	
	}
	public static ArrayList<FraisForfait>chargerLesFichefraisForfait(String unVisiteur, String unMois){
		ArrayList<FraisForfait>lesFichesFraisForfait = new ArrayList<FraisForfait>();
		Connection c = null;
		try { 
		       Class.forName("com.mysql.jdbc.Driver") ; 
		       c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", ""); 
		       Statement st = c.createStatement();
		       ResultSet rs = st.executeQuery("SELECT fraisforfait.id AS idfrais, fraisforfait.libelle AS libelle_frais, lignefraisforfait.quantite AS quantite, "+
		       								  "etat.libelle AS libelle_etat, fraisforfait.montant AS montant, lignefraisforfait.idVisiteur AS unIdVisiteur, "+
		       								  "lignefraisforfait.mois AS unMois "+
		       								  "FROM lignefraisforfait, fraisforfait, etat "+
		       								  "WHERE fraisforfait.id = lignefraisforfait.idfraisforfait "+
		       								  "AND etat.id = lignefraisforfait.idEtat "+
		       								  "AND lignefraisforfait.idVisiteur = '"+unVisiteur+"' "+
		       								  "AND lignefraisforfait.mois = '"+unMois+"' "+
		       								  "ORDER BY lignefraisforfait.idfraisforfait");
		       while(rs.next()){
		    	   String idFrais = rs.getString("idfrais");
		    	   String libelleFrais = rs.getString("libelle_frais");
		    	   int quantite = rs.getInt("quantite");
		    	   String libelleEtat = rs.getString("libelle_etat");
		    	   double montant = rs.getDouble("montant");
		    	   String idVisiteur = rs.getString("unIdVisiteur");
		    	   String mois = rs.getString("unMois");
		    	   lesFichesFraisForfait.add(new FraisForfait(idFrais, 
		    			   									  libelleFrais, 
		    			   									  quantite, 
		    			   									  libelleEtat, 
		    			   									  montant, 
		    			   									  idVisiteur, 
		    			   									  mois));
		       }
		       rs.close();
		       c.close ();
		       
		} 
		catch (ClassNotFoundException erreur){
		       System.out.println ("Driver non chargé !" + erreur); 
		} 
		catch (SQLException erreur){ 
			   System.out.println("Erreur SQL ! " + erreur);
		}
		return lesFichesFraisForfait;		
	
	}	
	public static ArrayList<FraisHorsForfait>chargerLesFicheFraisHorsForfait(String unVisiteur,String unMois ){
		ArrayList<FraisHorsForfait> lesFichesFraisHorsForfait = new ArrayList<FraisHorsForfait>();
		Connection c = null;
		try { 
		       Class.forName ("com.mysql.jdbc.Driver") ; 
		       c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", "");
		       Statement st = c.createStatement ();
		       ResultSet rs = st.executeQuery ("SELECT *"+
		    		   							" FROM lignefraishorsforfait"+
		    		   							" INNER JOIN etat"+
		    		   							" ON etat.id = lignefraishorsforfait.idEtat"+
		    		   							" WHERE lignefraishorsforfait.idVisiteur ='"+unVisiteur+"'"+
		    		   							" AND lignefraishorsforfait.mois ='"+unMois+"'");
		      
			       while(rs.next()){
			    	   int idFraisHorsForfait = rs.getInt("id");
			    	   String idVisiteur = rs.getString("idVisiteur");
			    	   String mois = rs.getString("mois");
			    	   String libelle = rs.getString("libelle");
			    	   Date dateHorsForfait = rs.getDate("date");
			    	   double montant = rs.getDouble("montant");
			    	   String idEtat = rs.getString("idEtat");
			    	   String libelleEtat = rs.getString("etat.libelle");
			    	   lesFichesFraisHorsForfait.add(new FraisHorsForfait(idFraisHorsForfait, 
			    			   											  idVisiteur, 
			    			   											  mois, 
			    			   											  libelle, 
			    			   											  dateHorsForfait, 
			    			   											  montant, 
			    			   											  idEtat,
			    			   											  libelleEtat));
			       }
		    	   
		       rs.close();
		       c.close ();
		} 
		catch (ClassNotFoundException erreur) {
		       System.out.println ("Driver non chargé !" + erreur); 
		} 
		catch (SQLException erreur) { 
			   System.out.println("Erreur SQL ! " + erreur);
		}
		return lesFichesFraisHorsForfait;	
	}	
	public static ArrayList<HorsClassification>chargerLesFichesHorsClassification(String unVisiteur, String unMois ){
		ArrayList<HorsClassification>fraisHorsClassification = new ArrayList<HorsClassification>();
		Connection c = null;
		try { 
		       Class.forName ("com.mysql.jdbc.Driver") ; 
		       c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", "");
		       Statement st = c.createStatement ();
		       ResultSet rs = st.executeQuery ( "SELECT *"+
		    		   						 	" FROM fichefrais"+
		    		   							" INNER JOIN etat"+
		    		   							" ON etat.id = fichefrais.idEtat"+
		    		   							" WHERE fichefrais.idVisiteur ='"+unVisiteur+"'"+
		    		   							" AND fichefrais.mois ='"+unMois+"'");
		      
			       while(rs.next()){
			    	   String idVisiteur = rs.getString("idVisiteur");
			    	   String mois = rs.getString("mois");
			    	   int nbJustificatifs = rs.getInt("nbJustificatifs");
			    	   double montantValide = rs.getDouble("montantValide");
			    	   Date dateModif = rs.getDate("dateModif");
			    	   String idEtat = rs.getString("idEtat");
			    	   String libelleEtat = rs.getString("etat.libelle");
			    	   
			    	   fraisHorsClassification.add(new HorsClassification(idVisiteur, mois, nbJustificatifs, montantValide, dateModif, idEtat, libelleEtat));
			       }
		    	   
		       rs.close();
		       c.close ();
		} 
		catch (ClassNotFoundException erreur) {
		       System.out.println ("Driver non chargé !" + erreur); 
		} 
		catch (SQLException erreur) { 
			   System.out.println("Erreur SQL ! " + erreur);
		}
		return fraisHorsClassification; 	
	}
	
	public static void validationFichesFraisForfait(String unVisiteur, String mois, boolean accepte){
		String etat;
		if(accepte == true){
			etat = "VA";
		}
		
		else{
			etat = "RF";
		}
			
		Connection c = null;
		try { 
		       Class.forName("com.mysql.jdbc.Driver") ; 
		       c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", ""); 
		       Statement st = c.createStatement();
		       st.executeQuery("UPDATE fichefrais"+
				  " SET idEtat = '"+ etat +"', dateModif = now()"+
				  " WHERE fichefrais.idVisiteur ='" + unVisiteur +
				  "' AND fichefrais.mois = '" + mois + "'");
		       
		       c.close();
		} 
		catch (ClassNotFoundException erreur) {
		       System.out.println ("Driver non chargé ! " + erreur); 
		} 
		catch (SQLException erreur) { 
			   System.out.println("Erreur SQL ! " + erreur);
		}	
	}

	public static void validationFichesFraisForfait(String unVisiteur, String mois, ArrayList<FraisForfait>lesFiches){
	
	  		for(FraisForfait uneFicheFrais: lesFiches){
	  		String idEtat = uneFicheFrais.getLibelleEtat();
	  		int quantite = uneFicheFrais.getQuantite();
	  		Connection c = null;
	  		try { 
	  			Class.forName("com.mysql.jdbc.Driver") ; 
	  			c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", ""); 
	  			Statement st = c.createStatement();
	  			if(idEtat!="RF"){
	    			st.executeQuery("UPDATE lignefraisforfait"+
	    						  	      " SET quantite = "+quantite+", lignefraisforfait.idEtat = '"+idEtat+"'"+
	    						  	      " WHERE lignefraisforfait.idvisiteur = '"+unVisiteur+"'"+
	    						  	      " AND lignefraisforfait.mois = '"+mois +"'"+
	    						  	      " AND lignefraisforfait.idfraisforfait = '"+idEtat +"'");
	  			}
	  			c.close();
	  		} 
	  		catch (ClassNotFoundException erreur) {
	  			System.out.println ("Driver non chargé !" + erreur); 
	  		} 
	  		catch (SQLException erreur) { 
	  			System.out.println("Erreur SQL ! " + erreur);
	  		}	
	  	}
	}
	
	public static void validationFichesFraisHorsForfait(String unVisiteur, String mois, ArrayList<FraisHorsForfait>lesFiches){
	

  		for(FraisHorsForfait uneFicheFrais: lesFiches){
  		String idEtat = uneFicheFrais.getIdEtat();
  		
  		Connection c = null;
  		try { 
  			Class.forName("com.mysql.jdbc.Driver") ; 
  			c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", ""); 
  			Statement st = c.createStatement();
  			st.executeQuery("UPDATE lignefraishorsforfait"+
  					  " SET lignefraishorsforfait.idEtat = '"+ idEtat+"'"+
  					  " WHERE lignefraishorsforfait.idVisiteur = '"+unVisiteur+"'"+
  					  " AND lignefraishorsforfait.mois = '"+ mois+"'"+
  					  " AND lignefraishorsforfait.id = '"+idEtat+"'");
  			
  			c.close();
  		} 
  		catch (ClassNotFoundException erreur) {
  			System.out.println ("Driver non chargé !" + erreur); 
  		} 
  		catch (SQLException erreur) { 
  			System.out.println("Erreur SQL ! " + erreur);
  		}	
  	}
	}
}

package gsb_frais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Passerelle {
	
	public static ArrayList<Visiteur>chargerLesComptables(){
		
		ArrayList<Visiteur> lesComptables = new ArrayList<Visiteur>();
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
		    	   System.out.println(rs.getString("libelle_frais"));
		    	   String idFrais = rs.getString("idfrais");
		    	   String libelleFrais = rs.getString("libelle_frais");
		    	   int quantite = rs.getInt("quantite");
		    	   String libelleEtat = rs.getString("libelle_etat");
		    	   double montant = rs.getDouble("montant");
		    	   String idVisiteur = rs.getString("unIdVisiteur");
		    	   String mois = rs.getString("unMois");
		    	   lesFichesFraisForfait.add(new FraisForfait(idFrais, libelleFrais, quantite, libelleEtat, montant, idVisiteur, mois));
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
		return lesFichesFraisForfait;		
	
	}	
}

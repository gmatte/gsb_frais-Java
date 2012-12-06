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

	public static ArrayList<Visiteur> chargerLesVisiteurs(){
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
	
	public static ArrayList<Mois> chargerLesMois(String unVisiteur){
		
		ArrayList<Mois> lesMois = new ArrayList<Mois>();
		Connection c = null;
		try { 
		       Class.forName("com.mysql.jdbc.Driver") ; 
		       c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", ""); 
		       Statement st = c.createStatement ();
		       ResultSet rs = st.executeQuery ("SELECT fichefrais.mois as mois FROM Fichefrais where fichefrais.idVisiteur = '"+unVisiteur+"'");
		       while(rs.next()){
		    	   lesMois.add(new Mois(rs.getString(1)));
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
	public static ArrayList<Visiteur> chargerLesFichefraisForfait(Visiteur unVisiteur,Mois unMois ){
		ArrayList<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
		Connection c = null;
		try { 
		       Class.forName("com.mysql.jdbc.Driver") ; 
		       c = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", ""); 
		       Statement st = c.createStatement ();
		       ResultSet rs = st.executeQuery ("SELECT * FROM Fichefrais where typeUtilisateur = "+unVisiteur+" and mois = "+unMois);
		       while(rs.next()){
		    	   lesVisiteurs.add(new Visiteur(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
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
}

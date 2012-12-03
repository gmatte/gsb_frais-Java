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
		       Class.forName("org.postgresql.Driver") ; 
		       c = DriverManager.getConnection("jdbc:postgresql:gsb_frais",
		    		   							"Guillaume","Gm20Hb19*"); 
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
		       Class.forName ("org.postgresql.Driver") ; 
		       c = DriverManager.getConnection ("jdbc:postgresql:gsb_frais", "Guillaume","Gm20Hb19*"); 
		       Statement st = c.createStatement ();
		       ResultSet rs = st.executeQuery ("SELECT * FROM visiteur where typeutilisateur = 'Visiteur'");
		       while(rs.next()){
		    	   lesVisiteurs.add(new Visiteur(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(4)));
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

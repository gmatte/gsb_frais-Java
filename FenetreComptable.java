package gsb_frais;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

public class FenetreComptable extends JFrame implements ActionListener {
	
	private JPanel panelGlobal;
	private JPanel panelLabel;
	private JLabel labelBienvenue;
	private JLabel labelQuestion;
	private JButton voirFicheFrais;
	private JButton deconnecter;
	private JPanel panelBouton;
	private String prenomComptable;
	private String nomComptable;
	
	public FenetreComptable(String nomComptable, String prenomComptable){
		this.setTitle("Bienvenue "+nomComptable+" "+prenomComptable);
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.prenomComptable = prenomComptable;
		this.nomComptable = nomComptable;
		
		panelGlobal = new JPanel();
		panelBouton = new JPanel();
		panelLabel = new JPanel();
		
		labelBienvenue = new JLabel("Bienvenue "+nomComptable+" "+prenomComptable);
		labelBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		labelQuestion = new JLabel("Que voulez-vous faire ?");
		labelQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		
		deconnecter = new JButton("Se déconnecter");
		deconnecter.addActionListener(this);
		voirFicheFrais = new JButton("Voir les fiches de frais");
		voirFicheFrais.addActionListener(this);
		
		panelLabel.setLayout(new BorderLayout());
		panelLabel.add(labelBienvenue, BorderLayout.NORTH);
		panelLabel.add(labelQuestion, BorderLayout.SOUTH);
		
		panelBouton.setLayout(new FlowLayout());
		panelBouton.add(voirFicheFrais);
		panelBouton.add(deconnecter);
		
		panelGlobal.setLayout(new BorderLayout());
		panelGlobal.add(panelLabel, BorderLayout.NORTH);
		panelGlobal.add(panelBouton, BorderLayout.SOUTH);
		
		this.getContentPane().add(panelGlobal);
		
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == deconnecter){
			JOptionPane.showMessageDialog(null, "Au revoir "+prenomComptable+" "+nomComptable , "Déconnexion", JOptionPane.INFORMATION_MESSAGE);
			FenetreConnexion connexion = new FenetreConnexion();
			dispose();
		}
		
		if(e.getSource() == voirFicheFrais){
			SelectionnerVisiteur fen = new SelectionnerVisiteur(nomComptable, prenomComptable);
			dispose();
		}
		
	}

}

package gsb_frais;


import java.awt.*;
import javax.swing.*;

class AfficheImage extends JPanel{
    // D�claration de la propri�t� fond de type Image
    private Image fond;

    public AfficheImage(String s){
        // Permet de charger une image
        this.fond = getToolkit().getImage(s);
    }
    
   /* Cette m�thode est appel�e  automatiquement lors du chargement ou du redimensionnement 
        de la fen�tre*/
    
   @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }
}
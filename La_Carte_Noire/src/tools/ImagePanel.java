package tools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {   
    private String nom;
    
    public ImagePanel(String nom){
        super();
        this.nom = nom;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage image = null;
        
        super.paintComponent(g);
        Graphics2D graphic = (Graphics2D) g;
        File fichier = new File(ImageProvider.getImageFile(nom,null));
        try {
            image = ImageIO.read(fichier);
        } catch (IOException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        graphic.drawImage(image, 0, 0, this);
    } 
}

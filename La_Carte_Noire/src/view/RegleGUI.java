package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import tools.ImagePanel;

public class RegleGUI extends javax.swing.JFrame {
    private JButton BT_Retour;
    private JPanel panel, regles;
    
    public RegleGUI() {
        initComponents();
    }
   
    private void initComponents() {
        this.setTitle("Règles");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(965,620));
        
        panel = new ImagePanel("menu");
        panel.setPreferredSize(new Dimension(965,620));
        panel.setLayout(new AbsoluteLayout());
        
        BT_Retour = new JButton();
        BT_Retour.setIcon(new ImageIcon(getClass().getResource("/images/Bouton about_dark.png")));
        BT_Retour.setBorder(null);
        BT_Retour.setContentAreaFilled(false);
        BT_Retour.setMargin(new Insets(0, 0, 0, 0)); 
        BT_Retour.addActionListener(this::BT_RetourActionPerformed);
        panel.add(BT_Retour,new AbsoluteConstraints(25, 25, -1, -1));
        
        regles = new ImagePanel("regles");
        regles.setPreferredSize(new Dimension(900,400));
        regles.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(28, 0, 0)));
        panel.add(regles,new AbsoluteConstraints(32, 170, -1, -1));

        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }           
    
    private void BT_RetourActionPerformed(ActionEvent evt) {
        this.dispose();
    }
}

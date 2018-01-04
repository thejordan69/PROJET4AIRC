package view;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import tools.ImagePanel;

public class ModeGUI extends JFrame {
    private JButton BT_Local, BT_Multi, BT_Retour;
    private JPanel panel;  
    
    public ModeGUI() {
        initComponents();
    }
                          
    private void initComponents() {
        this.setTitle("Mode de jeu");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(965,620));
        
        panel = new ImagePanel("menu");
        panel.setPreferredSize(new Dimension(965,620));
        panel.setLayout(new AbsoluteLayout());
        
        BT_Retour = new JButton();
        BT_Retour.setIcon(new ImageIcon(getClass().getResource("/images/Bouton_retour_dark.png")));
        BT_Retour.setBorder(null);
        BT_Retour.setContentAreaFilled(false);
        BT_Retour.setMargin(new Insets(0, 0, 0, 0)); 
        BT_Retour.addActionListener(this::BT_RetourActionPerformed);
        panel.add(BT_Retour,new AbsoluteConstraints(25, 25, -1, -1));
        
        BT_Local = new JButton();
        BT_Local.setIcon(new ImageIcon(getClass().getResource("/images/Bouton_local_dark.png")));
        BT_Local.setBorder(null);
        BT_Local.setContentAreaFilled(false);
        BT_Local.setMargin(new Insets(0, 0, 0, 0));
        BT_Local.addActionListener(this::BT_LocalActionPerformed);
        panel.add(BT_Local,new AbsoluteConstraints(282, 200, -1, -1));
        
        BT_Multi = new JButton();
        BT_Multi.setIcon(new ImageIcon(getClass().getResource("/images/Bouton_reseau_dark.png")));
        BT_Multi.setBorder(null);
        BT_Multi.setContentAreaFilled(false);
        BT_Multi.setMargin(new Insets(0, 0, 0, 0));
        BT_Multi.addActionListener(this::BT_MultiActionPerformed);
        panel.add(BT_Multi,new AbsoluteConstraints(282, 300, -1, -1));
        
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }                      
                          
    private void BT_RetourActionPerformed(ActionEvent evt) {
        this.dispose();
    }
    
    private void BT_LocalActionPerformed(ActionEvent evt) {                                          
        new PseudoGUI();
    } 
    
    private void BT_MultiActionPerformed(ActionEvent evt) {                                          
            new ReseauGUI();
    }     
}

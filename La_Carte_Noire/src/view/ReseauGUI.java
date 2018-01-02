package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import tools.ImagePanel;

public class ReseauGUI extends JFrame {
    private JPanel panel;
    private JButton BT_Retour, BT_Jouer;
    private JLabel JL_Serveur, JL_Client, JL_IP, JL_Info, JL_Erreur, JL_Pseudo;
    private JRadioButton RB_Serveur, RB_Client;
    private ButtonGroup group;
    private JTextField TF_IP, TF_Pseudo;
    
    public ReseauGUI(){
        initComponents();
    }
    
    private void initComponents() {
        this.setTitle("Mode en réseau");
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
        
        JL_Serveur = new JLabel("Serveur",JLabel.CENTER);
        JL_Serveur.setFont(JL_Serveur.getFont().deriveFont(18f));
        JL_Serveur.setForeground(Color.WHITE);
        JL_Client = new JLabel("Client",JLabel.CENTER);
        JL_Client.setFont(JL_Serveur.getFont().deriveFont(18f));
        JL_Client.setForeground(Color.WHITE);
        panel.add(JL_Serveur,new AbsoluteConstraints(250, 185, -1, -1));
        panel.add(JL_Client,new AbsoluteConstraints(650, 185, -1, -1));
        
        RB_Client = new JRadioButton();
        RB_Client.setContentAreaFilled(false);
        RB_Client.setActionCommand("Client");
        RB_Serveur = new JRadioButton();
        RB_Serveur.setContentAreaFilled(false);
        RB_Serveur.setActionCommand("Serveur");
        group = new ButtonGroup();
        group.add(RB_Client);
        group.add(RB_Serveur);
        RB_Serveur.addActionListener(this::Radio_ButtonsActionPerformed);
        RB_Client.addActionListener(this::Radio_ButtonsActionPerformed);
        panel.add(RB_Serveur,new AbsoluteConstraints(265, 215, -1, -1));
        panel.add(RB_Client,new AbsoluteConstraints(665, 215, -1, -1));
        
        JL_IP = new JLabel("Votre IP est : " + getIP(),JLabel.CENTER);
        JL_IP.setFont(JL_IP.getFont().deriveFont(15f));
        JL_IP.setForeground(Color.WHITE);
        panel.add(JL_IP,new AbsoluteConstraints(190, 250, -1, -1));
        JL_Info = new JLabel("Renseignez l'adresse IP de l'hôte :",JLabel.CENTER);
        JL_Info.setFont(JL_Info.getFont().deriveFont(15f));
        JL_Info.setForeground(Color.WHITE);
        panel.add(JL_Info,new AbsoluteConstraints(570, 250, -1, -1));
        JL_Erreur = new JLabel("L'adresse IP n'est pas valide",JLabel.CENTER);
        JL_Erreur.setFont(JL_Erreur.getFont().deriveFont(15f));
        JL_Erreur.setForeground(Color.WHITE);
        JL_Erreur.setVisible(false);
        panel.add(JL_Erreur,new AbsoluteConstraints(570, 315, -1, -1));
        
        TF_IP = new JTextField();
        TF_IP.setEnabled(false);
        TF_IP.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        panel.add(TF_IP,new AbsoluteConstraints(620, 280, 120, 20));
        JL_Pseudo = new JLabel("Votre pseudo :",JLabel.CENTER);
        JL_Pseudo.setFont(JL_Pseudo.getFont().deriveFont(15f));
        JL_Pseudo.setForeground(Color.WHITE);
        panel.add(JL_Pseudo,new AbsoluteConstraints(410, 325, 120, 20));
        TF_Pseudo = new JTextField();
        TF_Pseudo.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        panel.add(TF_Pseudo,new AbsoluteConstraints(410, 350, 120, 20));

        BT_Jouer = new JButton();
        BT_Jouer.setIcon(new ImageIcon(getClass().getResource("/images/Bouton jouer_dark.png")));
        BT_Jouer.setBorder(null);
        BT_Jouer.setContentAreaFilled(false);
        BT_Jouer.setMargin(new Insets(0, 0, 0, 0));
        BT_Jouer.setEnabled(false);
        BT_Jouer.addActionListener(this::BT_JouerActionPerformed);
        panel.add(BT_Jouer,new AbsoluteConstraints(400, 400, -1, -1));
        this.getContentPane().add(panel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }     
     
    private void BT_RetourActionPerformed(ActionEvent evt) {
        this.dispose();
    }
    
    private String getIP() {
        String ip = ""; 
        try {
            ip = Inet4Address.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ReseauGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip;
    }
    
    private void checkChamps(){
        if(group.getSelection().getActionCommand().equals("Serveur")){
            TF_IP.setEnabled(false);
            JL_Erreur.setVisible(false);
            if(!TF_Pseudo.getText().equals("")){
                BT_Jouer.setEnabled(true);
            }
            else{
                BT_Jouer.setEnabled(false);
            }
        }
        else{
            TF_IP.setEnabled(true);
            String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
            Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
            Matcher matcher = pattern.matcher(TF_IP.getText());

            if (matcher.find()){
                if(!TF_Pseudo.getText().equals("")){
                    BT_Jouer.setEnabled(true);
                    JL_Erreur.setVisible(false);
                }
                else{
                    BT_Jouer.setEnabled(false);
                }
            } else{
                BT_Jouer.setEnabled(false);
                JL_Erreur.setVisible(true);
            }    
        }  
    }
    
    private void Radio_ButtonsActionPerformed(ActionEvent evt){
        checkChamps();
    }
    
    private void BT_JouerActionPerformed(ActionEvent evt){
        Dimension plateau = new Dimension(800,800);
        Dimension recap = new Dimension(500,800);
        
        if(group.getSelection().getActionCommand().equals("Serveur")){
            try {
                new PlateauGUISocket(plateau,recap, TF_Pseudo.getText(),null,"Serveur");
            } catch (IOException ex) {
                Logger.getLogger(ReseauGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                new PlateauGUISocket(plateau,recap, TF_Pseudo.getText(),TF_IP.getText(),"Client");
            } catch (IOException ex) {
                Logger.getLogger(ReseauGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

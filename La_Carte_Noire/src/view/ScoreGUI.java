package view;

import controler.ScoreControler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.JoueurScore;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import tools.ImageJsrollPane;
import tools.ImagePanel;

public class ScoreGUI extends JFrame {
    private JPanel globalPanel, scorePanel;
    private JScrollPane scrollPanel;
    private ArrayList<JoueurScore> map;
    private JButton BT_Retour;

    public ScoreGUI() throws IOException {
        initComponents();
    }

    private void initComponents() throws IOException {
        this.setTitle("Scores");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(965,620));
        
        BT_Retour = new JButton();
        BT_Retour.setIcon(new ImageIcon(getClass().getResource("/images/Bouton_retour_dark.png")));
        BT_Retour.setBorder(null);
        BT_Retour.setContentAreaFilled(false);
        BT_Retour.setMargin(new Insets(0, 0, 0, 0)); 
        BT_Retour.addActionListener(this::BT_RetourActionPerformed);
       
        //récupération de la liste des scores
        map = ScoreControler.getScores();
        globalPanel = new ImagePanel("menu");
        globalPanel.setPreferredSize(new Dimension(965,620));
        globalPanel.setLayout(new AbsoluteLayout());
        
        scorePanel = new ImageJsrollPane();
        scorePanel.setLayout(new GridLayout(map.size(),1));
        
        for(JoueurScore tmp : map){
            JPanel current = new JPanel(new GridLayout(1,2));
            current.setPreferredSize(new Dimension(200,40));
            current.setOpaque(false);
            current.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel pseudo = new JLabel(tmp.getPseudo(),JLabel.CENTER);
            pseudo.setFont(pseudo.getFont().deriveFont(17f));
            pseudo.setForeground(Color.BLACK);
            JLabel score = new JLabel(String.valueOf(tmp.getScore()),JLabel.CENTER);
            score.setFont(score.getFont().deriveFont(17f));
            score.setForeground(Color.BLACK);
            current.add(pseudo);
            current.add(score);
            scorePanel.add(current);
        }
        
        scrollPanel = new JScrollPane(scorePanel);
        scrollPanel.setPreferredSize(new Dimension(850,400));
        globalPanel.add(BT_Retour,new AbsoluteConstraints(25, 25, -1, -1));
        globalPanel.add(scrollPanel,new AbsoluteConstraints(56, 175, -1, -1));
        this.getContentPane().add(globalPanel);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void BT_RetourActionPerformed(ActionEvent evt) {
        this.dispose();
    }
}

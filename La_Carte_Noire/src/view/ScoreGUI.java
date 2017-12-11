package view;

import controler.ScoreControler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class ScoreGUI extends JFrame {
    private JPanel globalPanel, scorePanel;
    private JScrollPane scrollPanel;
    private HashMap<String,Integer> map;

    public ScoreGUI() throws IOException {
        initComponents();
    }

    private void initComponents() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Scores");
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(500,500));
        this.getContentPane().setLayout(new AbsoluteLayout());
       
        map = ScoreControler.getScores();
        globalPanel = new JPanel(new BorderLayout());
        globalPanel.setPreferredSize(new Dimension(400,500));
        globalPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        scorePanel = new JPanel(new GridLayout(map.size(),1));
        scorePanel.setPreferredSize(new Dimension(200,200));
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        for(Map.Entry<String,Integer> tmp : map.entrySet()){
            JPanel current = new JPanel(new GridLayout(1,2));
            current.setPreferredSize(new Dimension(200,200));
            current.setBorder(BorderFactory.createLineBorder(Color.orange));
            JLabel pseudo = new JLabel(tmp.getKey(),JLabel.CENTER);
            JLabel score = new JLabel(String.valueOf(tmp.getValue()),JLabel.CENTER);
            current.add(pseudo);
            current.add(score);
            scorePanel.add(current);
        }
        scrollPanel = new JScrollPane(scorePanel);
        scrollPanel.setPreferredSize(new Dimension(200,200));
        globalPanel.add(scrollPanel,BorderLayout.CENTER);
        this.getContentPane().add(globalPanel, new AbsoluteConstraints(0, 0, 960, 590));
        this.setVisible(true);
    }
}

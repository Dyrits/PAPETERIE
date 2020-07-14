package fr.eni.papeterie.ihm.catalogue;

import fr.eni.papeterie.ihm.ControlsArticle;

import javax.swing.*;
import java.awt.*;

public class FrameCatalogue extends JFrame {

    /**
     * Constructeur.
     */
    public FrameCatalogue() {
        super("Catalogue");
        this.setSize(new Dimension(500, 300));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Icône:
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../resources/aim.png")));
        initIHM();
    }

    /**
     * Initialise l'écran.
     */
    private void initIHM() {
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setLayout(new GridLayout(1, 0));
        JTable table = new TableCatalogue(ControlsArticle.getCatalogue());
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        this.setContentPane(panel);
    }
}

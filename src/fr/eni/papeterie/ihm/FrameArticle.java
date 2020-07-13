package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.*;
import java.awt.*;

public class FrameArticle extends JFrame {
    private JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;
    private JPanel panelType, panelGrammage, panelBoutons;
    private JRadioButton radioTypeStylo, radioTypeRamette;
    private JCheckBox checkBoxGrammage80, checkBoxGrammage100;
    private JComboBox comboCouleurs;
    private JButton btnPrevious, btnNew, btnSave, btnDelete, btnForward;

    /**
     * Constructeur.
     */
    public FrameArticle() {
        this.setTitle("Catalogue d'articles");
        this.setSize(new Dimension(500, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initIHM();
    }

    /**
     * Initialise l'écran.
     */
    private void initIHM() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints panelGrid = new GridBagConstraints();
        panelGrid.insets = new Insets(5, 5, 5, 5);
        panelGrid.fill = GridBagConstraints.BOTH;
        // Ajout des composants au panel:
        // (Ligne 1)
        panelGrid.gridy = 0;
        panelGrid.gridx = 0;
        panel.add(new Label("Référence"), panelGrid);
        panelGrid.gridx = 1;
        panel.add(getTxtReference(), panelGrid);
        // (Ligne 2)
        panelGrid.gridy = 1;
        panelGrid.gridx = 0;
        panel.add(new Label("Désignation"), panelGrid);
        panelGrid.gridx = 1;
        panel.add(getTxtDesignation(), panelGrid);
        // (Ligne 3)
        panelGrid.gridy = 2;
        panelGrid.gridx = 0;
        panel.add(new Label("Marque"), panelGrid);
        panelGrid.gridx = 1;
        panel.add(getTxtMarque(), panelGrid);
        // (Ligne 4)
        panelGrid.gridy = 3;
        panelGrid.gridx = 0;
        panel.add(new Label("Stock"), panelGrid);
        panelGrid.gridx = 1;
        panel.add(getTxtStock(), panelGrid);
        // (Ligne 5)
        panelGrid.gridy = 4;
        panelGrid.gridx = 0;
        panel.add(new Label("Prix"), panelGrid);
        panelGrid.gridx = 1;
        panel.add(getTxtPrix(), panelGrid);
        // (Ligne 6)
        panelGrid.gridy = 5;
        panelGrid.gridx = 0;
        panel.add(new Label("Type"), panelGrid);
        panelGrid.gridx = 1;
        panel.add(getPanelType(), panelGrid);
        // (Ligne 7)
        panelGrid.gridy = 6;
        panelGrid.gridx = 0;
        panel.add(new Label("Grammage"), panelGrid);
        panelGrid.gridx = 1;
        panel.add(getPanelGrammage(), panelGrid);
        // (Ligne 8)
        panelGrid.gridy = 7;
        panelGrid.gridx = 0;
        panel.add(new Label("Couleur"), panelGrid);
        panelGrid.gridx = 1;
        panel.add(getComboCouleurs(), panelGrid);
        // (Ligne 9)
        panelGrid.gridy = 8;
        panelGrid.gridx = 0;
        panelGrid.gridwidth = 2;
        panel.add(getPanelBoutons(), panelGrid);
        // Affectation à l'écran:
        this.setContentPane(panel);
    }

    /**
     * Affiche un article entré en paramètre.
     * @param article Article | Article à afficher.
     */
    public void displayArticleIHM(Article article) {
        getTxtReference().setText(article.getReference());
        getTxtMarque().setText(article.getMarque());
        getTxtDesignation().setText(article.getDesignation());
        getTxtStock().setText(String.valueOf(article.getQteStock()));
        getTxtPrix().setText(String.valueOf(article.getPrixUnitaire()));
        getRadioTypeStylo().setSelected(article instanceof Stylo);
        getRadioTypeRamette().setSelected(article instanceof Ramette);
        getComboCouleurs().setEnabled(article instanceof Stylo);
        getCheckBoxGrammage80().setEnabled(article instanceof Ramette);
        getCheckBoxGrammage100().setEnabled(article instanceof Ramette);
        if (article instanceof Stylo) {
            getComboCouleurs().setSelectedItem(((Stylo) article).getCouleur());
        } else if (article instanceof Ramette){
            getCheckBoxGrammage80().setSelected(((Ramette) article).getGrammage() == 80);
            getCheckBoxGrammage100().setSelected(((Ramette) article).getGrammage() == 100);
        }
        getRadioTypeStylo().setEnabled(false);
        getRadioTypeStylo().setEnabled(false);
    }

    /**
     * @return Article | Article actuellement affiché.
     */
    public Article getArticleIHM() {
        Article article = null;
        try {
            if (getRadioTypeStylo().isSelected()) {
                article = new Stylo((String) getComboCouleurs().getSelectedItem());
            } else if (getRadioTypeRamette().isSelected()) {
                article = new Ramette(getCheckBoxGrammage80().isSelected() ? 80 : 100);
            }
            assert article != null;
            article.setIdArticle(ControlsArticle.index);
            article.setMarque(getTxtMarque().getText());
            article.setReference(getTxtReference().getText());
            article.setPrixUnitaire(Float.parseFloat(getTxtPrix().getText()));
            article.setDesignation(getTxtDesignation().getText());
            article.setQteStock(Integer.parseInt(getTxtStock().getText()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return article;
    }


    // GETTERS


    // Text fields:

    public JTextField getTxt(JTextField txt) {
        if (txt == null) { return new JTextField(30); }
        return txt;
    }

    public JTextField getTxtReference() { return this.txtReference = getTxt(this.txtReference); }

    public JTextField getTxtDesignation() { return this.txtDesignation = getTxt(this.txtDesignation); }

    public JTextField getTxtMarque() { return this.txtMarque = getTxt(this.txtMarque); }

    public JTextField getTxtStock() { return this.txtStock = getTxt(this.txtStock); }

    public JTextField getTxtPrix() { return this.txtPrix = getTxt(this.txtPrix); }


    // Radio buttons:

    public JRadioButton getRadioType(JRadioButton radio, String text) {
        if (radio == null) {
            radio = new JRadioButton(text);
            radio.addActionListener(event -> {
                getCheckBoxGrammage80().setEnabled(!text.equals("Stylo"));
                getCheckBoxGrammage100().setEnabled(!text.equals("Stylo"));
                getComboCouleurs().setEnabled(text.equals("Stylo"));
            });
        }
        return radio;
    }

    public JPanel getPanelType() {
        if (panelType == null) {
            panelType = new JPanel();
            panelType.setLayout(new BoxLayout(panelType, BoxLayout.PAGE_AXIS));
            panelType.add(getRadioTypeStylo());
            panelType.add(getRadioTypeRamette());
            ButtonGroup radioTypeBG = new ButtonGroup();
            radioTypeBG.add(getRadioTypeStylo());
            radioTypeBG.add(getRadioTypeRamette());
        }
        return panelType;
    }

    public JRadioButton getRadioTypeStylo() {
        return this.radioTypeStylo = getRadioType(this.radioTypeStylo, "Stylo");
    }

    public JRadioButton getRadioTypeRamette() {
        return this.radioTypeRamette = getRadioType(this.radioTypeRamette, "Ramette");
    }


    // Checkboxes :

    public JCheckBox getCheckBoxGrammage(JCheckBox checkBox, String text) {
        if (checkBox == null) { return new JCheckBox(text); }
        return checkBox;
    }

    public JPanel getPanelGrammage() {
        if (panelGrammage == null) {
            panelGrammage = new JPanel();
            panelGrammage.setLayout(new BoxLayout(panelGrammage, BoxLayout.PAGE_AXIS));
            panelGrammage.add(getCheckBoxGrammage80());
            panelGrammage.add(getCheckBoxGrammage100());
            ButtonGroup checkBoxGrammageBG = new ButtonGroup();
            checkBoxGrammageBG.add(getCheckBoxGrammage80());
            checkBoxGrammageBG.add(getCheckBoxGrammage100());
        }
        return panelGrammage;
    }

    public JCheckBox getCheckBoxGrammage80() {
        return this.checkBoxGrammage80 = getCheckBoxGrammage(this.checkBoxGrammage80, "80 grammes");
    }

    public JCheckBox getCheckBoxGrammage100() {
        return this.checkBoxGrammage100 = getCheckBoxGrammage(this.checkBoxGrammage100, "100 grammes");
    }


    // List:

    public JComboBox<String> getComboCouleurs() {
        if (this.comboCouleurs == null) {
            String[] couleurs = { "bleu", "rouge", "noir", "vert" };
            this.comboCouleurs = new JComboBox<>(couleurs);
        }
        return this.comboCouleurs;
    }


    // Buttons:

    public JButton getBtn(String source) {
        Icon icon = new ImageIcon(this.getClass().getResource(source));
        return new JButton(icon);
    }

    public JPanel getPanelBoutons() {
        if (panelBoutons == null) {
            panelBoutons = new JPanel();
            panelBoutons.setLayout(new FlowLayout());
            panelBoutons.add(getBtnPrevious());
            panelBoutons.add(getBtnNew());
            panelBoutons.add(getBtnSave());
            panelBoutons.add(getBtnDelete());
            panelBoutons.add(getBtnForward());
        }
        return panelBoutons;
    }

    public JButton getBtnPrevious() {
        if (this.btnPrevious == null) {
            this.btnPrevious = getBtn("./resources/Back24.gif");
            this.btnPrevious.addActionListener(event -> ControlsArticle.previousArticle());
        }
        return this.btnPrevious;
    }

    public JButton getBtnNew() {
        if (this.btnNew == null) {
            this.btnNew = getBtn("./resources/New24.gif");
            this.btnNew.addActionListener(event -> ControlsArticle.newArticle());
        }
        return this.btnNew;
    }

    public JButton getBtnSave() {
        if (this.btnSave == null) {
            this.btnSave = getBtn("./resources/Save24.gif");
            this.btnSave.addActionListener(event -> ControlsArticle.saveArticle());
        }
        return this.btnSave;
    }

    public JButton getBtnDelete() {
        if (this.btnDelete == null) {
            this.btnDelete = getBtn("./resources/Delete24.gif");
            this.btnDelete.addActionListener(event -> ControlsArticle.deleteArticle());
        }
        return this.btnDelete;
    }

    public JButton getBtnForward() {
        if (this.btnForward == null) {
            this.btnForward = getBtn("./resources/Forward24.gif");
            this.btnForward.addActionListener(event -> ControlsArticle.forwardArticle());
        }
        return this.btnForward;
    }
}

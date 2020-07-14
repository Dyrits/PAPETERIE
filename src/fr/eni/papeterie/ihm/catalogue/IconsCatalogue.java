package fr.eni.papeterie.ihm.catalogue;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class IconsCatalogue implements TableCellRenderer {

    private static ImageIcon imageStylo;
    private static ImageIcon imageRamette;

    /**
     * Constructeur initialisant le catalogue d'icônes.
     */
    public IconsCatalogue() {
        super();
        imageStylo = new ImageIcon(getClass().getResource("../resources/pencil.gif"));
        imageRamette = new ImageIcon(getClass().getResource("../resources/ramette.gif"));
    }

    /**
     * @return JLabel | Label incluant un icône en fonction du type d'article.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String type = (String) value;
        JLabel component = new JLabel();
        if (type.equals("Ramette")) {
            component.setIcon(imageRamette);
        } else if (type.equals("Stylo")){
            component.setIcon(imageStylo);
        }
        component.setHorizontalAlignment(SwingConstants.CENTER);
        return component;
    }

}

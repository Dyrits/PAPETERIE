package fr.eni.papeterie.ihm.catalogue;

import fr.eni.papeterie.bo.Article;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class TableCatalogue extends JTable {
    int[] columnWidths = new int[]{50, 100, 100, 200, 50, 50};

    /**
     * Constructeur utilisant le model TableCatalogueModel.
     * @param catalogue List<Article> | Catalogue d'article.
     */
    public TableCatalogue(List<Article> catalogue) {
        super(new TableCatalogueModel(catalogue));
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);
        for (int columnIndex = 0; columnIndex < this.getColumnCount() ; columnIndex ++) {
            this.getColumnModel().getColumn(columnIndex).setPreferredWidth(columnWidths[columnIndex]);
        }
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getColumn(0).setCellRenderer(new IconsCatalogue());
        this.setRowHeight(30);
    }
}

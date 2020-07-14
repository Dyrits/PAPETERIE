package fr.eni.papeterie.ihm.catalogue;

import fr.eni.papeterie.bo.Article;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;

public class TableCatalogueModel extends AbstractTableModel {
    private final HashMap<Integer, Object> GET_VALUES = new HashMap<>();
    private final String[] COLUMN_NAMES = {"", "Reference", "Marque", "Designation", "Prix unitaire", "Stock"};
    private final List<Article> CATALOGUE;

    /**
     * @param catalogue List<Article> | Liste des articles à afficher.
     */
    public TableCatalogueModel(List<Article> catalogue) {
        super();
        this.CATALOGUE = catalogue;
    }

    /**
     * [!] Indispensable pour afficher le nom des colonnes.
     */
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    /**
     * @return int | Nombre de lignes.
     */
    @Override
    public int getRowCount() {
        return CATALOGUE.size();
    }

    /**
     * @return int | Nombre de colonnes.
     */
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * @return Object | Valeur à la position donnée.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        setGetValues(rowIndex);
        return GET_VALUES.get(columnIndex);
    }

    /**
     * Initialise les clés, correspondant aux colonnes, aux valeurs associées pour un index spécifié.
     */
    public void setGetValues(int rowIndex) {
        GET_VALUES.put(0, CATALOGUE.get(rowIndex).getClass().getSimpleName());
        GET_VALUES.put(1, CATALOGUE.get(rowIndex).getReference());
        GET_VALUES.put(2, CATALOGUE.get(rowIndex).getMarque());
        GET_VALUES.put(3, CATALOGUE.get(rowIndex).getDesignation());
        GET_VALUES.put(4, CATALOGUE.get(rowIndex).getPrixUnitaire());
        GET_VALUES.put(5, CATALOGUE.get(rowIndex).getQteStock());
    }
}

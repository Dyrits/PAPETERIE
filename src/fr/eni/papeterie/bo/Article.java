package fr.eni.papeterie.bo;

public abstract class Article {
    private int idArticle, qteStock;
    private float prixUnitaire;
    private String reference, marque, designation;

    /**
     * Constructeur.
     * @param idArticle int | Identifiant.
     * @param marque String |  Marque
     * @param reference String | Référence
     * @param designation String | Désignation
     * @param prixUnitaire float | Prix unitaire.
     * @param qteStock int | Quantité en stock.
     */
    Article(int idArticle, String marque, String reference, String designation, float prixUnitaire, int qteStock) {
        this(marque, reference, designation, prixUnitaire, qteStock);
        setIdArticle(idArticle);
    }

    Article(String marque, String reference, String designation, float prixUnitaire, int qteStock) {
        setMarque(marque);
        setReference(reference);
        setDesignation(designation);
        setPrixUnitaire(prixUnitaire);
        setQteStock(qteStock);
    }

    Article() {}

    @Override
    public String toString() {
        return "Article " +
                "[idArticle=" + idArticle +
                ", qteStock=" + qteStock +
                ", prixUnitaire=" + prixUnitaire +
                ", reference='" + reference + "'" +
                ", marque='" + marque + "'" +
                ", designation='" + designation + "'] ";
    }

    // GETTERS & SETTERS

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}

package fr.eni.papeterie.bo;

public class Stylo extends Article {
    private String couleur;

    public Stylo() {}

    /**
     * Constructeur reprenant les paramètres de la classe Article.
     * @param couleur String | Couleur.
     */
    public Stylo(int idArticle, String marque, String reference, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(idArticle, marque, reference, designation, prixUnitaire, qteStock);
        setCouleur(couleur);
    }

    public Stylo(String marque, String reference, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(marque, reference, designation, prixUnitaire, qteStock);
        setCouleur(couleur);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Stylo " +
                "[couleur='" + couleur + "']";
    }

    // GETTERS & SETTERS

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}

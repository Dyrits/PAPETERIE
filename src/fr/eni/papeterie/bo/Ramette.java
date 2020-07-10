package fr.eni.papeterie.bo;

public class Ramette extends Article {
    private int grammage;

    public Ramette() {}

    /**
     * Constructeur reprenant les paramètres de la classe Article.
     * @param grammage int  | Grammage.
     */
    public Ramette(int idArticle, String marque, String reference, String designation, float prixUnitaire, int qteStock, int grammage) {
        super(idArticle, marque, reference, designation, prixUnitaire, qteStock);
        setGrammage(grammage);
    }

    public Ramette(String marque, String reference, String designation, float prixUnitaire, int qteStock, int grammage) {
        super(marque, reference, designation, prixUnitaire, qteStock);
        setGrammage(grammage);
    }

    public Ramette(int grammage) {
        setGrammage(grammage);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Ramette " +
                "[grammage='" + grammage + "']";
    }

    // GETTERS & SETTERS

    public int getGrammage() {
        return grammage;
    }

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }
}

package fr.eni.papeterie.bo;

public class Ligne {
    protected int qte;
    private Article article;

    /**
     * Constructeur.
     * @param article Article | Article.
     * @param qte int | Quantité.
     */
    public Ligne(Article article, int qte) {
        setArticle(article);
        setQte(qte);
    }

    @Override
    public String toString() {
        return "Ligne " +
                "[qte=" + qte +
                ", article=" + article + "]";
    }


    // GETTERS & SETTERS

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Article getArticle() {
        return article;
    }

    private void setArticle(Article article) {
        this.article = article;
    }
}

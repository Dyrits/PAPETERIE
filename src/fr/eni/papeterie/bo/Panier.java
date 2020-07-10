package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private float montant = 0.0f;
    private final List<Ligne> lignesPanier;

    public Panier() {
        lignesPanier = new ArrayList<>();
    }

    /**
     * Ajoute une ligne avec un article et sa quantité au panier et ajuste le stock.
     * @param article Article | Article.
     * @param qte int | Quantité.
     */
    public void addLigne(Article article, int qte) {
        article.setQteStock(article.getQteStock() - qte);
        lignesPanier.add(new Ligne(article, qte));
        setMontant();
    }

    /**
     * Modifie la quantité d'article pour une ligne spécifiée et ajuste le stock.
     * @param index index | Index de la ligne.
     * @param newQte int |  Nouvelle quantité.
     */
    public void updateLigne(int index, int newQte) {
        Article article = lignesPanier.get(index).getArticle();
        article.setQteStock(article.getQteStock() + (lignesPanier.get(index).getQte() - newQte));
        lignesPanier.get(index).setQte(newQte);
        setMontant();
    }

    /**
     * Supprime une ligne spécifiée et ajuste le stock.
     * @param index int | Index de la ligne.
     */
    public void removeLigne(int index) {
        Article article = lignesPanier.get(index).getArticle();
        article.setQteStock(article.getQteStock() + lignesPanier.get(index).getQte());
        lignesPanier.remove(index);
        setMontant();
    }

    @Override
    public String toString() {
        StringBuilder panier = new StringBuilder();
        for (int index = 0; index < lignesPanier.size(); index ++) {
            panier.append("ligne ").append(index).append(" :\t").append(lignesPanier.get(index)).append("\n");
        }
        panier.append("\n").append("Valeur du panier: ").append(getMontant());
        return panier.toString();
    }

    // GETTERS & SETTERS

    public float getMontant() {
        return (float) (Math.round(montant * 100.0) / 100.0);
    }

    public void setMontant() {
        float total = 0;
        for (Ligne ligne : lignesPanier) {
            total += ligne.getArticle().getPrixUnitaire() * ligne.getQte();
        }
        this.montant = total;
    }

    public Ligne getLigne(int index) {
        return lignesPanier.get(index);
    }

    public List<Ligne> getLignesPanier() {
        return lignesPanier;
    }
}

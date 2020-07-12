package fr.eni.papeterie.bo;

import fr.eni.papeterie.bll.BLLException;

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
     * @throws BOException Exception
     */
    public void addLigne(Article article, int qte) throws BOException {
        int newQteStock = article.getQteStock() - qte;
        if (newQteStock < 0) {
            throw new BOException("La quantité d'article en stock est insuffisante.");
        }
        article.setQteStock(newQteStock);
        lignesPanier.add(new Ligne(article, qte));
        setMontant();
    }

    /**
     * Modifie la quantité d'article pour une ligne spécifiée et ajuste le stock.
     * @param index index | Index de la ligne.
     * @param newQte int |  Nouvelle quantité.
     * @throws BOException Exception
     */
    public void updateLigne(int index, int newQte) throws BOException {
        Article article = lignesPanier.get(index).getArticle();
        int newQteStock = article.getQteStock() + (lignesPanier.get(index).getQte() - newQte);
        if (newQteStock < 0) {
            throw new BOException("La quantité d'article en stock est insuffisante.");
        }
        article.setQteStock(newQteStock);
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

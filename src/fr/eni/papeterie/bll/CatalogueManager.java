package fr.eni.papeterie.bll;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

import java.util.List;

public class CatalogueManager {
    private static DAO daoArticles;

    /**
     * Constructeur initialisant une interface d'accès au catalogue d'articles.
     * @throws BLLException Exception
     */
    public CatalogueManager() throws BLLException {
        daoArticles = DAOFactory.getArticleDAO();
    }

    /**
     * @return List | Liste de l'ensemble des articles.
     * @throws BLLException Exception
     */
    public List<Article> getCatalogue() throws BLLException {
        try {
            return daoArticles.selectAll();
        } catch (DALException exception) {
            throw new BLLException("Erreur lors de la récupération du catalogue.", exception);
        }
    }

    /**
     * @param article Article | Article à ajouter.
     * @throws BLLException Exception
     */
    public void addArticle(Article article) throws BLLException {
        try {
            checkArticle(article);
            daoArticles.insert(article);
        } catch (DALException exception) {
            throw new BLLException("Erreur lors de l'ajout d'un nouvel article.", exception);
        }
    }

    /**
     * @param article Article | Article à modifier.
     * @throws BLLException Exception
     */
    public void updateArticle(Article article) throws BLLException {
        try {
            checkArticle(article);
            daoArticles.update(article);
        } catch (DALException exception) {
            throw new BLLException("Erreur lors de la modification d'un article.", exception);
        }
    }

    /**
     * @param article Article | Article à supprimer.
     * @throws BLLException Exception
     */
    public void removeArticle(Article article) throws BLLException {
        try {
            daoArticles.delete(article.getIdArticle());
        } catch (DALException exception) {
            throw new BLLException("Erreur lors de la suppression d'un article.", exception);
        }
    }

    /**
     * Vérifie la validité d'un article.
     * Vérifie s'il contient l'ensemble des valeurs correctes pour l'ensemble de ses attributs.
     * (L'identifiant étant auto-généré, celui-ci n'est pas vérifié.)
     * @param article Article | Article à vérifier.
     * @throws BLLException Exception
     */
    private void checkArticle(Article article) throws BLLException {
        if (article == null) { throw new BLLException("L'article est nul."); }
        StringBuilder errors = new StringBuilder();
        if (article.getReference() == null || article.getReference().trim().isEmpty()) {
            errors.append("Champs obligatoire. L'article n'a pas de référence.").append("\n");
        }
        if (article.getMarque() == null || article.getMarque().trim().isEmpty()) {
            errors.append("Champs obligatoire. L'article n'a pas de référence.").append("\n");
        }
        if (article.getDesignation() == null || article.getDesignation().trim().isEmpty()) {
            errors.append("Champs obligatoire. L'article n'a' pas de référence.").append("\n");
        }
        if (article.getDesignation() == null || article.getDesignation().trim().isEmpty()) {
            errors.append("Champs obligatoire. L'article ne contient pas de référence.").append("\n");
        }
        if (article.getPrixUnitaire() < 0) {
            errors.append("Champs obligatoirement égal à zéro ou positif. Le prix unitaire est négatif.").append("\n");
        }
        if (article.getQteStock() < 0) {
            errors.append("Champs obligatoirement égal à zéro ou positif. La quantité est négative.").append("\n");
        }
        if (article instanceof Stylo) {
            Stylo stylo = (Stylo) article;
            if (stylo.getCouleur() == null || stylo.getCouleur().trim().isEmpty()) {
                errors.append("Stylo | Champs obligatoire. L'article n'a pas de couleur.").append("\n");
            }
        }
        if (article instanceof Ramette) {
            Ramette ramette = (Ramette) article;
            if (ramette.getGrammage() < 1) {
                errors.append("Ramette | Champs obligatoirement positif. Le grammage est invalide.").append("\n");
            }
        }
        if (!errors.toString().isEmpty()) {
            throw new BLLException(errors.toString());
        }
    }


}

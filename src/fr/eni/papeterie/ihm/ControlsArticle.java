package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Stylo;

import java.util.List;

public class ControlsArticle {
    static int index = 0;
    private static final FrameArticle FRAME_ARTICLE = new FrameArticle();
    private static final CatalogueManager ARTICLE_MANAGER = getArticleManager();
    private static final List<Article> CATALOGUE = getCatalogue();


    /**
     * Démarre l'écran et affiche le premier article du catalogue.
     */
    static void start() {
        FRAME_ARTICLE.setVisible(true);
        Article initial = ControlsArticle.CATALOGUE.get(index);
        FRAME_ARTICLE.getBtnPrevious().setEnabled(false);
        FRAME_ARTICLE.displayArticleIHM(initial);
    }

    static void previousArticle() {
        if (index > 0) {
            Article previous = ControlsArticle.CATALOGUE.get(-- index);
            FRAME_ARTICLE.displayArticleIHM(previous);
            FRAME_ARTICLE.getBtnForward().setEnabled(true);
        }
        if (index == 0) { FRAME_ARTICLE.getBtnPrevious().setEnabled(false); }
        if (index == CATALOGUE.size() - 1) { FRAME_ARTICLE.getBtnForward().setEnabled(false); }
    }

    static void forwardArticle() {
        if (index < CATALOGUE.size()) {
            Article next = ControlsArticle.CATALOGUE.get(++ index);
            FRAME_ARTICLE.displayArticleIHM(next);
            FRAME_ARTICLE.getBtnPrevious().setEnabled(true);
        }
        if (index == CATALOGUE.size() - 1) { FRAME_ARTICLE.getBtnForward().setEnabled(false); }
    }

    static void newArticle() {
        index = CATALOGUE.size();
        Article defaultNewArticle = new Stylo("", "", "", 0.0f, 0, "bleu");
        FRAME_ARTICLE.displayArticleIHM(defaultNewArticle);
        FRAME_ARTICLE.getRadioTypeStylo().setEnabled(true);
        FRAME_ARTICLE.getRadioTypeRamette().setEnabled(true);
        FRAME_ARTICLE.getBtnDelete().setEnabled(false);
        FRAME_ARTICLE.getBtnForward().setEnabled(false);
        FRAME_ARTICLE.getBtnPrevious().setEnabled(index > 0);
    }

    static void deleteArticle() {
        try {
            ARTICLE_MANAGER.removeArticle(CATALOGUE.get(index));
            CATALOGUE.remove(index);
            // Affichage de l'article précédent, s'il existe) après suppression.
            if (index > 0 && CATALOGUE.size() != 0) { previousArticle(); }
            // Sinon : Affichage de l'article suivant, s'il existe.
            else if (index == 0 && CATALOGUE.size() > 0) { forwardArticle(); }
            // Sinon: Affichage du formulaire de création d'un nouvel article.
            else { newArticle(); }
        } catch (BLLException exception) {
            exception.printStackTrace();
        }
    }

    static void saveArticle() {
        Article article = FRAME_ARTICLE.getArticleIHM();
        try {
            if (CATALOGUE.size() == index) {
                ARTICLE_MANAGER.addArticle(article);
                CATALOGUE.add(article);
                FRAME_ARTICLE.displayArticleIHM(article);
            } else {
                ARTICLE_MANAGER.updateArticle(article);
                CATALOGUE.set(index, article);
            }
        } catch (BLLException exception) {
            exception.printStackTrace();
        }
    }



    // STATIC GETTERS

    private static CatalogueManager getArticleManager() {
        CatalogueManager _ARTICLE_MANAGER = null;
        try {
            _ARTICLE_MANAGER =  new CatalogueManager();
        } catch (BLLException exception) {
            exception.printStackTrace();
        }
        return _ARTICLE_MANAGER;
    }

    private static List<Article> getCatalogue() {
        List<Article> _CATALOGUE = null;
        try {
            _CATALOGUE = ARTICLE_MANAGER.getCatalogue();
        } catch (BLLException exception) {
            exception.printStackTrace();
        }
        return _CATALOGUE;
    }


}

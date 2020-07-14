package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.ihm.catalogue.FrameCatalogue;

import java.util.List;

public class ControlsArticle {
    static int index = 0;
    private static final FrameArticle FRAME_ARTICLE = new FrameArticle();
    private static final FrameCatalogue FRAME_CATALOGUE = new FrameCatalogue();
    private static final CatalogueManager ARTICLE_MANAGER = getArticleManager();
    private static final List<Article> CATALOGUE = getCatalogue();


    /**
     * Démarre l'écran et affiche le premier article du catalogue.
     */
    static void start() {
        FRAME_ARTICLE.setVisible(true);
        Article initial = ControlsArticle.CATALOGUE.get(index);
        FRAME_ARTICLE.getPanelButtons().getBtnPrevious().setEnabled(false);
        FRAME_ARTICLE.displayArticleIHM(initial);

        FRAME_CATALOGUE.setVisible(true);
    }

    static void btnPrevious() {
        if (index > 0) {
            Article previous = ControlsArticle.CATALOGUE.get(-- index);
            FRAME_ARTICLE.displayArticleIHM(previous);
            FRAME_ARTICLE.getPanelButtons().getBtnForward().setEnabled(true);
        }
        if (index == 0) { FRAME_ARTICLE.getPanelButtons().getBtnPrevious().setEnabled(false); }
        if (index == CATALOGUE.size() - 1) { FRAME_ARTICLE.getPanelButtons().getBtnForward().setEnabled(false); }
    }

    static void btnForward() {
        if (index < CATALOGUE.size()) {
            Article next = ControlsArticle.CATALOGUE.get(++ index);
            FRAME_ARTICLE.displayArticleIHM(next);
            FRAME_ARTICLE.getPanelButtons().getBtnPrevious().setEnabled(true);
        }
        if (index == CATALOGUE.size() - 1) { FRAME_ARTICLE.getPanelButtons().getBtnForward().setEnabled(false); }
    }

    static void btnNew() {
        index = CATALOGUE.size();
        Article defaultNewArticle = new Stylo("", "", "", 0.0f, 0, "bleu");
        FRAME_ARTICLE.displayArticleIHM(defaultNewArticle);
        FRAME_ARTICLE.getRadioTypeStylo().setEnabled(true);
        FRAME_ARTICLE.getRadioTypeRamette().setEnabled(true);
        FRAME_ARTICLE.getPanelButtons().getBtnDelete().setEnabled(false);
        FRAME_ARTICLE.getPanelButtons().getBtnForward().setEnabled(false);
        FRAME_ARTICLE.getPanelButtons().getBtnPrevious().setEnabled(index > 0);
    }

    static void btnDelete() {
        try {
            ARTICLE_MANAGER.removeArticle(CATALOGUE.get(index));
            CATALOGUE.remove(index);
            // Affichage de l'article précédent, s'il existe) après suppression.
            if (index > 0 && CATALOGUE.size() != 0) { btnPrevious(); }
            // Sinon : Affichage de l'article suivant, s'il existe.
            else if (index == 0 && CATALOGUE.size() > 0) { btnForward(); }
            // Sinon: Affichage du formulaire de création d'un nouvel article.
            else { btnNew(); }
        } catch (BLLException exception) {
            exception.printStackTrace();
        }
    }

    static void btnSave() {
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

    public static List<Article> getCatalogue() {
        CatalogueManager _ARTICLE_MANAGER;
        List<Article> _CATALOGUE = null;
        try {
            _ARTICLE_MANAGER =  new CatalogueManager();
            _CATALOGUE = _ARTICLE_MANAGER.getCatalogue();
        } catch (BLLException exception) {
            exception.printStackTrace();
        }
        return _CATALOGUE;
    }


}

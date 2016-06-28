package maillet.maxime.rssfeed.models;

import java.util.ArrayList;

import maillet.maxime.rssfeed.entities.Article;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleList extends ArrayList<Article> {

    private static ArticleList instance;
    public static ArticleList getInstance() {
        if(instance == null)
            instance = new ArticleList();
        return instance;
    }
}

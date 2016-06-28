package maillet.maxime.rssfeed.entities;

import java.io.Serializable;

/**
 * Created by maxime on 28/06/2016.
 */
public class Article implements Serializable {

    private static final long serialVersionUID = -29238982928391L;

    private static int nb_article = 0;

    private int ID;

    private String title;

    private String description;

    private String publication_date;

    private String image_url;

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Article(String title, String description, String publication_date, String image_url) {
        this.ID = this.generateID();
        this.title = title;
        this.description = description;
        this.publication_date = publication_date;
        this.image_url = image_url;
    }

    private int generateID() {
        Article.nb_article += 1;
        return Article.nb_article;
    }

    public int getID() {
        return this.ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }
}

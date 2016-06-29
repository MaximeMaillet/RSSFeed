package maillet.maxime.rssfeed.entities;

import java.io.Serializable;

import maillet.maxime.rssfeed.models.ArticleList;

/**
 * Created by maxime on 28/06/2016.
 */
public class Article implements Serializable {

    /**
     * Serial version
     */
    private static final long serialVersionUID = -29238982928391L;

    /**
     * Link to image when article image does not exist
     */
    public static String noImage = "http://vignette3.wikia.nocookie.net/shokugekinosoma/images/6/60/No_Image_Available.png/revision/latest?cb=20150708082716";

    /**
     * Unique ID of Article
     */
    private int ID;

    /**
     * Title of article
     */
    private String title;

    /**
     * Description of article
     */
    private String description;

    /**
     * Publication date of article
     */
    private String publication_date;

    /**
     * Image url of article
     */
    private String image_url;

    /**
     * Article url of article details
     */
    private String url;

    /**
     * Constructor of Article
     * @param title
     * @param description
     * @param publication_date
     * @param image_url
     * @param url
     */
    public Article(String title, String description, String publication_date, String image_url, String url) {
        this.ID = this.generateID();
        this.title = title;
        this.description = description;
        this.publication_date = publication_date;
        this.image_url = image_url;
        this.url = url;
    }

    /**
     * Method which generate an unique ID
     * @return int
     */
    private int generateID() {
        ArticleList.nb_article += 1;
        return ArticleList.nb_article;
    }

    /**
     * Getter to ID
     * @return int
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Getter to title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter to title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter to description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter to description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter to image URL
     * @return
     */
    public String getImage_url() {
        return image_url;
    }

    /**
     * Setter to image URL
     * @param image_url
     */
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    /**
     * Getter to publication date
     * @return
     */
    public String getPublication_date() {
        return publication_date;
    }

    /**
     * Setter to publication date
     * @param publication_date
     */
    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    /**
     * Getter to article URL
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter to article URL
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}

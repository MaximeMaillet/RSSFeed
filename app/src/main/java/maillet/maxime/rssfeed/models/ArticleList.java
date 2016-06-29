package maillet.maxime.rssfeed.models;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import maillet.maxime.rssfeed.entities.Article;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleList extends ArrayList<Article> {

    /**
     * Singleton
     * Unique instance of ArticleList
     */
    private static ArticleList instance;

    /**
     * Number of article in list
     */
    public static int nb_article = 0;

    /**
     * Name of saving file
     */
    public static String filename = "articles.srl";

    /**
     * Singleton
     * Static method to access to ArticleList instance
     * @return ArticleList
     */
    public static ArticleList getInstance() {
        if(instance == null)
            instance = new ArticleList();
        return instance;
    }

    /**
     * Static method to save data in file
     *
     * @param context
     * @param key
     */
    public static void save(Context context, String key) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            ArticleList list = ArticleList.getInstance();

            for(int i=0; i<list.size(); i++)
                oos.writeObject(list.get(i));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(oos != null)
                    oos.close();
                if(fos != null)
                    fos.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Static method to read data from file
     *
     * @param context
     * @param key
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void load(Context context, String key) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(key);
            ois = new ObjectInputStream(fis);
            Object art = null;

            while((art = ois.readObject()) != null)
                ArticleList.getInstance().add((Article) art);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(fis != null)
                    fis.close();
                if(ois != null)
                    ois.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method which override add method for test if object is already in ArticleList
     * @param object
     * @return boolean
     */
    @Override
    public boolean add(Article object) {

        boolean is_present = false;

        for(int i=0; i<this.size(); i++) {
            if(this.get(i).getID() == object.getID())
                is_present = true;
        }

        if(!is_present)
            return super.add(object);
        else
            return true;
    }

    /**
     * Returns an Article if it exists
     *
     * @param ID
     * @return Article
     */
    public Article findByID(int ID) {
        for(int i=0; i<this.size(); i++) {
            Article art = this.get(i);
            if(art.getID() == ID)
                return art;
        }
        return null;
    }
}

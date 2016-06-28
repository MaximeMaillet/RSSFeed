package maillet.maxime.rssfeed.models;

import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import maillet.maxime.rssfeed.entities.Article;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleList extends ArrayList<Article> {

    private static ArticleList instance;
    private static String filename = "articles.srl";
    public static ArticleList getInstance() {
        if(instance == null)
            instance = new ArticleList();
        return instance;
    }

    private File pathToSave;

    public ArticleList() {
        this.load();
    }

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

    public void setPathToSave(File pathToSave) {
        this.pathToSave = pathToSave;
    }

    public void save() {
        String filename = ArticleList.filename;
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(this.pathToSave, ArticleList.filename)));
            for(int i=0; i<this.size(); i++)
                out.writeObject(this.get(i));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(out != null)
                    out.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void load() {
        ObjectInputStream input = null;
        String filename = ArticleList.filename;

        try {
            input = new ObjectInputStream(new FileInputStream(new File(this.pathToSave, ArticleList.filename)));
            Object art = null;
            while((art = input.readObject()) != null)
                this.add((Article) art);

        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(input != null)
                    input.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}

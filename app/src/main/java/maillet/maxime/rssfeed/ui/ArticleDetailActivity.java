package maillet.maxime.rssfeed.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import maillet.maxime.rssfeed.R;
import maillet.maxime.rssfeed.adapters.ArticleAdapter;
import maillet.maxime.rssfeed.entities.Article;
import maillet.maxime.rssfeed.models.ArticleList;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleDetailActivity extends Activity {

    /**
     * Article loaded from click by User on listView
     */
    private Article article = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int article_ID = extras.getInt("ID");
            ArticleDetailFragment detailFragment = (ArticleDetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);

            ArticleList list = ArticleList.getInstance();
            this.article = list.findByID(article_ID);


            if(this.article != null) {
                detailFragment.update(this.article);
            }
        }
    }
}

package maillet.maxime.rssfeed.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import maillet.maxime.rssfeed.R;
import maillet.maxime.rssfeed.adapters.ArticleAdapter;
import maillet.maxime.rssfeed.entities.Article;
import maillet.maxime.rssfeed.models.ArticleList;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int article_ID = extras.getInt("ID");
            ArticleDetailFragment detailFragment = (ArticleDetailFragment) getFragmentManager()
                    .findFragmentById(R.id.detailFragment);

            Article article_target = null;

            ArticleList list = ArticleList.getInstance();
            for(int i=0; i<list.size(); i++) {
                Article art = list.get(i);
                if(art.getID() == article_ID)
                    article_target = art;
            }

            if(article_target != null)
                detailFragment.update(article_target);
        }
    }
}

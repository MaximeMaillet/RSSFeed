package maillet.maxime.rssfeed.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.crazyhitty.chdev.ks.rssmanager.OnRssLoadListener;
import com.crazyhitty.chdev.ks.rssmanager.RssItem;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.Iterator;
import java.util.List;

import maillet.maxime.rssfeed.R;
import maillet.maxime.rssfeed.adapters.ArticleAdapter;
import maillet.maxime.rssfeed.entities.Article;
import maillet.maxime.rssfeed.models.ArticleList;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleListActivity extends AppCompatActivity implements OnRssLoadListener {

    private ListView article_list;

    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.article_list = (ListView) findViewById(R.id.article_list);
        ArticleList list = ArticleList.getInstance();
        list.setPathToSave(getFilesDir());
        this.adapter = new ArticleAdapter(this, list);
        this.article_list.setAdapter(this.adapter);
        //this.loadFeeds("http://www.lemonde.fr/m-actu/rss_full.xml");

        this.article_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ArticleDetailActivity.class);
                Bundle extra = new Bundle();
                Article item = (Article) article_list.getItemAtPosition(position);
                extra.putSerializable("ID", item.getID());
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
    }

    private void loadFeeds(String url) {
        String[] urlArr = {url};
        new RssReader(ArticleListActivity.this).showDialog(true).urls(urlArr).parse(this);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(ArticleListActivity.this, "Error: "+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<RssItem> rssItems) {

        Iterator<RssItem> iter = rssItems.iterator();
        ArticleList article_list = ArticleList.getInstance();

        while(iter.hasNext()) {
            RssItem item = iter.next();
            Article article = new Article(item.getTitle(), item.getDescription(), item.getPubDate(), item.getImageUrl());
            article_list.add(article);
        }

        article_list.save();
        this.adapter.addAll(article_list);
    }
}

package maillet.maxime.rssfeed.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

    /**
     * Link to RSS feed
     */
    public static String rssFeedUrl = "http://www.lemonde.fr/m-actu/rss_full.xml";

    /**
     * Adapter for ListView, contains list of article
     */
    private ArticleAdapter adapter;

    /**
     * Layout for pull-to-refresh
     */
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        this.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFeeds(ArticleListActivity.rssFeedUrl);
            }
        });

        final ListView article_list = (ListView) findViewById(R.id.article_list);
        ArticleList list = ArticleList.getInstance();
        this.adapter = new ArticleAdapter(this, list);
        article_list.setAdapter(this.adapter);

        ArticleList.load(getApplicationContext(), ArticleList.filename);
        this.loadFeeds(rssFeedUrl);

        article_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    /**
     * Load RSS feed from network
     * @param url
     */
    private void loadFeeds(String url) {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            String[] urlArr = {url};
            new RssReader(ArticleListActivity.this).showDialog(true).urls(urlArr).parse(this);
        }
        else {
            Toast.makeText(getApplicationContext(), "Pas de connexion internet", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Load RSS feed from network
     * Only for pull-to-refresh
     * @param url
     */
    private void refreshFeeds(String url) {
        this.loadFeeds(url);
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(ArticleListActivity.this, "Impossible de charger le flux RSS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<RssItem> rssItems) {

        Iterator<RssItem> iter = rssItems.iterator();
        ArticleList article_list = ArticleList.getInstance();

        while(iter.hasNext()) {
            RssItem item = iter.next();
            Article article = new Article(item.getTitle(), item.getDescription(), item.getPubDate(), item.getImageUrl(), item.getLink());
            article_list.add(article);
        }
        ArticleList.save(getApplicationContext(), ArticleList.filename);
        this.adapter.addAll(article_list);
    }
}

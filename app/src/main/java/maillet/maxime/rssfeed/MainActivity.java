package maillet.maxime.rssfeed;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.crazyhitty.chdev.ks.rssmanager.OnRssLoadListener;
import com.crazyhitty.chdev.ks.rssmanager.RssItem;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import maillet.maxime.rssfeed.adapters.ArticleAdapter;
import maillet.maxime.rssfeed.entities.Article;
import maillet.maxime.rssfeed.models.ArticleList;
import maillet.maxime.rssfeed.ui.ArticleDetailActivity;

import android.R.*;
/**
 * Created by maxime on 28/06/2016.
 */
public class MainActivity extends AppCompatActivity implements OnRssLoadListener {

    private ListView article_list;

    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.article_list = (ListView) findViewById(R.id.article_list);
        this.adapter = new ArticleAdapter(this, ArticleList.getInstance());
        this.article_list.setAdapter(this.adapter);
        this.loadFeeds("http://www.lemonde.fr/m-actu/rss_full.xml");

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
        new RssReader(MainActivity.this).showDialog(true).urls(urlArr).parse(this);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(MainActivity.this, "Error: "+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<RssItem> rssItems) {

        Iterator<RssItem> iter = rssItems.iterator();
        while(iter.hasNext()) {
            RssItem item = iter.next();
            Article article = new Article(item.getTitle(), item.getDescription(), item.getPubDate(), item.getImageUrl());
            this.adapter.add(article);
        }
    }
}

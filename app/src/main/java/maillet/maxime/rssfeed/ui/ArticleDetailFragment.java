package maillet.maxime.rssfeed.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import maillet.maxime.rssfeed.R;
import maillet.maxime.rssfeed.entities.Article;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleDetailFragment extends Fragment {

    /**
     * UI, text view for title
     */
    private TextView title;

    /**
     * UI, text view for description
     */
    private TextView description;

    /**
     * UI, text view for publication date
     */
    private TextView publication_date;

    /**
     * UI, image view for article image
     */
    private ImageView image;

    /**
     * Button for read article in browser
     */
    private Button article_read;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        this.title = (TextView) view.findViewById(R.id.article_detail_title);
        this.description = (TextView) view.findViewById(R.id.article_detail_description);
        this.publication_date = (TextView) view.findViewById(R.id.article_detail_publication_date);
        this.image = (ImageView) view.findViewById(R.id.article_detail_image);
        this.article_read = (Button) view.findViewById(R.id.article_read);
        return view;
    }

    /**
     * Update UI when article is loaded in Activity
     * @param article
     */
    public void update(final Article article) {
        this.title.setText(article.getTitle());
        this.description.setText(article.getDescription());
        this.publication_date.setText(article.getPublication_date());
        if(article.getImage_url() == null) {
            article.setImage_url(Article.noImage);
        }
        Picasso.with(getContext()).load(article.getImage_url()).into(this.image);
        this.article_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                startActivity(browserIntent);
            }
        });

        if (article.getUrl() == null)
            this.article_read.setVisibility(View.INVISIBLE);
    }
}

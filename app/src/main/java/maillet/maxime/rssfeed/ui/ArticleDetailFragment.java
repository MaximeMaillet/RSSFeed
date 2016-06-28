package maillet.maxime.rssfeed.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import maillet.maxime.rssfeed.R;
import maillet.maxime.rssfeed.entities.Article;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleDetailFragment extends Fragment {

    private TextView title;
    private TextView description;
    private TextView publication_date;
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        this.title = (TextView) view.findViewById(R.id.article_detail_title);
        this.description = (TextView) view.findViewById(R.id.article_detail_description);
        this.publication_date = (TextView) view.findViewById(R.id.article_detail_publication_date);
        this.image = (ImageView) view.findViewById(R.id.article_detail_image);

        return view;
    }

    public void update(Article article) {
        this.title.setText(article.getTitle());
        this.description.setText(article.getDescription());
        this.publication_date.setText(article.getPublication_date());
        if(article.getImage_url() == null) {
            article.setImage_url("http://vignette3.wikia.nocookie.net/shokugekinosoma/images/6/60/No_Image_Available.png/revision/latest?cb=20150708082716");
        }
        Picasso.with(getContext()).load(article.getImage_url()).into(this.image);
    }
}

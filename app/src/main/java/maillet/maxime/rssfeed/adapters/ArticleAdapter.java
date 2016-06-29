package maillet.maxime.rssfeed.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import maillet.maxime.rssfeed.R;
import maillet.maxime.rssfeed.entities.Article;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {

    /**
     * Constructor
     * @param context
     * @param users
     */
    public ArticleAdapter(Context context, ArrayList<Article> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article article = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_article, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.article_list_title);
        TextView description = (TextView) convertView.findViewById(R.id.article_list_description);
        ImageView image = (ImageView) convertView.findViewById(R.id.article_list_image);

        title.setText(article.getTitle());
        description.setText(article.getDescription());
        if(article.getImage_url() == null) {
            article.setImage_url(Article.noImage);
        }
        Picasso.with(convertView.getContext()).load(article.getImage_url()).into(image);

        return convertView;
    }
}

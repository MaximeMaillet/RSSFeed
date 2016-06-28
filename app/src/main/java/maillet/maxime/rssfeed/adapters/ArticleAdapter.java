package maillet.maxime.rssfeed.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import maillet.maxime.rssfeed.R;
import maillet.maxime.rssfeed.entities.Article;
import maillet.maxime.rssfeed.ui.RoundedTransformation;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, ArrayList<Article> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article article = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_article, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        tvName.setText(article.getTitle());
        tvHome.setText(article.getDescription());
        if(article.getImage_url() == null) {
            article.setImage_url("http://vignette3.wikia.nocookie.net/shokugekinosoma/images/6/60/No_Image_Available.png/revision/latest?cb=20150708082716");
        }
        Picasso.with(convertView.getContext()).load(article.getImage_url())
                .transform(new RoundedTransformation(0, 5))
                .into(imageView);

        return convertView;
    }
}

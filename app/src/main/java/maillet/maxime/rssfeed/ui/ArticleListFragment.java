package maillet.maxime.rssfeed.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import maillet.maxime.rssfeed.R;

/**
 * Created by maxime on 28/06/2016.
 */
public class ArticleListFragment extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {
    }
}

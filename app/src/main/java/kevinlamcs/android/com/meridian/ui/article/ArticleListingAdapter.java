package kevinlamcs.android.com.meridian.ui.article;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kevinlamcs.android.com.meridian.data.model.api.Article;

public class ArticleListingAdapter extends RecyclerView.Adapter<ArticleListingViewHolder> {

    private List<Article> articles;

    public ArticleListingAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public ArticleListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ArticleListingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleListingViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }
}

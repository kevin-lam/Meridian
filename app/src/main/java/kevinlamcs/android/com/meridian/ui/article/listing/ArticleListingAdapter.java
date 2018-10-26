package kevinlamcs.android.com.meridian.ui.article.listing;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.article.item.ArticleListingViewHolder;
import kevinlamcs.android.com.meridian.util.image.ImageLoader;

public class ArticleListingAdapter extends ListAdapter<Article, ArticleListingViewHolder> implements ListPreloader.PreloadModelProvider<Article> {

    private final ImageLoader imageLoader;
    private final Fragment fragment;

    @Inject
    public ArticleListingAdapter(Fragment fragment) {
        super(diffCallback);
        this.fragment = fragment;
        this.imageLoader = new ImageLoader(new WeakReference<>(fragment.getContext()));
    }

    @Override
    public ArticleListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_entry, parent, false);
        return new ArticleListingViewHolder(view, fragment);
    }

    @Override
    public void onBindViewHolder(ArticleListingViewHolder holder, int position) {
        Article article = getItem(position);
        if (article != null) holder.onBind(article);
    }

    public static final DiffUtil.ItemCallback<Article> diffCallback = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldArticle, @NonNull Article newArticle) {
            return (oldArticle.getTitle() + oldArticle.getAuthor()).equals(newArticle.getTitle() + newArticle.getAuthor());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldArticle, @NonNull Article newArticle) {
            return oldArticle.equals(newArticle);
        }
    };

    @NonNull
    @Override
    public List<Article> getPreloadItems(int position) {
        if (position < getItemCount()) {
            Article article = getItem(position);
            return Collections.singletonList(article);
        }
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull Article item) {
        if (item.hasPhoto()) {
         //   return imageLoader.load(item.getJumboPhotoUrl()).thumbnail(item.getLargeThumbnailUrl()).getGlideRequest();
        }
        return null;
    }

    public void setArticles(List<Article> articles) {
        submitList(articles);
    }
}

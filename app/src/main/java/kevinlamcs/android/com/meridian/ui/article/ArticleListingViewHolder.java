package kevinlamcs.android.com.meridian.ui.article;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.base.BaseViewHolder;
import kevinlamcs.android.com.meridian.util.TimeFormatter;
import kevinlamcs.android.com.meridian.util.image.GlideApp;
import kevinlamcs.android.com.meridian.util.image.GlideRequests;
import kevinlamcs.android.com.meridian.util.image.ImageLoader;

public class ArticleListingViewHolder extends BaseViewHolder<Article> {

    @BindView(R.id.article_listing_section)
    TextView section;

    @BindView(R.id.article_listing_title)
    TextView title;

    @BindView(R.id.article_listing_date)
    TextView date;

    @BindView(R.id.article_listing_image)
    ImageView image;

    private final ArticleListingItemViewModel articleListingItemViewModel;
    private final ArticleListingFragment fragment;
    private final ImageLoader imageLoader;


    public ArticleListingViewHolder(View itemView, ArticleListingFragment fragment, ImageLoader imageLoader) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.imageLoader = imageLoader;
        this.fragment = fragment;
        this.articleListingItemViewModel = new ArticleListingItemViewModel(new TimeFormatter());
        subscribeToViewModelChanges();
    }

    @Override
    public void onBind(Article article) {
        articleListingItemViewModel.setArticle(article);
        loadThumbnail(article);
    }

    private void loadThumbnail(Article article) {
        String standardThumbnailUrl = article.getStandardThumbnailUrl();
        if (!TextUtils.isEmpty(standardThumbnailUrl)) {
            imageLoader.load(standardThumbnailUrl).into(image);
        }
    }

    public void subscribeToViewModelChanges() {
        articleListingItemViewModel.getCreatedDate().observe(fragment, date::setText);
        articleListingItemViewModel.getSection().observe(fragment, section::setText);
        articleListingItemViewModel.getTitle().observe(fragment, title::setText);
    }
}

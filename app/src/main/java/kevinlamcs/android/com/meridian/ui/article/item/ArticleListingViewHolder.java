package kevinlamcs.android.com.meridian.ui.article.item;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.base.BaseViewHolder;
import kevinlamcs.android.com.meridian.util.TimeFormatter;
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
    private final ImageLoader imageLoader;
    private final TimeFormatter timeFormatter;
    private final Fragment fragment;

    public ArticleListingViewHolder(View itemView, Fragment fragment) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.fragment = fragment;
        this.imageLoader = new ImageLoader(new WeakReference<>(fragment.getContext()));
        this.timeFormatter = new TimeFormatter();
        this.articleListingItemViewModel = new ArticleListingItemViewModel();
        subscribeToViewModelChanges();
    }

    @Override
    public void onBind(Article article) {
        articleListingItemViewModel.setArticle(article);
    }

    @OnClick
    public void onClick(View view) {
        articleListingItemViewModel.displayArticle(view);
    }

    public void subscribeToViewModelChanges() {
        articleListingItemViewModel.getArticle().observe(fragment, article -> {
            date.setText(timeFormatter.elapsed(article.getCreatedDate()));
            section.setText(article.getSection());
            title.setText(article.getTitle());
            if (article.hasPhoto()) {
                imageLoader.load(article.getJumboPhotoUrl())
                        .thumbnail(article.getLargeThumbnailUrl())
                        .into(image);
            }
        });
    }
}

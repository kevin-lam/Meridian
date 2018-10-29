package kevinlamcs.android.com.meridian.ui.article.item;

import android.support.constraint.ConstraintLayout;
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

    @BindView(R.id.article_entry)
    ConstraintLayout itemContainer;

    private static final String OPEN_ARTICLE_TRANSITION = "open article transition";
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
        unsubscribeToViewModelChanges();
        subscribeToViewModelChanges();
    }

    @Override
    public void onBind(Article article) {
        articleListingItemViewModel.setArticle(article);
        articleListingItemViewModel.setTransitionName(OPEN_ARTICLE_TRANSITION + article.getTitle());
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

        articleListingItemViewModel.getTransitionName().observe(fragment, transitionName -> itemContainer.setTransitionName(transitionName));
    }

    public void unsubscribeToViewModelChanges() {
        articleListingItemViewModel.getArticle().removeObservers(fragment);
        articleListingItemViewModel.getTransitionName().removeObservers(fragment);
    }
}

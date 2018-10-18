package kevinlamcs.android.com.meridian.ui.article.display;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.chip.ChipGroup;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.Provides;
import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.base.BaseFragment;
import kevinlamcs.android.com.meridian.ui.base.BaseViewModel;
import kevinlamcs.android.com.meridian.ui.browser.BrowserClient;
import kevinlamcs.android.com.meridian.util.image.ImageLoader;

public class ArticleFragment extends BaseFragment {

    @BindView(R.id.article_description_scroll_container)
    HorizontalScrollView descriptionScroller;

    @BindView(R.id.article_description_chip_group)
    ChipGroup descriptionTags;

    @BindView(R.id.article_image)
    ImageView image;

    @BindView(R.id.article_section)
    TextView section;

    @BindView(R.id.article_subsection)
    TextView subsection;

    @BindView(R.id.article_title)
    TextView title;

    @BindView(R.id.article_abstract)
    TextView description;

    @BindView(R.id.article_author)
    TextView author;

    @Inject
    BrowserClient client;

    private static final String ARG_ARTICLE = "ARTICLE";
    private ArticleViewModel articleViewModel;
    private ViewModelProvider.Factory viewModelFactory;
    private ImageLoader imageLoader;

    public ArticleFragment() {
    }

    public static ArticleFragment newInstance(Article article) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ARTICLE, article);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showBackButton(true);
        this.imageLoader = new ImageLoader(new WeakReference<>(this.getContext()));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Article article = (Article) this.getArguments().getSerializable(ARG_ARTICLE);
        articleViewModel.setArticle(article);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    public BaseViewModel getViewModel() {
        articleViewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel.class);
        return articleViewModel;
    }

    @Override
    public void subscribeToViewModelChanges() {
        articleViewModel.getArticle().observe(this, article -> {
            section.setText(article.getSection());
            subsection.setText(article.getSubsection());
            title.setText(article.getTitle());
            description.setText(article.getDescription());
            author.setText(articleViewModel.formatAuthor(article.getAuthor()));
            articleViewModel.addDescriptionTags(getContext(), descriptionTags, article.getDescriptionFacet());
            if (article.hasDescriptionTags()) {
                descriptionScroller.setVisibility(View.VISIBLE);
            }
            if (article.hasPhoto()) {
                imageLoader.load(article.getJumboPhotoUrl()).thumbnail(article.getLargeThumbnailUrl()).into(image);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.article_full_content_button)
    public void click() {
        articleViewModel.displayFullArticle(client);
    }
}

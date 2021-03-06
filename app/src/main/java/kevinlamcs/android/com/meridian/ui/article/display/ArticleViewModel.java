package kevinlamcs.android.com.meridian.ui.article.display;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.view.LayoutInflater;

import java.util.List;

import javax.inject.Inject;

import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.data.model.api.Article;
import kevinlamcs.android.com.meridian.ui.base.BaseViewModel;
import kevinlamcs.android.com.meridian.ui.browser.BrowserClient;
import kevinlamcs.android.com.meridian.util.TextUtil;

public class ArticleViewModel extends BaseViewModel {

    private MutableLiveData<Article> article = new MutableLiveData<>();

    @Inject
    public ArticleViewModel() {
    }

    public void setArticle(Article article) {
        this.article.setValue(article);
    }

    public MutableLiveData<Article> getArticle() {
        return article;
    }

    public String formatAuthor(String author) {
        String lowerCaseAuthor = author.toLowerCase();
        String capitalizedAuthor = TextUtil.capitalize(lowerCaseAuthor);
        return TextUtil.toLowerCaseAt(0, capitalizedAuthor);
    }

    public void addDescriptionTags(Context context, ChipGroup descriptionTags, List<String> descriptionFacets) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (String description : descriptionFacets) {
            Chip tag = (Chip) inflater.inflate(R.layout.chip_description, descriptionTags, false);
            tag.setChipText('#' + TextUtil.removeNonAlphaNumeric(description));
            descriptionTags.addView(tag);
        }
    }

    public void displayFullArticle(BrowserClient client) {
        client.load(article.getValue().getUrl());
    }
}

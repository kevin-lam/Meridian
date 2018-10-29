package kevinlamcs.android.com.meridian.ui.article.listing;

import android.content.Context;

import kevinlamcs.android.com.meridian.R;
import kevinlamcs.android.com.meridian.util.AppConstants;

public class ArticleSection {

    private String title;
    private String value;
    private int id;

    public ArticleSection(int id, String title) {
        this.id = id;
        this.title = title;
        this.value = idToValue();
    }

    private String idToValue() {
        switch (this.id) {
            case R.id.nav_front:
                return AppConstants.SECTION_HOME;
            case R.id.nav_world:
                return AppConstants.SECTION_WORLD;
            case R.id.nav_us:
                return AppConstants.SECTION_NATIONAL;
            case R.id.nav_politics:
                return AppConstants.SECTION_POLITICS;
            case R.id.nav_business:
                return AppConstants.SECTION_BUSINESS;
            case R.id.nav_tech:
                return AppConstants.SECTION_TECHNOLOGY;
            case R.id.nav_science:
                return AppConstants.SECTION_SCIENCE;
            case R.id.nav_sports:
                return AppConstants.SECTION_SPORTS;
            case R.id.nav_food:
                return AppConstants.SECTION_FOOD;
            case R.id.nav_travel:
                return AppConstants.SECTION_TRAVEL;
        }
        return "";
    }

    @Override
    public String toString() {
        return value;
    }
}

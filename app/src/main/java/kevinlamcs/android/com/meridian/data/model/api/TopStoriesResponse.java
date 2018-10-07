package kevinlamcs.android.com.meridian.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class TopStoriesResponse {

    public TopStoriesResponse() {
    }

    @Expose
    @SerializedName("results")
    private List<Article> results;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("num_results")
    private Integer numResults;

    @Expose
    @SerializedName("last_updated")
    private String lastUpdated;

    @Expose
    @SerializedName("copyright")
    private String copyright;

    @Expose
    @SerializedName("section")
    private String section;

    public List<Article> getResults() {
        return results;
    }

    public void setResults(List<Article> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}

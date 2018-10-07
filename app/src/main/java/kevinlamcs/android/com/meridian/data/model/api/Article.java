package kevinlamcs.android.com.meridian.data.model.api;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kevinlamcs.android.com.meridian.util.AppConstants;
import kevinlamcs.android.com.meridian.util.converter.MultimediaListTypeConverter;
import kevinlamcs.android.com.meridian.util.converter.StringListTypeConverter;

@Entity(tableName = AppConstants.ARTICLE_DATABASE_NAME, primaryKeys = {"title", "author"})
@TypeConverters({StringListTypeConverter.class, MultimediaListTypeConverter.class})
public class Article {

    public Article() {
    }

    @Expose
    @SerializedName("title")
    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @Expose
    @SerializedName("section")
    @ColumnInfo(name = "section")
    private String section;

    @Expose
    @SerializedName("subsection")
    @ColumnInfo(name = "subsection")
    private String subsection;

    @Expose
    @SerializedName("results")
    @ColumnInfo(name = "description")
    private String description;

    @Expose
    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;

    @Expose
    @SerializedName("byline")
    @NonNull
    @ColumnInfo(name = "author")
    private String author;

    @Expose
    @SerializedName("item_type")
    @ColumnInfo(name = "item_type")
    private String itemType;

    @Expose
    @SerializedName("updated_date")
    @ColumnInfo(name = "updated_date")
    private String updatedDate;

    @Expose
    @SerializedName("created_date")
    @ColumnInfo(name = "created_date")
    private String createdDate;

    @Expose
    @SerializedName("publish_date")
    @ColumnInfo(name = "publish_date")
    private String publishDate;

    @Expose
    @SerializedName("material_type_facet")
    @ColumnInfo(name = "material_type_facet")
    private String materialTypeFacet;

    @Expose
    @SerializedName("kicker")
    @ColumnInfo(name = "kicker")
    private String kicker;

    @Expose
    @SerializedName("des_facet")
    @ColumnInfo(name = "des_facet")
    private List<String> descriptionFacet;

    @Expose
    @SerializedName("org_facet")
    @ColumnInfo(name = "org_facet")
    private List<String> organizationFacet;

    @Expose
    @SerializedName("per_facet")
    @ColumnInfo(name = "per_facet")
    private List<String> personFacet;

    @Expose
    @SerializedName("geo_facet")
    @ColumnInfo(name = "geo_facet")
    private List<String> geographyFacet;

    @Expose
    @SerializedName("multimedia")
    @ColumnInfo(name = "multimedia")
    private List<Multimedia> multimedia;

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull String author) {
        this.author = author;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public List<String> getDescriptionFacet() {
        return descriptionFacet;
    }

    public void setDescriptionFacet(List<String> descriptionFacet) {
        this.descriptionFacet = descriptionFacet;
    }

    public List<String> getOrganizationFacet() {
        return organizationFacet;
    }

    public void setOrganizationFacet(List<String> organizationFacet) {
        this.organizationFacet = organizationFacet;
    }

    public List<String> getPersonFacet() {
        return personFacet;
    }

    public void setPersonFacet(List<String> personFacet) {
        this.personFacet = personFacet;
    }

    public List<String> getGeographyFacet() {
        return geographyFacet;
    }

    public void setGeographyFacet(List<String> geographyFacet) {
        this.geographyFacet = geographyFacet;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }
}

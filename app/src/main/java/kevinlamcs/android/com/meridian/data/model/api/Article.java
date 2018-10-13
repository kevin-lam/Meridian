package kevinlamcs.android.com.meridian.data.model.api;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import kevinlamcs.android.com.meridian.util.converter.MultimediaListTypeConverter;
import kevinlamcs.android.com.meridian.util.converter.StringListTypeConverter;

import static kevinlamcs.android.com.meridian.data.model.api.Article.TABLE_NAME;

@Entity(tableName = TABLE_NAME, primaryKeys = {"title", "author"})
@TypeConverters({StringListTypeConverter.class, MultimediaListTypeConverter.class})
public class Article {

    public static final String TABLE_NAME = "articles";

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

    @Override
    public boolean equals(Object obj) {

        Article other = (Article) obj;

        boolean titleSame = Objects.equals(this.title, other.getTitle());
        boolean authorSame = Objects.equals(this.author, other.getAuthor());
        boolean sectionSame = Objects.equals(this.section, other.getSection());
        boolean subSectionSame = Objects.equals(this.subsection, other.getSubsection());
        boolean descriptionSame = Objects.equals(this.description, other.getDescription());
        boolean urlSame = Objects.equals(this.url, other.getUrl());
        boolean itemTypeSame = Objects.equals(this.itemType, other.getItemType());
        boolean updatedDateSame = Objects.equals(this.updatedDate, other.getUpdatedDate());
        boolean createdDateSame = Objects.equals(this.createdDate, other.getCreatedDate());
        boolean publishDateSame = Objects.equals(this.publishDate, other.getPublishDate());
        boolean materialTypeFacetSame = Objects.equals(this.materialTypeFacet, other.getMaterialTypeFacet());
        boolean kickerSame = Objects.equals(this.kicker, other.getKicker());
        boolean descriptionFacetSame = Objects.equals(this.descriptionFacet, other.descriptionFacet);
        boolean personFacetSame = Objects.equals(this.personFacet, other.getPersonFacet());
        boolean geographyFacetSame = Objects.equals(this.geographyFacet, other.getGeographyFacet());
        boolean multimediaSame = Objects.equals(this.multimedia, other.getMultimedia());

        return (titleSame && authorSame && sectionSame && subSectionSame && descriptionSame &&
                urlSame && itemTypeSame && updatedDateSame && createdDateSame && publishDateSame &&
                materialTypeFacetSame && kickerSame && descriptionFacetSame && personFacetSame &&
                geographyFacetSame && multimediaSame);
    }

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

    public String getStandardThumbnailUrl() {
        return getPhotoUrl(Multimedia.Photo.STANDARD_THUMBNAIL);
    }

    public String getLargeThumbNailUrl() {
        return getPhotoUrl(Multimedia.Photo.LARGE_THUMBNAIL);
    }

    public String getNormalPhotoUrl() {
        return getPhotoUrl(Multimedia.Photo.NORMAL);
    }

    private String getPhotoUrl(int type) {
        List<Multimedia> photoList = getMultimedia();
        if (!photoList.isEmpty()) {
            return photoList.get(type).getUrl();
        }
        return "";
    }
}

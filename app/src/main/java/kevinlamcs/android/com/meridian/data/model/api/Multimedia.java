package kevinlamcs.android.com.meridian.data.model.api;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Multimedia {

    public Multimedia() {
    }

    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("format")
    private String format;

    @Expose
    @SerializedName("height")
    private Integer height;

    @Expose
    @SerializedName("width")
    private Integer width;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("subtype")
    private String subtype;

    @Expose
    @SerializedName("caption")
    private String caption;

    @Expose
    @SerializedName("copyright")
    private String copyright;

    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}

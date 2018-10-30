package kevinlamcs.android.com.meridian.data.model.api;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Multimedia implements Serializable{

    public enum PhotoType {
        STANDARD_THUMBNAIL(0), LARGE_THUMBNAIL(1), NORMAL(2), MEDIUM(3), JUMBO(4);

        private final int value;
        static String[] formats = new String[5];
        static {
            formats[0] = "Standard Thumbnail";
            formats[1] = "thumbLarge";
            formats[2] = "Normal";
            formats[3] = "mediumThreeByTwo210";
            formats[4] = "superJumbo";
        }
        PhotoType(int value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return formats[this.value];
        }

        public int valueOf() {
            return ordinal();
        }
    }

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

    @Override
    public boolean equals(Object obj) {

        Multimedia other = (Multimedia) obj;

        boolean urlSame = Objects.equals(this.url, other.getUrl());
        boolean formatSame = Objects.equals(this.format, other.getFormat());
        boolean heightSame = Objects.equals(this.height, other.getHeight());
        boolean widthSame = Objects.equals(this.width, other.getWidth());
        boolean typeSame = Objects.equals(this.type, other.getType());
        boolean subTypeSame = Objects.equals(this.subtype, other.getSubtype());
        boolean captionSame = Objects.equals(this.caption, other.getCaption());
        boolean copyrightSame = Objects.equals(this.copyright, other.getCopyright());
        return (urlSame && formatSame && heightSame && widthSame && typeSame && subTypeSame &&
                captionSame && copyrightSame);
    }

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

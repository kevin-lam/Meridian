package kevinlamcs.android.com.meridian.util.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kevinlamcs.android.com.meridian.data.model.api.Multimedia;

public class MultimediaListTypeConverter {

    @TypeConverter
    public static List<Multimedia> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Multimedia>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<Multimedia> list) {
        return new Gson().toJson(list);
    }
}

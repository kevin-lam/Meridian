package kevinlamcs.android.com.meridian.util.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.lang.ref.WeakReference;

import kevinlamcs.android.com.meridian.util.AppConstants;

public class ImageLoader {

    private final WeakReference<Context> contextReference;
    private GlideRequest<Drawable> glideRequest;

    public ImageLoader(WeakReference<Context> contextReference) {
        this.contextReference = contextReference;
    }

    public ImageLoader load(String url) {
        Context context = contextReference.get();
        if (context != null) {
            glideRequest = GlideApp.with(context)
                    .asDrawable()
                    .dontAnimate()
                    .override(AppConstants.THUMBNAIL_SIZE, AppConstants.THUMBNAIL_SIZE)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .load(url);
        }
        return this;
    }

    public void into(ImageView view) {
        if (glideRequest != null) {
            glideRequest.into(view);
            glideRequest = null;
        }
    }

    public GlideRequest<Drawable> getGlideRequest() {
        return glideRequest;
    }
}

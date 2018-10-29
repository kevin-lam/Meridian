package kevinlamcs.android.com.meridian.util.image;

import com.bumptech.glide.util.ViewPreloadSizeProvider;

import dagger.Module;
import dagger.Provides;

import static com.bumptech.glide.ListPreloader.*;

@Module
public class ImageModule {

    @Provides
    PreloadSizeProvider providePreloadSizeProvider() {
        return new ViewPreloadSizeProvider();
    }
}

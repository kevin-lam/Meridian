package kevinlamcs.android.com.meridian.util;

import dagger.Module;
import dagger.Provides;
import kevinlamcs.android.com.meridian.util.TimeFormatter;

@Module
public class UtilModule {

    @Provides
    TimeFormatter provideTimeFormatter() {
        return new TimeFormatter();
    }
}

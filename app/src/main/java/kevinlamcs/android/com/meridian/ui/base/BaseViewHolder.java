package kevinlamcs.android.com.meridian.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public abstract void onBind(T item);

    public BaseViewHolder(View itemView) {
        super(itemView);
    }
}

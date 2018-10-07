package kevinlamcs.android.com.meridian.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public abstract void onBind(int position);

    public BaseViewHolder(View itemView) {
        super(itemView);
    }
}

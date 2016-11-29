package cs321.customstrength;
/**
 * Created by Savindi on 11/28/2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public abstract class DemoViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public DemoViewHolder(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.cardTitle);
    }
}
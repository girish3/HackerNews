package github.girish3.hackernews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.girish3.hackernews.R;
import github.girish3.hackernews.TimeDiff;
import github.girish3.hackernews.data.Comment;

/**
 * Created by Girish on 30/05/16.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private final List<Comment> mComments;
    private final Context mContext;

    public CommentAdapter(Context context, List<Comment> comments) {
        mComments = comments;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.comment_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Comment comment = mComments.get(position);
        setView(holder.mUp, comment.getType());
        holder.tvComment.setText(Html.fromHtml(comment.getComment()));
        holder.tvAuthor.setText(comment.getAuthor());
        holder.tvTime.setText(TimeDiff.getTimeAgo(comment.getTime()));
    }

    private void setView(ImageView mUp, int type) {

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        if (type == Comment.COMMENT_TYPE) params.setMargins(0, dpToPx(7), 0, 0);
        else params.setMargins(dpToPx(15), dpToPx(7), 0, 0);
        params.width = dpToPx(10);
        params.height = dpToPx(10);
        mUp.setLayoutParams(params);
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    private String getFormattedTime(long time) {
        return "-1 hours ago";
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.comment)
        TextView tvComment;

        @Bind(R.id.time_diff)
        TextView tvTime;

        @Bind(R.id.author)
        TextView tvAuthor;

        @Bind(R.id.up_image)
        ImageView mUp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

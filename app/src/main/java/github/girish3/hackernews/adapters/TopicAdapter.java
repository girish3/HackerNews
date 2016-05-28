package github.girish3.hackernews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.girish3.hackernews.R;
import github.girish3.hackernews.data.Topic;

/**
 * Created by Girish on 28/05/16.
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private final ArrayList<Topic> mTopics;
    private final Context mContext;

    public TopicAdapter(Context context, ArrayList<Topic> topics) {
        mTopics = topics;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.topic_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Topic topic = mTopics.get(position);
        holder.tvHeadline.setText(topic.getHeadline());
        holder.tvPoints.setText(mContext.getString(R.string.points_text, topic.getPoints()));
        holder.tvAuthor.setText(mContext.getString(R.string.author_text, topic.getAuthor()));
        holder.tvTime.setText(getFormattedTime(topic.getTime()));
        holder.tvCommentCount.setText(mContext.getString(R.string.author_text, topic.getCommentCount()));
    }

    private String getFormattedTime(int time) {
        return "-1 hours ago";
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.headline)
        TextView tvHeadline;

        @Bind(R.id.points)
        TextView tvPoints;

        @Bind(R.id.time_diff)
        TextView tvTime;

        @Bind(R.id.author)
        TextView tvAuthor;

        @Bind(R.id.comment_count)
        TextView tvCommentCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
package github.girish3.hackernews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.girish3.hackernews.R;
import github.girish3.hackernews.TimeDiff;
import github.girish3.hackernews.data.Topic;

/**
 * Created by Girish on 28/05/16.
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private final List<Topic> mTopics;
    private final Context mContext;
    private OnItemClickListener mListener;

    public TopicAdapter(Context context, List<Topic> topics, OnItemClickListener listener) {
        mTopics = topics;
        mContext = context;
        mListener = listener;
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
        holder.tvTime.setText(TimeDiff.getTimeAgo(topic.getTime()));
        holder.tvCommentCount.setText(mContext.getString(R.string.comment_text, topic.getCommentCount()));
    }

    private String getFormattedTime(long time) {
        return "-1 hours ago";
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Topic topic);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) mListener.onItemClick(mTopics.get(getAdapterPosition()));
        }
    }
}

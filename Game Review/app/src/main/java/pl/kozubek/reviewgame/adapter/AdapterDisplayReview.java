package pl.kozubek.reviewgame.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.entity.Review;

public class AdapterDisplayReview extends RecyclerView.Adapter<AdapterDisplayReview.ViewHolder> {

    private static final String TAG = "AdapterDisplayReview";
    private Long idUser;

    private static String jsonToken = "";
    private LayoutInflater inflater;
    private List<Review> review;
    private Context context;
    private static final DecimalFormat df = new DecimalFormat("0");

    public AdapterDisplayReview(List<Review> review, Context context, String token, Long idUser) {
        this.review = review;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.jsonToken = token;
        this.idUser = idUser;
    }

    @NonNull
    @Override
    public AdapterDisplayReview.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custome_list_review_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterDisplayReview.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        Log.d(TAG, "onBindViewHolder: set viewHolder");
        viewHolder.reviewNick.setText("Nick: " + review.get(i).getNick());
        viewHolder.reviewDate.setText("Date: " + review.get(i).getDate().substring(0, review.get(i).getDate().indexOf("T")));
        viewHolder.gameMark.setText("Mark: " + df.format(review.get(i).getReviewMark()) + "/5");
        viewHolder.gameMark.setText("Mark: " + df.format(review.get(i).getReviewMark()) + "/5");
        viewHolder.gameDescription.setText(review.get(i).getReviewDescription());

    }

    @Override
    public int getItemCount() {
        return review.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView reviewNick, reviewDate, gameMark, gameDescription;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            reviewNick = itemView.findViewById(R.id.reviewNick);
            reviewDate = itemView.findViewById(R.id.reviewDate);
            gameMark = itemView.findViewById(R.id.gameMark);
            gameDescription = itemView.findViewById(R.id.gameDescription);
        }
    }
}
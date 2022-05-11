package pl.kozubek.reviewgame.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import pl.kozubek.reviewgame.entity.Game;
import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.display.DisplayReviewActivity;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private static final String TAG = "Adapter";
    private LayoutInflater inflater;
    private List<Game> games;
    private Context context;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Adapter(Context context, List<Game> games) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.games = games;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custome_list_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        viewHolder.gameTitle.setText(games.get(i).getTitle());
        viewHolder.gameAuthor.setText("Author: " + games.get(i).getAuthor());
        viewHolder.gameDescription.setText(games.get(i).getDescription());
        viewHolder.gameMark.setText("Mark: " + df.format(games.get(i).getMark()));
        Picasso.get().load(games.get(i).getImageURL()).into(viewHolder.gameImage);

        viewHolder.itemView.setOnClickListener(view -> {
            Log.d(TAG,"onBindViewHolder: click on" +games.get(i));
//            Toast.makeText(context, "You clicked " + games.get(i).getId(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, DisplayReviewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", games.get(i).getId());
            intent.putExtra("title", games.get(i).getTitle());
            intent.putExtra("imageURL", games.get(i).getImageURL());
            intent.putExtra("author", games.get(i).getAuthor());
            intent.putExtra("mark", games.get(i).getMark());
            intent.putExtra("description", games.get(i).getDescription());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        System.out.println(games.size());
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView gameTitle, gameAuthor, gameDescription, gameMark;
        ImageView gameImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameTitle = itemView.findViewById(R.id.gameTitle);
            gameAuthor = itemView.findViewById(R.id.gameAuthor);
            gameDescription = itemView.findViewById(R.id.gameDescription);
            gameMark = itemView.findViewById(R.id.gameMark);
            gameImage = itemView.findViewById(R.id.gameImage);
        }
    }
}

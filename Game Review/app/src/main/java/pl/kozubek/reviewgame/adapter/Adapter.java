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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.display.DisplayReviewActivity;
import pl.kozubek.reviewgame.entity.Game;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private static final String TAG = "Adapter";
    private Long idUser;
    private LayoutInflater inflater;
    private List<Game> games;
    private Context context;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private String jsonToken;

    public Adapter(Context context, List<Game> games,String token, Long idUser) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.games = games;
        this.jsonToken = token;
        this.idUser = idUser;
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
            Intent intent = new Intent(context, DisplayReviewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", games.get(i).getId());
            intent.putExtra("idUser",idUser);
            intent.putExtra("title", games.get(i).getTitle());
            intent.putExtra("imageURL", games.get(i).getImageURL());
            intent.putExtra("author", games.get(i).getAuthor());
            intent.putExtra("mark", games.get(i).getMark());
            intent.putExtra("description", games.get(i).getDescription());
            intent.putExtra("jwtToken", jsonToken);
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

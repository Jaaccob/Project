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

import java.util.List;

import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.display.DisplayReviewActivity;
import pl.kozubek.reviewgame.dto.GameDto;

public class AdapterDisplayFollowedGames extends RecyclerView.Adapter<AdapterDisplayFollowedGames.ViewHolder> {

    private static final String TAG = "AdapterDisplayFollowedGames";
    private Long idUser;

    private LayoutInflater inflater;
    private List<GameDto> games;
    private Context context;
    private String jsonToken;

    public AdapterDisplayFollowedGames(Context context, List<GameDto> games, String token, Long idUser) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.games = games;
        this.jsonToken = token;
        this.idUser = idUser;
    }

    @NonNull
    @Override
    public AdapterDisplayFollowedGames.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custome_list_followed_game_layout, viewGroup, false);
        return new AdapterDisplayFollowedGames.ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterDisplayFollowedGames.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        viewHolder.gameTitle.setText(games.get(i).getTitle());
        viewHolder.gameAuthor.setText("Author: " + games.get(i).getAuthor());
        viewHolder.gameDescription.setText(games.get(i).getDescription());
        Picasso.get().load(games.get(i).getImageURL()).into(viewHolder.gameImage);

        viewHolder.itemView.setOnClickListener(view -> {
            Log.d(TAG, "onBindViewHolder: click on" + games.get(i));
            Intent intent = new Intent(context, DisplayReviewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", games.get(i).getId());
            intent.putExtra("idUser", idUser);
            intent.putExtra("title", games.get(i).getTitle());
            intent.putExtra("imageURL", games.get(i).getImageURL());
            intent.putExtra("author", games.get(i).getAuthor());
            intent.putExtra("description", games.get(i).getDescription());
            intent.putExtra("jwtToken", jsonToken);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView gameTitle, gameAuthor, gameDescription, gameType;
        ImageView gameImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameTitle = itemView.findViewById(R.id.follow_game_title);
            gameAuthor = itemView.findViewById(R.id.follow_game_author);
            gameDescription = itemView.findViewById(R.id.follow_game_description);
            gameType = itemView.findViewById(R.id.follow_game_type);
            gameImage = itemView.findViewById(R.id.follow_game_image);
        }
    }
}

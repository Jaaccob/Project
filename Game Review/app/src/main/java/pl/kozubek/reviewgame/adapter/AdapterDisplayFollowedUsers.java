package pl.kozubek.reviewgame.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.display.DisplayUserProfile;
import pl.kozubek.reviewgame.entity.User;

public class AdapterDisplayFollowedUsers extends RecyclerView.Adapter<AdapterDisplayFollowedUsers.ViewHolder> {
    private static final String TAG = "AdapterDisplayFollowedUsers";
    private Long idUser;

    private LayoutInflater inflater;
    private List<User> users;
    private Context context;
    private String jsonToken;

    public AdapterDisplayFollowedUsers(Context context, List<User> users, String token, Long idUser) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.users = users;
        this.jsonToken = token;
        this.idUser = idUser;
    }

    @NonNull
    @Override
    public AdapterDisplayFollowedUsers.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custome_list_followed_user, viewGroup, false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterDisplayFollowedUsers.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        viewHolder.userNick.setText(users.get(i).getNick());
//        Picasso.get().load(users.get(i).getImageURL()).into(viewHolder.userImage);
//        Picasso.get().load("https://i.ytimg.com/vi/wcLayGm_pM4/maxresdefault.jpg").into(viewHolder.userImage);

        viewHolder.itemView.setOnClickListener(view -> {
            Log.d(TAG, "onBindViewHolder: click on" + users.get(i));
            Intent intent = new Intent(context, DisplayUserProfile.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", users.get(i).getId());
            intent.putExtra("idUser", idUser);
            intent.putExtra("nick", users.get(i).getNick());
//            intent.putExtra("imageURL", "https://i.ytimg.com/vi/wcLayGm_pM4/maxresdefault.jpg");
            intent.putExtra("firstName", users.get(i).getFirstName());
            intent.putExtra("lastName", users.get(i).getLastName());
            intent.putExtra("email", users.get(i).getEmail());
            intent.putExtra("jwtToken", jsonToken);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView userNick;
//        ImageView userImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            userImage = itemView.findViewById(R.id.follow_user_image);
            userNick = itemView.findViewById(R.id.follow_user_nick);
        }
    }
}

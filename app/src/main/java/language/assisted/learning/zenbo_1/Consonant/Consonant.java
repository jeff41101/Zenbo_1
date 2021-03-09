package language.assisted.learning.zenbo_1.Consonant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asus.robotframework.API.RobotCallback;

import java.util.ArrayList;
import java.util.List;

import language.assisted.learning.zenbo_1.Adapter.Function;
import language.assisted.learning.zenbo_1.R;
import language.assisted.learning.zenbo_1.RobotActivity;

public class Consonant extends RobotActivity {

    private static final String TAG = Consonant.class.getSimpleName();
    private List<Function> consonant_functions;

    public Consonant(RobotCallback robotCallback, RobotCallback.Listen robotListenCallback) {
        super(robotCallback, robotListenCallback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consonant);
        //consonant functions
        consonant_functions = new ArrayList<>();
        String[] funcs = getResources().getStringArray(R.array.consonant_functions);
        setupFunctions(consonant_functions, funcs);
        //recycler
        RecyclerView recyclerView = findViewById(R.id.consonant_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //adapter
        IconAdapter adapter = new IconAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setupFunctions(List<Function> consonant_functions, String[] funcs) {
        consonant_functions.add(new Function(funcs[0], R.drawable.bemele));
        consonant_functions.add(new Function(funcs[1], R.drawable.peteke));
        consonant_functions.add(new Function(funcs[2], R.drawable.degene));
        consonant_functions.add(new Function(funcs[3], R.drawable.fehe));
        consonant_functions.add(new Function(funcs[4], R.drawable.gichishi));
        consonant_functions.add(new Function(funcs[5], R.drawable.gecheshe));
    }

    public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconHolder> {
        @NonNull
        @Override
        public IconHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_icon, parent, false);
            return new IconHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull IconHolder holder, int position) {
            final Function function1 = consonant_functions.get(position);
            holder.nameText.setText(function1.getName());
            holder.iconImage.setImageResource(function1.getIcon());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClicked(function1);
                }
            });
        }


        @Override
        public int getItemCount() {
            return consonant_functions.size();
        }

        public class IconHolder extends RecyclerView.ViewHolder {
            ImageView iconImage;
            TextView nameText;

            public IconHolder(@NonNull View itemView) {
                super(itemView);
                iconImage = itemView.findViewById(R.id.item_icon);
                nameText = itemView.findViewById(R.id.item_name);
            }
        }
    }

    private void itemClicked(Function function) {
        switch (function.getIcon()) {
            case R.drawable.bemele:
                robotAPI.robot.speak("ㄅㄇㄌ");
                Log.d(TAG, "itemClicked: " +  "bemele");
                Intent bemele_intent = new Intent(Consonant.this, bemele.class);
                startActivity(bemele_intent);
                break;
            case R.drawable.peteke:
                break;
            case R.drawable.fehe:
                break;
            case R.drawable.gichishi:
                break;
            case R.drawable.gecheshe:
                break;
        }
    }
}
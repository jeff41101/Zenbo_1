package language.assisted.learning.zenbo_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asus.robotframework.API.RobotCallback;

import java.util.ArrayList;
import java.util.List;

import language.assisted.learning.zenbo_1.Adapter.Function;
import language.assisted.learning.zenbo_1.Consonant.Consonant;

public class MainActivity extends RobotActivity {

    private static final int REQUEST_LOGIN = 100;
    public static boolean logon = false;
    private List<Function> functions;

    public MainActivity(RobotCallback robotCallback, RobotCallback.Listen robotListenCallback) {
        super(robotCallback, robotListenCallback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!logon) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, REQUEST_LOGIN);
        }
        else {
            robotAPI.robot.speak("嗨!我是Zenbo,很高興認識你們");
            robotAPI.robot.speak("歡迎來到注音符號樂園，你們想學什麼呢?");
        }
        //Recycler
        functions = new ArrayList<>();
        String[] funcs = getResources().getStringArray(R.array.functions);
        setupFunctions(functions, funcs);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //adapter
//        FunctionAdapter adapter = new FunctionAdapter(this);
        IconAdapter adapter = new IconAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setupFunctions(List<Function> functions, String[] funcs) {
        functions.add(new Function(funcs[0], R.drawable.consonant));
        functions.add(new Function(funcs[1], R.drawable.vowel));
        functions.add(new Function(funcs[2], R.drawable.basic));
        functions.add(new Function(funcs[3], R.drawable.application));
        functions.add(new Function(funcs[4], R.drawable.change));
//        functions.add(new Function(funcs[5], R.drawable.baymab));
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
            final Function function = functions.get(position);
            holder.nameText.setText(function.getName());
            holder.iconImage.setImageResource(function.getIcon());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClicked(function);
                }
            });
        }

        @Override
        public int getItemCount() {
            return functions.size();
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
            case R.drawable.consonant:
                robotAPI.robot.speak("聲母");
                Intent Consonantintent = new Intent(MainActivity.this, Consonant.class);
                startActivity(Consonantintent);
                break;
            case R.drawable.vowel:
                break;
            case R.drawable.basic:
                break;
            case R.drawable.application:
                break;
            case R.drawable.change:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode != RESULT_OK) {
                finish();
            }
        }
    }
}
package language.assisted.learning.zenbo_1.Consonant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.asus.robotframework.API.RobotCallback;
import com.google.firebase.database.FirebaseDatabase;

import language.assisted.learning.zenbo_1.R;
import language.assisted.learning.zenbo_1.RobotActivity;

import static language.assisted.learning.zenbo_1.LoginActivity.id;

public class bemele_quiz1 extends RobotActivity {

    private static final String TAG = bemele_quiz1.class.getSimpleName();
    private Button ans1;
    private Button ans2;
    private Button ans3;
    private Button ans4;
    private TextView title;

    public bemele_quiz1(RobotCallback robotCallback, RobotCallback.Listen robotListenCallback) {
        super(robotCallback, robotListenCallback);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_quiz);
        findViews();
        ans1.setText("A. ㄅ");
        ans2.setText("B. ㄉ");
        ans3.setText("C. ㄌ");
        ans4.setText("D. ㄇ");
        title.setText("請問下列哪一個選項是「ㄇ」?");
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(bemele_quiz1.this);
                // 透過 Builder 物件提供的方法設定視窗。
                builder.setTitle("確認答案為 A ?");
                // Listener 設定為 null，表示按下後不做任何動作。
                builder.setNegativeButton("否", null);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(id)
                                        .child("Answer")
                                        .child(bemele_quiz.class.getSimpleName())
                                        .child("2")
                                        .setValue("A");
                                test_end();
                            }
                        }
                );

                builder.show();
            }

        });
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(bemele_quiz1.this);
                // 透過 Builder 物件提供的方法設定視窗。
                builder.setTitle("確認答案為 B ?");
                // Listener 設定為 null，表示按下後不做任何動作。
                builder.setNegativeButton("否", null);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(id)
                                        .child("Answer")
                                        .child(bemele_quiz.class.getSimpleName())
                                        .child("2")
                                        .setValue("B");
                                test_end();
                            }
                        }
                );
                builder.show();
            }

        });
        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(bemele_quiz1.this);
                // 透過 Builder 物件提供的方法設定視窗。
                builder.setTitle("確認答案為 C ?");
                // Listener 設定為 null，表示按下後不做任何動作。
                builder.setNegativeButton("否", null);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(id)
                                        .child("Answer")
                                        .child(bemele_quiz.class.getSimpleName())
                                        .child("2")
                                        .setValue("C");
                                test_end();
                            }
                        }
                );
                builder.show();
            }

        });
        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(bemele_quiz1.this);
                // 透過 Builder 物件提供的方法設定視窗。
                builder.setTitle("確認答案為 D ?");
                // Listener 設定為 null，表示按下後不做任何動作。
                builder.setNegativeButton("否", null);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(id)
                                        .child("Answer")
                                        .child(bemele_quiz.class.getSimpleName())
                                        .child("2")
                                        .setValue("D");
                                test_end();
                            }
                        }
                );
                builder.show();
            }

        });
    }

    private void test_end() {
        robotAPI.robot.speak("哇很棒小朋友!測驗結束囉!");
        new AlertDialog.Builder(bemele_quiz1.this)
                .setTitle("作答完成")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(bemele_quiz1.this, Consonant.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

    private void findViews() {
        title = findViewById(R.id.Title);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

}

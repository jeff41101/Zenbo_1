package language.assisted.learning.zenbo_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private static Object ID = null;
    private TextView nameText;
    private TextView genderText;
    private TextView ageText;
    private TextView idText;
    private TextView passwdText;
    private String name;
    private String gender;
    private String age;
    private String id;
    private String passwd;
    private language.assisted.learning.zenbo_1.userData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    private void viewstoString() {
        name = nameText.getText().toString();
        gender = genderText.getText().toString();
        age = ageText.getText().toString();
        id = idText.getText().toString();
        passwd = passwdText.getText().toString();
    }

    private void findViews() {
        nameText = findViewById(R.id.name);
        genderText = findViewById(R.id.gender);
        ageText = findViewById(R.id.age);
        idText = findViewById(R.id.id);
        passwdText = findViewById(R.id.passwd);
    }

    public void register_click(View view) {
        findViews();
        viewstoString();
        setResult(RESULT_OK);
        FirebaseDatabase.getInstance().getReference("users").child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ID = dataSnapshot.getValue();
                        if (ID == null) {
                            userData = new userData(name, gender, passwd, age);
                            userData.setId(id);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(id)
                                    .setValue(userData);
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RegisterActivity.this);
                            builder.setTitle("註冊結果")
                                    .setMessage("註冊完成，請問是否返回上一頁")
                                    .setNegativeButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            setResult(RESULT_OK);
                                            finish();
                                        }
                                    });
                            builder.setPositiveButton("否",null).show();
                        }
                        else {
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setTitle("註冊結果")
                                    .setMessage("帳號已經被使用，註冊失敗，請重新輸入")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    public void return_click(View view) {
        setResult(RESULT_OK);
        finish();
    }
}
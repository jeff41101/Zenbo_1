package language.assisted.learning.zenbo_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int REQUEST_LOGIN = 300;
    //private static final int REQUEST_CODE_CAMERA = 5;
    private EditText edPasswd;
    private EditText edUserid;
    private CheckBox cbRemember;
    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Camera Permission
//        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//        if (permission == PackageManager.PERMISSION_GRANTED) {
//            takephoto();
//        }
//        else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
//        }
//        getSharedPreferences("Users", MODE_PRIVATE)
//                .edit()
//                .putString("id", "Jeff")
//                .commit();
        findViews();
        //checkbox
        cbRemember.setChecked(getSharedPreferences("Users", MODE_PRIVATE)
                .getBoolean("REMEMBER_USERID", false));
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getSharedPreferences("Users", MODE_PRIVATE)
                        .edit()
                        .putBoolean("REMEMBER_USERID", b)
                        .apply();
            }
        });
        String userid = getSharedPreferences("Users", MODE_PRIVATE)
                .getString("USERID", "");
        edUserid.setText(userid);
    }
    // take photo
//    private void takephoto() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivity(intent);
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_CAMERA) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                takephoto();
//            }
//        }
//    }

    // find view method
    private void findViews() {
        edUserid = findViewById(R.id.userid);
        edPasswd = findViewById(R.id.passwd);
        cbRemember = findViewById(R.id.cb_rem_userid);
    }

    // login method
    public void login(View view) {
        final String userid = edUserid.getText().toString();
        final String passwd = edPasswd.getText().toString();
        id = userid;
        FirebaseDatabase.getInstance().getReference("users").child(userid).child("passwd")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String pw = (String) dataSnapshot.getValue();
                        if (pw.equals(passwd)){
                            //remember the id
                            getSharedPreferences("This_id", MODE_PRIVATE)
                                    .edit()
                                    .putString("USERID", userid)
                                    .commit();
                            //checkbox design
                            boolean remember = getSharedPreferences("Users", MODE_PRIVATE)
                                    .getBoolean("REMEMBER_USERID", false);
                            if (remember) {
                                getSharedPreferences("Users", MODE_PRIVATE)
                                        .edit()
                                        .putString("USERID", userid)
                                        .commit();
                            }
                            //Log.d(TAG, "onDataChange: " + passwd);
                            setResult(RESULT_OK);
                            finish();
                        }
                        else {
                            new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("登入結果")
                            .setMessage("登入失敗")
                            .setPositiveButton("OK", null)
                            .show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//        if ("jack".equals(userid) && "1234".equals(passwd)) {
//            setResult(RESULT_OK);
//            finish();
//        }
//        else {
//            new AlertDialog.Builder(LoginActivity.this)
//                    .setTitle("登入結果")
//                    .setMessage("登入失敗")
//                    .setPositiveButton("OK", null)
//                    .show();
//        }
    }

    public void quit(View view) {
        finish();
    }
    
    public void Register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        setResult(RESULT_CANCELED);
        startActivityForResult(intent, REQUEST_LOGIN);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean remember = getSharedPreferences("Users", MODE_PRIVATE)
                .getBoolean("REMEMBER_USERID", false);
        if (!remember) {
            getSharedPreferences("Users", MODE_PRIVATE)
                    .edit()
                    .putString("USERID", "")
                    .commit();
        }
    }
}
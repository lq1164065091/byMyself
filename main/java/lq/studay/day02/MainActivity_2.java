package lq.studay.day02;

//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.annotation.RequiresApi;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.Toast;
//

import android.support.v7.app.AppCompatActivity;


//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import lq.studay.R;

public class MainActivity_2 extends AppCompatActivity {
//
//    @BindView(R.id.et_username)
//    EditText etUsername;
//    @BindView(R.id.et_password)
//    EditText etPassword;
//    @BindView(R.id.button)
//    Button btn_login;
//    @BindView(R.id.cb_record_pass)
//    CheckBox cbRecordPass;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_2);
//        ButterKnife.bind(this);
//    }
//
//
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @OnClick(R.id.button)
//    public void onViewClicked() {
//
//        if (!cbRecordPass.isChecked()) {
//            Toast.makeText(getApplicationContext(),"未选中",Toast.LENGTH_SHORT).show();
//            return;
//        }
//
////        try {

//            输出
//            String username = etUsername.getText().toString();
//            String password = etPassword.getText().toString();
////            String path = getDataDir().getPath();
//            FileOutputStream outputStream = null;
//            outputStream = this.openFileOutput("text.txt", MODE_PRIVATE);
//            outputStream.write((username+"##"+password).getBytes());
//            outputStream.flush();


//            输入
//            FileInputStream inputStream = openFileInput("text.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            String s = reader.readLine();
//            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();


//            SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
//            SharedPreferences.Editor edit = sp.edit();
//            edit.putString("username","123");
//            edit.putString("password","123");
//            edit.commit();


//        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
//        String str="";
//        Map<String, ?> all = sp.getAll();
//        for (String s : all.keySet()) {
//            str+=all.get(s);
//        }
//        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
//


//            //sd卡
//        Log.e("TAG",Environment.getExternalStorageDirectory().getPath());
//        Log.e("TAG",Environment.getDataDirectory().getPath());
//        Log.e("TAG",Environment.getDownloadCacheDirectory().getPath());
//        Log.e("TAG",Environment.getRootDirectory().getPath());
//



//            Log.e("TAG",Environment.getExternalStorageState());

//        } catch (IOException e) {
//            e.printStackTrace();
//        }







//    }
}

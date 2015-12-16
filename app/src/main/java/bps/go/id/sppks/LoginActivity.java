package bps.go.id.sppks;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;

import bps.go.id.sppks.models.Constants;
import bps.go.id.sppks.models.MPetugas;

/**
 * Created by handi_000 on 7/13/2015.
 */
public class LoginActivity extends Activity {

    private void setPetugas(){
        SharedPreferences pref=getSharedPreferences(Constants.SHARED_PREF,0);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(Constants.SHARED_KODE_PETUGAS,textUsername.getText().toString());
        editor.commit();
    }

    EditText textUsername,textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        textUsername= (EditText) findViewById(R.id.text_username);
        textPassword= (EditText) findViewById(R.id.text_password);

        Button buttonLogin= (Button) findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textUsername.getText().toString().equals(Constants.USER_ADMIN)){
                    if(textPassword.getText().toString().equals(Constants.PASSWORD_ADMIN)){
                        setPetugas();
                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Password anda salah ",Toast.LENGTH_LONG).show();
                    }
                }else{
                    MPetugas petugas=new Select().from(MPetugas.class).where("kode_petugas=? AND password=?",textUsername.getText().toString(),textPassword.getText().toString()).executeSingle();
                    if(petugas!=null){
                        setPetugas();
                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Username / Password anda salah silahkan perbaiki kembali ",Toast.LENGTH_LONG).show();

                    }
                }




            }
        });

    }
}

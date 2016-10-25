package cn.edu.jlu.a4g_profibus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends Activity {
    private MaterialEditText edittextUsername;
    private MaterialEditText edittextPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private AlertDialog reg_dialog;
    private MaterialEditText edittextUsernameReg;
    private MaterialEditText edittextPasswordReg;
    private Button buttonConfirmReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createDialog();
        edittextUsername = (MaterialEditText) findViewById(R.id.edittext_username);
        edittextPassword = (MaterialEditText) findViewById(R.id.edittext_password);
        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonRegister = (Button) findViewById(R.id.button_register);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edittextUsername.getText().toString())||TextUtils.isEmpty(edittextPassword.getText().toString())){
                    Toast.makeText(LoginActivity.this,"请填写账户和密码！",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(checkPassword(edittextUsername.getText().toString(),edittextPassword.getText().toString())){
                        //登录成功后，写入系统配置，保存登录信息
                        SharedPreferences login=getSharedPreferences("login",MODE_PRIVATE);
                        SharedPreferences.Editor editor=login.edit();
                        editor.putString("username",edittextUsername.getText().toString());
                        editor.commit();

                        Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,cn.edu.jlu.a4g_profibus.MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"账户或密码错误！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               reg_dialog.show();
            }
        });
        buttonConfirmReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edittextUsernameReg.getText().toString())||TextUtils.isEmpty(edittextPasswordReg.getText().toString())){
                    Toast.makeText(LoginActivity.this,"请填写账户和密码！",Toast.LENGTH_SHORT).show();
                }
                else{
                    reg_dialog.dismiss();
                    Toast.makeText(LoginActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean checkPassword(String username,String password){
        //共计三组有效用户和密码
        if(username.equals("admin01") && password.equals("jlu001")){
            return true;
        }
        if(username.equals("admin02") && password.equals("jlu002")){
            return true;
        }
        if(username.equals("admin03") && password.equals("jlu003")){
            return true;
        }
        return false;
    }
    public void createDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        LayoutInflater inflater=getLayoutInflater();
        View dialog_view=inflater.inflate(R.layout.layout_regdialog,null);
        edittextUsernameReg = (MaterialEditText) dialog_view.findViewById(R.id.edittext_username_reg);
        edittextPasswordReg = (MaterialEditText) dialog_view.findViewById(R.id.edittext_password_reg);
        buttonConfirmReg = (Button) dialog_view.findViewById(R.id.button_confirm_reg);
        builder.setView(dialog_view);
        reg_dialog=builder.create();
    }
}

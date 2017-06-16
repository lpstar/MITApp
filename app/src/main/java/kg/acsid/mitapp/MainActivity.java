package kg.acsid.mitapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import kg.acsid.mitapp.ext.CheckLogin;
import kg.acsid.mitapp.ext.ChooseRoleReg;
import kg.acsid.mitapp.ext.EnterDialog;
import kg.acsid.mitapp.ext.Roles;
import kg.acsid.mitapp.teacher.Teacher;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    String login;
    String pass;

    int roleId;
    final Context context = this;
    LayoutInflater inflater;
    View propmtView;

    EditText etLogin;
    EditText etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showEnterWindowDialog();
    }
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void showEnterWindowDialog(){
        ArrayList<String> roles = new ArrayList<String>();
        roles.add("Мугалим");
        roles.add("Студент");
        roles.add("Админ");
        roles.add("Староста");
        roles.add("Куратор");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,roles);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inflater = LayoutInflater.from(context);
        propmtView = inflater.inflate(R.layout.enter_dialog,null);

        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        etLogin = (EditText) propmtView.findViewById(R.id.etLogin);
        etPass = (EditText) propmtView.findViewById(R.id.etPass);


        final Spinner spinner = (Spinner) propmtView.findViewById(R.id.spinnerUserRoles);
        spinner.setPrompt("Выбор роли пользователя");
        spinner.setAdapter(arrayAdapter);

        Button acceptBtn = (Button) propmtView.findViewById(R.id.btnAccept);
        Button cancelBtn = (Button) propmtView.findViewById(R.id.btnCancel);
        Button registrationBtn = (Button) propmtView.findViewById(R.id.dialog_reg_select_button);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setRoleId(position);
                Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = etLogin.getText().toString();
                pass = etPass.getText().toString();
                try {
                    JSONObject jsonObject = new CheckLogin(context,login,pass).execute().get();
                    if(jsonObject != null){
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        String jsonText = jsonObject.toString();
                        Teacher teacher = gson.fromJson(jsonText,Teacher.class);
                        if(alertDialog.isShowing()){
                            alertDialog.dismiss();
                        }
                    }else{
                        Toast.makeText(context,"FATAL!",Toast.LENGTH_LONG).show();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Отмена",Toast.LENGTH_SHORT).show();
                finish();
                System.exit(0);
            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Регистрация",Toast.LENGTH_SHORT).show();
                intent = new Intent(context, ChooseRoleReg.class);
                startActivity(intent);
            }
        });
        alertDialog.setCancelable(true);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    finish();
                    System.exit(0);
                    return true;
                }
                return false;
            }
        });
        alertDialog.setView(propmtView);
        alertDialog.show();

    }

}

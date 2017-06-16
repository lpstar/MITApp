package kg.acsid.mitapp.student;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import kg.acsid.mitapp.R;
import kg.acsid.mitapp.adapters.GroupAdapter;
import kg.acsid.mitapp.ext.LoadGroup;
import kg.acsid.mitapp.other.Group;
import kg.acsid.mitapp.parser.JSONParserCustom;

public class StudentRegistration extends AppCompatActivity {
    static String TAG_SUCCESS = "success";
    static String TAG_GROUPS = "groups";
    Context context;
    ArrayList<Group> groupList;
    JSONParserCustom jsonParserCustom;
    JSONArray jsonArray;
    TabHost tabHost;
    EditText etFName;
    EditText etName;
    EditText etLName;
    EditText etPhone;
    EditText etPass;
    EditText etLogin;
    EditText etAddress;
    EditText etMail;
    Switch switchIsContract;
    Spinner sGroupList;

    Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        setTitle("Регистрация нового студента");
        context = this;
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        createTabHost();
        tabHost.setCurrentTab(0);
        etAddress = (EditText) findViewById(R.id.stud_address_et);
        etFName = (EditText) findViewById(R.id.stud_fname_et);
        etName = (EditText) findViewById(R.id.stud_name_et);
        etMail = (EditText) findViewById(R.id.stud_mail_et);
        etLName = (EditText) findViewById(R.id.stud_lname_et);
        etLName = (EditText) findViewById(R.id.stud_mail_et);
        etPhone = (EditText) findViewById(R.id.stud_phone_et);
        switchIsContract = (Switch) findViewById(R.id.switch_is_contract_student);
        sGroupList = (Spinner) findViewById(R.id.stud_group_list);
        LoadGroup async = new LoadGroup(1,context);
        try {
            groupList = async.execute(groupList).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        GroupAdapter myGroups = new GroupAdapter(context,groupList);
        sGroupList.setAdapter(myGroups);

        btnReg = (Button) findViewById(R.id.reg_stud_btn);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student s = new Student();
                Group g = (Group) sGroupList.getSelectedItem();
                s.setFName(etFName.getText().toString());
                s.setLName(etLName.getText().toString());
                s.setMail(etMail.getText().toString());
                s.setAddress(etAddress.getText().toString());
                s.setPhone(etPhone.getText().toString());
                s.setGruppaId(g.getId());
                LoadGroup async = new LoadGroup(s,context,2);
                async.execute();
            }
        });
    }

    void createTabHost(){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.stud_main_data_tab);
        tabSpec.setIndicator("Основные данные");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.stud_secondary_data_tab);
        tabSpec.setIndicator("Контактные данные");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.stud_data_tab);
        tabSpec.setIndicator("Учебные данные");
        tabHost.addTab(tabSpec);
    }



}

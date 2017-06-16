package kg.acsid.mitapp.teacher;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;

import java.util.ArrayList;

import kg.acsid.mitapp.R;
import kg.acsid.mitapp.ext.TeacherViewModel;
import kg.acsid.mitapp.other.Kafedra;

public class TeacherRegistration extends AppCompatActivity {
    TabHost tabHost;
    TeacherViewModel teacherViewModel;
    Context context;
    ArrayList<Kafedra> kafedraArrayList;
    ArrayAdapter<Kafedra> arrayAdapter;
    Spinner sKafedraList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);
        context = this;
        tabHost = (TabHost) findViewById(R.id.teacher_tabhost);
        tabHost.setup();
        createTabHost();
        teacherViewModel = new TeacherViewModel(context);
        kafedraArrayList = new ArrayList<Kafedra>();
        kafedraArrayList = teacherViewModel.loadKafedra();
        sKafedraList = (Spinner) findViewById(R.id.teach_kafedra_list);
        arrayAdapter = new ArrayAdapter<Kafedra>(context,R.layout.custom_spinner_list_item,kafedraArrayList);
        arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_list_item);
        sKafedraList.setAdapter(arrayAdapter);

    }

    void createTabHost(){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.teach_reg_first_tab);
        tabSpec.setIndicator("Основные данные");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.teach_reg_second_tab);
        tabSpec.setIndicator("Контактные данные");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.teach_reg_third_tab);
        tabSpec.setIndicator("Учебные данные");
        tabHost.addTab(tabSpec);
    }
}

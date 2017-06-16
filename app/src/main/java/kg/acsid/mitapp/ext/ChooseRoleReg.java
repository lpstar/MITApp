package kg.acsid.mitapp.ext;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import kg.acsid.mitapp.R;
import kg.acsid.mitapp.student.StudentRegistration;
import kg.acsid.mitapp.teacher.TeacherRegistration;

public class ChooseRoleReg extends AppCompatActivity{
    RadioGroup radioGroup;
    int role = 0;
    Intent intent;
    Context context;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role_reg);
        context = this;
        btnNext = (Button) findViewById(R.id.btnNext);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_reg_choose_role);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioBtnTeacher:
                         intent = new Intent(context, TeacherRegistration.class);
                        break;
                    case R.id.radioBtnStudent:
                        intent = new Intent(context, StudentRegistration.class);
                        break;
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent != null){
                    startActivity(intent);
                }
            }
        });
    }
}

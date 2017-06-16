package kg.acsid.mitapp.ext;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kg.acsid.mitapp.admin.Admin;
import kg.acsid.mitapp.kurator.Kurator;
import kg.acsid.mitapp.parser.JSONParserCustom;
import kg.acsid.mitapp.starosta.Starosta;
import kg.acsid.mitapp.student.Student;
import kg.acsid.mitapp.teacher.Teacher;
import kg.acsid.mitapp.teacher.TeacherLogPass;

/**
 * Created by Админ on 08.06.2017.
 */

public class CheckLogin extends AsyncTask<String,String,JSONObject> {

    Context context;
    ProgressDialog dlg;
    Teacher teacher;
    TeacherLogPass teacherLogPass;
    Student student;
    Kurator kurator;
    Starosta starosta;
    Admin admin;


    String login;
    String password;
    int role = 0;
    boolean dostup = false;
    final String TAG_SUCCESS = "success";
    JSONArray jsonArray = null;
    JSONParserCustom jsonParserCustom = null;

    private static String URL_POST = "";
    private static String TAG = "";

    public CheckLogin(Context context, String login, String password) {
        this.context = context;
        this.login = login;
        this.password = password;
    }

    public CheckLogin() {
    }

    @Override
    protected void onPreExecute() {
        dlg = new ProgressDialog(context);
        dlg.show();

    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        dlg.dismiss();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        return checkLoginAndPass();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public boolean getDostup() {
        return dostup;
    }

    public void setDostup(boolean dostup) {
        this.dostup = dostup;
    }
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public TeacherLogPass getTeacherLogPass() {
        return teacherLogPass;
    }

    public void setTeacherLogPass(TeacherLogPass teacherLogPass) {
        this.teacherLogPass = teacherLogPass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Kurator getKurator() {
        return kurator;
    }

    public void setKurator(Kurator kurator) {
        this.kurator = kurator;
    }

    public Starosta getStarosta() {
        return starosta;
    }

    public void setStarosta(Starosta starosta) {
        this.starosta = starosta;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    JSONObject checkLoginAndPass(){
        switch (role){
            case 0:
                URL_POST = "http://mit.oshsu.kg/android/get_teacher_log_pass.php";
                TAG = "teacher";
                break;
            case 1:
                URL_POST = "http://mit.oshsu.kg/android/get_student_log_pass.php";
                TAG = "students";
                break;
            case 2:
                URL_POST = "http://mit.oshsu.kg/android/get_admin_log_pass.php";
                TAG = "admin";
                break;
            case 3:
                URL_POST = "http://mit.oshsu.kg/android/get_kurator_log_pass.php";
                TAG = "kurator";
                break;
            case 4:
                URL_POST = "http://mit.oshsu.kg/android/get_starosta_log_pass.php";
                TAG = "starosta";
                break;
            default:
                URL_POST = "http://mit.oshsu.kg/android/get_default.php";
                TAG = "default";
                break;
        }
        return check(URL_POST);
    }




    private JSONObject check(String url) {
        JSONObject o = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("login",login));
        params.add(new BasicNameValuePair("pass",password));
        jsonParserCustom = new JSONParserCustom();
        JSONObject jsonObject = jsonParserCustom.makeHttpRequest(url,"POST",params);
        if(jsonObject != null){
            try{
                int success = jsonObject.getInt(TAG_SUCCESS);
                if(success == 1){
                    JSONArray jsonArray = jsonObject.getJSONArray(TAG);
                    o = jsonArray.getJSONObject(0);
                }
                if(success == 0){
                    o = null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
}

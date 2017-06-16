package kg.acsid.mitapp.ext;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kg.acsid.mitapp.MainActivity;
import kg.acsid.mitapp.other.Group;
import kg.acsid.mitapp.parser.JSONParserCustom;
import kg.acsid.mitapp.student.Student;

/**
 * Created by Админ on 13.06.2017.
 */
public class LoadGroup extends AsyncTask<ArrayList<Group>,Integer,ArrayList<Group>>{
    ProgressDialog progressDialog;
    ArrayList<Group> arrayList;
    JSONParserCustom jsonParserCustom;
    JSONArray jsonArray;
    public int actionIndex = 0;
    public Context context;
    public Student student;
    static String TAG_SUCCESS = "success";
    static String TAG_GROUPS = "groups";

    public LoadGroup(Student student, Context context, int actionIndex) {
        this.student = student;
        this.context = context;
        this.actionIndex = actionIndex;
    }

    @Override
    protected ArrayList<Group> doInBackground(ArrayList<Group>... params) {
        switch (actionIndex){
            case 1:
                arrayList = loadGroups();
                break;
            case 2:
                postRegistrationData(student);
                arrayList = null;
            break;
            default:
                arrayList = null;
                break;
        }
        return arrayList;
    }

    public LoadGroup(int actionIndex,Context context) {
        this.actionIndex = actionIndex;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Загрузка данных для ргеистрации...");
        progressDialog.show();
    }
    @Override
    protected void onPostExecute(ArrayList<Group> groups) {
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    ArrayList<Group> loadGroups(){
        arrayList = new ArrayList<Group>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("groups","*"));
        jsonParserCustom = new JSONParserCustom();
        JSONObject jsonObject = jsonParserCustom.makeHttpRequest("http://mit.oshsu.kg/android/reg_student.php","POST",params);
        if(jsonObject != null){
            try {
                int success = jsonObject.getInt(TAG_SUCCESS);
                if(success == 1){
                    String jsonString;
                    Group g;
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson;
                    jsonArray = jsonObject.getJSONArray(TAG_GROUPS);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        g = new Group();
                        JSONObject o = jsonArray.getJSONObject(i);
                        gson = gsonBuilder.create();
                        jsonString = o.toString();
                        g = new Group();
                        g = gson.fromJson(jsonString,Group.class);
                        arrayList.add(g);
                    }
                }else if(success == 0){
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
    public void  postRegistrationData(Student student){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(student);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("data",jsonString));
        jsonParserCustom = new JSONParserCustom();
        boolean res = jsonParserCustom.postDataToServer("http://mit.oshsu.kg/reg_student.php","POST",params);
        if(res == true){
            arrayList = null;
        }
    }


    public ArrayList<Group> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Group> arrayList) {
        this.arrayList = arrayList;
    }

}

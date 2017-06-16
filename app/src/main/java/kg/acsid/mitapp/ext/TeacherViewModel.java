package kg.acsid.mitapp.ext;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

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

import kg.acsid.mitapp.other.Group;
import kg.acsid.mitapp.other.Kafedra;
import kg.acsid.mitapp.parser.JSONParserCustom;
import kg.acsid.mitapp.teacher.Teacher;

/**
 * Created by Админ on 16.06.2017.
 */

public class TeacherViewModel {
    static String TAG_SUCCESS = "success";
    static String TAG_KAFEDRA = "kafedra";
    static String TAG_TEACHER = "teacher";
    int commandStatus = 0;
    List<NameValuePair> params;
    ArrayList<Kafedra> kafedraArrayList;
    Teacher teacher;
    Context context;
    TeacherAsyncTask async;

    String postJsonString;
    public TeacherViewModel(int commandStatus) {
        this.commandStatus = commandStatus;
    }

    public TeacherViewModel(int commandStatus, List<NameValuePair> params, ArrayList<Kafedra> kafedraArrayList, Teacher teacher, Context context, TeacherAsyncTask async, String postJsonString) {
        this.commandStatus = commandStatus;
        this.params = params;
        this.kafedraArrayList = kafedraArrayList;
        this.teacher = teacher;
        this.context = context;
        this.async = async;
        this.postJsonString = postJsonString;
    }

    public TeacherViewModel() {
    }

    public TeacherViewModel(Context context) {
        this.context = context;
    }

    public ArrayList<Kafedra> loadKafedra(){
        commandStatus = 1;
        async = new TeacherAsyncTask();
        try {
            kafedraArrayList = async.execute(kafedraArrayList).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return kafedraArrayList;
    }

    public int getCommandStatus() {
        return commandStatus;
    }

    public void setCommandStatus(int commandStatus) {
        this.commandStatus = commandStatus;
    }

    public List<NameValuePair> getParams() {
        return params;
    }

    public void setParams(List<NameValuePair> params) {
        this.params = params;
    }

    public ArrayList<Kafedra> getKafedraArrayList() {
        return kafedraArrayList;
    }

    public void setKafedraArrayList(ArrayList<Kafedra> kafedraArrayList) {
        this.kafedraArrayList = kafedraArrayList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TeacherAsyncTask getAsync() {
        return async;
    }

    public void setAsync(TeacherAsyncTask async) {
        this.async = async;
    }

    public String getPostJsonString() {
        return postJsonString;
    }

    public void setPostJsonString(String postJsonString) {
        this.postJsonString = postJsonString;
    }

    class TeacherAsyncTask extends AsyncTask<ArrayList<Kafedra>,Integer,ArrayList<Kafedra>>{
        ProgressDialog progressDialog;
        JSONParserCustom jsonParserCustom;
        JSONArray jsonArray;
        GsonBuilder gsonBuilder;
        Gson gson;
        public TeacherAsyncTask() {

        }

        @Override
        protected ArrayList<Kafedra> doInBackground(ArrayList<Kafedra>... params) {
            switch (commandStatus){
                case 1:
                    kafedraArrayList = loadAsyncKafedra();
                    break;
                case 2:
                    postTeacherDataToDB();
                    kafedraArrayList = null;
                    break;
            }
            return kafedraArrayList;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Загрузка данных, подождите...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Kafedra> array) {
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
        private void postTeacherDataToDB() {

        }

        private ArrayList<Kafedra> loadAsyncKafedra() {
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("kafedra","*"));
            kafedraArrayList = new ArrayList<Kafedra>();
            jsonParserCustom = new JSONParserCustom();
            JSONObject jsonObject = jsonParserCustom.makeHttpRequest("http://mit.oshsu.kg/android/reg_teacher.php","POST",params);
            if(jsonObject != null){
                try {
                    int success = jsonObject.getInt(TAG_SUCCESS);
                    if(success == 1){
                        String jsonString;
                        Kafedra k;
                         gsonBuilder = new GsonBuilder();
                        jsonArray = jsonObject.getJSONArray(TAG_KAFEDRA);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            k = new Kafedra();
                            JSONObject o = jsonArray.getJSONObject(i);
                            gson = gsonBuilder.create();
                            jsonString = o.toString();
                            k = gson.fromJson(jsonString,Kafedra.class);
                            kafedraArrayList.add(k);
                        }
                    }else if(success == 0){
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return kafedraArrayList;
        }
    }
}

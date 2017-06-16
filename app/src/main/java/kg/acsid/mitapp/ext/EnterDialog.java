package kg.acsid.mitapp.ext;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kg.acsid.mitapp.R;

/**
 * Created by Админ on 07.06.2017.
 */

public class EnterDialog {
    private static String URL_GET_TEACHER_LOG_PASS = "http://mit.oshsu.kg/android/get_teacher_log_pass.php";
    private static String URL_GET_STUDENT_LOG_PASS = "http://mit.oshsu.kg/android/get_student_log_pass.php";
    private static String URL_GET_ADMIN_LOG_PASS = "http://mit.oshsu.kg/android/get_admin_log_pass.php";
    private static String URL_GET_KURATOR_LOG_PASS = "http://mit.oshsu.kg/android/get_kurator_log_pass.php";
    String login;
    String password;

    public EnterDialog(String login, String password) {
        this.login = login;
        this.password = password;
    }

    boolean checkLoginAndPass(int role){
        boolean result = false;
        switch (role){
            case 1:
                result = checkStudent();
                break;
            case 2:
                result = checkTeacher();
                break;
            case 3:
                result = checkAdmin();
                break;
            case 4:
                result = checkStarosta();
                break;
            default:
                result = checkGuest();
                break;
        }
        return  result;
    }

    private boolean checkGuest() {
        boolean res =false;

        return  res;
    }

    private boolean checkStarosta() {
        boolean res = false;

        return  res;
    }

    private boolean checkAdmin() {
        boolean res = false;

        return  res;
    }

    private boolean checkTeacher() {
        boolean res = false;

        return res;
    }

    private boolean checkStudent() {
        boolean res = false;

        return res;
    }
}

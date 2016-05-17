package com.example.activitycollector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;


/**
 * Created by Administrator on 2016/4/26.
 */
public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent){
        AlertDialog.Builder dialogbuilder=new AlertDialog.Builder(context);
        dialogbuilder.setTitle("warning");
        dialogbuilder.setMessage("You are forced to be offline.Please try to login again");
        dialogbuilder.setCancelable(false);
        dialogbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int which){
                ActivityCollector.finishAll();
                Intent intent=new Intent(context,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        AlertDialog alertDialog=dialogbuilder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}

package com.example.activitycollector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2016/4/27.
 */
public class Myactivity extends Activity implements View.OnClickListener{


    private EditText edit;
    private ImageView imageView;
    private ProgressBar progressBar;
    NotificationManager manager;
    int notification_ID;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar) ;
        edit = (EditText) findViewById(R.id.edit);
        this.manager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        this.findViewById(R.id.btn_send).setOnClickListener(this);
        this.findViewById(R.id.btn_cancle).setOnClickListener(this);

        //dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        String inputText=load();
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this,"restoring succeeded",Toast.LENGTH_SHORT).show();

        }
        findViewById(R.id.saveData).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button) .setOnClickListener(this);
        findViewById(R.id.button4) .setOnClickListener(this);
        findViewById(R.id.seekBar) .setOnClickListener(this);
        findViewById(R.id.button5) .setOnClickListener(this);
        findViewById(R.id.create_database).setOnClickListener(this);


    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText=edit.getText().toString();
        save(inputText);
    }

//保存文件
    private void save(String inputText) {
        FileOutputStream out= null;
        BufferedWriter writer =null;
        try
        {
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }catch(IOException e)
            {

                e.printStackTrace();
            }
        }
    }
    //加载文件
    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try {
            in=openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


    @Override
    public  void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.button:
            {
                if (progressBar.getVisibility()==View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                }else
                    progressBar.setVisibility(View.GONE);
            }
            break;
            case R.id.button1:
            {
                Toast.makeText(Myactivity.this, "正在转接到拨号设备", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10010"));
                startActivity(intent);
            }
            break;
            case R.id.button2:
            {
                Toast.makeText(Myactivity.this, "为您跳转到百度", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://baidu.com"));
                startActivity(intent);
            }
            break;
            case R.id.button3:
            {
                if(imageView.getVisibility()==View.GONE)
                {
                    imageView.setVisibility(View.VISIBLE);
                }
                else
                {
                    imageView.setVisibility(View.GONE);
                }

            }
            break;
            case R.id.button4: {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Myactivity.this);
                dialog.setTitle("this is a dialog");
                dialog.setMessage("something important");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                    }
                });
                dialog.show();
            }
            break;
            case R.id.button5:
            {
                ProgressDialog progressDialog=new ProgressDialog(Myactivity.this);
                progressDialog.setTitle("this is a progressdialog");
                progressDialog.setMessage("Loading.....");
                progressDialog.setCancelable(true);
                progressDialog.show();
            }
            break;
            case R.id.seekbar:
            {
                Intent intent=new Intent(Myactivity.this,SeekBarActivity.class);
                startActivity(intent);
            }
            break;

            /**case R.id.add_data:
            {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name","The da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("prices",19);
                db.insert("Book",null,values);
                values.clear();
            }
            break;
             */
            case R.id.btn_send:
                this.sendNotification();
                break;
            case R.id.btn_cancle:
                this.manager.cancel(this.notification_ID);
                break;
            default:
                break;

        }
    }
    private void sendNotification() {
        //设置点击后的意图转到activity
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.test_icon);//设置图标
        builder.setTicker("hello"); //手机状态提示
        builder.setWhen(System.currentTimeMillis());//设置时间
        builder.setContentTitle("通知栏通知");
        builder.setContentText("我来自NotificationDemo");
        builder.setContentIntent(pintent); //点击后的意图
        //开启提示音，提示灯和震动 all代表全都开启
        builder.setDefaults(Notification.DEFAULT_ALL);
        /**
         * 获取notification builder方法只适用于android4.1以上
         * 4.1以下版本 builder.getNotification()方法
         */
        //获取notification builder方法只适用于android4.1以上
        //4.1以下版本 builder.getNotification()方法
        Notification notification = builder.build();

        //发送通知到通知栏
        this.manager.notify(this.notification_ID, notification);
    }






}

package com.talabto.reciverappbyezzat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.talabto.reciverappbyezzat.database.base.MainDataBase;
import com.talabto.reciverappbyezzat.database.dao.ModelDao;
import com.talabto.reciverappbyezzat.database.model.ResponseModel;
import com.talabto.reciverappbyezzat.services.MyForeService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //var
    private AlertDialog alertDialog;
    private List<String> list = new ArrayList<>();
    private ModelDao modelDao;


    // ui
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkServiceIsRun();

        init();


        if (getIntent().getAction() != null) {
            if (getIntent().getAction().equals("show_dialog")) {
                String user = getIntent().getStringExtra("user");

                showRecivedDialog(user);
            }
        }


    }

    private void init() {
        listView = findViewById(R.id.rec_reciver);
        MainDataBase mainDataBase = MainDataBase.getInstance(getApplicationContext());
        modelDao = mainDataBase.getDao();
        getLocalData();






    }

    void getLocalData() {
        if (!list.isEmpty())
        {
            list.clear();
        }
        for (ResponseModel r:modelDao.getAllData())
        {
            list.add(r.getData());
        }

        ArrayAdapter<ResponseModel> responseModelArrayAdapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, list);

        listView.setAdapter(responseModelArrayAdapter);
    }


    void checkServiceIsRun() {
        if (!isMyServiceRunning(MyForeService.class)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(new Intent(getApplicationContext(), MyForeService.class));
            } else {
                startService(new Intent(getApplicationContext(), MyForeService.class));
            }

        }

    }

    public void cancelServices(View view) {
        stopService(new Intent(getApplicationContext(), MyForeService.class));
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void showRecivedDialog(String user_data) {
        Intent intent=new Intent("com.talabto.emitterbyezzat.secd_re");


        alertDialog = new AlertDialog.Builder(this)
                .setTitle("new massger is recived  ")
                .setMessage("want to save it in local database ?!")

                .setPositiveButton("ok save ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("revicer_res","ok accept");
                        sendBroadcast(intent);
                        modelDao.setNewData(new ResponseModel(user_data));
                        getLocalData();


                    }
                }).setNegativeButton("Non Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("revicer_res","non ok refused");
                        sendBroadcast(intent);
                    }
                }).create();
        alertDialog.show();
    }
}
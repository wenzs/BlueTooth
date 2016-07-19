package com.wenzs.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/19.
 */
public class DeviceList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView bluetoothlist;
    List<BluetoothDevice> bluetoothDevices;
    List<String> bluetoothname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list_layout);

        bluetoothlist= (ListView) findViewById(R.id.bluetoothlist);
        bluetoothDevices=new ArrayList<>();
        bluetoothname=new ArrayList<>();
        BluetoothAdapter btadapter=BluetoothAdapter.getDefaultAdapter();
        btadapter.enable();
        Set<BluetoothDevice> bondedDevices = btadapter.getBondedDevices();
        for (BluetoothDevice btdevice:bondedDevices) {
            bluetoothDevices.add(btdevice);
            bluetoothname.add(btadapter.getName());
        }
        ArrayAdapter btlistadapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,bluetoothname);
        bluetoothlist.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent=new Intent(this,MainActivity.class);
        this.setResult(0XAA, intent);
        Bundle bundle=new Bundle();
        bundle.putParcelable("bluetoothDevices",bluetoothDevices.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

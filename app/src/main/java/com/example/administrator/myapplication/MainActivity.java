package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            String s=getContent();
            JSONObject j=new JSONObject(s);
           String name= j.getString("name");
           Log.w("name",name);
        } catch (Exception e) {
            Log.w("sb","傻逼又出错了"+e.getMessage());
            e.printStackTrace();

        }
    }

    public String getContent()throws Exception{
        StringBuilder sb=new StringBuilder();
        ReadableByteChannel rbc=Channels.newChannel(getResources().openRawResource(R.raw.person));
        ByteBuffer bbf=ByteBuffer.allocate(1024);
        while(rbc.read(bbf)>0){
             if( bbf.hasArray()){
               sb.append( new String(bbf.array()));
             }
        }
        return sb.toString();
    }
}

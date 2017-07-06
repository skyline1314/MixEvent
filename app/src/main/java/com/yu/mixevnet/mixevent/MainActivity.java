package com.yu.mixevnet.mixevent;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yu.mixevnet.mixevent.components.MixEvnetView;

public class MainActivity extends AppCompatActivity {
    private Activity mCurActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurActivity=this;
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        MixEvnetView mix_root = (MixEvnetView) findViewById(R.id.mix_root);
        mix_root.setEventViewListener(new MixEvnetView.EventViewListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCurActivity,"单击",Toast.LENGTH_LONG);
                Log.d("单击","单击");
            }

            @Override
            public void onLongPress() {
                Toast.makeText(mCurActivity,"长按",Toast.LENGTH_LONG);
                Log.d("长按","长按");
            }

            @Override
            public void onLongPressUp() {
                Toast.makeText(mCurActivity,"长按松开",Toast.LENGTH_LONG);
                Log.d("长按","长按松开");
            }
        });
    }
}

package com.yu.mixevnet.mixevent.components;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 实现点击，长按以及松开事件
 * Created by skyline on 2017/7/6.
 */
public class MixEvnetView extends RelativeLayout implements View.OnTouchListener, View.OnClickListener {
    private static int MSG_LONG_PRESS = 1;
    private static int MSG_LONG_PRESS_UP = 2;
    private boolean bLongPress = false;
    Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_LONG_PRESS) {
                bLongPress = true;
                if (mListener != null) {
                    mListener.onLongPress();
                }
            } else if (msg.what == MSG_LONG_PRESS_UP) {
                bLongPress = false;
                if (mListener != null) {
                    mListener.onLongPressUp();
                }
            }
        }
    };

    public MixEvnetView(Context context) {
        super(context);
        init();
    }

    public MixEvnetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MixEvnetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandle.sendEmptyMessageDelayed(MSG_LONG_PRESS, 400);
                break;
            case MotionEvent.ACTION_UP:
                mHandle.removeMessages(MSG_LONG_PRESS);
                if (bLongPress) {
                    mHandle.sendEmptyMessage(MSG_LONG_PRESS_UP);
                    return true;
                }
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onClick(view);
        }
    }

    public interface EventViewListener {
        void onClick(View v);

        void onLongPress();

        void onLongPressUp();
    }

    private EventViewListener mListener;

    public void setEventViewListener(EventViewListener mListener) {
        this.mListener = mListener;
    }
}

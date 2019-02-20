package com.shanghai.templateapp.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanghai.templateapp.R;
import com.shanghai.templateapp.base.BaseFragment;
import com.shanghai.templateapp.util.CustomThreadFactoryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;


public class HomeFragment extends Fragment {

    // @BindView(R.id.tv_thread)
    TextView mTvThread;
    String TAG = "msg";
    ThreadPoolExecutor executor;
    ThreadText mThreadText;
    HandlerText handlerText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    class HandlerText extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            switch (msg.what) {
                case 0:
                    Log.i(TAG, "0");
                    mTvThread.setText( format.format(new Date()));
                    break;
                case 1:
                    Log.i(TAG, "1");
                    mTvThread.setText("ios");
                    break;
                default:
                    mTvThread.setText("ios");
                    break;
            }
        }
    }


    private void initView(View view) {
        mTvThread = view.findViewById(R.id.tv_thread);
        mThreadText = new ThreadText();
        ThreadFactory threadFactory = new CustomThreadFactoryBuilder().setNamePrefix("Ad-Thread").setPriority(Thread.MAX_PRIORITY).build();
        executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), threadFactory);
        handlerText = new HandlerText();
        executor.execute(mThreadText);

    }

    class ThreadText implements Runnable {
        @Override
        public void run() {
           while (true){
                try {
                    Thread.sleep(500);

                    handlerText.sendEmptyMessage(0);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executor.remove(mThreadText);
    }
}

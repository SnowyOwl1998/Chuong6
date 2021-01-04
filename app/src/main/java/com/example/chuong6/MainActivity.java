package com.example.chuong6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import java.io.File;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //6-1
        long startTime = System.nanoTime();
        long duration = System.nanoTime() - startTime;
        Log.d("log6-1", "Duration: " + duration);
        //6-2
        measureNanoTime();
        //6-3
        long startTime1 = Debug.threadCpuTimeNanos();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long duration1 = Debug.threadCpuTimeNanos() - startTime1;
        Log.d("log6-3", "Duration: " + duration1 + " nanoseconds");
        //6-4 Su dung code C cho ung dung lap trinh bang code C
        //6-5 Su dung Profiler
        final File methodTracingFile = new File(getFilesDir(), "AppStart.trace");
        Log.d("log6-5","methodTracingPath=" + methodTracingFile.getPath());
        Debug.startMethodTracing(methodTracingFile.getPath());
        BigInteger fN = Fibonacci.computeRecursivelyWithCache(100000);
        Debug.stopMethodTracing();
    }
    private void measureNanoTime() {
        final int ITERATIONS = 100000;
        long total = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (int i = 0; i < ITERATIONS; i++) {
            long startTime = System.nanoTime();
            long time = System.nanoTime() - startTime;
            total += time;
            if (time < min) {
                min = time;
            }
            if (time > max) {
                max = time;
            }
        }
        Log.d("log6-2", "Average time: " + ((float)total / ITERATIONS) + " nanoseconds");
        Log.d("log6-2", " Minimum: " + min);
        Log.d("log6-2", " Maximum: " + max);
    }

}
package com.tiandawu.ebook;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tiandawu.ebook.slider.VerticalPageSlider;

public class MainActivity extends AppCompatActivity {

    private BookViewPager mBookViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideStatusBar(this);
        setContentView(R.layout.activity_main);
        mBookViewPager = (BookViewPager) findViewById(R.id.bookViewPager);
        mBookViewPager.setAdapter(new MyAdapter());
        mBookViewPager.setSlider(new VerticalPageSlider());
        mBookViewPager.setOnTapListener(new BookViewPager.OnTapListener() {
            @Override
            public void onSingleTap(MotionEvent event) {
                int screenWidth = getResources().getDisplayMetrics().widthPixels;
                int x = (int) event.getX();
                if (x > screenWidth / 2) {
                    mBookViewPager.slideNext();
//                    Log.e("tt", "++++++++");
                } else if (x <= screenWidth / 2) {
                    mBookViewPager.slidePrevious();
//                    Log.e("tt", "---------");
                }
            }
        });
    }

    private class MyAdapter extends BookViewPagerAdapter<String> {

        private int index = 0;

        @Override
        public View getView(View convertView, String s) {
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.page_content, null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText(s);
            return convertView;
        }

        @Override
        public String getCurrentContent() {
//            Log.e("tt", "getNextContent = " + "第" + index + "页！");
            return "第" + index + "页！";
        }

        @Override
        public String getNextContent() {
//            Log.e("tt", "getNextContent = " + "第" + (index + 1) + "页！");
            return "第" + (index + 1) + "页！";
        }

        @Override
        public String getPreviousContent() {
//            Log.e("tt", "getPreviousContent = " + "第" + (index - 1) + "页！");
            return "第" + (index - 1) + "页！";
        }

        @Override
        public boolean hasNextContent() {
//            Log.e("tt", "hasNextContent = " + index);
            return index < 2;
        }

        @Override
        public boolean hasPreviousContent() {
//            Log.e("tt", "hasPreviousContent = " + index);
            return index > 0;
        }

        @Override
        protected void computeNext() {
            ++index;
            Log.e("tt", "computeNext = " + index);
        }

        @Override
        protected void computePrevious() {
            --index;
            Log.e("tt", "computePrevious = " + index);
        }
    }

    /**
     * 隐藏状态栏
     *
     * @param activity
     */
    private void hideStatusBar(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


}

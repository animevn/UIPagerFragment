package com.haanhgs.app.viewpagerfragmentdemo;

import android.content.res.TypedArray;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vpMain)
    ViewPager vpMain;

    private int[] imageList;
    private final List<Fragment> fragmentList = new ArrayList<>();

    private void initImageList(){
        TypedArray imageString = getResources().obtainTypedArray(R.array.imageList);
        int count = imageString.length();
        imageList = new int[count];
        for (int i = 0; i < count; i++){
            imageList[i] = imageString.getResourceId(i, 0);
        }
        imageString.recycle();
    }

    private void initFragmentList(){
        for (int imgId : imageList) {
            fragmentList.add(ImageFragment.instance(imgId));
        }
    }

    private void initViewPager(){
        Adapter adapter = new Adapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList);
        vpMain.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initImageList();
        initFragmentList();
        initViewPager();
    }
}

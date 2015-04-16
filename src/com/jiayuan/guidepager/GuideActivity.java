package com.jiayuan.guidepager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GuideActivity extends Activity {
	private ViewPager mPager;
	private List<View> mViews = new ArrayList<View>();
	private ImageView mDot1,mDot2,mDot3,mDot4;
	private int nCurrentSelect = 0;
	private ImageButton mEnter;
	private SharedPreferences mSharedPreferences;
	private Editor mEditor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guide);
		initViews();
		initEvents();
	}
	private void initEvents() {
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				if (arg0==3) {
					mEnter.setVisibility(View.VISIBLE);
				}else{
					mEnter.setVisibility(View.GONE);
				}
				setCurrentSelect(arg0);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		//button click
		mEnter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent mIntent = new Intent(GuideActivity.this,MainActivity.class);
				startActivity(mIntent);
				finish();
			}
		});
	}
	protected void setCurrentSelect(int select) {
		setUnFoncus();
		switch (select) {
		case 0:
			mDot1.setImageResource(R.drawable.new_guide_round_selected);
			break;
		case 1:
			mDot2.setImageResource(R.drawable.new_guide_round_selected);
			break;
		case 2:
			mDot3.setImageResource(R.drawable.new_guide_round_selected);
			break;
		case 3:
			mDot4.setImageResource(R.drawable.new_guide_round_selected);
			break;
		default:
			break;
		}
	}
	private void setUnFoncus() {
		mDot1.setImageResource(R.drawable.new_guide_round);
		mDot2.setImageResource(R.drawable.new_guide_round);
		mDot3.setImageResource(R.drawable.new_guide_round);
		mDot4.setImageResource(R.drawable.new_guide_round);
	}
	private void initViews() {
		mPager = (ViewPager) findViewById(R.id.id_pager);
		mDot1 = (ImageView) findViewById(R.id.id_dot1);
		mDot2 = (ImageView) findViewById(R.id.id_dot2);
		mDot3 = (ImageView) findViewById(R.id.id_dot3);
		mDot4 = (ImageView) findViewById(R.id.id_dot4);
		mEnter = (ImageButton) findViewById(R.id.id_enter);
		//init pager
		View page1 = LayoutInflater.from(this).inflate(R.layout.guide01, null,false);
		View page2 = LayoutInflater.from(this).inflate(R.layout.guide02, null,false);
		View page3 = LayoutInflater.from(this).inflate(R.layout.guide03, null,false);
		View page4 = LayoutInflater.from(this).inflate(R.layout.guide04, null,false);
		mViews.add(page1);
		mViews.add(page2);
		mViews.add(page3);
		mViews.add(page4);
		mPager.setAdapter(new PagerAdapter() {
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(mViews.get(position));
			}
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			@Override
			public int getCount() {
				return mViews.size();
			}
		});

	}

}

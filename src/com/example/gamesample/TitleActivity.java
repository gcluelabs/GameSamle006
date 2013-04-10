package com.example.gamesample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
public class TitleActivity extends Activity {
	/** Called when the activity is first created **/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Titleを消す
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		MyView mView = new MyView(this);
		setContentView(mView);
	}
	/**
	 * バックキーを押した時にアプリを終了するようにした
	 */
	public void onPause() {
		super.onPause();
		finish();
	}
}

/**
*  描画用のクラス
*/
class MyView extends View {

	/**
	 * ねこ画像
	 */
	private Bitmap mCat;
	
	/**
	 * Context
	 */
	private Context mContext;
	/**
	 * Paintの設定値
	 */
	private Paint mPaint;
	/**
	 * コンストラクタ
	 * 
	 * @param c
	 */
	public MyView(Context c) {
		super(c);
		mContext = c;
		setFocusable(true);
		// Resourceインスタンスの生成 
		Resources res = this.getContext().getResources(); 
		// 画像の読み込み(res/drawable/neko5.png)   
		mCat = BitmapFactory.decodeResource(res, R.drawable.neko5);
		mPaint = new Paint();
	}
	
	/**
	 * 描画処理
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/* 背景色を設定 */
		canvas.drawColor(Color.CYAN);
		
		/* 文字を描画 */
		mPaint.setTextSize(50);
		mPaint.setColor(Color.BLACK);
		canvas.drawText("ねずみとり", 100, 200, mPaint);
		canvas.drawBitmap(mCat, 170, 400, null);
	}
	
	/**
	 * タッチイベント
	 */
	public boolean onTouchEvent(MotionEvent event) {
		//タッチした時に実行
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// GameSampleを起動
			Intent selectIntent = new Intent();
			selectIntent.setClassName("com.example.gamesample", "com.example.gamesample.GameSample");
			mContext.startActivity(selectIntent);
		}
		return true;
	}
}
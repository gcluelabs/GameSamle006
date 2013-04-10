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
public class OverActivity extends Activity {
	/** Called when the activity is first created **/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Titleを消す
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		String score = "" + getIntent().getIntExtra("score", 0);
		MyOverView mView = new MyOverView(this, score);
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
class MyOverView extends View {
	
	/**
	 * ねずみの画像
	 */
	private Bitmap mRat;
	
	/**
	 * Context
	 */
	private Context mContext;
	/**
	 * Paintの設定値
	 */
	private Paint mPaint;
	/**
	 * スコア
	 */
	private String score;

	/**
	 * コンストラクタ
	 * 
	 * @param c
	 */
	public MyOverView(Context c, String score) {
		super(c);
		mContext = c;
		setFocusable(true);
		this.score = score;
		// Resourceインスタンスの生成 
		Resources res = this.getContext().getResources(); 
		// 画像の読み込み(res/drawable/rat.png)   
		mRat = BitmapFactory.decodeResource(res, R.drawable.rat);
		
		//Canvasに描くものの設定値を初期化
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
		canvas.drawText("スコア:" + score, 100, 250, mPaint);
		canvas.drawBitmap(mRat, 170, 400, null);
	}
	
	/**
	 * タッチイベント
	 */
	public boolean onTouchEvent(MotionEvent event) {
		//タッチした時に実行
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// タイトルを起動
			Intent selectIntent = new Intent();
			selectIntent.setClassName("com.example.gamesample", "com.example.gamesample.TitleActivity");
			mContext.startActivity(selectIntent);
		}
		return true;
	}
}
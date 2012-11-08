package com.example.recordvoice;

import java.io.IOException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.ViewGroup;

public class Preview extends ViewGroup implements SurfaceHolder.Callback {

	Camera camera = new Call().camera;
	Call call = new Call();
	public boolean mProgressFlag = false;
	
	public Preview(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	//プレビュー画像データを取得する
	public void takePreviewRawDate() {
		if (!mProgressFlag) {
			mProgressFlag = true;
			//プレビューするたびに呼ばれる
			//editPreviewImageの内部の処理で画像を保存する
			camera.setPreviewCallback(editPreviewImage);
			
		}
	}
	
	public final Camera.PreviewCallback editPreviewImage = new Camera.PreviewCallback() {

		@Override
		public void onPreviewFrame(byte[] data, Camera c) {
			// TODO 自動生成されたメソッド・スタブ
			c.setPreviewCallback(null); //一度かぎりしか実行しないのでnullを指定
			c.stopPreview();
			
			//画像の保存処理
			call.onPictureT(data, c);
			
			mProgressFlag = false;
		}
		
	
	};

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO 自動生成されたメソッド・スタブ
		Log.d("surfaceChanged()","surfaceChanced()");
		takePreviewRawDate();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	
}

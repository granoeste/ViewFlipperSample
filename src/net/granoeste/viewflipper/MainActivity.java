
package net.granoeste.viewflipper;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();

	// ViewFlipper
	private ViewFlipper vf;

	// 画面サイズを格納する変数
	int disWidth;
	int disHeight;

	// 画面のタッチのゾーンを保持するクラス
	static class Zone {
		int startX;
		int startY;
		int endX;
		int endY;

		@Override
		public String toString() {
			return "( " + startX + " , " + startY + " -> " + endX + " , " + endY + " )";
		}

		public boolean inPosition(int x, int y) {
			if (startX <= x && x <= endX && startY <= y && y <= endY) {
				return true;
			}
			return false;
		}

		public boolean inPosition(float x, float y) {
			if (startX <= x && x <= endX && startY <= y && y <= endY) {
				return true;
			}
			return false;
		}
	}

	static Zone zoneTopLeft = new Zone();
	static Zone zoneTopCenter = new Zone();
	static Zone zoneTopRight = new Zone();

	static Zone zoneLeft = new Zone();
	static Zone zoneCenter = new Zone();
	static Zone zoneRight = new Zone();

	static Zone zoneBottomLeft = new Zone();
	static Zone zoneBottomCenter = new Zone();
	static Zone zoneBottomRight = new Zone();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		vf = (ViewFlipper) findViewById(R.id.layoutswitcher);

		mGestureDetector = new GestureDetector(this, mSimpleOnGestureListener);

		// 画面情報の取得
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		Log.d(TAG, "density=" + metrics.density);
		Log.d(TAG, "densityDpi=" + metrics.densityDpi);
		Log.d(TAG, "scaledDensity=" + metrics.scaledDensity);
		Log.d(TAG, "widthPixels=" + metrics.widthPixels);
		Log.d(TAG, "heightPixels=" + metrics.heightPixels);
		Log.d(TAG, "xDpi=" + metrics.xdpi);
		Log.d(TAG, "yDpi=" + metrics.ydpi);

		disWidth = metrics.widthPixels;
		disHeight = metrics.heightPixels;

		// ゾーンのサイズの設定 縦横で幅の狭い方のサイズの20%とする。
		int size = (disWidth < disHeight) ? (int) (disWidth * 0.2) : (int) (disHeight * 0.2);

		// -------------
		// ゾーンの設定
		// -------------
		// 左上 North West
		zoneTopLeft.startX = 0;
		zoneTopLeft.startY = 0;
		zoneTopLeft.endX = 0 + size;
		zoneTopLeft.endY = 0 + size;
		// 中央上 North
		zoneTopCenter.startX = 0 + size + 1;
		zoneTopCenter.startY = 0;
		zoneTopCenter.endX = disWidth - size - 1;
		zoneTopCenter.endY = 0 + size;
		// 右上 North East
		zoneTopRight.startX = disWidth - size;
		zoneTopRight.startY = 0;
		zoneTopRight.endX = disWidth;
		zoneTopRight.endY = 0 + size;
		// 左 West
		zoneLeft.startX = 0;
		zoneLeft.startY = 0 + size + 1;
		zoneLeft.endX = 0 + size;
		zoneLeft.endY = disHeight - size - 1;
		// 中央 Center
		zoneCenter.startX = 0 + size + 1;
		zoneCenter.startY = 0 + size + 1;
		zoneCenter.endX = disWidth - size - 1;
		zoneCenter.endY = disHeight - size - 1;
		// 右 East
		zoneRight.startX = disWidth - size;
		zoneRight.startY = 0 + size + 1;
		zoneRight.endX = disWidth;
		zoneRight.endY = disHeight - size - 1;
		// 左下 South West
		zoneBottomLeft.startX = 0;
		zoneBottomLeft.startY = disHeight - size;
		zoneBottomLeft.endX = 0 + size;
		zoneBottomLeft.endY = disHeight;
		// 中央下 South
		zoneBottomCenter.startX = 0 + size + 1;
		zoneBottomCenter.startY = disHeight - size;
		zoneBottomCenter.endX = disWidth - size - 1;
		zoneBottomCenter.endY = disHeight;
		// 右下 South East
		zoneBottomRight.startX = disWidth - size;
		zoneBottomRight.startY = disHeight - size;
		zoneBottomRight.endX = disWidth;
		zoneBottomRight.endY = disHeight;

		Log.d(TAG, "Zone Size=" + size);
		Log.d(TAG, "Zone Top Left=" + zoneTopLeft);
		Log.d(TAG, "Zone Top Center=" + zoneTopCenter);
		Log.d(TAG, "Zone Top Right=" + zoneTopRight);
		Log.d(TAG, "Zone Left=" + zoneLeft);
		Log.d(TAG, "Zone Center=" + zoneCenter);
		Log.d(TAG, "Zone Right=" + zoneRight);
		Log.d(TAG, "Zone Bottom Left=" + zoneBottomLeft);
		Log.d(TAG, "Zone Bottom Center=" + zoneBottomCenter);
		Log.d(TAG, "Zone Bottom Right=" + zoneBottomRight);

	}

	@Override
	public boolean onTouchEvent(MotionEvent touchevent) {

		mGestureDetector.onTouchEvent(touchevent);

		switch (touchevent.getAction()) {
		// 画面がタッチされたときの動作
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "onTouchEvent() ACTION_DOWN");
				break;
			// タッチが離されたときの動作
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onTouchEvent() ACTION_UP");
				break;
			// タッチしたまま移動したときの動作
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onTouchEvent() ACTION_MOVE");
				break;
			// 他の要因によってタッチがキャンセルされたときの動作
			case MotionEvent.ACTION_CANCEL:
				Log.d(TAG, "onTouchEvent() ACTION_CANCEL");
				break;
			default:
				break;
		}

		return false;
	}

	private GestureDetector mGestureDetector;

	// 複雑なタッチイベント処理
	private final SimpleOnGestureListener mSimpleOnGestureListener = new SimpleOnGestureListener() {

		// 画面をタップした時の呼ばれるイベント順番
		//
		// シングルタップ (ちょい押し)
		// onDown -> onShowPress -> onSIngleTapUp -> onSingleTapConfirmed
		//
		// ロングタップ (長押し)
		// onDown -> onShowPress -> onLongPress
		//
		// ドラッグ（おす→ドラッグ→普通に離す）
		// onDown -> (onShowPress) -> onScroll *n
		// ※onShowPressは、スクロールを直ぐに行った場合には省略される。
		//
		// フリック（おす→ドラッグ→ぱっと離す）
		// onDown -> (onShowPress) -> onScroll *n -> onFling
		// ※onShowPressは、スクロールを直ぐに行った場合には省略される。
		//
		// ダブルタップ
		// onDown -> onShowPress -> onSingleTapUp -> onDoubleTap -> onDoubleTapEvent

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			Log.d(TAG, "onDoubleTap()");
			return super.onDoubleTap(e);
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			Log.d(TAG, "onDoubleTapEvent()");
			return super.onDoubleTapEvent(e);
		}

		@Override
		public boolean onDown(MotionEvent e) {
			Log.d(TAG, "onDown()");
			return super.onDown(e);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			Log.d(TAG, "onFling()");

			float sX = e1.getX();
			float sY = e1.getY();
			float eX = e2.getX();
			float eY = e2.getY();
			float rangeX = eX - sX;
			float rangeY = eY - sY;

			Log.d(TAG, "onFling() (X,Y)=(" + sX + " , " + sY + ")->(" + eX + " , " + eY + ") : ("
					+ Math.abs(rangeX) + " , " + Math.abs(rangeY) + ")");

			if (zoneCenter.inPosition(eX, eY)) {
				if (Math.abs(rangeX) > Math.abs(rangeY)) {
					// Left to Right
					if (e1.getX() < e2.getX()) {
						vf.setInAnimation(AnimationHelper.inFromLeftAnimation());
						vf.setOutAnimation(AnimationHelper.outToRightAnimation());
						vf.showNext();
					}
					// Right to Left
					if (e1.getX() > e2.getX()) {
						vf.setInAnimation(AnimationHelper.inFromRightAnimation());
						vf.setOutAnimation(AnimationHelper.outToLeftAnimation());
						vf.showPrevious();
					}
				} else {
					// Top to Bottom
					if (e1.getY() < e2.getY()) {
						vf.setInAnimation(AnimationHelper.inFromTopAnimation());
						vf.setOutAnimation(AnimationHelper.outToBottomAnimation());
						vf.showNext();
					}
					// Bottom to Top
					if (e1.getY() > e2.getY()) {
						vf.setInAnimation(AnimationHelper.inFromBottomAnimation());
						vf.setOutAnimation(AnimationHelper.outToTopAnimation());
						vf.showPrevious();
					}
				}
			} else if ((zoneLeft.inPosition(sX, sY) && zoneLeft.inPosition(eX, eY))
					|| (zoneRight.inPosition(sX, sY) && zoneRight.inPosition(eX, eY))) {
				if (Math.abs(rangeX) > Math.abs(rangeY)) {
					// X軸の移動が多い場合は何もしない
				} else {
					// Top to Bottom
					if (e1.getY() < e2.getY()) {
						vf.setInAnimation(AnimationHelper.inFromTopAlphaAnimation());
						vf.setOutAnimation(AnimationHelper.outToBottomAlphaAnimation());
						vf.showNext();
					}
					// Bottom to Top
					if (e1.getY() > e2.getY()) {
						vf.setInAnimation(AnimationHelper.inFromBottomAlphaAnimation());
						vf.setOutAnimation(AnimationHelper.outToTopAlphaAnimation());
						vf.showPrevious();
					}
				}
			} else if ((zoneTopCenter.inPosition(sX, sY) && zoneTopCenter.inPosition(eX, eY))
					|| (zoneBottomCenter.inPosition(sX, sY) && zoneBottomCenter.inPosition(eX, eY))) {
				if (Math.abs(rangeX) > Math.abs(rangeY)) {
					// Left to Right
					if (e1.getX() < e2.getX()) {
						vf.setInAnimation(AnimationHelper.inFromLeftAlphaAnimation());
						vf.setOutAnimation(AnimationHelper.outToRightAlphaAnimation());
						vf.showNext();
					}
					// Right to Left
					if (e1.getX() > e2.getX()) {
						vf.setInAnimation(AnimationHelper.inFromRightAlphaAnimation());
						vf.setOutAnimation(AnimationHelper.outToLeftAlphaAnimation());
						vf.showPrevious();
					}
				} else {
					// Y軸の移動が多い場合は何もしない
				}
			}

			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			Log.d(TAG, "onLongPress()");
			super.onLongPress(e);
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			Log.d(TAG, "onScroll()");
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		@Override
		public void onShowPress(MotionEvent e) {
			Log.d(TAG, "onShowPress()");
			super.onShowPress(e);
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			Log.d(TAG, "onSingleTapConfirmed()=(" + e.getX() + " , " + e.getY() + ")");

			float x = e.getX();
			float y = e.getY();

			if (zoneTopLeft.inPosition(x, y)) {
				// 左上タップ 左上にスケールアウト&右下からスケールイン
				vf.setOutAnimation(AnimationHelper.outScaleAnimation(0, 0));
				vf.setInAnimation(AnimationHelper.inScaleAnimation(disWidth, disHeight));
				vf.showPrevious();
			} else if (zoneTopCenter.inPosition(x, y)) {
				// 上中央タップ 上中央にローテートスケールアウト&下中央からローテートスケールイン
				vf.setOutAnimation(AnimationHelper.outRotateScaleAnimation(disWidth / 2,
						disHeight / 2, disWidth / 2, 0));
				vf.setInAnimation(AnimationHelper.inRotateScaleAnimation(disWidth / 2,
						disHeight / 2, disWidth / 2, disHeight));
				vf.showPrevious();
			} else if (zoneTopRight.inPosition(x, y)) {
				// 右上タップ 右上にスケールアウト&左下からスケールイン
				vf.setOutAnimation(AnimationHelper.outScaleAnimation(disWidth, 0));
				vf.setInAnimation(AnimationHelper.inScaleAnimation(0, disHeight));
				vf.showNext();
			} else if (zoneLeft.inPosition(x, y)) {
				// 左タップ 左にスケールアウト&右からスケールイン
				vf.setOutAnimation(AnimationHelper.outScaleAnimation(0, disHeight / 2));
				vf.setInAnimation(AnimationHelper.inScaleAnimation(disWidth, disHeight / 2));
				vf.showPrevious();
			} else if (zoneCenter.inPosition(x, y)) {
				// 中央タップ フェードアウト&イン
				vf.setOutAnimation(AnimationHelper.outAlphaAnimation());
				vf.setInAnimation(AnimationHelper.inAlphaAnimation());
				vf.showNext();
			} else if (zoneRight.inPosition(x, y)) {
				// 右タップ 右にスケールアウト&左からスケールイン
				vf.setOutAnimation(AnimationHelper.outScaleAnimation(disWidth, disHeight / 2));
				vf.setInAnimation(AnimationHelper.inScaleAnimation(0, disHeight / 2));
				vf.showNext();
			} else if (zoneBottomLeft.inPosition(x, y)) {
				// 左下タップ 左下にスケールアウト&右上からスケールイン
				vf.setOutAnimation(AnimationHelper.outScaleAnimation(0, disHeight));
				vf.setInAnimation(AnimationHelper.inScaleAnimation(disWidth, 0));
				vf.showPrevious();
			} else if (zoneBottomCenter.inPosition(x, y)) {
				// 下中央タップ 下中央にローテートスケールアウト&上中央からローテートスケールイン
				vf.setOutAnimation(AnimationHelper.outRotateScaleAnimation(disWidth / 2,
						disHeight / 2, disWidth / 2, disHeight));
				vf.setInAnimation(AnimationHelper.inRotateScaleAnimation(disWidth / 2,
						disHeight / 2, disWidth / 2, 0));
				vf.showPrevious();
			} else if (zoneBottomRight.inPosition(x, y)) {
				// 右下タップ 右下にスケールアウト&左上からスケールイン
				vf.setOutAnimation(AnimationHelper.outScaleAnimation(disWidth, disHeight));
				vf.setInAnimation(AnimationHelper.inScaleAnimation(0, 0));
				vf.showNext();
			}

			return false;
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			Log.d(TAG, "onSingleTapUp()");
			return super.onSingleTapUp(e);
		}

	};

	// Switching the photo by the rotation of the ball for Nexus Q.
	// dispatch key event of volume down and up.
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		//Log.v(TAG, "action:" + event.getAction() + " dispatchKeyEvent:" + keyCode);

		if (event.getAction() == KeyEvent.ACTION_DOWN) {

			if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
				Log.v(TAG, "action:ACTION_DOWN dispatchKeyEvent:KEYCODE_VOLUME_DOWN");

				vf.setInAnimation(AnimationHelper.inFromLeftAlphaAnimation());
				vf.setOutAnimation(AnimationHelper.outToRightAlphaAnimation());
				vf.showNext();

				return true;

			} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
				Log.v(TAG, "action:ACTION_DOWN dispatchKeyEvent:KEYCODE_VOLUME_UP");

				vf.setInAnimation(AnimationHelper.inFromRightAlphaAnimation());
				vf.setOutAnimation(AnimationHelper.outToLeftAlphaAnimation());
				vf.showPrevious();

				return true;

			} else if (keyCode == 164) {
				Log.v(TAG,
						"action:ACTION_DOWN dispatchKeyEvent:164 - This is center touch event for Nexus Q.");

				return true;
			}
		}

		return super.dispatchKeyEvent(event);
	}
}

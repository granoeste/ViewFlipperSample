
package net.granoeste.viewflipper;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationHelper {

	// Right -> Left
	public static Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
				);
		inFromRight.setDuration(350);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	public static Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
				);
		outtoLeft.setDuration(350);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	// Left -> Right
	public static Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
				);
		inFromLeft.setDuration(350);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	public static Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
				);
		outtoRight.setDuration(350);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}

	// Top -> Bottom
	public static Animation inFromTopAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
				);
		inFromRight.setDuration(350);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	public static Animation outToBottomAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, +1.0f
				);
		outtoLeft.setDuration(350);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	// Bottom -> Top
	public static Animation inFromBottomAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
				);
		inFromLeft.setDuration(350);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	public static Animation outToTopAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f
				);
		outtoRight.setDuration(350);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}

	// Scale
	public static Animation inScaleAnimation(float pivotX, float pivotY) {
		Animation scale = new ScaleAnimation(
				0.0f, //fromX	開始 X 伸縮率
				1.0f, //toX		終了 X 伸縮率
				0.0f, //fromY	開始 Y 伸縮率
				1.0f, //toY		終了 Y 伸縮率
				pivotX, pivotY
				);
		scale.setDuration(1000);
		scale.setInterpolator(new AccelerateInterpolator());
		return scale;
	}

	public static Animation outScaleAnimation(float pivotX, float pivotY) {
		Animation scale = new ScaleAnimation(
				1.0f, //fromX	開始 X 伸縮率
				0.0f, //toX		終了 X 伸縮率
				1.0f, //fromY	開始 Y 伸縮率
				0.0f, //toY		終了 Y 伸縮率
				pivotX, pivotY
				);
		scale.setDuration(1000);
		scale.setInterpolator(new AccelerateInterpolator());
		return scale;
	}

	// Alpha
	public static Animation inAlphaAnimation() {
		AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
		alpha.setDuration(1000);
		alpha.setInterpolator(new AccelerateInterpolator());
		return alpha;
	}

	public static Animation outAlphaAnimation() {
		AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
		alpha.setDuration(1000);
		alpha.setInterpolator(new AccelerateInterpolator());
		return alpha;
	}

	// Rotate & Scale
	public static Animation inRotateScaleAnimation(float pivotX, float pivotY, float posX,
			float posY) {
		AnimationSet set = new AnimationSet(true);

		RotateAnimation rotate = new RotateAnimation(360, 0, pivotX, pivotY);
		rotate.setInterpolator(new AccelerateInterpolator());

		Animation scale = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, posX, posY);
		scale.setInterpolator(new AccelerateInterpolator());

		set.addAnimation(rotate);
		set.addAnimation(scale);

		set.setDuration(1000);
		set.setInterpolator(new AccelerateInterpolator());
		return set;

	}

	public static Animation outRotateScaleAnimation(float pivotX, float pivotY, float posX,
			float posY) {
		AnimationSet set = new AnimationSet(true);

		RotateAnimation rotate = new RotateAnimation(0, 360, pivotX, pivotY);
		rotate.setInterpolator(new AccelerateInterpolator());

		Animation scale = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, posX, posY);
		scale.setInterpolator(new AccelerateInterpolator());

		set.addAnimation(rotate);
		set.addAnimation(scale);

		set.setDuration(1000);
		set.setInterpolator(new AccelerateInterpolator());
		return set;
	}

	// Right -> Left Alpha
	public static Animation inFromRightAlphaAnimation() {
		AnimationSet set = new AnimationSet(true);

		set.addAnimation(inFromRightAnimation());
		set.addAnimation(inAlphaAnimation());

		return set;
	}

	public static Animation outToLeftAlphaAnimation() {
		AnimationSet set = new AnimationSet(true);

		set.addAnimation(outToLeftAnimation());
		set.addAnimation(outAlphaAnimation());

		return set;
	}

	// Left -> Right Alpha
	public static Animation inFromLeftAlphaAnimation() {
		AnimationSet set = new AnimationSet(true);

		set.addAnimation(inFromLeftAnimation());
		set.addAnimation(inAlphaAnimation());

		return set;
	}

	public static Animation outToRightAlphaAnimation() {
		AnimationSet set = new AnimationSet(true);

		set.addAnimation(outToRightAnimation());
		set.addAnimation(outAlphaAnimation());

		return set;
	}

	// Top -> Bottom Alpha
	public static Animation inFromTopAlphaAnimation() {
		AnimationSet set = new AnimationSet(true);

		set.addAnimation(inFromTopAnimation());
		set.addAnimation(inAlphaAnimation());

		return set;
	}

	public static Animation outToBottomAlphaAnimation() {
		AnimationSet set = new AnimationSet(true);

		set.addAnimation(outToBottomAnimation());
		set.addAnimation(outAlphaAnimation());

		return set;
	}

	// Bottom -> Top Alpha
	public static Animation inFromBottomAlphaAnimation() {
		AnimationSet set = new AnimationSet(true);

		set.addAnimation(inFromBottomAnimation());
		set.addAnimation(inAlphaAnimation());

		return set;
	}

	public static Animation outToTopAlphaAnimation() {
		AnimationSet set = new AnimationSet(true);

		set.addAnimation(outToTopAnimation());
		set.addAnimation(outAlphaAnimation());

		return set;
	}

}

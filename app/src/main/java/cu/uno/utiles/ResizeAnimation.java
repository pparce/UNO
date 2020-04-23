package cu.uno.utiles;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeAnimation extends Animation {
    private int mWidth;
    private int mStartWidth;
    private View mView;

    public ResizeAnimation(View view, int width) {
        mView = view;
        mWidth = width;
        mStartWidth = view.getHeight();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newWidth = mStartWidth + (int) ((mWidth - mStartWidth) * interpolatedTime);

        mView.getLayoutParams().height = newWidth;
        mView.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}

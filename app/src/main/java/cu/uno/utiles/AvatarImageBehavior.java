package cu.uno.utiles;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import cu.uno.R;
import com.google.android.material.appbar.AppBarLayout;


/**
 * 图片控件位置动画
 *
 * @author wangchenlong
 */
@SuppressWarnings("unused")
public class AvatarImageBehavior extends CoordinatorLayout.Behavior<CircleImageView> {

    private Rect mTmpRect;
    Context context;
    int height;
    ValueAnimator myAnimator;

    public AvatarImageBehavior(Context context, AttributeSet attrs) {
        context = context;
        height = context.getResources().getDimensionPixelOffset(R.dimen.avatar_size);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        updateFabVisibility(parent, (AppBarLayout) dependency, child);

        return false;
    }

    private boolean updateFabVisibility(CoordinatorLayout parent,
                                        AppBarLayout appBarLayout, CircleImageView child) {
        final CoordinatorLayout.LayoutParams lp =
                (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (lp.getAnchorId() != appBarLayout.getId()) {
            return false;
        }

        if (mTmpRect == null) {
            mTmpRect = new Rect();
        }

        // First, let's get the visible rect of the dependency
        final Rect rect = mTmpRect;
        ViewGroupUtils.getDescendantRect(parent, appBarLayout, rect);


        if (rect.bottom <= height) {

            child.animate().scaleY(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(200).start();
            child.animate().scaleX(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(200).start();

        } else {
            child.animate().scaleY(1).setInterpolator(new DecelerateInterpolator()).setDuration(100).start();
            child.animate().scaleX(1).setInterpolator(new DecelerateInterpolator()).setDuration(100).start();
        }
        return true;
    }

}
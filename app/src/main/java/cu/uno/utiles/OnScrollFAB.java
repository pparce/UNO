package cu.uno.utiles;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Created by Pedro on 29/04/2018.
 */
public class OnScrollFAB extends CoordinatorLayout.Behavior<NestedScrollView> {
    public OnScrollFAB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


}

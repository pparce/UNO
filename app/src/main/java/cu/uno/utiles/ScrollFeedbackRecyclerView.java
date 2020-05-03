package cu.uno.utiles;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.lang.ref.WeakReference;

public class ScrollFeedbackRecyclerView extends RecyclerView {

    private WeakReference<Callbacks> mCallbacks;
    boolean estado = false;
    boolean onPress = false;
    boolean onScroll = false;

    public ScrollFeedbackRecyclerView(Context context) {
        super(context);
        attachCallbacks(context);
    }

    public ScrollFeedbackRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        attachCallbacks(context);
    }



    @Override
    public void onScrolled(int dx, int dy) {
        if (onScroll) {
            int[] aux = {0, 1};
            if (((StaggeredGridLayoutManager) getLayoutManager()).findFirstCompletelyVisibleItemPositions(aux)[0] == 0
            && !onPress) {
                mCallbacks.get().setExpanded(true);
                estado = true;
            } else {
                if (estado) {
                    mCallbacks.get().setExpanded(false);
                    estado = false;
                }
            }
        }
        onScroll = true;
        super.onScrolled(dx, dy);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                onPress = true;
                break;
            case MotionEvent.ACTION_UP:
                onPress = false;
                break;
        }
        return super.onTouchEvent(e);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (!(layout instanceof StaggeredGridLayoutManager)) {
            throw new IllegalArgumentException(layout.toString() + " must be of type LinearLayoutManager");
        }
        super.setLayoutManager(layout);
    }

    private void attachCallbacks(Context context) {

        try {
            mCallbacks = new WeakReference<>((Callbacks) context);
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " +
                    "ScrollFeedbackRecyclerView.Callbacks");
        }

    }

    public interface Callbacks {

        boolean isAppBarCollapsed();

        void setExpanded(boolean expanded);

    }
}

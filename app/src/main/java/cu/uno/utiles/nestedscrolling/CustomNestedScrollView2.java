package cu.uno.utiles.nestedscrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * A custom {@link NestedScrollView} that customizes the sample app's
 * nested scrolling behavior.
 */
public class CustomNestedScrollView2 extends NestedScrollView2 {

  public CustomNestedScrollView2(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
    final RecyclerView rv = (RecyclerView) target;
    if ((dy < 0 && isRvScrolledToTop(rv)) || (dy > 0 && !isNsvScrolledToBottom(this))) {
      // The NestedScrollView should steal the scroll event away from the
      // RecyclerView if: (1) the user is scrolling their finger down and the
      // RecyclerView is scrolled to the top of its content, or (2) the user
      // is scrolling their finger up and the NestedScrollView is not scrolled
      // to the bottom of its content.
      scrollBy(0, dy);
      consumed[1] = dy;
      return;
    }
    super.onNestedPreScroll(target, dx, dy, consumed, type);
  }

  /**
   * Returns true iff the {@link NestedScrollView} is scrolled to the bottom
   * of its content (i.e. the card is completely expanded).
   */
  private static boolean isNsvScrolledToBottom(NestedScrollView nsv) {
    return !nsv.canScrollVertically(1);
  }

  /**
   * Returns true iff the {@link RecyclerView} is scrolled to the
   * top of its content (i.e. its first item is completely visible).
   */
  private static boolean isRvScrolledToTop(RecyclerView rv) {
    final StaggeredGridLayoutManager lm = (StaggeredGridLayoutManager) rv.getLayoutManager();
    int[] posiciones = null;
//    Log.e("posicion de recyler", lm.findViewByPosition(0).getTop() + "");
    return lm.findViewByPosition(0).getTop() == 0;
  }
}

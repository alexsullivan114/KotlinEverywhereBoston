package alexsullivan.com.reactivetodo

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class DismissHelper(private val itemSwiped: (ViewHolder) -> Unit) :
  ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
  override fun onMove(
    recyclerView: RecyclerView,
    viewHolder: ViewHolder,
    target: ViewHolder
  ): Boolean = false

  override fun onSwiped(viewHolder: ViewHolder, direction: Int) =
    itemSwiped(viewHolder)
}

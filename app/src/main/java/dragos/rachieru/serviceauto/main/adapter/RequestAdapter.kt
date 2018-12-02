package dragos.rachieru.serviceauto.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dragos.rachieru.serviceauto.entities.IssueRequest

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
class RequestAdapter(val onChangeStatusListener: RequestStatusChanger) : RecyclerView.Adapter<RequestHolder>(){

    private val items = ArrayList<IssueRequest>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestHolder {
        return RequestHolder(parent, onChangeStatusListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RequestHolder, position: Int) {
        holder.bind(items[position])
    }

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setItems(collection: Collection<IssueRequest>) {
        if (items.isNotEmpty()) {
            val size = items.size
            items.clear()
            notifyItemRangeRemoved(0, size)
        }
        items.addAll(collection)
        notifyItemRangeInserted(0, collection.size)
    }

    fun addAll(collection: Collection<IssueRequest>) {
        val size = items.size
        items.addAll(collection)
        notifyItemRangeInserted(size, collection.size)
    }

    fun add(issue: IssueRequest) {
        items.add(issue)
        notifyItemInserted(items.size - 1)
    }

    fun clear() {
        if (items.isNotEmpty()) {
            val size = items.size
            items.clear()
            notifyItemRangeRemoved(0, size)
        }
    }
}
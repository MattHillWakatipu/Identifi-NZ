package fi.co_de.identifi_nz.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import fi.co_de.identifi_nz.R
import fi.co_de.identifi_nz.data.ActivityCardAdapter.ItemViewHolder

class ActivityCardAdapter(
    private val context: Context,
    private val dataset: List<Activity>
) : Adapter<ItemViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class ItemViewHolder(view: View) : ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.recent_activity_item_description)
        val imageView: ImageView = view.findViewById(R.id.recent_activity_item_icon)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // Create a new view
        val adapterLayout = LayoutInflater.from(context)
            .inflate(R.layout.recent_activity_list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount(): Int = dataset.size

}
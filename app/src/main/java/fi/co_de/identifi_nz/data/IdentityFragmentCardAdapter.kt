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
import fi.co_de.identifi_nz.data.IdentityFragmentCardAdapter.ItemViewHolder

class IdentityFragmentCardAdapter(
    private val context: Context,
    private val dataset: List<IdentityFragment>?
) : Adapter<ItemViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class ItemViewHolder(view: View) : ViewHolder(view) {
        val iconImageView: ImageView = view.findViewById(R.id.identity_fragment_item_icon)
        val statusImageView: ImageView = view.findViewById(R.id.identity_fragment_status_icon)
        val itemDescriptionTextView: TextView =
            view.findViewById(R.id.identity_fragment_item_description)
        val statusDescriptionTextView: TextView =
            view.findViewById(R.id.identity_fragment_status_description)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // Create a new view
        val adapterLayout = LayoutInflater.from(context)
            .inflate(R.layout.identity_fragment_list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset?.get(position)

        if (item != null) {
            holder.iconImageView.setImageResource(item.iconResourceId)
            holder.statusImageView.setImageResource(item.statusResourceId)
            holder.itemDescriptionTextView.text = item.itemDescription
            holder.statusDescriptionTextView.text =
                context.resources.getString(item.statusDescriptionResourceId)
        }
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount(): Int = dataset?.size ?: 0

}
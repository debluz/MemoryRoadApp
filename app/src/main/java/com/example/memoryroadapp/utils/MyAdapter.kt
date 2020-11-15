package com.example.memoryroadapp.utils

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.databinding.ItemLocationBinding
import kotlinx.android.synthetic.main.item_location.view.*

interface OnItemListener {
    fun onItemClickListener(location: MyLocation)

}

class MyAdapter(private val onItemListener: OnItemListener)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    private var _locations: ArrayList<MyLocation> = ArrayList()
    val locations: ArrayList<MyLocation>
    get() {
        return if(_locations != null){
            _locations
        } else {
            ArrayList()
        }
    }
    private var selectedLocations: ArrayList<MyLocation> = ArrayList()
    private var checkBoxFlag : Boolean = false


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    inner class MyViewHolder(val binding: ItemLocationBinding, private val onItemListener: OnItemListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if(adapterPosition != RecyclerView.NO_POSITION){
                if(checkBoxFlag){
                    v?.checkBox?.isChecked = !v?.checkBox?.isChecked!!
                } else {
                    onItemListener.onItemClickListener(_locations[adapterPosition])
                }

            }
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLocationBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding, onItemListener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val location = _locations[position]

        if(checkBoxFlag){
            holder.itemView.checkBox.visibility = View.VISIBLE
        } else {
            holder.itemView.checkBox.visibility = View.GONE
            holder.itemView.checkBox.isChecked = false
            selectedLocations.clear()
        }

        holder.itemView.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                selectedLocations.add(location)
                HelperClass.logTestMessage("Added to selectedLocations: ${location.name}")
            } else {
                selectedLocations.remove(location)
                HelperClass.logTestMessage("Removed from selectedLocations: ${location.name}")
            }
        }

        holder.binding.apply {
            this.item = location
            this.executePendingBindings()
        }

        val imageView = holder.itemView.location_image_view
        val uri = Uri.parse(location.imageUrl)

        if(!location.imageUrl.isNullOrEmpty()){
            Glide.with(holder.itemView.context)
                .load(uri)
                .into(imageView)
        } else {
            Glide.with(holder.itemView.context)
                .clear(imageView)
            imageView.setImageResource(R.drawable.ic_baseline_photo_size_select_actual_40)
        }
    }

    fun setCheckBoxFlag(flag: Boolean){
        checkBoxFlag = flag
        notifyDataSetChanged()
    }

    fun getCheckBoxFlag(): Boolean = checkBoxFlag

    fun getLocationAt(position: Int): MyLocation = _locations[position]

    fun getSelectedLocations(): ArrayList<MyLocation> = selectedLocations

    override fun getItemCount(): Int = _locations.size

    internal fun setLocations(locations: ArrayList<MyLocation>){
        this._locations = locations
        notifyDataSetChanged()
    }



}


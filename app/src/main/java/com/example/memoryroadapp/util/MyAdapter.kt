package com.example.memoryroadapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.databinding.ItemLocationBinding
import com.google.android.gms.common.util.DataUtils

class MyAdapter() : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var locations = emptyList<MyLocation>()

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    inner class MyViewHolder(val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root){

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_location, parent, false)
        val binding = ItemLocationBinding.inflate(inflater)

        return MyViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val location = locations[position]
        holder.binding.apply {
            this.item = location
            this.executePendingBindings()
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = locations.size

    internal fun setLocations(locations: List<MyLocation>){
        this.locations = locations
        notifyDataSetChanged()
    }
}
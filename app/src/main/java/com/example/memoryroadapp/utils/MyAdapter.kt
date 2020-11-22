package com.example.memoryroadapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memoryroadapp.models.MyLocation
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
        return _locations
    }
    private var selectedLocations: ArrayList<MyLocation> = ArrayList()
    private var checkBoxFlag : Boolean = false


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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLocationBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding, onItemListener)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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
            } else {
                selectedLocations.remove(location)
            }
        }

        holder.binding.apply {
            this.item = location
            this.executePendingBindings()
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


package com.maderpino.babysleep.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.maderpino.babysleep.Interface.IITemClickListener
import com.maderpino.babysleep.Models.Categorys
import com.maderpino.babysleep.R
import com.maderpino.babysleep.fragments.HomeFragment
import com.maderpino.babysleep.fragments.HomeFragmentDirections
import com.squareup.picasso.Picasso

class CategoryAdapter (val arrayList: ArrayList<Categorys>, val context: Context):
 RecyclerView.Adapter<CategoryAdapter.ViewHolder> ()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var title: TextView
        var image: ImageView

        lateinit var iItemClickListener: IITemClickListener

        fun setClick(iItemClickListener: IITemClickListener){
            this.iItemClickListener = iItemClickListener
        }

        init {
            title = itemView.findViewById(R.id.title) as TextView
            image = itemView.findViewById(R.id.image) as ImageView

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            iItemClickListener.onItemClickListener(view!!,adapterPosition)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(arrayList!![position].title!!)

        Picasso.get().load(arrayList[position].image).into(holder.image)


        holder.setClick(object : IITemClickListener {
            override fun onItemClickListener(view: View, position: Int) {
                val category = arrayList[position].title

                view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSoundsFragment(category))

            }
        })

    }


    override fun getItemCount(): Int {
        return arrayList?.size ?:0
    }


}
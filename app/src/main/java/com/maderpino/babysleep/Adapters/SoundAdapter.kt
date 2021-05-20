package com.maderpino.babysleep.Adapters

import android.content.Context
import android.content.res.Resources
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.maderpino.babysleep.Interface.IITemClickListener
import com.maderpino.babysleep.Models.SoundItem
import com.maderpino.babysleep.R
import com.squareup.picasso.Picasso

class SoundAdapter (val arrayList: ArrayList<SoundItem>, val context: Context):
 RecyclerView.Adapter<SoundAdapter.ViewHolder> ()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var title: TextView
        var image: ImageView
        var imageButtonPlay: ImageView
        var controlSound = false
        var mPlayer: MediaPlayer?= null
        var currentPlayingPosition = -1;

        lateinit var nameSound:String

        lateinit var iItemClickListener: IITemClickListener

        fun setClick(iItemClickListener: IITemClickListener){
            this.iItemClickListener = iItemClickListener
        }

        init {
            title = itemView.findViewById(R.id.title) as TextView
            image = itemView.findViewById(R.id.image) as ImageView
            imageButtonPlay = itemView.findViewById(R.id.buttonPlay) as ImageView

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            iItemClickListener.onItemClickListener(view!!,adapterPosition)

        }




    }

    override fun onBindViewHolder(holder: SoundAdapter.ViewHolder, position: Int) {
        holder.title.setText(arrayList!![position].title!!)
        holder.nameSound = arrayList!![position].nameSound

        Picasso.get().load(arrayList[position].image).into(holder.image)

        Picasso.get().load(arrayList[position].imageButtonPlay).into(holder.imageButtonPlay)

        holder.imageButtonPlay.setOnClickListener {
            if (holder.mPlayer?.isPlaying == true){
                Picasso.get().load(R.drawable.playbutton).into(holder.imageButtonPlay)
                holder.mPlayer?.stop()
                holder.mPlayer?.reset()
                holder.mPlayer?.release()
                holder.mPlayer = null
                holder.controlSound = false
            }else{
                val rawResId = context.resIdByName(holder.nameSound, "raw")
                holder.mPlayer = MediaPlayer.create(context, rawResId)
                holder.mPlayer!!.start()
                Picasso.get().load(R.drawable.pause_button).into(holder.imageButtonPlay)
                holder.controlSound = true
                Log.d("Array: ", arrayList.size.toString())

            }

        }

        holder.setClick(object : IITemClickListener {
            override fun onItemClickListener(view: View, position: Int) {

            }
        })

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_sound, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return arrayList?.size ?:0
    }

    fun Context.resIdByName(resIdName: String?, resType: String): Int {
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }




}
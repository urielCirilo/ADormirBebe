package com.maderpino.babysleep.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.maderpino.babysleep.Adapters.SoundAdapter
import com.maderpino.babysleep.Models.SoundItem
import com.maderpino.babysleep.R
import com.maderpino.babysleep.databinding.FragmentSoundsBinding


class SoundsFragment : Fragment() {



    lateinit var category : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentSoundsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sounds, container, false)

        val args = arguments?.let {  SoundsFragmentArgs.fromBundle(it) }

        category = args?.Category.toString()

        binding.title.text = category


        val arrayListAnimals = ArrayList<SoundItem> ()
        arrayListAnimals.add(SoundItem(getString(R.string.leon), R.drawable.leon, "leon",  R.drawable.playbutton) )
        arrayListAnimals.add(SoundItem(getString(R.string.cat), R.drawable.cat, "cat",  R.drawable.playbutton))


        val arrayListTransportation = ArrayList<SoundItem> ()
        arrayListTransportation.add(SoundItem(getString(R.string.train), R.drawable.train, "train",  R.drawable.playbutton))


        val arrayListNature = ArrayList<SoundItem> ()
        arrayListNature.add(SoundItem(getString(R.string.rain), R.drawable.rain, "rain",  R.drawable.playbutton))




        if(category.equals(getString(R.string.animales))){
            binding.image.setImageResource(R.drawable.leon)
            val soundAdapterAnimals = SoundAdapter(arrayListAnimals, requireContext())
            binding.recyclearViewListSounds.adapter = soundAdapterAnimals
        }else if (category.equals(getString(R.string.transporte))){
            binding.image.setImageResource(R.drawable.coche)

            val soundAdapterTransportation = SoundAdapter(arrayListTransportation, requireContext())
            binding.recyclearViewListSounds.adapter = soundAdapterTransportation

            }else if ((category.equals(getString(R.string.nature)))){
            binding.image.setImageResource(R.drawable.tree)
            val soundAdapterNature = SoundAdapter(arrayListNature, requireContext())
            binding.recyclearViewListSounds.adapter = soundAdapterNature
        }

        binding.recyclearViewListSounds.setHasFixedSize(true)
        binding.recyclearViewListSounds.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


        return binding.root

    }


}
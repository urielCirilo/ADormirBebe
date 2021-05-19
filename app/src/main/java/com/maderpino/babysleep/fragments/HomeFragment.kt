package com.maderpino.babysleep.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.maderpino.babysleep.Adapters.CategoryAdapter
import com.maderpino.babysleep.Models.Categorys
import com.maderpino.babysleep.R
import com.maderpino.babysleep.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        val arrayListCategorys = ArrayList<Categorys> ()
        arrayListCategorys.add(Categorys(getString(R.string.nature), R.drawable.tree ))
        arrayListCategorys.add(Categorys(getString(R.string.animales), R.drawable.leon ))
        arrayListCategorys.add(Categorys(getString(R.string.transporte), R.drawable.coche ))


        val categoryAdapter = CategoryAdapter(arrayListCategorys, requireContext())
        binding.recyclearViewCategory.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclearViewCategory.adapter = categoryAdapter

        return binding.root
    }



}
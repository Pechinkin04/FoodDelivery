package com.example.fooddelivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.Models.PopularModel
import com.example.fooddelivery.Models.SharedModel
import com.example.fooddelivery.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    //private lateinit var adapter: ByAgainAdapter
    private lateinit var list: ArrayList<PopularModel>

    private lateinit var sharedModel: SharedModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)


        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel::class.java)


        list = ArrayList()
        //  list.add(PopularModel("Burger", 5,5, R.drawable.pop_menu_burger, 1))
        //  list.add(PopularModel("Sandwich", 7,7, R.drawable.pop_menu_sandwich, 1))
        //  list.add(PopularModel("Momo", 8,8, R.drawable.pop_menu_momo, 1))
        //  list.add(PopularModel("Burger", 5,5, R.drawable.pop_menu_burger, 1))
        //  list.add(PopularModel("Sandwich", 7,7, R.drawable.pop_menu_sandwich, 1))
        //  list.add(PopularModel("Momo", 8,8, R.drawable.pop_menu_momo, 1))
        //  list.add(PopularModel("Burger", 5,5, R.drawable.pop_menu_burger, 1))
        //  list.add(PopularModel("Sandwich", 7,7, R.drawable.pop_menu_sandwich, 1))
        //  list.add(PopularModel("Momo", 8,8, R.drawable.pop_menu_momo, 1))
        //  list.add(PopularModel("Burger", 5,5, R.drawable.pop_menu_burger, 1))
        //  list.add(PopularModel("Sandwich", 7,7, R.drawable.pop_menu_sandwich, 1))
        //  list.add(PopularModel("Momo", 8,8, R.drawable.pop_menu_momo, 1))


//        adapter = ByAgainAdapter(
//            requireContext(),
//            //  list
//            sharedViewModel.cartItems.value ?: ArrayList()
//        )
//
//
//        binding.buyAgainRv.layoutManager = LinearLayoutManager(requireContext())
//        binding.buyAgainRv.adapter = adapter

        return binding.root
    }

}
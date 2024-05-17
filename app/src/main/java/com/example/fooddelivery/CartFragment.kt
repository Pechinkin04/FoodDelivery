package com.example.fooddelivery

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelivery.Adapters.CardAdapter
import com.example.fooddelivery.Models.PopularModel
import com.example.fooddelivery.Models.SharedModel
import com.example.fooddelivery.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var binding : FragmentCartBinding

    //private lateinit var list : ArrayList<PopularModel>
    private lateinit var adapter : CardAdapter

    private lateinit var sharedModel: SharedModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)

        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel :: class.java)


        adapter = CardAdapter(requireContext(), sharedModel.cartItem.value ?: ArrayList())

        binding.cartRV.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRV.adapter = adapter


        binding.procceed.setOnClickListener{
            val totalPrice = sharedModel.cartItem.value?.let { it1 -> calPrice(it1) }
            if (totalPrice == 0) {
                Toast.makeText(requireContext(), "List is Empty", Toast.LENGTH_SHORT).show()
                updateCartIcon()
            } else {
                updateCartIconNotif()
            }
            val TotalPrice = totalPrice.toString()
            val intent = Intent(requireContext(), Details :: class.java)
            intent.putExtra("totalPrice", TotalPrice)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем главную активность (MainActivity)
        val mainActivity = activity as MainActivity

        // ...

        // Логика обновления корзины
        val totalPrice = sharedModel.cartItem.value?.let { it1 -> calPrice(it1) }
        if (totalPrice == 0) {
            mainActivity.updateBottomNavigationIcon(R.id.cart, R.drawable.menu_cart)
        } else {
            mainActivity.updateBottomNavigationIcon(R.id.cart, R.drawable.menu_cart_notif)
        }

        // ...

        // Обновляем иконку в bottom_menu.xml
        //mainActivity.updateBottomNavigationIcon(R.id.cart, R.drawable.menu_cart_notif)
    }


    fun calPrice(itemPrices: List<PopularModel>) : Int {
        var totalPrice = 0

        itemPrices.forEach { itemPrice ->
            val price = itemPrice.getFoodPriceConstant() * itemPrice.getFoodCount()
            totalPrice += price

        }

        return totalPrice
    }

    fun updateCartIconNotif() {
        val mainActivity = activity as MainActivity
        mainActivity.updateCartIcon(R.drawable.menu_cart_notif)
    }

    fun updateCartIcon() {
        val mainActivity = activity as MainActivity
        mainActivity.updateCartIcon(R.drawable.menu_cart)
    }


}
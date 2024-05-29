package com.example.roomdatabaseexample.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.adapter.UserAdapter
import com.example.roomdatabaseexample.databinding.FragmentHomeBinding
import com.example.roomdatabaseexample.db.AppDatabase
import com.example.roomdatabaseexample.models.User


class HomeFragment : Fragment(),UserAdapter.RvAction {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    lateinit var appDatabase: AppDatabase
    lateinit var userAdapter: UserAdapter
    lateinit var list: ArrayList<User>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        onResume()
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        appDatabase = AppDatabase.getInstance(requireContext())

        userAdapter = UserAdapter(appDatabase.myDao().showUser() as ArrayList,this)

        binding.rv.adapter = userAdapter
    }

    override fun moreClick(user: User, position: Int, imageView: ImageView) {
        appDatabase = AppDatabase.getInstance(requireContext())
        val menu = PopupMenu(requireContext(),imageView)
        menu.inflate(R.menu.my_menu)
        menu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_edit->{
                    findNavController().navigate(R.id.addFragment, bundleOf("key" to user))
                }
                R.id.menu_delete->{
                    appDatabase.myDao().deleteUser(user)
                    onResume()

                }
            }
            true
        }
        menu.show()
    }



}
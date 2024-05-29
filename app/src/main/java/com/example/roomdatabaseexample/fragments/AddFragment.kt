package com.example.roomdatabaseexample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.databinding.FragmentAddBinding
import com.example.roomdatabaseexample.db.AppDatabase
import com.example.roomdatabaseexample.models.User


class AddFragment : Fragment() {
    private val binding by lazy { FragmentAddBinding.inflate(layoutInflater) }
    lateinit var appDatabase: AppDatabase
    var user:User? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            user = arguments?.getSerializable("key") as User
            editUser()
        }catch (e:Exception){
            addUser()
        }

        return binding.root
    }

    private fun editUser() {
        appDatabase = AppDatabase.getInstance(requireContext())
        binding.name.setText(user?.name)
        binding.number.setText(user?.nummber)
        binding.brnSave.setOnClickListener {
           user?.name = binding.name.text.toString()
           user?.nummber = binding.number.text.toString()
            appDatabase.myDao().editUser(user!!)
            Toast.makeText(requireContext(), "Saqlandi", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun addUser() {
        appDatabase = AppDatabase.getInstance(requireContext())
        binding.brnSave.setOnClickListener {
            val user = User(
                binding.name.text.toString(),
                binding.number.text.toString()
            )
            appDatabase.myDao().addUser(user)
            Toast.makeText(requireContext(), "Saqlandi", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }


}
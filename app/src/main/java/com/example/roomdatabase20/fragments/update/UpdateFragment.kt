package com.example.roomdatabase20.fragments.update

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase20.R
import com.example.roomdatabase20.model.User
import com.example.roomdatabase20.viewModel.UserViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        var updateFirstName = view?.findViewById<EditText>(R.id.updateFirstName)
        var updateLastName = view?.findViewById<EditText>(R.id.updateLastName)
        var updateAge = view?.findViewById<EditText>(R.id.updateAge)

        val firstName = updateFirstName?.setText(args.currentUser.firstName)
        val lastName = updateLastName?.setText(args.currentUser.lastName)
        val age = updateAge?.setText(args.currentUser.age.toString())

        val upDate_btn = view.findViewById<Button>(R.id.updateBtn)
        upDate_btn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view

    }

    private fun updateItem() {
        var updateFirstName = view?.findViewById<EditText>(R.id.updateFirstName)
        var updateLastName = view?.findViewById<EditText>(R.id.updateLastName)
        var updateAge = view?.findViewById<EditText>(R.id.updateAge)

        val firstName = updateFirstName?.text.toString()
        val lastName = updateLastName?.text.toString()
        val age = Integer.parseInt(updateAge?.text.toString())


        if (inputCheck(firstName, lastName, updateAge?.text)) {
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(
        firstName: String,
        lastName: String,
        age: Editable?
    ): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age!!.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully removed ${args.currentUser.firstName}.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setPositiveButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }


}




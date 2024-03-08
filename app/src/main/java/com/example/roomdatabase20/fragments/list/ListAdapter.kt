package com.example.roomapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase20.R
import com.example.roomdatabase20.fragments.list.ListFragmentDirections
import com.example.roomdatabase20.model.User


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtID: TextView = itemView.findViewById(R.id.txtID)
        val txtFirst: TextView = itemView.findViewById(R.id.txtfirst)
        val txtLast: TextView = itemView.findViewById(R.id.txtLast)
        val txtAge: TextView = itemView.findViewById(R.id.txtAge)
        val rowLayout: ConstraintLayout = itemView.findViewById(R.id.rowLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {



        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.txtID.text = currentItem.id.toString()
        holder.txtFirst.text = currentItem.firstName
        holder.txtLast.text = currentItem.lastName
        holder.txtAge.text = currentItem.age.toString()


        holder.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)


        }
    }
    
        fun setData(user: List<User>) {
            this.userList = user
            notifyDataSetChanged()
        }


}


//    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val txtID: TextView = itemView.findViewById(R.id.txtID)
//        val txtFirst: TextView = itemView.findViewById(R.id.txtfirst)
//        val txtLast: TextView = itemView.findViewById(R.id.txtLast)
//        val txtAge: TextView = itemView.findViewById(R.id.txtAge)
//    }

//    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
//        val currentItem = userList[position]
//        holder.txtID.text = currentItem.id.toString()
//        holder.txtFirst.text = currentItem.firstName
//        holder.txtLast.text = currentItem.lastName
//        holder.txtAge.text = currentItem.age.toString()
//
//        holder.itemView.rowLayout.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
//        }
//
//    }

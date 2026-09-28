package com.example.a24012011189_mad_p7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(private var persons: List<Person>) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name)
        val phoneText: TextView = itemView.findViewById(R.id.phone)
        val emailText: TextView = itemView.findViewById(R.id.email)
        val addressText: TextView = itemView.findViewById(R.id.address)
        val mapButton: Button = itemView.findViewById(R.id.btnMap)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = persons[position]
        holder.nameText.text = person.name
        holder.phoneText.text = person.phoneNo
        holder.emailText.text = person.emailId
        holder.addressText.text = person.address
        holder.mapButton.setOnClickListener {
            val intent = android.content.Intent(holder.itemView.context, MapActivity::class.java)
            intent.putExtra("person", person)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = persons.size

    fun updateData(newPersons: List<Person>) {
        persons = newPersons
        notifyDataSetChanged()
    }
}

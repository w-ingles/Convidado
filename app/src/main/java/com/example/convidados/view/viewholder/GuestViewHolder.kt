package com.example.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.model.GuestModel
import com.example.convidados.view.listener.GuestListener


class GuestViewHolder(itemView: View,val listener: GuestListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel){
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener(){
            listener.onClick(guest.id)
        }
        textName.setOnLongClickListener{

            AlertDialog.Builder(itemView.context).setTitle(R.string.remocao_convidado)
                .setMessage("Deseja Remover?")
                .setPositiveButton(R.string.remover){ dialog,which ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton("Cancelar",null)
                .show()


            true
        }

    }


}
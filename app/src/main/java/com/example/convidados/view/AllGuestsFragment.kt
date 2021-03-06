package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.GuestListener
import com.example.convidados.viewmodel.AllGuestsViewModel
import java.util.*

class AllGuestsFragment : Fragment() {


    private lateinit var mViewModel: AllGuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?)
            : View? {


        mViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_all, container, false)

        val recyclear = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        recyclear.layoutManager = LinearLayoutManager(context)

        //ADAPTER O ALINHAMENTO DOS DADOS PARA O RECYCLEAR
        recyclear.adapter = mAdapter


        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID,id)


                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load()
            }


        }
        mAdapter.attachListener(mListener)

        observer()




        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load()
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)

        })
    }


}

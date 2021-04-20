package com.example.acalculator

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_history.*

var lista: ArrayList<Operation>? = ArrayList<Operation>()

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val binding =
            ButterKnife.bind(this, view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            lista = arguments?.getParcelableArrayList<Operation>(EXTRA_HISTORY)
        }
    }


    override fun onStart() {
        super.onStart()

        list_historic.layoutManager = LinearLayoutManager(activity as Context)
        list_historic.adapter = lista?.let { HistoryAdapter(activity as Context, R.layout.item_expression, it) }


    }

}
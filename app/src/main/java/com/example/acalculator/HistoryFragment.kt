package com.example.acalculator

import android.content.Context

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_history.*



var lista: ArrayList<Operation>? = ArrayList()

class HistoryFragment : Fragment(), OnListChanged {

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        val binding =
            ButterKnife.bind(this, view)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterListener()
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
        list_historic.layoutManager = LinearLayoutManager(activity as Context)
        list_historic.adapter = lista?.let { HistoryAdapter(activity as Context, R.layout.item_expression, ArrayList(viewModel.onCreateList())) }
    }

    override fun onListChanged(value: List<Operation>) {
        list_historic.layoutManager = LinearLayoutManager(activity as Context)
        list_historic.adapter =  HistoryAdapter(activity as Context, R.layout.item_expression, ArrayList(viewModel.onCreateList()) )
    }

}
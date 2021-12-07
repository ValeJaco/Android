package com.example.dummybase.ui.seances

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dummybase.R
import com.example.dummybase.data.model.Seance
import com.example.dummybase.databinding.SeanceFragmentBinding
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class SeancesListRecyclerAdapter(private val viewModel: SeancesListViewModel) :
    RecyclerView.Adapter<SeancesListRecyclerAdapter.SeancesListHolder>() {

    var items: List<Seance> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeancesListHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<SeanceFragmentBinding>(
                inflater,
                R.layout.seance_fragment,
                parent,
                false
            )
        return SeancesListHolder(binding)
    }

    override fun onBindViewHolder(holder: SeancesListRecyclerAdapter.SeancesListHolder, position: Int) {
        val item = items[position]
        holder.bind(item,viewModel)
    }

    fun setSeancesList(seancesList: List<Seance>) {
        items = seancesList
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    class SeancesListHolder(val binding: SeanceFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Seance, viewModel: SeancesListViewModel) {
            binding.seance = item
            binding.vm = viewModel
        }
    }
}
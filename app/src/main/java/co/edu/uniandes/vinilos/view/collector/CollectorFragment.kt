package co.edu.uniandes.vinilos.view.collector

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.edu.uniandes.vinilos.databinding.FragmentCollectorBinding
import co.edu.uniandes.vinilos.viewmodel.CollectorViewModel

class CollectorFragment : Fragment() {

    private var _binding: FragmentCollectorBinding? = null
    private val collectorViewModel: CollectorViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectorBinding.inflate(layoutInflater)
        val root: View = binding.root

        val adapter = CollectorAdapter()
        binding.rvCollectors.adapter = adapter

        adapter.onCollectorSelected = this::goToDetailCollector

        collectorViewModel.listCollector.observe(viewLifecycleOwner,  {
            adapter.data = it
        })

        collectorViewModel.getCollectors()

        return root
    }

    private fun goToDetailCollector(id: Int) {
        val intent = Intent(binding.root.context, CollectorDetailActivity::class.java).apply {
            putExtra("ID_COLLECTOR", id)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
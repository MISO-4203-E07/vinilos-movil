package co.edu.uniandes.vinilos.view.artist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import co.edu.uniandes.vinilos.databinding.FragmentArtistBinding
import co.edu.uniandes.vinilos.viewmodel.ArtistViewModel

class ArtistFragment : Fragment() {

    private var _binding: FragmentArtistBinding? = null
    private val artistViewModel: ArtistViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistBinding.inflate(layoutInflater)
        val root: View = binding.root

        val adapter = ArtistAdapter()
        binding.tvArtist.adapter = adapter

        adapter.onArtistSelected = this::goToDetailArtist

        artistViewModel.listArtist.observe(viewLifecycleOwner,  {
            adapter.data = it
        })

        artistViewModel.getArtists()

        return root
    }

    private fun goToDetailArtist(id: Int) {
        val intent = Intent(binding.root.context, ArtistDetailActivity::class.java).apply {
            putExtra("ID_ARTIST", id)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package co.edu.uniandes.vinilos.view.album

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import co.edu.uniandes.vinilos.databinding.FragmentAlbumBinding
import co.edu.uniandes.vinilos.viewmodel.AlbumViewModel

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val albumViewModel: AlbumViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumBinding.inflate(layoutInflater)
        val root: View = binding.root

        val adapter = AlbumAdapter()
        binding.rvAlbums.adapter = adapter

        adapter.onAlbumSelected = this::goToDetailAlbum

        albumViewModel.listAlbums.observe(viewLifecycleOwner,  {
            adapter.data = it
        })

        albumViewModel.getAlbums()

        return root
    }

    private fun goToDetailAlbum(id: Int) {
        val intent = Intent(binding.root.context, DetailAlbumActivity::class.java).apply {
            putExtra("ID_ALBUM", id)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
package com.polish.pocketmonsters.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polish.pocketmonsters.R
import com.polish.pocketmonsters.adapter.PokemonAdapter
import com.polish.pocketmonsters.databinding.FragmentDisplayPokemonCharactersBinding
import com.polish.pocketmonsters.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DisplayPokemonCharactersFragment : Fragment() {
    val TAG = "DISPLAY_POK_CHA_FRAG"
    lateinit var pokemonAdapter:PokemonAdapter
    lateinit var myRecyclerview:RecyclerView


    /**
     * reference to the viewmodel
     */
    private val pokemonViewModel:PokemonViewModel by viewModels()

    private var _binding:FragmentDisplayPokemonCharactersBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentDisplayPokemonCharactersBinding.inflate(inflater, container, false)
        val view = binding.root
        /**
         * initialize the recyclerview
         */
        myRecyclerview = binding.fragDisplayPokemonCharactersRv
        myRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)



        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
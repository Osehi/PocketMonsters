package com.polish.pocketmonsters.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polish.pocketmonsters.R
import com.polish.pocketmonsters.adapter.PokemonAdapter
import com.polish.pocketmonsters.databinding.FragmentDisplayPokemonCharactersBinding
import com.polish.pocketmonsters.networkdatamodel.PokemonCharacters
import com.polish.pocketmonsters.networkdatamodel.Result
import com.polish.pocketmonsters.utils.DataState
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
    lateinit var mySpinner: Spinner
    lateinit var selectedLimit:String
    lateinit var display:Button
    lateinit var pokemonCharacterAdapter: PokemonAdapter


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
         * initialize views
         */
        mySpinner = binding.fragDisplayPokemonCharactersSr
        display = binding.fragDisplayPokemonCharactersBtn
        /**
         * initialize the recyclerview
         */
        myRecyclerview = binding.fragDisplayPokemonCharactersRv
        myRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        /**
         * declare the spinner adapter
         */
        val adapter = ArrayAdapter<String>(requireContext(), R.layout.support_simple_spinner_dropdown_item,resources.getStringArray(R.array.numbers))
        mySpinner.adapter = adapter
        /**
         * add spinner
         */
        addSpinnerSelection()
        // make the call to the api, at on click of the display button
        display.setOnClickListener {
            pokemonViewModel.getAllPokemonCharacters(selectedLimit)
        }

        /**
         * observe the response and set it to the adapter
         */
        pokemonViewModel.getAllPokemonResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is DataState.Success -> {
                    val pokemonData = it.data.results
                    populateAdapter(pokemonData!!)
                    Log.d(TAG, "result of the $pokemonData")
                }
            }
        })




        return view
    }

    /**
     * onItemSelectionListener is added on the spinner item
     * to listen to the selected item
     */
    private fun addSpinnerSelection(){
        mySpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(requireContext(), "spinner output${parent?.getItemAtPosition(position).toString()}", Toast.LENGTH_LONG).show()
                selectedLimit = parent?.getItemAtPosition(position).toString()
                Log.d(TAG, "the selected item is :$selectedLimit")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do something
            }

        }
    }

    private fun populateAdapter(dataStream:List<Result>){
        pokemonAdapter = PokemonAdapter(dataStream)
        myRecyclerview.adapter = pokemonAdapter
        pokemonAdapter.notifyDataSetChanged()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
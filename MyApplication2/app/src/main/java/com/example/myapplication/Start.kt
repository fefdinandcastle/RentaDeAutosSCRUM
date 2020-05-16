package com.example.myapplication

import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R.id.mitexto
import kotlinx.android.synthetic.main.fragment_start.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Start.newInstance] factory method to
 * create an instance of this fragment.
 */
class Start : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var item: ClipData.Item? = null
    lateinit var textViewTitle : TextView
    val elementos: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cargarMenu()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun cargarMenu(){
        elementos.add("Music")
        elementos.add("Videos")
        elementos.add("Photos")
        elementos.add("Extras")
        elementos.add("Settings")
        elementos.add("Shuffle Songs")
        elementos.add("Now Playing")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_start, container, false)
        textViewTitle = root.findViewById<View>(R.id.mitexto) as TextView
        textViewTitle.text = "text"
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_menu.layoutManager = LinearLayoutManager(context)
        rv_menu.adapter = MenuAdapter(elementos,context)
    }

    public fun update(s:String){
        textViewTitle.text=s
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Start.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Start().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
package com.ukt.utssender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ukt.utssender.databinding.FragmentFirstBinding
import com.ukt.utssender.model.PowerOnModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@Suppress("UNUSED_VALUE")
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var firestore: FirebaseFirestore
    private lateinit var ref: DocumentReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        firestore = Firebase.firestore
        ref = firestore.collection("sensorPIR").document("PowerOn")
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePowerOn()
        binding.buttonFirst.setOnClickListener {
            ref.update("startTask", true)
        }
    }


    private fun observePowerOn(){
        ref. addSnapshotListener { value, error ->
            if(error == null){
                val data = value?.toObject(PowerOnModel::class.java)
                binding.buttonFirst.isEnabled = data == null || data.startTask == false
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
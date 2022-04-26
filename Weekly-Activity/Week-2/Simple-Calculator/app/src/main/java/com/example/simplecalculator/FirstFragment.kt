package com.example.simplecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.simplecalculator.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = view.findViewById<TextView>(R.id.result)

        binding.addButton.setOnClickListener {
            result.text = (getNum1(view) + getNum2(view)).toString()
        }
        binding.subtractButton.setOnClickListener {
            result.text = (getNum1(view) - getNum2(view)).toString()
        }
        binding.divideButton.setOnClickListener {
            result.text = (getNum1(view) / getNum2(view)).toString()
        }
        binding.multiplyButton.setOnClickListener {
            result.text = (getNum1(view) * getNum2(view)).toString()
        }
    }

    fun getNum1(view: View): Int{
        val value_one = view.findViewById<EditText>(R.id.value_one)
        return Integer.parseInt(value_one.text.toString())
    }

    fun getNum2(view: View): Int{
        val value_two = view.findViewById<EditText>(R.id.value_two)
        return Integer.parseInt(value_two.text.toString())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
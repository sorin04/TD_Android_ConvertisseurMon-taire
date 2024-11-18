package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tauxEUR_USD = 1.09
    private val tauxEUR_UAH = 40.0
    private val tauxUSD_MXN = 20.10
    private var taux:Double = tauxEUR_USD
    private var haut:Double = 0.0
    private var bas:Double = 0.0
    private lateinit var binding : ActivityMainBinding
    private lateinit var dataViewModel: DataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        binding.infoModel = dataViewModel
        binding.lifecycleOwner = this@MainActivity
        dataViewModel.initData()

        dataViewModel.data.observe(this, Observer<Data> { data ->
            Log.i("observe", "Observer $data.toString()")
        })

        binding.euroUSD.isChecked = true

        binding.buttonBas.setOnClickListener {
            when {
                binding.editTextHaut.text.toString().isNotEmpty() -> {
                    haut = binding.editTextHaut.text.toString().toDouble()
                    bas = haut * taux
                    dataViewModel.updateData(Data(null, null, haut, bas))
                }
                binding.editTextHaut.text.toString().isEmpty() -> {
                    Toast.makeText(applicationContext, "Convertission impossible", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.buttonHaut.setOnClickListener {
            when {
                binding.editTextBas.text.toString().isNotEmpty() -> {
                    bas = binding.editTextBas.text.toString().toDouble()
                    haut = bas / taux
                    dataViewModel.updateData(Data(null, null, haut, bas))
                }
                binding.editTextBas.text.toString().isEmpty() -> {
                    Toast.makeText(applicationContext, "Convertission impossible", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.euroUSD.setOnClickListener {
            updateLabel(tauxEUR_USD, "Euro", "Dollar")
        }
        binding.euroUAH.setOnClickListener {
            updateLabel(tauxEUR_UAH, "Euro", "Grivnya")
        }
        binding.dollarPeso.setOnClickListener {
            updateLabel(tauxUSD_MXN, "Dollar", "Peso")
        }
    }

    private fun updateLabel(taux:Double, labelHaut:String, labelBas:String) {
        this.taux = taux
        dataViewModel.updateData(Data(labelHaut, labelBas, null, null))
    }
}
package com.example.calculatoruhuy

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatoruhuy.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var operasi = ""
    private var hasil = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Tombol angka
        binding.n0.setOnClickListener { tambahOperasi("0") }
        binding.n1.setOnClickListener { tambahOperasi("1") }
        binding.n2.setOnClickListener { tambahOperasi("2") }
        binding.n3.setOnClickListener { tambahOperasi("3") }
        binding.n4.setOnClickListener { tambahOperasi("4") }
        binding.n5.setOnClickListener { tambahOperasi("5") }
        binding.n6.setOnClickListener { tambahOperasi("6") }
        binding.n7.setOnClickListener { tambahOperasi("7") }
        binding.n8.setOnClickListener { tambahOperasi("8") }
        binding.n9.setOnClickListener { tambahOperasi("9") }

        // Tombol operator
        binding.nTambah.setOnClickListener { tambahOperasi("+") }
        binding.nKurang.setOnClickListener { tambahOperasi("-") }
        binding.nKali.setOnClickListener { tambahOperasi("*") }
        binding.nBagi.setOnClickListener { tambahOperasi("/") }
        binding.nKurungbuka.setOnClickListener { tambahOperasi("(") }
        binding.nKurungtutup.setOnClickListener { tambahOperasi(")") }
        binding.nDot.setOnClickListener { tambahOperasi(".") }

        // Tombol reset
        binding.nReset.setOnClickListener {
            operasi = ""
            hasil = ""
            binding.textoperasi.text = ""
            binding.texthasil.text = ""
        }

        // Tombol delete
        binding.nDelete.setOnClickListener {
            if (operasi.isNotEmpty()) {
                operasi = operasi.dropLast(1)
                binding.textoperasi.text = operasi
            }
        }

        // Tombol samadengan
        binding.nSamadengan.setOnClickListener {
            hasil = kalkulasi(operasi)
            binding.texthasil.text = hasil
            Toast.makeText(this, "Hasil Perhitungan : "+ hasil, Toast.LENGTH_SHORT).show()
            operasi = hasil
        }
    }

    private fun tambahOperasi(value: String) {
        operasi += value
        binding.textoperasi.text = operasi
    }

    private fun kalkulasi(operasi: String): String {
        return try {
            val operasiPerhitungan = ExpressionBuilder(operasi).build()
            val hasilPerhitungan = operasiPerhitungan.evaluate()
            hasilPerhitungan.toString()
        } catch (e: Exception) {
            "Error"
        }
    }
}

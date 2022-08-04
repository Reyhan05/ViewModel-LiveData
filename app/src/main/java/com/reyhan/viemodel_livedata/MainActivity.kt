package com.reyhan.viemodel_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reyhan.viemodel_livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var getScoreViewModel: GetScoreViewModel

    /*private var scoreA = 0
    private var scoreB = 0*/

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getScoreViewModel = ViewModelProvider(this).get(GetScoreViewModel::class.java)

        initView()

        binding.btnPlusScoreTeamA.setOnClickListener(this)
        binding.btnMinusScoreTeamA.setOnClickListener(this)
        binding.btnPlusScoreTeamB.setOnClickListener(this)
        binding.btnMinusScoreTeamB.setOnClickListener(this)
        binding.btnReset.setOnClickListener(this)

    }

     fun initView() {
         // Kita akan mendapatkan data score menggunakan liveData
         // agar data yang sudah ribuah masih bisa di pantau diobserve oleh uinya
         getScoreViewModel.getScoreA()?.observe(this, Observer {
             // disini kita akan menampilkan livedata agar setiap perubahan
             // yang ada di LiveData bisa di tampilkan
             if (it != null){
                 binding.tvScoreTeamA.text = it.toString()
             }
         })

         getScoreViewModel.getScoreB()?.observe(this, Observer {
             if (it != null){
                 binding.tvScoreTeamB.text = it.toString()
             }
         })
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnPlusScoreTeamA -> {
                getScoreViewModel.addScoreA()
            }
            R.id.btnMinusScoreTeamA -> {
                getScoreViewModel.minScoreA()
            }
            R.id.btnPlusScoreTeamB -> {
                getScoreViewModel.addScoreB()
            }
            R.id.btnMinusScoreTeamB -> {
                getScoreViewModel.minScoreB()
            }
            R.id.btnReset -> {
                getScoreViewModel.resetScore()
            }
        }
    }

}
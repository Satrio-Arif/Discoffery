package com.project.discofferytemp.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.auth.FirebaseAuth
import com.project.discofferytemp.*
import com.project.discofferytemp.adapter.HomeAdapter
import com.project.discofferytemp.databinding.FragmentHomeBinding
import com.project.discofferytemp.helper.Data
import com.project.discofferytemp.model.Articles
import com.project.discofferytemp.viewmodel.ArticleViewModel
import com.project.discofferytemp.viewmodel.HomeViewModel
import com.project.discofferytemp.viewmodel.ViewModelFactoryArticle
import com.project.discofferytemp.viewmodel.ViewModelFactoryHome
import java.io.File


class Home : Fragment() {
    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var articlesData:ArrayList<Articles>
    private lateinit var model: HomeViewModel
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user =auth.currentUser

        binding?.tvName?.text =user?.displayName?:"Phaksi Bangun Asmoro"
        showLoading(true)
        binding?.profileImage?.setOnClickListener {

            val intent = Intent(this@Home.context, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding?.homeToPrice?.setOnClickListener {
            Data.flag = 1
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frame_container, Price()).commit()
        }

        binding?.scanPhoto?.setOnClickListener {
            startCameraX()
        }
        model = ViewModelProvider(this, ViewModelFactoryHome.getInstance()).get(HomeViewModel::class.java)
        model.getArticle()
        model.article.observe(viewLifecycleOwner){

            this.articlesData =it
            for (i in it.indices){
                this.articlesData[i].img = resources.obtainTypedArray(R.array.data_photo).getResourceId(0, -1)
            }
            showData(this.articlesData)
        }

        val year = ArrayList<Entry>()
        year.add(Entry(60F, 31150.00696795f))
        year.add(Entry(61F, 30329.0480653f))
        year.add(Entry(62F, 29777.12477044f))
        year.add(Entry(63F, 28867.41765281f))
        year.add(Entry(64F, 28345.97724905f))
        year.add(Entry(65F, 27395.4623901f))
        year.add(Entry(66F, 27218.43560506f))
        year.add(Entry(67F, 26732.52432932f))
        year.add(Entry(68F, 26407.63277398f))
        year.add(Entry(69F, 26004.17231287f))
        year.add(Entry(70F, 26661.79384631f))
        year.add(Entry(72F, 26677.35083455f))
        year.add(Entry(73F, 27271.84303921f))
        year.add(Entry(74F, 27387.3903679f))
        year.add(Entry(75F, 27792.42731941f))
        year.add(Entry(76F, 27470.26557522f))
        year.add(Entry(77F, 27385.66589043f))
        year.add(Entry(78F, 27494.02667329f))
        year.add(Entry(79F, 27832.33246188f))
        year.add(Entry(80F, 27861.87606313f))
        year.add(Entry(81F, 27754.03629261f))
        year.add(Entry(82F, 27804.91693921f))
        year.add(Entry(83F, 27361.33485867f))
        year.add(Entry(84F, 26975.55579528f))
        year.add(Entry(85F, 26289.2663527f))
        year.add(Entry(86F, 25908.10301836f))
        year.add(Entry(88F, 25269.5962781f))
        year.add(Entry(89F, 24218.40251075f))


        val day = ArrayList<Entry>()
        day.add(Entry(0F, 35456f))
        day.add(Entry(1F, 33456f))
        day.add(Entry(2F, 34456f))
        day.add(Entry(3F, 35347f))
        day.add(Entry(4F, 34507f))
        day.add(Entry(5F, 33534f))
        day.add(Entry(6F, 33674f))
        day.add(Entry(7F, 33142f))
        day.add(Entry(8F, 32883f))
        day.add(Entry(9F, 30464.8f))
        day.add(Entry(10F, 30631.4f))
        day.add(Entry(11F, 29869F))
        day.add(Entry(12F, 29452.2f))
        day.add(Entry(13F, 28574f))
        day.add(Entry(14F, 28252f))
        day.add(Entry(15F, 28238f))
        day.add(Entry(16F, 27027f))
        day.add(Entry(17F, 26404f))
        day.add(Entry(18F, 26516f))
        day.add(Entry(19F, 26068f))
        day.add(Entry(20F, 25879f))
        day.add(Entry(21F, 26061f))
        day.add(Entry(22F, 25704f))
        day.add(Entry(23F, 25942f))
        day.add(Entry(24F, 25529f))
        day.add(Entry(25F, 25690f))
        day.add(Entry(26F, 25704f))
        day.add(Entry(27F, 27858f))
        day.add(Entry(28F, 26773f))
        day.add(Entry(29F, 28025f))
        day.add(Entry(30F, 29229f))
        day.add(Entry(31F, 30480f))
        day.add(Entry(32F, 28703f))
        day.add(Entry(33F, 27543f))
        day.add(Entry(34F, 29068f))
        day.add(Entry(35F, 28949f))
        day.add(Entry(36F, 30482f))
        day.add(Entry(37F, 32456.8f))
        day.add(Entry(38F, 31088.6f))
        day.add(Entry(39F, 32667.6f))
        day.add(Entry(40F, 30632f))
        day.add(Entry(41F, 29561f))
        day.add(Entry(42F, 30450f))
        day.add(Entry(43F, 30254f))
        day.add(Entry(44F, 31395f))
        day.add(Entry(45F, 32606f))
        day.add(Entry(46F, 34456.8f))
        day.add(Entry(47F, 34088.6f))
        day.add(Entry(48F, 33450f))
        day.add(Entry(49F, 32254f))
        day.add(Entry(50F, 31807f))
        day.add(Entry(51F, 31451f))
        day.add(Entry(52F, 30037f))
        day.add(Entry(53F, 29976f))
        day.add(Entry(54F, 28198f))
        day.add(Entry(55F, 27976f))
        day.add(Entry(56F, 26192f))
        day.add(Entry(57F, 25053f))
        day.add(Entry(58F, 25158f))
        day.add(Entry(59F, 25963f))


        val kasusLineDataSet = LineDataSet(year, "Month Prediction")
        kasusLineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        kasusLineDataSet.color = Color.parseColor("#EEAA94")
        kasusLineDataSet.setDrawCircles(false)
        kasusLineDataSet.lineWidth = 2f
        kasusLineDataSet.setDrawValues(false)
        kasusLineDataSet.setDrawHorizontalHighlightIndicator(true)
        kasusLineDataSet.setCircleColor(Color.parseColor("#EEAA94"))


        val daylinedataset = LineDataSet(day, "Daily Prediction")
        daylinedataset.mode = LineDataSet.Mode.CUBIC_BEZIER
        daylinedataset.color = Color.parseColor("#8C5243")
        daylinedataset.circleRadius = 0f
        daylinedataset.lineWidth = 2f
        daylinedataset.setDrawCircles(false)
        daylinedataset.setDrawValues(false)
        daylinedataset.setCircleColor(Color.parseColor("#8C5243"))
        val legend = binding?.lineChart?.legend
        legend?.isEnabled = true
        legend?.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend?.orientation = Legend.LegendOrientation.HORIZONTAL
        legend?.setDrawInside(false)
        binding?.lineChart?.isAutoScaleMinMaxEnabled = true
        binding?.lineChart?.description?.isEnabled = true
        binding?.lineChart?.description?.textColor
        binding?.lineChart?.description = null
        binding?.lineChart?.axisLeft?.granularity = 6f
        binding?.lineChart?.axisLeft?.gridLineWidth = 1f
        binding?.lineChart?.axisLeft?.setDrawAxisLine(false)
        binding?.lineChart?.xAxis?.setDrawGridLines(false)
        binding?.lineChart?.axisRight?.isEnabled = false
        binding?.lineChart?.legend?.isEnabled = false
        binding?.lineChart?.axisLeft?.enableGridDashedLine(20f, 20f, 5f)
        binding?.lineChart?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        binding?.lineChart?.data = LineData(daylinedataset, kasusLineDataSet)
        binding?.lineChart?.animateXY(100, 500)
    }

    private fun startCameraX() {
        val intent = Intent(activity, CameraActivity::class.java)
        launchCamera2.launch(intent)
    }

    private val launchCamera2 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == MainActivity.CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            val intent = Intent(activity, ScanPhoto::class.java)
            intent.putExtra("picture", myFile)
            intent.putExtra(
                "isBackCamera",
                isBackCamera
            )
            startActivity(intent)
        }
    }

    private fun showData(value: ArrayList<Articles>) {
        binding?.rvArticles?.layoutManager = LinearLayoutManager(activity)
        val adapter = HomeAdapter(requireActivity())
        adapter.setData(value)
        binding?.rvArticles?.adapter = adapter
        showLoading(false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(Is_load: Boolean) {
        if (Is_load) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }


}





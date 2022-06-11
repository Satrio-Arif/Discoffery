package com.project.discofferytemp.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.project.discofferytemp.databinding.FragmentPriceBinding


class Price : Fragment() {
    private var _binding: FragmentPriceBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPriceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val year = ArrayList<Entry>()
        year.add(Entry(60F, 76027.00696795f))
        year.add(Entry(61F, 77427.0480653f))
        year.add(Entry(62F, 76554.12477044f))
        year.add(Entry(63F, 72661.41765281f))
        year.add(Entry(64F, 75004.97724905f))
        year.add(Entry(65F, 71219.4623901f))
        year.add(Entry(66F, 70329.43560506f))
        year.add(Entry(67F, 70115.52432932f))
        year.add(Entry(68F, 71090.63277398f))
        year.add(Entry(69F, 71731.17231287f))
        year.add(Entry(70F, 72714.79384631f))
        year.add(Entry(72F, 72099.35083455f))
        year.add(Entry(73F, 71636.84303921f))
        year.add(Entry(74F, 73572.3903679f))
        year.add(Entry(75F, 75286.42731941f))
        year.add(Entry(76F, 76070.26557522f))
        year.add(Entry(77F, 75909.66589043f))
        year.add(Entry(78F, 75779.02667329f))
        year.add(Entry(79F, 74695.33246188f))
        year.add(Entry(80F, 73989.87606313f))
        year.add(Entry(81F, 72444.03629261f))
        year.add(Entry(82F, 72454.91693921f))
        year.add(Entry(83F, 72157.33485867f))
        year.add(Entry(84F, 69954.55579528f))
        year.add(Entry(85F, 68705.2663527f))
        year.add(Entry(86F, 68988.10301836f))
        year.add(Entry(88F, 70271.5962781f))
        year.add(Entry(89F, 70971.40251075f))


        val day = ArrayList<Entry>()
        day.add(Entry(0F, 69956f))
        day.add(Entry(1F, 67510f))
        day.add(Entry(2F, 69540f))
        day.add(Entry(3F, 69093f))
        day.add(Entry(4F, 70355f))
        day.add(Entry(5F, 71826f))
        day.add(Entry(6F, 71954f))
        day.add(Entry(7F, 72034f))
        day.add(Entry(8F, 70931f))
        day.add(Entry(9F, 70931.8f))
        day.add(Entry(10F, 68597.4f))
        day.add(Entry(11F, 68965f))
        day.add(Entry(12F, 70931f))
        day.add(Entry(13F, 72386f))
        day.add(Entry(14F, 73025f))
        day.add(Entry(15F, 73729f))
        day.add(Entry(16F, 73952f))
        day.add(Entry(17F, 72769f))
        day.add(Entry(18F, 72306f))
        day.add(Entry(19F, 74064f))
        day.add(Entry(20F, 75647f))
        day.add(Entry(21F, 74688f))
        day.add(Entry(22F, 71954f))
        day.add(Entry(23F, 71491f))
        day.add(Entry(24F, 71459f))
        day.add(Entry(25F, 70755f))
        day.add(Entry(26F, 69956f))
        day.add(Entry(27F, 72945f))
        day.add(Entry(28F, 72673f))
        day.add(Entry(29F, 70595f))
        day.add(Entry(30F, 70771f))
        day.add(Entry(31F, 68993f))
        day.add(Entry(32F, 69611f))
        day.add(Entry(33F, 71040f))
        day.add(Entry(34F, 69684f))
        day.add(Entry(35F, 69029f))
        day.add(Entry(36F, 70595f))
        day.add(Entry(37F, 69460f))
        day.add(Entry(38F, 67088.6f))
        day.add(Entry(39F, 65895f))
        day.add(Entry(40F, 65160f))
        day.add(Entry(41F, 70308f))
        day.add(Entry(42F, 68837f))
        day.add(Entry(43F, 68389f))
        day.add(Entry(44F, 71874f))
        day.add(Entry(45F, 72642f))
        day.add(Entry(46F, 69924f))
        day.add(Entry(47F, 68981f))
        day.add(Entry(48F, 68309f))
        day.add(Entry(49F, 69396f))
        day.add(Entry(50F, 72450f))
        day.add(Entry(51F, 72642f))
        day.add(Entry(52F, 69572f))
        day.add(Entry(53F, 69396f))
        day.add(Entry(54F, 69396f))
        day.add(Entry(55F, 73936f))
        day.add(Entry(56F, 76558f))
        day.add(Entry(57F, 76174f))
        day.add(Entry(58F, 74304f))
        day.add(Entry(59F, 72450f))


        val kasusLineDataSet = LineDataSet(year, "Month Prediction")
        kasusLineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        kasusLineDataSet.color = Color.parseColor("#EEAA94")
        kasusLineDataSet.setDrawCircles(false)
        kasusLineDataSet.lineWidth = 2f
        kasusLineDataSet.setDrawValues(false)
        kasusLineDataSet.setDrawHorizontalHighlightIndicator(true)
        kasusLineDataSet.setCircleColor(Color.parseColor("#EEAA94"))


        val daylinedataset = LineDataSet(day, "Data Set")
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
        binding?.lineChart?.legend?.isEnabled = true
        binding?.lineChart?.axisLeft?.enableGridDashedLine(20f, 20f, 5f)
        binding?.lineChart?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        binding?.lineChart?.data = LineData(daylinedataset, kasusLineDataSet)
        binding?.lineChart?.animateXY(100, 500)


    }


}
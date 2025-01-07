package edu.iesam.ddi_tarea_7_ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import edu.iesam.ddi_tarea_7_ui.databinding.ActivityMainBinding
import edu.iesam.ddi_tarea_7_ui.features.data.local.LocalListOfImages
import edu.iesam.ddi_tarea_7_ui.features.presentation.CarouselAdapter

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewpager)
        val listOfImages = LocalListOfImages().getList()
        viewPager.adapter = CarouselAdapter(listOfImages)
    }


}
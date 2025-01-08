package edu.iesam.ddi_tarea_7_ui

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import edu.iesam.ddi_tarea_7_ui.databinding.ActivityMainBinding
import edu.iesam.ddi_tarea_7_ui.features.data.local.LocalListOfImages
import edu.iesam.ddi_tarea_7_ui.features.presentation.CarouselAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var favoriteClick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        val viewPager: ViewPager2 = findViewById(R.id.viewpager)
        val listOfImages = LocalListOfImages().getList()
        viewPager.adapter = CarouselAdapter(listOfImages)

        setupView()
    }

    private fun setupView() {
        binding.apply {

            // Escuchar cambios en el scroll (Toolbar)
            nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                if (scrollY > 300) { // Aparece la Toolbar al hacer scroll hacia abajo
                    if (appBarLayout.visibility != View.VISIBLE) {
                        appBarLayout.visibility = View.VISIBLE
                        appBarLayout.animate()
                            .translationY(0f)
                            .alpha(1f)
                            .setDuration(300)
                            .start()
                    }
                } else { // Oculta la Toolbar cuando se desplaza hacia arriba
                    if (appBarLayout.visibility == View.VISIBLE) {
                        appBarLayout.animate()
                            .translationY(-appBarLayout.height.toFloat())
                            .alpha(0f)
                            .setDuration(300)
                            .withEndAction {
                                appBarLayout.visibility = View.GONE
                            }
                            .start()
                    }
                }
            }

            // Botón (Enviar)
            button2.setOnClickListener {
                Toast.makeText(this@MainActivity, "Botón (Enviar) pulsado", Toast.LENGTH_SHORT)
                    .show()
            }

            // Botón (Hacer una Oferta)
            offer.setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Botón (Hacer una Oferta) pulsado",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Botón (Comprar)
            buy.setOnClickListener {
                Toast.makeText(this@MainActivity, "Botón (Comprar) pulsado", Toast.LENGTH_SHORT)
                    .show()
            }

            // Botón (Favorito)
            favorite.setOnClickListener {
                if (favoriteClick) {
                    favoriteClick = false
                    favorite.text = "18"
                    favorite.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.baseline_favorite_border_24,
                        0,
                        0,
                        0
                    )
                    favorite.compoundDrawableTintList =
                        ContextCompat.getColorStateList(this@MainActivity, R.color.black)
                } else {
                    favoriteClick = true
                    favorite.text = "19"
                    favorite.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.baseline_favorite_24,
                        0,
                        0,
                        0
                    )
                    favorite.compoundDrawableTintList =
                        ContextCompat.getColorStateList(this@MainActivity, R.color.red)
                }
            }


            // Texto (Descripción)
            val spannableString = SpannableString(getString(R.string.description_text))
            // Buscar la posición de la palabra "Más"
            val startIndex = spannableString.toString().indexOf("Más")
            val endIndex = startIndex + "Más".length

            // Añadir el evento ClickableSpan
            spannableString.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    // Mostrar el Toast
                    Toast.makeText(this@MainActivity, "Botón (Más) pulsado", Toast.LENGTH_SHORT)
                        .show()
                }
            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            description.text = spannableString

            // Habilitar la detección de clics en el texto
            description.movementMethod = LinkMovementMethod.getInstance()
        }
    }

}
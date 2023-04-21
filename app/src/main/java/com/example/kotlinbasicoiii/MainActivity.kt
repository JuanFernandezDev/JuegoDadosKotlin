package com.example.kotlinbasicoiii

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.kotlinbasicoiii.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Damos scroll al textview que muestra cada tirada de los dados
        binding.resultadoDados.movementMethod = ScrollingMovementMethod()

        var numeros = arrayListOf<Number>()
        var sumaTotal = 0


        //Inicializamos los numberPicker
        binding.nCaras.minValue = 2
        binding.nCaras.maxValue = 100
        binding.nCaras.value = 2
        binding.nCaras.wrapSelectorWheel = true
        binding.nCaras.setFormatter { i -> String.format("%03d", i) }

        binding.nDados.minValue = 1
        binding.nDados.maxValue = 100
        binding.nDados.value = 1
        binding.nDados.wrapSelectorWheel = true
        binding.nDados.setFormatter { i -> String.format("%03d", i) }


        //Creamos el setOnClickListener para que al tirar cree un numero random (1, al numero de caras) por cada dado
        binding.tirar.setOnClickListener {
            numeros.clear()
            sumaTotal = 0

            var numDados = binding.nDados.value
            var numCaras = binding.nCaras.value

            if (numDados != null && numCaras != null){
                for (i in 0 until numDados) {
                    val random = Random()
                    val aleatorio = random.nextInt(numCaras) + 1
                    numeros.add(aleatorio)
                    sumaTotal += aleatorio
                }

                binding.sumaDados.text = "La suma de los dados es: " + sumaTotal.toString()

                binding.resultadoDados.text = "Resultado dados: " + numeros.joinToString(", ")
            }

            //Ocultamos el teclado al presionar en "Tirar"
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }

        }



    }
}
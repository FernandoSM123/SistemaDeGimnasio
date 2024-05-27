package com.example.sistemadegimnasio.viewModel

import androidx.lifecycle.ViewModel

class PesoException(message: String) : Exception(message)

class WeightCalculatorViewModel() : ViewModel() {

    val discosDisponibles = listOf(45.0, 35.0, 5.0, 2.5)
    val pesoBarra = 45.0


    //DISTRIBUIR PESAS CON BASE AL PESO INGRESADO
    fun distribuirPeso(pesoDeseado: Int): String {

        //peso ingresado debe ser mayor al de la barra
        if (pesoDeseado <= pesoBarra.toInt()) {
            throw PesoException("El peso ingresado debe ser mayor a: $pesoBarra lbs")
        }

        //Se multiplica todo por 10 para trabajar solo con enteros
        val discosBy10 = this.discosDisponibles.map { (it * 10).toInt() }
        val barraBy10 = (this.pesoBarra * 10).toInt()
        val pesoDeseadoBy10 = pesoDeseado * 10

        val distribucionDiscos = mutableListOf<String>()
        var pesoRestante = pesoDeseadoBy10 - barraBy10

        for (disco in discosBy10) {
            if (pesoRestante <= 0) break
            val cantidadDiscos = ((pesoRestante / (disco * 2)) * 2)
            if (cantidadDiscos > 0) {
                distribucionDiscos.add("Discos de ${disco.toDouble() / 10} lbs: $cantidadDiscos")
                pesoRestante -= cantidadDiscos * disco
            }
        }

        //no se puede hacer la distribuccion de las barras con el peso ingresado
        if (pesoRestante != 0) {
            throw PesoException("No se puede distribuir el peso ingresado con los discos disponibles")
        }

        return distribucionDiscos.joinToString("\n", "Barra: $pesoBarra lbs\n")
    }
}
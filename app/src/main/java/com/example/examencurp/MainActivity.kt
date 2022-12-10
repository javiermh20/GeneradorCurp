package com.example.examencurp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Spinner
        val lstEstados = arrayOf("Aguascalientes","Baja California","Baja California Sur","Campeche","Coahuila de Zaragoza",
            "Colima","Chiapas","Chihuahua","Distrito Federal","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","México",
            "Michoacán de Ocampo","Morelos","Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo","San Luis Potosí",
            "Sinaloa","Sonora","Tabasco","Tamaulipas","Tlaxcala","Veracruz de Ignacio de la Llave","Yucatán","Zacatecas","Nacido en el Extranjero")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,lstEstados)
        estadosSpn.adapter = adapter

        // Botones
        fechaTxt.setOnClickListener{ showDatePickerDialog()}
        aceptarBtn.setOnClickListener {
            if (validarCampos() == true){
                aceptarBtn.setOnClickListener { calcularDatos() }
            }
        }
        limpiarBtn.setOnClickListener { limpiar() }
    }

    fun calcularDatos() {
        // Primera inicial y vocal del apellido
        val vPrimeraInicialVocalAP = vocalInicial()
        // Primera letra del apellido M
        val vPrimeraLetraAM = apellidomTxt.text?.subSequence(0, 1)
        // Primera letra del nombre
        val vPrimeraLetraNombre = nombreTxt.text?.subSequence(0, 1)
        // Fecha de nacimiento
        val vDay = fechaTxt.text?.subSequence(0, 2)
        val vMonth = fechaTxt.text?.subSequence(3,5)
        val vYear = fechaTxt.text?.subSequence(8, 10)
        // obtener Genero
        val vGenero = genero()
        // Determinar letra estado
        val vEstado = estadosSpn.selectedItem.toString()
        val IN = determinarClaveEstado(vEstado)
        // obtener consonantes
        val vConsonantes = consonantes()
        // Ultimo Digito
        val vLastDigit = ultDigito()
        resTxt.text = "Tu curp es: $vPrimeraInicialVocalAP$vPrimeraLetraAM$vPrimeraLetraNombre$vYear$vMonth$vDay$vGenero$IN$vConsonantes$vLastDigit"
    }

    fun vocalInicial(): String{
        // Ciclo para sacar la primera inical junto la vocal del apellido P
        val inputText = apellidopTxt.text.toString()
        var cadena = inputText.substring(0,1)
        var vocal = false;
        var i = 0
        while(i < inputText.length && !vocal){
            if((inputText.get(i) == 'a') ||
                (inputText.get(i) == 'e')  ||
                (inputText.get(i) == 'i') ||
                (inputText.get(i) == 'o') ||
                (inputText.get(i) == 'u')) {

                cadena = cadena+inputText.get(i)
                vocal = true
            }
            i++
        }
        val vPrimeraInicialVocalAP = cadena.uppercase()
        return vPrimeraInicialVocalAP
    }

    fun genero(): String {
        val vGenero: String
        if(radioH.isChecked) {
            vGenero = "H"
        } else {
            vGenero = "M"
        }
        return vGenero
    }

    fun consonantes(): String{
        var cadena = ""
        var nombre = nombreTxt.text.toString()
        var N = ""
        var apellidoPaterno = apellidopTxt.text.toString()
        var AP = ""
        var apellidoMaterno = apellidomTxt.text.toString()
        var AM = ""
        var i = 1
        var j = 1
        var k = 1

        var charAP = false;
        while(i < apellidoPaterno.length && !charAP){
            if((apellidoPaterno.get(i) == 'b') ||
                (apellidoPaterno.get(i) == 'c')  ||
                (apellidoPaterno.get(i) == 'd') ||
                (apellidoPaterno.get(i) == 'f') ||
                (apellidoPaterno.get(i) == 'g') ||
                (apellidoPaterno.get(i) == 'h') ||
                (apellidoPaterno.get(i) == 'j') ||
                (apellidoPaterno.get(i) == 'k') ||
                (apellidoPaterno.get(i) == 'l') ||
                (apellidoPaterno.get(i) == 'm') ||
                (apellidoPaterno.get(i) == 'n') ||
                (apellidoPaterno.get(i) == 'ñ') ||
                (apellidoPaterno.get(i) == 'p') ||
                (apellidoPaterno.get(i) == 'q') ||
                (apellidoPaterno.get(i) == 'r') ||
                (apellidoPaterno.get(i) == 's') ||
                (apellidoPaterno.get(i) == 't') ||
                (apellidoPaterno.get(i) == 'v') ||
                (apellidoPaterno.get(i) == 'w') ||
                (apellidoPaterno.get(i) == 'x') ||
                (apellidoPaterno.get(i) == 'y') ||
                (apellidoPaterno.get(i) == 'z')) {
                AP = AP + apellidoPaterno.get(i)
                charAP = true
            }
            i++
        }
        var charAM = false;
        while(j < apellidoMaterno.length && !charAM){
            if((apellidoMaterno.get(j) == 'b') ||
                (apellidoMaterno.get(j) == 'c')  ||
                (apellidoMaterno.get(j) == 'd') ||
                (apellidoMaterno.get(j) == 'f') ||
                (apellidoMaterno.get(j) == 'g') ||
                (apellidoMaterno.get(j) == 'h') ||
                (apellidoMaterno.get(j) == 'j') ||
                (apellidoMaterno.get(j) == 'k') ||
                (apellidoMaterno.get(j) == 'l') ||
                (apellidoMaterno.get(j) == 'm') ||
                (apellidoMaterno.get(j) == 'n') ||
                (apellidoMaterno.get(j) == 'ñ') ||
                (apellidoMaterno.get(j) == 'p') ||
                (apellidoMaterno.get(j) == 'q') ||
                (apellidoMaterno.get(j) == 'r') ||
                (apellidoMaterno.get(j) == 's') ||
                (apellidoMaterno.get(j) == 't') ||
                (apellidoMaterno.get(j) == 'v') ||
                (apellidoMaterno.get(j) == 'w') ||
                (apellidoMaterno.get(j) == 'x') ||
                (apellidoMaterno.get(j) == 'y') ||
                (apellidoMaterno.get(j) == 'z')) {
                AM = AM + apellidoMaterno.get(j)
                charAM = true
            }
            j++
        }


        var charN = false;
        while(k < nombre.length && !charN){
            if((nombre.get(k) == 'b') ||
                (nombre.get(k) == 'c')  ||
                (nombre.get(k) == 'd') ||
                (nombre.get(k) == 'f') ||
                (nombre.get(k) == 'g') ||
                (nombre.get(k) == 'h') ||
                (nombre.get(k) == 'j') ||
                (nombre.get(k) == 'k') ||
                (nombre.get(k) == 'l') ||
                (nombre.get(k) == 'm') ||
                (nombre.get(k) == 'n') ||
                (nombre.get(k) == 'ñ') ||
                (nombre.get(k) == 'p') ||
                (nombre.get(k) == 'q') ||
                (nombre.get(k) == 'r') ||
                (nombre.get(k) == 's') ||
                (nombre.get(k) == 't') ||
                (nombre.get(k) == 'v') ||
                (nombre.get(k) == 'w') ||
                (nombre.get(k) == 'x') ||
                (nombre.get(k) == 'y') ||
                (nombre.get(k) == 'z')) {
                N = N + nombre.get(k)
                charN = true
            }
            k++
        }
        cadena = "$AP$AM$N"
        return cadena.uppercase()
    }

    fun validarCampos() : Boolean{
        var validador = false

        if(TextUtils.isEmpty(nombreTxt.text.toString())){
            nombreTxt.error = "Coloca el nombre"
            validador = true
        }
        if(TextUtils.isEmpty(apellidopTxt.text.toString())) {
            apellidopTxt.error = "Coloca el apellido paterno"
            validador = true
        }
        if(TextUtils.isEmpty(apellidomTxt.text.toString())) {
            apellidomTxt.error = "Coloca el apellido materno "
            validador = true
        }
        if(TextUtils.isEmpty(fechaTxt.text.toString())) {
            fechaTxt.error = "Coloca tu fecha de nacimiento "
            validador = true
        }
        if(radioH.isActivated == false && radioM.isActivated == false){
            Toast.makeText(this,"Selecciona un genero", Toast.LENGTH_SHORT)
            radioH.error = "No seleccionaste un genero"
            radioM.error = "No seleccionaste un genero"
            validador = true
        }
        return validador
    }

    // Año de nacimiento ultimo digito
    fun ultDigito(): String {
        var aux = fechaTxt.text?.subSequence(6, 7)
        var aux2 = aux?.substring(0,1)
        var indice: String

        if(aux2 == "2"){
            indice = "1"
        }else{
            indice = "0"
        }
        return indice
    }

    // DATE PICKER
    private fun showDatePickerDialog() {
        val vDatePicker = DatePickerFragment{
                day, month, year -> onDateSelected(day,month,year) }
        vDatePicker.show(supportFragmentManager,"Selector de fecha")
    }
    fun onDateSelected(vDay:Int, vMonth:Int, vYear: Int){
        var vResultado = String.format("%02d",vDay)+"/"+String.format("%02d",vMonth+1)+"/"+vYear
        fechaTxt.setText(vResultado)
    }

    fun limpiar(){
        nombreTxt.setText("")
        apellidopTxt.setText("")
        apellidomTxt.setText("")
        radioH.isChecked = false
        radioM.isChecked = false
        fechaTxt.setText("")
        resTxt.text = ""
    }

    private fun determinarClaveEstado(pNbEstadoSeleccionado: String): String {
        val hmEstados: HashMap<String, String> = HashMap<String, String>()
        hmEstados.put("Aguascalientes", "AS")
        hmEstados.put("Baja California", "BC")
        hmEstados.put("Baja California Sur", "BS")
        hmEstados.put("Campeche", "CC")
        hmEstados.put("Coahuila de Zaragoza", "CL")
        hmEstados.put("Colima", "CM")
        hmEstados.put("Chiapas", "CS")
        hmEstados.put("Chihuahua", "CH")
        hmEstados.put("Distrito Federal", "DF")
        hmEstados.put("Durango", "DG")
        hmEstados.put("Guanajuato", "GT")
        hmEstados.put("Guerrero", "GR")
        hmEstados.put("Hidalgo", "HG")
        hmEstados.put("Jalisco", "JC")
        hmEstados.put("México", "MC")
        hmEstados.put("Michoacán de Ocampo", "MN")
        hmEstados.put("Morelos", "MS")
        hmEstados.put("Nayarit", "NT")
        hmEstados.put("Nuevo León", "NL")
        hmEstados.put("Oaxaca", "OC")
        hmEstados.put("Puebla", "PL")
        hmEstados.put("Querétaro", "QT")
        hmEstados.put("Quintana Roo", "QR")
        hmEstados.put("San Luis Potosí", "SP")
        hmEstados.put("Sinaloa", "SL")
        hmEstados.put("Sonora", "SR")
        hmEstados.put("Tabasco", "TC")
        hmEstados.put("Tamaulipas", "TS")
        hmEstados.put("Tlaxcala", "TL")
        hmEstados.put("Veracruz de Ignacio de la Llave", "VZ")
        hmEstados.put("Yucatán", "YN")
        hmEstados.put("Zacatecas", "ZS")
        hmEstados.put("Nacido en el Extranjero", "NE")
        return hmEstados[pNbEstadoSeleccionado].toString()
    }
}
package com.example.necekilosan

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.OnClickListener
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.necekilosan.databinding.ActivityMainBinding

/*
10 cu ise biz ceheckbox elave etdik
xml deki butun toxunuslari oxumaq ucun umumi OnclicListeneri overidde edirik.Belelikle
butun toxunuslari tez oxuya bileceyik. Umumi clasimiza bunu implement edek
 */
class MainActivity : AppCompatActivity(), OnClickListener {

    val Kilo_To_Pound = 2.2045
    val Mars = 0.38
    val Venera = 0.91
    val Yuputer = 2.34
    val Pound_To_Kilo = 0.45359237


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.drawable.www).into(binding.imageView)

        binding.cbVrnera.setOnClickListener(this)
        binding.cbYuputer.setOnClickListener(this)
        binding.cbMars.setOnClickListener(this)


        /*Bu hesabla butonu ile Varianti
        1 ci Edit textdeki deyeri burda gormek lazim
      var kullaniciKilo = binding.etKilo.text

     3cu. Butona tiklandigi anda bu işler gorulsun deyek
       ve burda  kiloToPound funkiyasinda isdifadeciden alinan deyeri isle yeni
      kilodan paunda cevir ve kullaniciAgirliqPaund olaraq mene ver deyirik
      binding.btnHesabla.setOnClickListener {
          var kullaniciAgirliqPound = kiloToPound(kullaniciKilo.toString().toDouble())

          4cu. Marsdaki cekini bilmek ucun marsdakiAgirliqPound adli deyisken
          yaradaraq yuxarda kilodan paunda cevrilmis deyerle marsin sabitini vuruquq ve elave edirik
          var marsdakiAgirliqPound = kullaniciAgirliqPound * Mars

          6 ci ise asagidaki fonkiyaya pound deyer gondermelik bunun ucun
           marsdkiAgirliqPound deyerini PoudToKilonun icine atiriq ve marsdakiAgirliqKilo deyerine beraberlesdirik
          var marsdakiAgirliqKilo = poundToKilo(marsdakiAgirliqPound)

          7 ci ise yuxardaki marsdakiAgirliqKilo sonucunu tvSonuc icine yeni ekrana yazdiririq
          binding.tvSonuc.text = marsdakiAgirliqKilo.formatla(2).toString()
      }*/

    }


    // 2.ci Programa girilen kilomuzu Paunda cevirek
    // Bunun ucun funksiya yazaq
    fun kiloToPound(kilo: Double): Double {
        return kilo * Kilo_To_Pound
    }



    //5ci  ise alinan pound deyerini indide bu fonkuyonla kiloya cevirek
    fun poundToKilo(pound: Double): Double {
        return pound * Pound_To_Kilo
    }

    /*
    10.1 Bu override view parametrede v ye atir
     */
    override fun onClick(v: View?) {

        /*
        11 Burda deyirrikki sene gelen butun v  deyeri bir CheckBoxdur
         */
        v as CheckBox
        /*
        12 sonra ise burda yazaq ki sene gelen deyer checkdise isCheked deyerini true et yeni islet
         */
        var isChecked: Boolean = v.isChecked


        if (!TextUtils.isEmpty(binding.etKilo.text.toString())) {
            /*
            17 asagida yazilanlar istifadeci oz cekisini yazdiqda kilo ile yazacaq biz ise bunu paunda cevirerek
            checkboxlara gondermeliyik.Evelce yazilan kilonu alaq kullanici kilo ya beraberlesdirek
             */
            var kullaniciKilo = binding.etKilo.text.toString().toDouble()
            /*
            17.1 sonra ise kullanici pounda cevirek. bunun ucun kiloToPound funksiyasina kullaniciKilo melumatini gonderek
            ve sonra geri qayidan deyeri kullaniciPounda beraberlesdirek
             */
            var kullanicipound = kiloToPound(kullaniciKilo)

            /*
            13 whenle deyirikki v nin icindeki id gore emeliyyatlar et
            */
            when (v.id) {
                R.id.cbMars -> if (isChecked) {
                    /*
                    19 Burda bir checkbox secildiyinde digerlerini tikini cixarir
                     */
                    binding.cbYuputer.isChecked = false
                    binding.cbVrnera.isChecked = false
                    /*
                    18 burda ise hesablaAgirliqPound funksiyasina 17.1 de elde etdiyimiz deyeri gonderek ve yaninda checkbox melumatinida
                     */
                    hesablaAgirliqPound(kullanicipound, v)
                }

                R.id.cbVrnera -> if (isChecked) {
                    binding.cbYuputer.isChecked = false
                    binding.cbMars.isChecked = false
                    /*
                    18 burda ise hesablaAgirliqPound funksiyasina 17.1 de elde etdiyimiz deyeri gonderek ve yaninda checkbox melumatinida
                     */
                    hesablaAgirliqPound(kullanicipound, v)
                }

                R.id.cbYuputer -> if (isChecked) {
                    binding.cbVrnera.isChecked = false
                    binding.cbMars.isChecked = false
                    /*
                    18 burda ise hesablaAgirliqPound funksiyasina 17.1 de elde etdiyimiz deyeri gonderek ve yaninda checkbox melumatinida
                     */
                    hesablaAgirliqPound(kullanicipound, v)
                }
            }
        }


    }

    /*
    14 Bu funksiyada (hesablam ve yazdirma islerini gorecek 13deki cekboxlarimin baslidigi an)
    Yeni yuxardan checkbox basilibsa artiq bu isleri gor deyek
    */
    fun hesablaAgirliqPound(pound: Double, checkBox: CheckBox) {
        var sonuc: Double = 0.0
        /*
        eger cbHansinasa toxunulubsa qarsindaki emeliyyati et deyirik
        */
        when (checkBox.id) {
            R.id.cbMars -> sonuc = pound * Mars
            R.id.cbVrnera -> sonuc = pound * Venera
            R.id.cbYuputer -> sonuc = pound * Yuputer
            else -> sonuc = 0.0
        }
        /*
        15 burda ise sonucToKiloya 5 de yazilan funksiyasina bizim 14 de yazdigimiz sonucu gonderek
        ve oda bize kilo olaraq geri getirsin
        */
        var sonucToKilo = poundToKilo(sonuc)
        /*
        16 ve sonda bu sonuc kilonu tvSonuc textview me yazdira bilerem
        */
        binding.tvSonuc.text = sonucToKilo.formatla(2)
    }


    // 8 ci ise double yazdigimiza gore ekranda coxlu regemler yazilir. bunu asagi etmek ucun funksiya yazaq
//evelce double extension funksiya yazaq kacTaneRakam adli deyiskene javanin icindeki uzun yazili reqemlerin tutuldugu
//yerde azaldaraq formatla deyiskenine atiriq deyerini
    fun Double.formatla(kacTanerakam: Int) = java.lang.String.format("%.${kacTanerakam}f", this)

}







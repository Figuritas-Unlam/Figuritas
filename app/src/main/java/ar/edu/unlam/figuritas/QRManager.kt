package ar.edu.unlam.figuritas

import android.graphics.Bitmap
import android.text.TextUtils
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class QRManager {

    //Codigo de verificacion, para saber si el qr lo genero nuestra app
    private val CODE_VERIFY = "Tomas1Aitor2Pablo3Elias4ROCA".hashCode().toString()

    //Validamos que el QR sea de nuestra app, sino no se tendrá en cuenta
    fun isVerify(contents: String?) = (CODE_VERIFY == TextUtils.substring(
            contents,
            0,
            CODE_VERIFY.length
        ))

    //Devolverá una lista de figuritas
    fun deserializationPruebaFiguritas(result: String): PruebaRegaloFiguritas{
        return Gson().fromJson(
            TextUtils.substring(result, CODE_VERIFY.length, result.length),
            PruebaRegaloFiguritas::class.java
        )
    }

    //Se genera el QR para regalar (Mediante la serializacion de la info + CODE_VERIFY, para luego validar)
    fun generateQR(figuritasARegalar: PruebaRegaloFiguritas, width : Int, height : Int): Bitmap? {
        val figures = serializationPruebaFiguritas(figuritasARegalar)
        return BarcodeEncoder().encodeBitmap(
            CODE_VERIFY+figures, BarcodeFormat.QR_CODE,
            width, height)
    }

    private fun serializationPruebaFiguritas(figus :PruebaRegaloFiguritas): String{
        return Gson().toJson(figus)
    }
}
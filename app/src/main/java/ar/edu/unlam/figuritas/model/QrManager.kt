package ar.edu.unlam.figuritas.model

import android.graphics.Bitmap
import android.text.TextUtils
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class QRManager {

    //Validamos que el QR sea de nuestra app, sino no se tendrá en cuenta
    fun isVerified(contents: String?) = (CODE_VERIFY == TextUtils.substring(
        contents,
        0,
        CODE_VERIFY.length
    ))

    //Devolverá una lista de figuritas
    fun deserializeStickers(playersJson: String): List<PlayerEntity> {
        return Gson().fromJson(
            TextUtils.substring(playersJson, CODE_VERIFY.length, playersJson.length),
            LIST_OF_PLAYERS
        )
    }

    //Se genera el QR para regalar (Mediante la serializacion de la info + CODE_VERIFY, para luego validar)
    fun generateQR(stickers: List<PlayerEntity>, width : Int, height : Int): Bitmap? {
        val figures = serializeStickers(stickers)
        return BarcodeEncoder().encodeBitmap(
            CODE_VERIFY+figures, BarcodeFormat.QR_CODE,
            width, height)
    }

    private fun serializeStickers(figus : List<PlayerEntity>): String{
        return Gson().toJson(figus)
    }

    companion object {
        private val LIST_OF_PLAYERS = object : TypeToken<List<PlayerEntity>>() {}.type
        //Codigo de verificacion, para saber si el qr lo genero nuestra app
        private val CODE_VERIFY = "Tomas1Aitor2Pablo3Elias4ROCA".hashCode().toString()
    }

}

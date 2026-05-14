package com.example.gramaangana.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import androidx.compose.foundation.layout.size
@Composable
fun GenerateQRCode(
    text: String,
    modifier: Modifier = Modifier
) {

    val writer = QRCodeWriter()

    val bitMatrix =
        writer.encode(
            text,
            BarcodeFormat.QR_CODE,
            512,
            512
        )

    val width = bitMatrix.width
    val height = bitMatrix.height

    val bitmap =
        Bitmap.createBitmap(
            width,
            height,
            Bitmap.Config.RGB_565
        )

    for (x in 0 until width) {

        for (y in 0 until height) {

            bitmap.setPixel(

                x,
                y,

                if (bitMatrix[x, y])
                    android.graphics.Color.BLACK

                else
                    android.graphics.Color.WHITE
            )
        }
    }

    Image(
        bitmap =
            bitmap.asImageBitmap(),

        contentDescription = null,

        modifier = modifier
    )
}
package com.example.jetpackuistudy.codescanner

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jetpackuistudy.R
import com.google.mlkit.common.MlKitException
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import java.util.Locale

class MainActivity10 : AppCompatActivity() {

    private var allowManualInput = true
    private var enableAutoZoom = true
    private var barcodeResultView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)
        barcodeResultView = findViewById(R.id.barcode_result_view)
    }

    fun onAllowManualInputCheckboxClicked(view: View) {
        allowManualInput = (view as CheckBox).isChecked
    }

    fun onEnableAutoZoomCheckboxClicked(view: View) {
        enableAutoZoom = (view as CheckBox).isChecked
    }

    fun onScanButtonClicked(view: View) {
        val optionsBuilder = GmsBarcodeScannerOptions.Builder()
        if (allowManualInput) {
            optionsBuilder.allowManualInput()
        }
        if (enableAutoZoom) {
            optionsBuilder.enableAutoZoom()
        }
        val gmsBarcodeScanner = GmsBarcodeScanning.getClient(this, optionsBuilder.build())
        gmsBarcodeScanner
            .startScan()
            .addOnSuccessListener { barcode: Barcode ->
                barcodeResultView!!.text = getSuccessfulMessage(barcode)
            }
            .addOnFailureListener { e: Exception -> barcodeResultView!!.text = getErrorMessage(e) }
            .addOnCanceledListener {
                barcodeResultView!!.text = getString(R.string.error_scanner_cancelled)
            }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(KEY_ALLOW_MANUAL_INPUT, allowManualInput)
        savedInstanceState.putBoolean(KEY_ENABLE_AUTO_ZOOM, enableAutoZoom)
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        allowManualInput = savedInstanceState.getBoolean(KEY_ALLOW_MANUAL_INPUT)
        enableAutoZoom = savedInstanceState.getBoolean(KEY_ENABLE_AUTO_ZOOM)
    }

    private fun getSuccessfulMessage(barcode: Barcode): String {
        val barcodeValue =
            String.format(
                Locale.US,
                "Display Value: %s\nRaw Value: %s\nFormat: %s\nValue Type: %s",
                barcode.displayValue,
                barcode.rawValue,
                barcode.format,
                barcode.valueType
            )
        return getString(R.string.barcode_result,) + barcodeValue
    }

    private fun getErrorMessage(e: Exception): String? {
        return if (e is MlKitException) {
            when (e.errorCode) {
                MlKitException.CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED ->
                    getString(R.string.error_camera_permission_not_granted)
                MlKitException.CODE_SCANNER_APP_NAME_UNAVAILABLE ->
                    getString(R.string.error_app_name_unavailable)
                else -> getString(R.string.error_default_message)
            }
        } else {
            e.message
        }
    }

    companion object {
        private const val KEY_ALLOW_MANUAL_INPUT = "allow_manual_input"
        private const val KEY_ENABLE_AUTO_ZOOM = "enable_auto_zoom"
    }
}
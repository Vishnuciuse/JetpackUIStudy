package com.example.jetpackuistudy.whatsapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackuistudy.R
import android.os.Environment
import android.provider.ContactsContract.Directory
import android.widget.Toast
import java.io.File

class WhatsAppActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var statusAdapter: StatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_whats_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (checkPermissions()) {
            loadStatuses()
            loadStatuses1()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }
    }

    private fun loadStatuses() {
        val statusList = mutableListOf<Status>()
        val statusDirectory = File(Environment.getExternalStorageDirectory().absolutePath + "/WhatsApp/Media/.Statuses")
        if (statusDirectory.exists()) {
            val files = statusDirectory.listFiles()
            files?.forEach {
                if (it.name.endsWith(".jpg") || it.name.endsWith(".png")) {
                    statusList.add(Status(it, StatusType.IMAGE))
                } else if (it.name.endsWith(".mp4")) {
                    statusList.add(Status(it, StatusType.VIDEO))
                }
            }
        }
        statusAdapter = StatusAdapter(statusList, this)
        recyclerView.adapter = statusAdapter
    }

    private fun checkPermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }



    private fun loadStatuses1() {
        val statusList = mutableListOf<Status>()
        val statusDirectory = File(Environment.getExternalStorageDirectory().absolutePath + "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses")

        // Check if the directory exists
        if (statusDirectory.exists()) {
            val files = statusDirectory.listFiles()

            // Check if the directory is empty
            if (files != null && files.isNotEmpty()) {
                files.forEach {
                    if (it.name.endsWith(".jpg") || it.name.endsWith(".jpeg") || it.name.endsWith(".png")) {
                        statusList.add(Status(it, StatusType.IMAGE))
                    } else if (it.name.endsWith(".mp4")) {
                        statusList.add(Status(it, StatusType.VIDEO))
                    }
                }
            } else {
                showEmptyStatusMessage("No statuses found.")
            }
        } else {
            showEmptyStatusMessage("The .Statuses directory does not exist.")
        }

        // If statuses are found, set the adapter, otherwise handle the empty state
        if (statusList.isNotEmpty()) {
            statusAdapter = StatusAdapter(statusList, this)
            recyclerView.adapter = statusAdapter
        }
    }

    private fun showEmptyStatusMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        // Optionally, you can show a message in the UI if you prefer not to use a Toast
        // For example, set a TextView's visibility to VISIBLE and set the message
        // emptyTextView.text = message
        // emptyTextView.visibility = View.VISIBLE
    }

}

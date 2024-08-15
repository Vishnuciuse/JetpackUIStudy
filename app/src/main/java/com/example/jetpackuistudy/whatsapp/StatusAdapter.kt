package com.example.jetpackuistudy.whatsapp

import android.content.Context
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpackuistudy.R
import java.io.File

class StatusAdapter(private val statusList: List<Status>, private val context: Context) :
    RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_status, parent, false)
        return StatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val status = statusList[position]
        // Load image or video thumbnail using a library like Glide or Picasso
        Glide.with(context).load(status.file).into(holder.statusImage)
        holder.downloadButton.setOnClickListener {
            downloadStatus(status)
        }
    }

    override fun getItemCount(): Int = statusList.size

    private fun downloadStatus(status: Status) {
        val destination = File(Environment.getExternalStorageDirectory().absolutePath + "/WhatsAppStatusDownloader/")
        if (!destination.exists()) {
            destination.mkdir()
        }
        status.file.copyTo(File(destination, status.file.name), overwrite = true)
        Toast.makeText(context, "Downloaded!", Toast.LENGTH_SHORT).show()
    }

    class StatusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val statusImage: ImageView = view.findViewById(R.id.statusImage)
        val downloadButton: Button = view.findViewById(R.id.downloadButton)
    }
}

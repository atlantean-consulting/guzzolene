package com.guzzoline.app.util

import android.content.Context
import com.guzzoline.app.data.TripLogEntity
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CsvExporter {

    fun export(context: Context, entries: List<TripLogEntity>): File {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd_HHmmss", Locale.US)
        val logDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        val fileName = "guzzoline_trips_${dateFormat.format(Date())}.csv"
        val file = File(context.cacheDir, fileName)

        file.bufferedWriter().use { writer ->
            writer.write("Trip Name,Origin,Destination,Distance (mi),MPG,Gas Price ($/gal),Total Cost ($),Date")
            writer.newLine()
            for (entry in entries) {
                writer.write(
                    "${csvEscape(entry.tripName)},${csvEscape(entry.origin)},${csvEscape(entry.destination)}," +
                    "${entry.distance},${entry.mpg},${entry.gasPrice}," +
                    String.format(Locale.US, "%.2f", entry.totalCost) + "," +
                    logDateFormat.format(Date(entry.timestamp))
                )
                writer.newLine()
            }
        }
        return file
    }

    private fun csvEscape(value: String): String {
        return if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            "\"${value.replace("\"", "\"\"")}\""
        } else {
            value
        }
    }
}

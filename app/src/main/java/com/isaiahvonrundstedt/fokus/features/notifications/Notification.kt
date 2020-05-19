package com.isaiahvonrundstedt.fokus.features.notifications

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.isaiahvonrundstedt.fokus.R
import com.isaiahvonrundstedt.fokus.features.shared.components.converter.DateTimeConverter
import kotlinx.android.parcel.Parcelize
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import java.util.*

@Parcelize
@Entity(tableName = "notifications")
data class Notification @JvmOverloads constructor (
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var title: String? = null,
    var content: String? = null,
    var data: String? = null,
    var type: Int = typeReminder,
    var dateTimeTriggered: LocalDateTime? = null
): Parcelable {

    private fun getIconRes(): Int {
        return if (type == typeDueAlert) R.drawable.ic_custom_warning
        else R.drawable.ic_custom_clipboards
    }

    fun getIconDrawable(context: Context): Drawable? {
        val colorRes = if (type == typeReminder) R.color.colorIconReminder else R.color.colorIconWarning
        val drawable = ContextCompat.getDrawable(context, getIconRes())

        drawable?.mutate()
        drawable?.colorFilter = BlendModeColorFilterCompat
            .createBlendModeColorFilterCompat(ContextCompat.getColor(context, colorRes),
            BlendModeCompat.SRC_ATOP)
        return drawable
    }

    fun formatDateTime(): String {
        val currentDateTime = LocalDate.now()

        return if (dateTimeTriggered!!.toLocalDate().isEqual(currentDateTime))
            DateTimeFormat.forPattern(DateTimeConverter.timeFormat).print(dateTimeTriggered)
        else if (dateTimeTriggered!!.toLocalDate().year == currentDateTime.year)
            DateTimeFormat.forPattern("MMMM d").print(dateTimeTriggered!!)
        else DateTimeFormat.forPattern("MMMM d yyyy").print(dateTimeTriggered!!)
    }

    companion object {
        const val typeReminder = 0
        const val typeDueAlert = 1
    }
}
package com.isaiahvonrundstedt.fokus.features.core.data

import androidx.room.Embedded
import androidx.room.Relation
import com.isaiahvonrundstedt.fokus.features.attachments.Attachment
import com.isaiahvonrundstedt.fokus.features.subject.Subject
import com.isaiahvonrundstedt.fokus.features.task.Task

// This entity is used to show both the
// task, subject and the attachments of the task
// in a single entity
data class Core @JvmOverloads constructor (
    @Embedded
    var task: Task,
    @Embedded
    var subject: Subject,
    @Relation(entity = Attachment::class, parentColumn = "taskID", entityColumn = "taskID")
    var attachmentList: List<Attachment> = emptyList()
)
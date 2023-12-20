package com.original.sense.psit.model


data class AssignedLectureModel(
    val permission_id: String,
    val lecture: List<Int> ,
    val assignedBy: String
)

data class AssignedLecture(
    val permission_id: String,
    val lecture: Int ,
    val assignedBy: String
)
package com.original.sense.psit.model

import com.original.sense.psit.model.ResponseModel.AssignedBy


data class AssignedLectureModel(
    val permission_id: String,
    val lecture: List<Int> ,
    val assignedBy: String,
    val assignedAt : String,
    val description : String,
    val students : List<Long>,
    val subject : String,
    val from : String,
    val to : String
)

data class AssignedLecture(
    val permission_id: String,
    val lecture: Int ,
    val assignedBy: String,
    val assignedAt : String,
    val description : String,
    val students : List<Long>,
    val subject : String,
    val from : String,
    val to : String
)



//data class ResponseDataPermission(
//    val assigned_at: String ,
//    val assigned_by: AssignedBy ,
//    val description: String ,
//    val from_date: String ,
//    val lectures: List<Int> ,
//    val permission_id: String ,
//    val students: List<Long> ,
//    val subject: String ,
//    val to_date: String ,
//    val type: String
//)
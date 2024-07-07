package projectapi

import com.google.gson.annotations.SerializedName

data class ProjectResponse(@SerializedName("status") var status : String, @SerializedName("message") var images : List<String>)

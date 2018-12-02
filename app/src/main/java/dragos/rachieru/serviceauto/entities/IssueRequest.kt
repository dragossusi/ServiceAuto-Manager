package dragos.rachieru.serviceauto.entities

import com.google.gson.annotations.SerializedName
import dragos.rachieru.serviceauto.entities.enums.RequestStatus
import java.util.*

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
class IssueRequest(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("issue")
    val issue: String,
    @SerializedName("car")
    val car: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("created")
    val created: Date,
    @SerializedName("status")
    var status: RequestStatus
)
package dragos.rachieru.serviceauto.api

import com.google.gson.annotations.SerializedName
import dragos.rachieru.serviceauto.entities.enums.RequestStatus

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
class StatusChangeBody(
    @SerializedName("status")
    val status: RequestStatus
)
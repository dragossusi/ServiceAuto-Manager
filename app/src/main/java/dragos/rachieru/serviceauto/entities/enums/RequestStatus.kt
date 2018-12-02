package dragos.rachieru.serviceauto.entities.enums

import com.squareup.moshi.Json

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
enum class RequestStatus {
    @Json(name = "pending")
    PENDING,
    @Json(name = "ongoing")
    ONGOING,
    @Json(name = "completed")
    COMPLETED,
    @Json(name = "revoked")
    REVOKED
}
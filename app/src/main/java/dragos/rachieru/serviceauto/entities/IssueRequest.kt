package dragos.rachieru.serviceauto.entities

import dragos.rachieru.serviceauto.entities.enums.RequestStatus
import java.util.*

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
class IssueRequest(
    val name: String,
    val phone: String,
    val issue: String,
    val car: String,
    val model: String,
    val created: Date,
    var status: RequestStatus
)
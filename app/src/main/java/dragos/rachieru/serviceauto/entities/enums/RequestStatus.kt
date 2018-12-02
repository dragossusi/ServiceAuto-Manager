package dragos.rachieru.serviceauto.entities.enums

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import dragos.rachieru.serviceauto.R

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
enum class RequestStatus(@StringRes val stringId: Int) {
    @SerializedName("pending")
    PENDING(R.string.pending),
    @SerializedName("ongoing")
    ONGOING(R.string.ongoing),
    @SerializedName("completed")
    COMPLETED(R.string.completed),
    @SerializedName("revoked")
    REVOKED(R.string.revoked)
}
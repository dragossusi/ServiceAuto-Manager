package dragos.rachieru.serviceauto.status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import dragos.rachieru.serviceauto.R
import dragos.rachieru.serviceauto.entities.enums.RequestStatus
import kotlinx.android.synthetic.main.list_status.*

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
class StatusChangeDialog : DialogFragment(), View.OnClickListener {

    var listener: ((RequestStatus) -> Unit)? = null
    var status: RequestStatus? = null

    override fun onClick(p0: View) {
        status?.run {
            when (p0.id) {
                R.id.text_pending -> prel(RequestStatus.PENDING)
                R.id.text_ongoing -> prel(RequestStatus.ONGOING)
                R.id.text_completed -> prel(RequestStatus.COMPLETED)
                R.id.text_revoked -> prel(RequestStatus.REVOKED)
            }
        }
        dismiss()
    }

    fun prel(status: RequestStatus) {
        listener?.run {
            if (status != this@StatusChangeDialog.status)
                invoke(status)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text_pending.setOnClickListener(this)
        text_ongoing.setOnClickListener(this)
        text_completed.setOnClickListener(this)
        text_revoked.setOnClickListener(this)
    }

}
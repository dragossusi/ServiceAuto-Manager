package dragos.rachieru.serviceauto.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dragos.rachieru.serviceauto.R
import dragos.rachieru.serviceauto.entities.IssueRequest
import dragos.rachieru.serviceauto.extensions.inflate
import kotlinx.android.synthetic.main.item_request.view.*

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
class RequestHolder(parent: ViewGroup,
                    val removeItem: RequestStatusChanger
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_request)), View.OnClickListener {

    var request:IssueRequest?=null

    override fun onClick(p0: View) {
        request?.let{
            removeItem.changeAt(it,adapterPosition)
        }
    }

    init {
        itemView.text_status.setOnClickListener(this)
    }

    fun bind(request: IssueRequest) {
        this.request = request
        itemView.text_name.text = request.name
        itemView.text_phone.text = request.phone
        itemView.text_status.setText(request.status.stringId)
        itemView.text_car_details.text = "${request.car} ${request.model}"
        itemView.text_issue.text = request.issue
    }

}
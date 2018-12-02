package dragos.rachieru.serviceauto.api

import dragos.rachieru.serviceauto.entities.IssueRequest
import io.reactivex.Single
import retrofit2.http.GET

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
interface ServiceAutoManagerApi {
    @GET("requests")
    fun getRequest(): Single<MutableList<IssueRequest>>
}
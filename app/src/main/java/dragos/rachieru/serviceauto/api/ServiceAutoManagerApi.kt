package dragos.rachieru.serviceauto.api

import dragos.rachieru.serviceauto.entities.IssueRequest
import dragos.rachieru.serviceauto.entities.LoginBody
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
interface ServiceAutoManagerApi {

    @POST("login")
    fun login(@Body body: LoginBody): Single<Boolean>

    @GET("requests")
    fun getRequests(@Query("status") status: String? = null): Single<MutableList<IssueRequest>>

    @PUT("requests/{requestId}")
    fun setStatus(@Path("requestId") id: String,
                  @Body statusBody: IssueRequest
    ): Completable
}
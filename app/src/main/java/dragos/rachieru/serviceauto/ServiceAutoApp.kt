package dragos.rachieru.serviceauto

import android.app.Application
import dragos.rachieru.serviceauto.api.ServiceAutoManagerApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ServiceAuto by Imbecile Games
 *
 * @author Dragos
 * @since 02.12.2018
 */
class ServiceAutoApp : Application() {

    lateinit var api: ServiceAutoManagerApi

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://teamp.go.ro:3000")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
//            .client(
//                OkHttpClient.Builder()
//                    .addInterceptor(HttpLoggingInterceptor())
//                    .build()
//            )
            .build()
        api = retrofit.create(ServiceAutoManagerApi::class.java)
    }
}

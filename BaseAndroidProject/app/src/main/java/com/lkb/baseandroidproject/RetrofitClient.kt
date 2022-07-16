import com.lkb.baseandroidproject.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private const val baseUrl = "" + BuildConfig.BASE_URL
    fun getInstance(): Retrofit {
        val httpClient = OkHttpClient().newBuilder()
        httpClient.connectTimeout(5000L, TimeUnit.MILLISECONDS)
        httpClient.readTimeout(5000L, TimeUnit.MILLISECONDS)
        httpClient.writeTimeout(5000L, TimeUnit.MILLISECONDS)
        val builder = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        return builder.client(httpClient.build()).build()
    }
}
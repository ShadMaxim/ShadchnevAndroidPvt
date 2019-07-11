package by.itacademy.myapp.dz9

import by.itacademy.myapp.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetProvaider {

    private var api: Api? = null

    fun providerGson(): Gson {
        val gson = GsonBuilder()
            .create()

        return gson
    }

    fun gprovidertOkHttp(): OkHttpClient {

        val okHttpBulder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) { // dlia razrabotki
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpBulder.addInterceptor(logging)
        }
        // okHttpBulder.connectTimeout(1, TimeUnit.SECONDS) выходит если не отвеч больше 1 сек

        val okHttpClient = okHttpBulder.build()
        return okHttpClient
    }

    fun gprovidertRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }

    fun gprovidertApi(retrofit: Retrofit): Api {

        if (api == null) {
            api = retrofit.create<Api>(Api::class.java)
        }
        return api!!
    }
}
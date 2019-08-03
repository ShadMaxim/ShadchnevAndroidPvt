package by.itacademy.myapp.dz12.student.model.provider

import by.itacademy.myapp.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Dz12NetProvider {

    private var studentApi: Dz12StudentApi? = null

    fun providerGson(): Gson {
        val gson = GsonBuilder()
            .create()

        return gson
    }

    fun providertOkHttp(): OkHttpClient {

        val okHttpBulder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) { // dlia razrabotki
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpBulder.addInterceptor(logging)
        }

        // okHttpBulder.connectTimeout(1, TimeUnit.SECONDS)   // выходит если не отвеч больше 1 сек

        val okHttpClient = okHttpBulder.build()

        return okHttpClient
    }

    fun providertRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Rx
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }

        fun providertStudentApi(retrofit: Retrofit): Dz12StudentApi {

        if (studentApi == null) {
            studentApi = retrofit.create<Dz12StudentApi>(
                Dz12StudentApi::class.java)
        }
        return studentApi!!
    }
}
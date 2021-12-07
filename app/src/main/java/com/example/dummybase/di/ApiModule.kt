package com.example.dummybase.di

import com.example.dummybase.BuildConfig
import com.example.dummybase.DummyBaseApp
import com.example.dummybase.api.ApiClient
import com.example.dummybase.data.model.converter.InstantConverter
import com.example.dummybase.utils.SerializeNulls
import com.example.dummybase.api.ApiService
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Request


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    val moshi: Moshi = Moshi.Builder()
        .add(SerializeNulls.JSON_ADAPTER_FACTORY)
        .add(InstantConverter())
        .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = moshi

    @Provides
    @Singleton
    fun provideApiService(): ApiService {

        //val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvd3d3Lndpa2ktamFyZGluLmZyXC9pbmRleC5waHBcL2FwaVwvbG9naW4iLCJpYXQiOjE2Mzg0NTQzNDMsImV4cCI6MTYzODQ1Nzk0MywibmJmIjoxNjM4NDU0MzQzLCJqdGkiOiJXU3JZakZsMkpWdVVjU0xEIiwic3ViIjoxLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.gSzhU5i44uZDivS2OK3XYh8-xCIovqkvACXAr6VzT3c";
        val httpClient = OkHttpClient.Builder()
        /*.addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()

            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Authorization", "Bearer " + token ) // <-- this is the important line
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        })*/
            .addNetworkInterceptor(FlipperOkhttpInterceptor(DummyBaseApp.networkFlipperPlugin))
            .build()

        return getRetrofitAdapter(httpClient, BuildConfig.CUVERIE_API_URL)
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiClient(apiService: ApiService): ApiClient =
        ApiClient(apiService)

    private fun getRetrofitAdapter(okHttpClient: OkHttpClient, url: String): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}
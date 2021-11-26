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
        val httpClient = OkHttpClient.Builder()
            // .addNetworkInterceptor(FlipperOkhttpInterceptor(DummyBaseApp.networkFlipperPlugin))
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
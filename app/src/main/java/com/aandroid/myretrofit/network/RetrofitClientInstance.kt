package com.aandroid.myretrofit.network

import com.aandroid.myretrofit.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClientInstance {

    companion object {

        private var retrofit: Retrofit? = null

        fun getRetrofitInstance(): Retrofit? {

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

            return retrofit
        }

    }
}
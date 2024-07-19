package com.example.premierleaguefixtures


import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


// Flow is called from the MainActivity
class Flow {

    fun fetchItems(): Flow<List<MatchItem>> = flow {

        //getting Retrofit response
        val response: Response<List<MatchItem>> = try {
            RetrofitInstance.api.getData()
        } catch (e: IOException) {
            Log.e(TAG, "onCreate: IOException, you might not have internet connection")
            return@flow
        } catch (e: HttpException) {
            Log.e(TAG, "onCreate: HttpException, unexpected response")
            return@flow
        } catch (e: Exception) {
            Log.e(TAG, "OnCreate: unidentified exception")
            return@flow
        }

        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!)
            Log.d(TAG, response.body().toString())
        } else {
            Log.e(TAG, "Response not successful")
        }
    }
}
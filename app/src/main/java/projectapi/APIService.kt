package projectapi

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getByRazas(@Url url: String) : Response<ProjectResponse>


}
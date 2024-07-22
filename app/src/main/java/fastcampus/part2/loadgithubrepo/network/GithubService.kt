package fastcampus.part2.loadgithubrepo.network

import fastcampus.part2.loadgithubrepo.model.Repo
import fastcampus.part2.loadgithubrepo.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    //깃허브 토큰 추가
    @Headers("Authorization: Bearer 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")

    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repo>>

    // search users api
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>


}
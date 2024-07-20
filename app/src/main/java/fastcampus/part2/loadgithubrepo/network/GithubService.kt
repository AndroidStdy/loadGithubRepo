package fastcampus.part2.loadgithubrepo.network

import fastcampus.part2.loadgithubrepo.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repo>>
}
package fastcampus.part2.loadgithubrepo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fastcampus.part2.loadgithubrepo.model.Repo
import fastcampus.part2.loadgithubrepo.model.UserDto
import fastcampus.part2.loadgithubrepo.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // githubService 구현
        val githubService = retrofit.create(GithubService::class.java)
        // api 호출
        githubService.listRepos("square").enqueue(object: Callback<List<Repo>>{
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                //성공할 경우
                Log.e("MainActivity", "List Repo : ${response.body().toString()}")
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                //실패할 경우

            }

        })
        githubService.searchUsers("squar").enqueue(object: Callback<UserDto>{
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                //성공할 경우
                Log.e("MainActivity", "Search User : ${response.body().toString()}")
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                //실패할 경우
            }

        })
    }
}
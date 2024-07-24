package fastcampus.part2.loadgithubrepo

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part2.loadgithubrepo.adapter.RepoAdapter
import fastcampus.part2.loadgithubrepo.databinding.ActivityRepoBinding
import fastcampus.part2.loadgithubrepo.model.Repo
import fastcampus.part2.loadgithubrepo.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoAdapter: RepoAdapter

    private var page = 0
    private var hasMore = true

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username") ?: return

        binding.tvUsername.text = username

        repoAdapter = RepoAdapter()
        val linearLayoutManager = LinearLayoutManager(this@RepoActivity)

        binding.repoRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = repoAdapter
        }

        // 스크롤 되는걸 이벤트로 받음
        binding.repoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = linearLayoutManager.itemCount //linearlayout이 받아온 item 총 개수
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()

                if (lastVisiblePosition >= (totalCount - 1) && hasMore) {

                    // 다음 페이지를 불러옴
                    page += 1
                    listRepo(username, page)
                }
            }
        })

        listRepo(username, 0)
    }

    private fun listRepo(username: String, page: Int) {
        val githubService = retrofit.create(GithubService::class.java)
        // api 호출
        githubService.listRepos(username, page).enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                //성공할 경우
                Log.e("MainActivity", "List Repo : ${response.body().toString()}")
                response.body()?.count() == 30
                //무한 스크롤 구현
                repoAdapter.submitList(repoAdapter.currentList + response.body().orEmpty())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                //실패할 경우

            }

        })
    }

}
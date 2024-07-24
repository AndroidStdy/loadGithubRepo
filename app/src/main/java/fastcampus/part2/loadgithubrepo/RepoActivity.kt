package fastcampus.part2.loadgithubrepo

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import fastcampus.part2.loadgithubrepo.databinding.ActivityRepoBinding

class RepoActivity: AppCompatActivity() {

    private lateinit var  binding: ActivityRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
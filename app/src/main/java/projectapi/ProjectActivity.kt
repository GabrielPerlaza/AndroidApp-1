package projectapi

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager.Query
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea7.R
import com.example.tarea7.databinding.ActivityProjectBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class ProjectActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding : ActivityProjectBinding
    private lateinit var svProjectApi : SearchView
    private lateinit var adapter : ProjectAdapter
    private val projectImages: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        svProjectApi = binding.sVProjectApi
        svProjectApi.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ProjectAdapter(projectImages)
        binding.rvProject.layoutManager = LinearLayoutManager(this)
        binding.rvProject.adapter  = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val llamada = getRetrofit().create(APIService::class.java).getByRazas("$query/images")
            if(llamada.isSuccessful){
                val perritos = llamada.body()
                val images = perritos?.images ?: emptyList()
                withContext(Dispatchers.Main) {
                    projectImages.clear()
                    projectImages.addAll(images)
                    adapter.notifyDataSetChanged()
                }

            }else {
                mostrarError("Ocurrio un error")
            }
        }
    }

    private suspend fun mostrarError(mensaje: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(this@ProjectActivity, mensaje, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}
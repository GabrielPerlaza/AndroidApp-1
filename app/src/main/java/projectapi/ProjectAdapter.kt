package projectapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea7.R
import com.squareup.picasso.Picasso

class ProjectAdapter(private val image: List<String>): RecyclerView.Adapter<ProjectViewFolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewFolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProjectViewFolder(layoutInflater.inflate(R.layout.item2, parent, false))
    }

    override fun getItemCount(): Int {
        return image.size
    }

    override fun onBindViewHolder(holder: ProjectViewFolder, position: Int) {
        val item = image[position]
        holder.bind(item)
    }
    }


package projectapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea7.databinding.Item2Binding
import com.squareup.picasso.Picasso

class ProjectViewFolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = Item2Binding.bind(view)

     fun bind(image:String){
        Picasso.get().load(image).into(binding.ivProject)
    }

}
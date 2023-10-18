// Lee Jra., Kim Williame (S12)
package com.mobdeve.mynotesapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mobdeve.mynotesapplication.databinding.ItemLayoutBinding

/*
*   Do not modify the parameters of the MyAdapter class. You'll need the viewNoteLauncher instance
*   from the MainActivity here.
* */
class MyAdapter(private val data: ArrayList<NoteModel>, private val viewNoteLauncher: ActivityResultLauncher<Intent>) : Adapter<MyViewHolder>() {
    // These are static keys that you can use for the passing data around.
    companion object {
        const val titleKey : String = "TITLE_KEY"
        const val bodyKey : String = "BODY_KEY"
        const val positionKey : String = "POSITION_KEY"
    }

    /*
    *   This method does not need to be modified; however, you may modify it according to how you'd
    *   like to solve the problem.
    * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemViewBinding: ItemLayoutBinding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(itemViewBinding)
    }

    /*
    *   TODO: Complete the onBindViewHolder logic.
    * */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ViewNoteActivity::class.java)
            intent.putExtra(titleKey, data[position].title)
            intent.putExtra(bodyKey, data[position].body)
            intent.putExtra(positionKey, position)
            viewNoteLauncher.launch(intent)
        }
        holder.bindData(data[position])
    }

    /*
    *   TODO: Complete the onBindViewHolder logic.
    * */
    override fun getItemCount(): Int {
        return data.size
    }
}
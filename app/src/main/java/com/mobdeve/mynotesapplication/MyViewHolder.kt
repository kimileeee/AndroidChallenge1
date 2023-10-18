// Lee Jra., Kim Williame (S12)
package com.mobdeve.mynotesapplication

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mobdeve.mynotesapplication.databinding.ItemLayoutBinding

/*
*   TODO: Make sure to specify the parameters needed by the ViewHolder super class. These are not
*       specified here because you can either use the ViewBinding instance of the item or the actual
*       instance of the itemView.
* */
class MyViewHolder(private var itemBinding: ItemLayoutBinding) : ViewHolder(itemBinding.root) {
    /*
    *   bindData() takes a single NoteModel instance and binds its data to the views in the
    *       ViewHolder. You can remove this in favor of implementing setters, but a single method
    *       is cleaner.
    * */
    fun bindData(noteData : NoteModel) {
        itemBinding.itemTitle.text = noteData.title // Assign to the edit text for the title
        itemBinding.itemBody.text = noteData.body.replace("\n", " ") // Assign to the edit text for the body
        // Note: Do not replace the .replace() method as this strips the new line character
    }
}
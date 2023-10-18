// Lee Jra., Kim Williame (S12)
package com.mobdeve.mynotesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.mynotesapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Do not remove these three variables
    private lateinit var data: ArrayList<NoteModel>         // Holds the data for the app
    private lateinit var myAdapter: MyAdapter               // Holds the adapter for the RecyclerView
    private lateinit var viewBinding: ActivityMainBinding   // Holds the views of the ActivityMainBinding

    /*
    *   TODO: Complete the viewNoteLauncher logic. The code below should handle making changes to the
    *       data ArrayList in case the "Save Changes" button was clicked. If none, then nothing should
    *       happen.
    * */
    private val viewNoteLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val position = result.data!!.getIntExtra(ViewNoteActivity.positionKey, 0)
            val title = result.data!!.getStringExtra(ViewNoteActivity.titleKey)?:""
            val body = result.data!!.getStringExtra(ViewNoteActivity.bodyKey)?:""

            data.set(position, NoteModel(title, body))
            myAdapter.notifyItemChanged(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        *   Please note that this application implementing ViewBinding. If you don't want to use
        *   ViewBinding, then please make sure to modify the buildFeatures attribute in the
        *   build.grade (app) to false and then modify the next two lines accordingly.
        * */
        this.viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.viewBinding.root)

        // This loads the note data into the data attribute.
        this.data = DataHelper.generateData()

        /*
        *   TODO: Provide the logic for setting up the RecyclerView.
        * */
        val recyclerView : RecyclerView = this.viewBinding.recyclerView
        myAdapter = MyAdapter(this.data, viewNoteLauncher)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
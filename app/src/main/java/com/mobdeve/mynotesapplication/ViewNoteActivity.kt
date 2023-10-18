// Lee Jra., Kim Williame (S12)
package com.mobdeve.mynotesapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.mobdeve.mynotesapplication.databinding.ActivityViewNoteBinding

class ViewNoteActivity : AppCompatActivity() {
    // These are static keys that you can use for the passing data around.
    companion object {
        const val titleKey : String = "TITLE_KEY"
        const val bodyKey : String = "BODY_KEY"
        const val positionKey: String = "POSITION_KEY"
    }

    /*
    *   Please use these two variables to keep track of the original content of the note. These are
    *   also used in the isTextStillOriginal(). You might want to assign the data from the incoming
    *   intent to these variables.
    * */
    private lateinit var titleString: String
    private lateinit var bodyString: String

    private lateinit var viewBinding: ActivityViewNoteBinding   // Holds the views of the ActivityViewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        *   Please note that this application implementing ViewBinding. If you don't want to use
        *   ViewBinding, then please make sure to modify the buildFeatures attribute in the
        *   build.grade (app) to false and then modify the next two lines accordingly.
        * */
        this.viewBinding = ActivityViewNoteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        /*
        *   TODO: Complete the rest of the logic for ViewNoteActivity
        * */
        titleString = this.intent.getStringExtra(titleKey)?:""
        bodyString = this.intent.getStringExtra(bodyKey)?:""
        val position = this.intent.getIntExtra(positionKey, 0)

        viewBinding.titleEtv.setText(titleString)
        viewBinding.bodyEtv.setText(bodyString)
        viewBinding.saveBtn.isEnabled = false

        viewBinding.titleEtv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                viewBinding.saveBtn.isEnabled = !isTextStillOriginal()
            }
        })

        viewBinding.bodyEtv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                viewBinding.saveBtn.isEnabled = !isTextStillOriginal()
            }
        })

        viewBinding.saveBtn.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(titleKey, viewBinding.titleEtv.text.toString())
            resultIntent.putExtra(bodyKey, viewBinding.bodyEtv.text.toString())
            resultIntent.putExtra(positionKey, position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

    }

    /*
    *   isTextStillOriginal() is a helper function that can be used when determining if there are
    *   any changes to either the title or body edit text. Please note that this uses the
    *   viewBinding approach. If you're not planning to use ViewBinding, please make the appropriate
    *   changes.
    * */
    private fun isTextStillOriginal() : Boolean {
        return (this.viewBinding.titleEtv.text.toString() == titleString) and (this.viewBinding.bodyEtv.text.toString() == bodyString)
    }
}
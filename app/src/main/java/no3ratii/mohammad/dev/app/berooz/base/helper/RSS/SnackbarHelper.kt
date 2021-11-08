import android.view.View
import com.google.android.material.snackbar.Snackbar

abstract class SnackbarHelper(var title: String ,var clickTitle : String, var view: View) {

    abstract fun onClick(it: View)

    fun initByClickable() {
        val snackbar: Snackbar = Snackbar.make(view, title, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(clickTitle,
            View.OnClickListener {
                onClick(it)
                snackbar.dismiss()
            })
        snackbar.show()
    }

    fun initNoneClickable() {
        val snackbar: Snackbar = Snackbar.make(view, title, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}
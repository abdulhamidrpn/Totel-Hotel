package com.rpn.totel.ui.fragment.base

//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.fragment_song_control.view.*
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.rpn.totel.utils.SettingsUtility
import org.koin.android.ext.android.inject


//open class BaseFragment<T : Any> : CoroutineFragment() {
open class BaseFragment : CoroutineFragment() {

    protected lateinit var dialog: AlertDialog

    protected val settingsUtility by inject<SettingsUtility>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}


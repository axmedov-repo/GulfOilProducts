package com.gulfoil.pdsapp.activity

import android.content.Context
import android.content.IntentFilter
import android.graphics.Rect
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.utils.CheckInternetReceiver
import com.gulfoil.pdsapp.utils.gone
import com.gulfoil.pdsapp.utils.isConnected
import com.gulfoil.pdsapp.utils.timber
import com.gulfoil.pdsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var internetReconnectedListener: (() -> Unit)? = null
fun setInternetReconnectedListener(f: () -> Unit) {
    internetReconnectedListener = f
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val checkInternetReceiver = CheckInternetReceiver()
    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModelImpl>()
    private var isStartListening: Boolean = !isConnected()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        viewModel.internetConnectionLiveData.observe(this, internetConnectionObserver)
        viewModel.showMessageOnTopOfScreenLiveData.observe(this, showMessageOnTopOfScreenObserver)

        setNavigationComponent()
    }

    override fun onResume() {
        super.onResume()
        isStartListening = false
    }

    private val internetConnectionObserver = Observer<Boolean> { internetConnected ->
        timber("Called internetConnectionObserver. Result=${internetConnected}", "sdjskjdksjd")

        val internetConnectionLayout = findViewById<LinearLayoutCompat>(R.id.internetConnectionLayout)
        val internetConnectionText = findViewById<TextView>(R.id.internetConnectionText)

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        if (internetConnected) {
            if (isStartListening) {
                internetReconnectedListener?.invoke()

                CoroutineScope(Dispatchers.Main).launch {
                    internetConnectionLayout.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.orange
                        )
                    )
                    internetConnectionText.text = getString(
                        if (viewModel.appLanguage == Languages.ENGLISH) R.string.successfully_online_en
                        else R.string.successfully_online_ru
                    )
                    window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.orange)
                    internetConnectionLayout.visible()

                    delay(1500L)

                    internetConnectionLayout.gone()
                    window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)
                }
//                internetConnectionLayout.gone()
//                window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)
            }
        } else {
            internetConnectionLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.sky_blue))
            internetConnectionText.text = getString(
                if (viewModel.appLanguage == Languages.ENGLISH) R.string.no_internet_en
                else R.string.no_internet_ru
            )
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.sky_blue)
            internetConnectionLayout.visible()
        }
        isStartListening = true
    }

    private val showMessageOnTopOfScreenObserver = Observer<String> { message ->
        val internetConnectionLayout = findViewById<LinearLayoutCompat>(R.id.internetConnectionLayout)
        val internetConnectionText = findViewById<TextView>(R.id.internetConnectionText)

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        CoroutineScope(Dispatchers.Main).launch {
            internetConnectionLayout.setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.sky_blue
                )
            )
            internetConnectionText.text = message
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.sky_blue)
            internetConnectionLayout.visible()

            delay(3000L)

            internetConnectionLayout.gone()
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)
        }
    }

    private fun setNavigationComponent() {
        val navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val graph = navHost.navController.navInflater.inflate(R.navigation.app_nav)
        navHost.navController.graph = graph
    }

    // For hiding keyboard when focus changed
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v: View? = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onStart() {
        val intent = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(checkInternetReceiver, intent)
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(checkInternetReceiver)
    }
}

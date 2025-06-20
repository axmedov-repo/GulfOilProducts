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
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.enums.MessageType
import com.gulfoil.pdsapp.utils.connection.CheckInternetReceiver
import com.gulfoil.pdsapp.utils.connection.isConnected
import com.gulfoil.pdsapp.utils.gone
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
        viewModel.showMessageLiveData.observe(this, showMessageObserver)

        setNavigationComponent()
    }

    override fun onResume() {
        super.onResume()
        isStartListening = false
    }

    private val internetConnectionObserver = Observer<Boolean> { internetConnected ->
        timber("Called internetConnectionObserver. Result=${internetConnected}", "sdjskjdksjd")

        val topBarLayout = findViewById<LinearLayoutCompat>(R.id.topBarLayout)
        val topBarText = findViewById<TextView>(R.id.topBarText)

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        if (internetConnected) {
            if (isStartListening) {
                internetReconnectedListener?.invoke()

                CoroutineScope(Dispatchers.Main).launch {
                    topBarLayout.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.orange
                        )
                    )
                    topBarText.text = getString(
                        if (viewModel.appLanguage == Languages.RUSSIAN) R.string.successfully_online_ru
                        else R.string.successfully_online_en
                    )
                    window.statusBarColor =
                        ContextCompat.getColor(this@MainActivity, R.color.orange)
                    topBarLayout.visible()

                    delay(1500L)

                    topBarLayout.gone()
                    window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)
                }
//                topBarLayout.gone()
//                window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)
            }
        } else {
            topBarLayout.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.sky_blue
                )
            )
            topBarText.text = getString(
                if (viewModel.appLanguage == Languages.RUSSIAN) R.string.no_internet_ru
                else R.string.no_internet_en
            )
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.sky_blue)
            topBarLayout.visible()
        }
        isStartListening = true
    }

    private val showMessageObserver = Observer<Triple<String, MessageType, () -> Unit>> { triple ->
        val message = triple.first
        val messageType = triple.second
        val returnAction = triple.third

        if (message.isEmpty()) return@Observer

        when (messageType) {
            MessageType.TOP_BANNER -> {
                val topBarLayout = findViewById<LinearLayoutCompat>(R.id.topBarLayout)
                val topBarText = findViewById<TextView>(R.id.topBarText)

                val window: Window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

                CoroutineScope(Dispatchers.Main).launch {
                    topBarLayout.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.sky_blue
                        )
                    )
                    topBarText.text = getProperMessageForResponse(message)

                    window.statusBarColor =
                        ContextCompat.getColor(this@MainActivity, R.color.sky_blue)
                    topBarLayout.visible()

                    delay(3000L)

                    topBarLayout.gone()
                    window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)
                }
            }

            MessageType.SEPARATE_SCREEN -> {
                val layoutMessage = findViewById<ConstraintLayout>(R.id.layoutMessage)
                val txtMessage = findViewById<TextView>(R.id.txtMessage)
                val btnTryAgain = findViewById<AppCompatButton>(R.id.btnTryAgain)

                layoutMessage.visible()
                txtMessage.text = getProperMessageForResponse(message)

                btnTryAgain.setOnClickListener {
                    returnAction()
                    layoutMessage.gone()
                }
            }
        }
    }

    private fun getProperMessageForResponse(message: String): String {
        return if (message.length == 3) {
            when (message.first()) {
                '1' -> getString(
                    if (viewModel.appLanguage == Languages.RUSSIAN) R.string.informational_error_ru
                    else R.string.informational_error_en
                )

                '3' -> getString(
                    if (viewModel.appLanguage == Languages.RUSSIAN) R.string.redirection_error_ru
                    else R.string.redirection_error_en
                )

                '4' -> getString(
                    if (viewModel.appLanguage == Languages.RUSSIAN) R.string.user_error_ru
                    else R.string.user_error_en
                )

                '5' -> getString(
                    if (viewModel.appLanguage == Languages.RUSSIAN) R.string.server_error_ru
                    else R.string.server_error_en
                )

                else -> message
            }
        } else {
            message
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

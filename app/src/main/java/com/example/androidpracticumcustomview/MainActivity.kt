package com.example.androidpracticumcustomview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.androidpracticumcustomview.ui.theme.CustomContainer
import com.example.androidpracticumcustomview.ui.theme.MainScreen
import com.example.androidpracticumcustomview.ui.util.Constants.DELAY_BEFORE_ADD_SECOND_CHILD

/*
Задание:
Реализуйте необходимые компоненты.
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        Раскомментируйте нужный вариант
         */
//        startXmlPracticum() // «традиционный» android (XML)
        setContent { // Jetpack Compose
            MainScreen()
        }
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(this)
        setContentView(customContainer)

        val firstView = TextView(this).apply {
            textSize = resources.getDimension(R.dimen.first_child_font_size)
            text = resources.getString(R.string.first_child_text)
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.purple_700))
        }

        customContainer.addView(firstView)

        val secondView = TextView(this).apply {
            textSize = resources.getDimension(R.dimen.second_child_font_size)
            text = resources.getString(R.string.second_child_text)
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.red))
        }

        // Добавление второго элемента через некоторое время
        Handler(Looper.getMainLooper()).postDelayed({
            customContainer.addView(secondView)
        }, DELAY_BEFORE_ADD_SECOND_CHILD)
    }
}
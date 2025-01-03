package com.example.androidpracticumcustomview.ui.theme

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.children

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */

@SuppressLint("ResourceAsColor")
class CustomContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    init {
        setWillNotDraw(false)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        var coordY = (height / 2).toFloat()
        val centerX = (width / 2).toFloat()
        var targetYAnimation = 0f

        children.forEachIndexed { index, child ->
            val coordX = centerX - child.width / 2
            if (index == 0) coordY -= child.height.toFloat()

            if (index > 0 || children.count() < 2) {
                child.x = coordX
                child.y = coordY
                child.alpha = 0f
                if (index > 0) targetYAnimation = height.toFloat() - child.height.toFloat()
                animateChild(child, targetYAnimation)
            }
            coordY += child.height.toFloat()
        }
    }

    override fun addView(child: View) {
        super.addView(child)
        if (childCount > 2) error("IllegalStateException")
    }

    private fun animateChild (child: View, targetY: Float) {
        val animAlpha = ObjectAnimator.ofFloat(child, "alpha", 1f)
        animAlpha.duration = 2000
        val animYOffset = ObjectAnimator.ofFloat(child, "y", targetY)
        animYOffset.duration = 5000

        val animatorSet = AnimatorSet()
        animatorSet.play(animAlpha).with(animYOffset)
        animatorSet.start()
    }
}
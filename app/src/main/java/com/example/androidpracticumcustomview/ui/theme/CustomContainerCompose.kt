package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.Layout
import com.example.androidpracticumcustomview.ui.util.Constants.ANIMATION_LENGTH_ALPHA_COMPOSE
import com.example.androidpracticumcustomview.ui.util.Constants.ANIMATION_LENGTH_MOVE_Y_COMPOSE
import kotlinx.coroutines.launch

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */

@Composable
fun CustomContainerCompose(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    val targetOffsetYFirstChild = remember { mutableFloatStateOf(0f) }
    val targetOffsetYSecondChild = remember { mutableFloatStateOf(0f) }
    val offsetYFirstChild = remember { Animatable(0f) }
    val offsetYSecondChild = remember { Animatable(0f) }
    val alphaAnimation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        launch {
            offsetYFirstChild.animateTo(
                targetValue = targetOffsetYFirstChild.floatValue,
                animationSpec = tween(ANIMATION_LENGTH_MOVE_Y_COMPOSE)
            )
        }
        launch {
            offsetYSecondChild.animateTo(
                targetValue = targetOffsetYSecondChild.floatValue,
                animationSpec = tween(ANIMATION_LENGTH_MOVE_Y_COMPOSE)
            )
        }
        launch {
            alphaAnimation.animateTo(1f, animationSpec = tween(ANIMATION_LENGTH_ALPHA_COMPOSE))
        }
    }

    Layout(
        modifier = modifier.alpha(alphaAnimation.value),
        content = content
    ) { measurables, constraints ->
        if (measurables.count() > 2) throw IllegalStateException("Not more 2 children")
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            val centerY = constraints.maxHeight / 2
            val centreX = constraints.maxWidth / 2

            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(
                    x = centreX - placeable.width / 2,
                    y = if (index == 0) {
                        targetOffsetYFirstChild.floatValue = placeable.height - centerY.toFloat()
                        offsetYFirstChild.value.toInt() + centerY - placeable.height
                    } else {
                        targetOffsetYSecondChild.floatValue = centerY.toFloat() - placeable.height
                        offsetYSecondChild.value.toInt() + centerY
                    }
                )
            }
        }
    }
}
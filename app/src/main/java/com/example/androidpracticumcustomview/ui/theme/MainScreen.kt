package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.androidpracticumcustomview.R

@Composable
fun MainScreen() {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), contentAlignment = Alignment.Center
        ) {

            CustomContainerCompose {
                Text(
                    text = stringResource(R.string.first_child_text),
                    fontSize = dimensionResource(R.dimen.first_child_font_size_compose).value.sp,
                    color = Color.Red
                )
                Text(
                    text = stringResource(R.string.second_child_text),
                    fontSize = dimensionResource(R.dimen.second_child_font_size_compose).value.sp,
                    color = Color.Blue
                )
            }
        }
    }
}
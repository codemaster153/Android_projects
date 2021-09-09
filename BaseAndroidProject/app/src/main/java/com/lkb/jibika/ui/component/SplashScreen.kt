package com.lkb.jibika.ui.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.lkb.baseandroidproject.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lkb.jibika.ui.theme.BaseAndroidProjectTheme

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Skip",
                modifier =
                Modifier.clickable {
                    Toast.makeText(context, "Skip clicked", Toast.LENGTH_SHORT).show()
                })
        }
        // Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
        val centreImage: Painter = painterResource(id = R.drawable.baby)
        Image(painter = centreImage, contentDescription = "")
        //  }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.header),
                modifier =
                Modifier
                    .padding(bottom = 8.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Skip clicked", Toast.LENGTH_SHORT)
                            .show()
                    })
            Text(
                text = stringResource(id = R.string.desc_txt),
                textAlign = TextAlign.Center,
                modifier =
                Modifier
                    .padding(top = 8.dp, start = 90.dp, end = 90.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Skip clicked", Toast.LENGTH_SHORT)
                            .show()
                    })
            val centreImage: Painter = painterResource(id = R.drawable.ic_dot_dot)
            Image(
                modifier = Modifier.padding(bottom = 40.dp, top = 40.dp),
                painter = centreImage,
                contentDescription = ""
            )
            //}
            // Row(modifier = Modifier.padding(bottom = 24.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Next")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseAndroidProjectTheme {
        SplashScreen()
    }
}
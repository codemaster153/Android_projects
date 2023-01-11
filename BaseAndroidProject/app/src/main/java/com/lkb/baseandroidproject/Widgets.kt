package com.lkb.baseandroidproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lkb.baseandroidproject.ui.theme.GreyText
import com.lkb.baseandroidproject.ui.theme.TextColor

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun QuizButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String = "Click",
    bgColor: Color = Color.White,
    imageRes: Int? = null,
    txtColor: Color = TextColor,
    onClick:()->Unit = {}
) {
    Button(
        modifier = modifier
            .height(65.dp)
            .fillMaxWidth(),
        onClick = {onClick.invoke()},
        shape = RoundedCornerShape(30),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(containerColor = bgColor)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = text, fontSize = 24.sp, color = txtColor)
            Spacer(modifier = Modifier.weight(1f))
            imageRes?.let {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "img left"
                )
            }
        }
    }
}

@Composable
fun TwinTextWidget(first: String, second: String) {
    Column {
        Text(text = first, color = GreyText, fontSize = 20.sp)
        Text(text = second, color = Color.White, fontSize = 36.sp, lineHeight = 44.sp)
    }
}
package com.example.mivaandroidtest.presentation.screens.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.FontSize

@Composable
fun SubjectItem(
    modifier: Modifier = Modifier,
    iconRes: Int,
    subject: String,
    backgroundColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(bottom = Dimens.space24)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(Dimens.space60)
                .background(backgroundColor, CircleShape)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = subject,
                tint = Color.White,
                modifier = Modifier.size(Dimens.space28)
            )
        }
        Spacer(modifier = modifier.height(Dimens.space8))
        Text(
            text = subject,
            fontSize = FontSize.fontSize11,
            fontFamily = FontFamily(Font(R.font.mulish, FontWeight.Normal))
        )
    }
}
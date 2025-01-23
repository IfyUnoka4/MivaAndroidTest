package com.example.mivaandroidtest.presentation.screens.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.FontSize
import com.example.mivaandroidtest.ui.theme.GreyF0
import com.example.mivaandroidtest.ui.theme.TextBlack50

@Composable
fun LessonsNumberCard (lessonNumber: Int, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .background(GreyF0, RoundedCornerShape(Dimens.space4))
            .padding(vertical = Dimens.space4, horizontal = Dimens.space8)
    ) {
        Text(
            text = "$lessonNumber Lessons",
            color = TextBlack50,
            fontSize = FontSize.fontSize12,
            modifier = modifier.alpha(0.5f)
        )
    }
}
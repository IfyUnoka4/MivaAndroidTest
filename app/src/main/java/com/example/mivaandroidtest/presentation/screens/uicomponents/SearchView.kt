package com.example.mivaandroidtest.presentation.screens.uicomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.Dimensions.cornerSize
import com.example.mivaandroidtest.ui.theme.FontSize
import com.example.mivaandroidtest.ui.theme.FontSize.fontSize14
import com.example.mivaandroidtest.ui.theme.NavyBlue20
import com.example.mivaandroidtest.ui.theme.TextBlack

@Composable
fun SearchView(state: MutableState<String>, modifier: Modifier = Modifier, hintText: String = stringResource(R.string.dashboard_search)) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(cornerSize)
            )
            .border(
                BorderStroke(
                    width = Dp.Hairline,
                    color = NavyBlue20
                ),
                shape = RoundedCornerShape(cornerSize)
            )
            .clip(RoundedCornerShape(cornerSize))
    ) {
        TextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
            },
            placeholder = {
                Text(
                   hintText,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextBlack,
                        fontSize = fontSize14,
                        fontFamily = FontFamily(Font(R.font.mulish, FontWeight.Normal))
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp),
            textStyle = TextStyle(
                color = TextBlack,
                fontSize = FontSize.fontSize18
            ),
            trailingIcon = {
                if (state.value != "") {
                    IconButton(
                        onClick = {
                            state.value =""
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(Dimens.space15)
                                .size(Dimens.space24)

                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null
                    )
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}
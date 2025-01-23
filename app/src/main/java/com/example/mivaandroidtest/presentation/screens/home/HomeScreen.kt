package com.example.mivaandroidtest.presentation.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.navigation.Screens
import com.example.mivaandroidtest.presentation.screens.uicomponents.Image
import com.example.mivaandroidtest.presentation.screens.uicomponents.SearchView
import com.example.mivaandroidtest.presentation.screens.uicomponents.SubjectItem
import com.example.mivaandroidtest.ui.theme.Dimens
import com.example.mivaandroidtest.ui.theme.FontSize
import com.example.mivaandroidtest.ui.theme.NavyBlue
import com.example.mivaandroidtest.utils.showToast
import com.example.mivaandroidtest.utils.subjectList

@Composable
fun HomeScreen(navController: NavHostController) {

    val searchTextState = rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.space16, vertical = Dimens.space24),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .background(NavyBlue, RoundedCornerShape(Dimens.space8))
                    .padding(vertical = Dimens.space4, horizontal = Dimens.space8)
            ) {
                Text(
                    text = stringResource(R.string.ss3_waec),
                    color = Color.White,
                    fontSize = FontSize.fontSize12
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = stringResource(R.string.notifications),
            )
        }
        Spacer(modifier = Modifier.height(Dimens.space24))

        Image(
            id = R.drawable.ad_carousel,
            contentDescription = stringResource(R.string.ad_carousel),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(Dimens.space24))

        SearchView(searchTextState)

        Spacer(modifier = Modifier.height(Dimens.space24))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxSize()
        ) {
            items(subjectList) { subject ->
                SubjectItem(
                    iconRes = subject.icon,
                    subject = subject.name,
                    backgroundColor = subject.color,
                    modifier = Modifier.clickable {
                        if (subject.name != context.getString(R.string.biology)) {
                            context.showToast("Not Available")
                        } else
                        navController.navigate(Screens.SubjectDetails.withArgs(subject.name))
                    }
                )
            }
        }

    }
}
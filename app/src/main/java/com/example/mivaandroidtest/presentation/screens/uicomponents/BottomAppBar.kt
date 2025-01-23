package com.example.mivaandroidtest.presentation.screens.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.ui.theme.Grey

@Composable
fun MyBottomAppBar() {
    var selectedScreen by remember { mutableStateOf("Home") }

    BottomAppBar(containerColor = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            BottomNavItem(
                iconRes = R.drawable.ic_home,
                label = "Home",
                selectedScreen,
                onClick = {
                    selectedScreen = "Home"
                }
            )
            BottomNavItem(
                iconRes = R.drawable.ic_classes,
                label = "Classes",
                selectedScreen,
                onClick = {
                    selectedScreen = "Classes"
                }
            )
            BottomNavItem(
                iconRes = R.drawable.ic_subscribe,
                label = "Subscribe",
                selectedScreen,
                onClick = {
                    selectedScreen = "Subscribe"
                }
            )
            BottomNavItem(
                iconRes = R.drawable.ic_downloads,
                label = "Downloads",
                selectedScreen,
                onClick = {
                    selectedScreen = "Downloads"
                }
            )
            BottomNavItem(
                iconRes = R.drawable.ic_more,
                label = "More",
                selectedScreen,
                onClick = {
                    selectedScreen = "More"
                }
            )
        }
    }
}

@Composable
fun BottomNavItem(iconRes: Int, label: String, selectedScreen: String, onClick: () -> Unit) {
    val isSelected = selectedScreen == label
    val iconColor = if (isSelected) Color.Black else Grey
    val textColor = if (isSelected) Color.Black else Grey

    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = iconColor
            )
        }
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = textColor)
    }
}

@Preview
@Composable
fun PreviewBottomAppBar() {
    MyBottomAppBar()
}
package net.iessochoa.sergiocontreras.masseffectnavigation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import net.iessochoa.sergiocontreras.masseffectnavigation.R

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(painter = painterResource(R.drawable.ic_arrow_back), contentDescription = "Volver")
    }
}

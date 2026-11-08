package net.iessochoa.sergiocontreras.masseffectnavigation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import net.iessochoa.sergiocontreras.masseffectnavigation.R

data class TopLevelDestination(
    val key: NavKey,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
)
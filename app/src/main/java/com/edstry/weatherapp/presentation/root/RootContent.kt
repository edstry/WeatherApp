package com.edstry.weatherapp.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.edstry.weatherapp.presentation.details.DetailsContent
import com.edstry.weatherapp.presentation.favourite.FavouriteContent
import com.edstry.weatherapp.presentation.search.SearchContent
import com.edstry.weatherapp.presentation.ui.theme.WeatherAppTheme


@Composable
fun RootContent(component: RootComponent) {
    WeatherAppTheme {
        /// Обрабатывает все переходы
        Children(
            stack = component.stack
        ) {
            when(val instance = it.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }
                is RootComponent.Child.Favourite -> {
                    FavouriteContent(component = instance.component)
                }
                is RootComponent.Child.Search -> {
                    SearchContent(component = instance.component)
                }
            }
        }
    }
}
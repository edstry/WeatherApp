package com.edstry.weatherapp.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.edstry.weatherapp.presentation.details.DetailsComponent
import com.edstry.weatherapp.presentation.favourite.FavouriteComponent
import com.edstry.weatherapp.presentation.search.SearchComponent

interface RootComponent {
    /// Тип конфигурации * - наружу торчать не должен
    /// он используется в реализации RootComponent, к нему мы не обращаемся из вне
    /// поэтому в качестве конфига может использоваться любой тип - "*"
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Favourite(val component: FavouriteComponent): Child
        data class Search(val component: SearchComponent): Child
        data class Details(val component: DetailsComponent): Child
    }
}
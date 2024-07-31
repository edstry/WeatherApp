package com.edstry.weatherapp.presentation.favourite

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.edstry.weatherapp.R
import com.edstry.weatherapp.presentation.extansions.tempToFormattedString
import com.edstry.weatherapp.presentation.ui.theme.CardGradients
import com.edstry.weatherapp.presentation.ui.theme.Gradient
import com.edstry.weatherapp.presentation.ui.theme.Orange

@Composable
fun FavouriteContent(component: FavouriteComponent) {
    val state by component.model.collectAsState()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            SearchCard(onClick = { component.onClickSearch() })
        }
        item {
            AddFavouriteCityCard(onClick = { component.onClickAddFavourite() })
        }
        itemsIndexed(
            items = state.cityItems,
            key = { _, item -> item.city.id }
        ) { index, item ->
            CityCard(
                onClick = { component.onCityItemClick(item.city) },
                cityItem = item,
                index = index
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CityCard(
    onClick: () -> Unit,
    cityItem: FavouriteStore.State.CityItem,
    index: Int
) {
    val gradient = getGradientByIndex(index)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 16.dp,
                spotColor = gradient.shadowColor,
                shape = MaterialTheme.shapes.extraLarge
            ),
        colors = CardDefaults.cardColors(containerColor = Color.Blue),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(
            modifier = Modifier
                .background(gradient.primaryGradient)
                .fillMaxSize()
                .clickable { onClick() }
                .sizeIn(minHeight = 196.dp)
                .padding(15.dp)
                .drawBehind {
                    drawCircle(
                        gradient.secondaryGradient,
                        center = Offset(
                            x = center.x - size.width / 10,
                            y = center.y + size.height / 2
                        ),
                        radius = size.maxDimension / 2
                    )
                }
        ) {
            when (val weatherState = cityItem.weatherState) {
                FavouriteStore.State.WeatherState.Initial -> {}
                FavouriteStore.State.WeatherState.Error -> {

                }

                is FavouriteStore.State.WeatherState.Loaded -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        GlideImage(
                            modifier = Modifier
                                .size(56.dp)
                                .align(Alignment.End),
                            model = weatherState.iconUrl,
                            contentDescription = null
                        )
                        Text(
                            text = weatherState.tempC.tempToFormattedString(),
                            color = MaterialTheme.colorScheme.background,
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 48.sp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = cityItem.city.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.background,
                        )
                    }
                }

                FavouriteStore.State.WeatherState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.background
                    )
                }
            }

        }
    }
}

@Composable
fun AddFavouriteCityCard(
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = MaterialTheme.shapes.extraLarge,
        border = BorderStroke(1.dp, Orange)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(196.dp)
                .fillMaxSize()
                .padding(24.dp)
                .clickable { onClick() }
        ) {
            Icon(
                modifier = Modifier.size(50.dp),
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = Orange
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(R.string.add_to_favourite),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 13.sp),
                color = Orange,
            )
        }
    }
}

@Composable
fun SearchCard(
    onClick: () -> Unit
) {
    Card(
        shape = CircleShape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .background(Orange)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 13.sp),
                color = MaterialTheme.colorScheme.background,
            )
        }
    }
}

private fun getGradientByIndex(index: Int): Gradient {
    val gradients = CardGradients.gradients
    return gradients[index % gradients.size]
}
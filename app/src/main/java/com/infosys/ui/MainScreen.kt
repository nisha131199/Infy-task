package com.infosys.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.infosys.data.domain.viewmodel.MainViewModel
import com.infosys.data.model.CityDetails
import com.infosys.ui.theme.Pink40
import com.infosys.ui.theme.Pink80
import com.infosys.ui.theme.Typography
import com.infosys.ui.theme.White

@Composable
fun Greeting(name: String, viewModel: MainViewModel) {
    val data = viewModel.response.collectAsState().value
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Hello $name!",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            style = Typography.titleLarge
        )
        TextButton(
            onClick = {
                viewModel.fetchCitiesList()
            }
        ) {
            Text(
                text = "Reverse",
                modifier = Modifier
                    .clip(roundShapeCorner8())
                    .background(Pink40)
                    .padding(8.dp),
                color = White,
                style = Typography.titleSmall
            )
        }
        Spacer(16)
        AnimateExpandableList(data.data!!)
    }
}

@Composable
fun AnimateExpandableList(citiesResponse: Collection<Map.Entry<String?, List<CityDetails>>>) {
    val expandedStates = remember { mutableStateListOf(*BooleanArray(citiesResponse.size) { false }.toTypedArray()) }
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = listState
    ) {

        itemsIndexed(citiesResponse.toList()) {index: Int, item: Map.Entry<String?, List<CityDetails>> ->
            ExpandableListItem(
                item = item,
                isExpanded = expandedStates[index],
                onExpandedChange = { expandedStates[index] = it }
            )
        }
    }
}

@Composable
fun ExpandableListItem(
    item: Map.Entry<String?, List<CityDetails>>,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val rotationAngle by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f, label = "")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = roundShapeCorner12())
            .background(
                color = Pink80,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(interactionSource = interactionSource, indication = null) {
                onExpandedChange(!isExpanded)
            }
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Pink40,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = item.key.toString(),
                style = Typography.titleLarge,
                modifier = Modifier.weight(1f),
                color = White
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                modifier = Modifier.graphicsLayer(rotationZ = rotationAngle),
                tint = White
            )
        }
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Text(
                    text = "Cities:=",
                    style = Typography.labelLarge,
                    color = White
                )
                Spacer(4)
                item.value.forEach {
                    Spacer(4)
                    TextTitleMedium(text = "${it.city}")
                }
            }
        }
    }
}
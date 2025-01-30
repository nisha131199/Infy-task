package com.infosys.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infosys.ui.theme.Typography
import com.infosys.ui.theme.White

/**
 * todo: Texts
 * */
@Composable
fun TextTitleMedium(text: String) {
    Text(
        text = text,
        style = Typography.titleMedium,
        color = White.copy(alpha = 0.6f)
    )
}

@Composable
fun TextTitleSmall(text: String) {
    Text(
        text = text,
        style = Typography.titleSmall,
        color = White.copy(alpha = 0.6f)
    )
}

@Composable
fun TextTitleLarge(text: String) {
    Text(
        text = text,
        style = Typography.titleLarge,
        color = White.copy(alpha = 0.6f)
    )
}


/**
 * todo: Spacers
 * */

@Composable
fun Spacer(size: Int) =
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(size.dp))


/**
 * todo: Shapes
 * */
@Composable
fun roundShapeCorner8() = RoundedCornerShape(8.dp)

@Composable
fun roundShapeCorner12() = RoundedCornerShape(12.dp)
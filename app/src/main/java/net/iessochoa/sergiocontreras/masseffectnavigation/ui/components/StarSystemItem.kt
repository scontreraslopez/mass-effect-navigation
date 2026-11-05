package net.iessochoa.sergiocontreras.masseffectnavigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.masseffectnavigation.model.StarSystem

@Composable
fun StarSystemItem(
    system: StarSystem,
    accent: Color,
    onToggleExplored: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onToggleExplored,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (system.explored) accent.copy(alpha = 0.12f)
            else MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)
        ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(if (system.explored) accent else accent.copy(alpha = 0.3f), CircleShape)
            )
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(text = system.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = system.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = "${system.planets} planetas",
                    style = MaterialTheme.typography.labelSmall,
                    color = accent,
                )
            }
            Checkbox(
                checked = system.explored,
                onCheckedChange = { onToggleExplored() },
                colors = CheckboxDefaults.colors(checkedColor = accent),
            )
        }
    }
}

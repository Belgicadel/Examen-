package com.examen.blegica_examen.ui.facturas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.examen.blegica_examen.data.factura.Factura
import com.examen.blegica_examen.ui.AppViewModelProvider
import com.examen.blegica_examen.ui.navegation.NavigationController
import com.examen.blegica_examen.R

object CategoriaDestination : NavigationController {
    override val route = "Factura"
    override val titleRes = R.string.detallefactura_titulo
}

@Composable
fun Screen(
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FacturaViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val facturaUiState by viewModel.facturaUiState.collectAsState()

    Scaffold(
        modifier = modifier,
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Body(
                facturaList = facturaUiState.facturaList,
                onItemClick = navigateToItemUpdate,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp)
            )
        }
    }
}


@Composable
private fun Body(
    facturaList: List<Factura>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        val contentColor = Color(0xFFB391EE)

        Text(
            text = "facturas",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            color = contentColor,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        List(
            facturaList = facturaList,
            onItemClick = { onItemClick(it.FacturaID) },
            contentPadding = contentPadding,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        )
    }

}

@Composable
private fun List(
    facturaList: List<Factura>,
    onItemClick: (Factura) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = facturaList, key = { it.FacturaID }) { categoria ->
            Info(
                factura = categoria,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(categoria) }
            )
        }
    }
}

@Composable
fun Info(
    factura: Factura,
    modifier: Modifier = Modifier
) {
    val cardBackgroundColor = Color(0xFF338A82)
    val contentColor = Color(0xFFC1B9CF)

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = factura.imagenId),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                DetailText(label = "ID: ${factura.FacturaID}", color = contentColor)
                DetailText(label = "Numero: ${factura.numero}", color = contentColor)
                DetailText(label = "Fecha: ${factura.fecha}", color = contentColor)
                DetailText(label = "Cliente: ${factura.fecha}", color = contentColor)
            }
        }
    }
}

@Composable
fun DetailText(label: String, color: Color) {
    Text(
        text = label,
        color = color,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}

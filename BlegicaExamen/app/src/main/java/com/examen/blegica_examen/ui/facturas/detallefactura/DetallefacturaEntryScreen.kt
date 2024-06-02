package com.examen.blegica_examen.ui.facturas.detallefactura

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.examen.blegica_examen.ui.navegation.NavigationController
import com.examen.blegica_examen.R
import com.examen.blegica_examen.ui.AppViewModelProvider
import com.examen.blegica_examen.data.detallefactura.DetalleFactura

object DetallefacturaDestination : NavigationController {
    override val route = "envio_detalle"
    override val titleRes = R.string.detallefactura_titulo
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    envioId: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetallefacturaEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val detallefacturaUiState by viewModel.detallefacturaUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DetalleFactura de las Facturas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                detallefacturaUiState.isLoading -> {
                    LoadingState()
                }
                detallefacturaUiState.errorMessage != null -> {
                    ErrorState(errorMessage = detallefacturaUiState.errorMessage!!)
                }
                else -> {
                    List(
                        detallesEnvio = detallefacturaUiState.detalleFactura,
                        envioId = envioId,
                    )
                }
            }
        }
    }
}

@Composable
fun List(
    detallesEnvio: List<DetalleFactura>,
    envioId: Int,
) {
    val detallesFiltrados = detallesEnvio.filter { it.facturaDetalleID == envioId }
    LazyColumn(
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(detallesFiltrados.size) { index ->
            Data(detallesFiltrados[index])
        }
    }
}
@Composable
fun Data(detalleFactura: DetalleFactura) {
    val cardBackgroundColor = Color(0xFFFF5790)
    val textColor = Color(0xFFEEDBDB)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Detail_Text(label = "ID: ${detalleFactura.id}", color = textColor)
                Detail_Text(
                    label = "Categorias ID: ${detalleFactura.facturaDetalleID}",
                    color = textColor
                )
            }
            Detail_Text(label = "Descripción: ${detalleFactura.descripcion}", color = textColor)
            Detail_Text(label = "Cantidad: ${detalleFactura.cantidad}", color = textColor)
            Detail_Text(label = "Precio: ${detalleFactura.precio}", color = textColor)
        }
    }
}

@Composable
fun LoadingState() {
    Text(
        text = "Loading...",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}

@Composable
fun Detail_Text(label: String, color: Color) {
    Text(
        text = label,
        color = color,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
@Composable
fun ErrorState(errorMessage: String) {
    Text(
        text = "Error: $errorMessage",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}

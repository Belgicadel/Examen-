package com.examen.blegica_examen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.examen.blegica_examen.ui.navegation.NavigationController

@Composable
fun Facturapp(navController: NavHostController = rememberNavController()) {
    NavigationController(navController = navController)
}


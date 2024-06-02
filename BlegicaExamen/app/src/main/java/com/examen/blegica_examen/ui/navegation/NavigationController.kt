package com.examen.blegica_examen.ui.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.examen.blegica_examen.ui.facturas.detallefactura.DetallefacturaDestination
import com.examen.blegica_examen.ui.facturas.CategoriaDestination
import com.examen.blegica_examen.ui.facturas.Screen
import com.examen.blegica_examen.ui.facturas.detallefactura.Screen

@Composable
fun NavigationController(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = CategoriaDestination.route,
        modifier = modifier
    ) {
        composable(route = CategoriaDestination.route) {
            Screen(
                navigateToItemUpdate = { itemId ->
                    navController.navigate("${DetallefacturaDestination.route}/$itemId")
                }
            )
        }
        composable(
            route = "${DetallefacturaDestination.route}/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getInt("itemId") ?: 0
            Screen(itemId, onBack= { navController.navigate(CategoriaDestination.route) })
        }
    }
}

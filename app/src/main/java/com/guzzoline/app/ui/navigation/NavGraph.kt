package com.guzzoline.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.guzzoline.app.data.SavedTripEntity
import com.guzzoline.app.ui.calculator.CalculatorScreen
import com.guzzoline.app.ui.savedtrips.SavedTripsScreen
import com.guzzoline.app.ui.triplog.TripLogScreen

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Calculator : Screen("calculator", "Fuel Up", Icons.Default.Calculate)
    data object TripLog : Screen("trip_log", "War Log", Icons.Default.ListAlt)
    data object SavedTrips : Screen("saved_trips", "Routes", Icons.Default.Bookmark)
}

private val screens = listOf(Screen.Calculator, Screen.TripLog, Screen.SavedTrips)

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Calculator.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Calculator.route) {
                CalculatorScreen(
                    onLoadSavedTrip = {
                        navController.navigate(Screen.SavedTrips.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(Screen.TripLog.route) {
                TripLogScreen()
            }
            composable(Screen.SavedTrips.route) {
                SavedTripsScreen(
                    onTripSelected = { trip ->
                        navController.navigate(Screen.Calculator.route + "?name=${trip.name}&origin=${trip.origin}&destination=${trip.destination}&distance=${trip.distance}") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(
                Screen.Calculator.route + "?name={name}&origin={origin}&destination={destination}&distance={distance}"
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val origin = backStackEntry.arguments?.getString("origin") ?: ""
                val destination = backStackEntry.arguments?.getString("destination") ?: ""
                val distance = backStackEntry.arguments?.getString("distance") ?: ""
                CalculatorScreen(
                    prefilledName = name,
                    prefilledOrigin = origin,
                    prefilledDestination = destination,
                    prefilledDistance = distance,
                    onLoadSavedTrip = {
                        navController.navigate(Screen.SavedTrips.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

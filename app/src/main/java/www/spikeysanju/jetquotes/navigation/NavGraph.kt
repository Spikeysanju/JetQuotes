/*
 *
 *  *
 *  *  * MIT License
 *  *  *
 *  *  * Copyright (c) 2020 Sanju S
 *  *  *
 *  *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  *  * of this software and associated documentation files (the "Software"), to deal
 *  *  * in the Software without restriction, including without limitation the rights
 *  *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  *  * copies of the Software, and to permit persons to whom the Software is
 *  *  * furnished to do so, subject to the following conditions:
 *  *  *
 *  *  * The above copyright notice and this permission notice shall be included in all
 *  *  * copies or substantial portions of the Software.
 *  *  *
 *  *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  *  * SOFTWARE.
 *  *
 *
 */

package www.spikeysanju.jetquotes.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import www.spikeysanju.jetquotes.view.details.DetailScreen
import www.spikeysanju.jetquotes.view.favourites.FavouritesScreen
import www.spikeysanju.jetquotes.view.quotes.QuotesListScreen
import www.spikeysanju.jetquotes.view.viewModel.MainViewModel

object EndPoints {
    const val ID = "id"
    const val QUOTE = "quote"
    const val AUTHOR = "author"
}

@ExperimentalMaterialApi
@Composable
fun NavGraph(toggleTheme: () -> Unit) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController, startDestination = Screen.Home.route) {
        // Quotes List
        composable(Screen.Home.route) {
            val viewModel: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            QuotesListScreen(viewModel, toggleTheme, actions)
        }

        // Quotes Details
        composable(
            "${Screen.Details.route}/{quote}/{author}",
            arguments = listOf(
                navArgument(EndPoints.QUOTE) { type = NavType.StringType },
                navArgument(EndPoints.AUTHOR) {
                    type = NavType.StringType
                })
        ) {
            val viewModel = hiltNavGraphViewModel<MainViewModel>(backStackEntry = it)
            DetailScreen(
                viewModel,
                actions.upPress,
                it.arguments?.getString(EndPoints.QUOTE) ?: "",
                it.arguments?.getString(EndPoints.AUTHOR) ?: ""
            )
        }

        // Favourites
        composable(Screen.Favourites.route) {
            val viewModel = hiltNavGraphViewModel<MainViewModel>(backStackEntry = it)
            FavouritesScreen(viewModel, actions)
        }
    }
}

class MainActions(navController: NavHostController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val gotoDetails: (String, String) -> Unit = { quote, author ->
        navController.navigate("${Screen.Details.route}/$quote/$author")
    }

    val gotoFavourites: () -> Unit = {
        navController.navigate(Screen.Favourites.route)
    }
}

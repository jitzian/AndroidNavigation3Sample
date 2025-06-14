package io.devexpert.nav3samples.ui.theme.screens

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import io.devexpert.nav3samples.ui.theme.screens.detail.ProductDetail
import io.devexpert.nav3samples.ui.theme.screens.detail.ProductDetailScreen
import io.devexpert.nav3samples.ui.theme.screens.detail.ProductDetailViewModel
import io.devexpert.nav3samples.ui.theme.screens.home.Home

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun Navigation() {


    //Add Home since it is the first screen
    val backStack = rememberNavBackStack(Home)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                slideOutHorizontally()
            )
        },
        popTransitionSpec = {
            ContentTransform(
                slideInHorizontally(),
                slideOutHorizontally(targetOffsetX = { it })
            )
        },

        //sceneStrategy = rememberListDetailSceneStrategy(),
        sceneStrategy = TwoPaneSceneStrategy(),
        entryProvider = entryProvider {
            entry<Home>(
                //metadata = TwoPaneScene.twoPane()
                metadata = ListDetailSceneStrategy.listPane(
                    detailPlaceholder = {
                        Text(
                            text = "Choose a product from the list",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                )
            ) {
                Home(
                    onProductClick = { productId ->
                        backStack.add(ProductDetail(productId))
                    }
                )
            }
            entry<ProductDetail>(
                //metadata = TwoPaneScene.twoPane()
                metadata = ListDetailSceneStrategy.detailPane()
            ) { key ->
                ProductDetailScreen(
                    viewModel = viewModel { ProductDetailViewModel(key.productId) },
                    onBackClick = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}
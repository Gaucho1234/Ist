import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screens.LoginScreen


@Composable
fun NavigationDemo() {
    // Create a NavController.
    val navController = rememberNavController()

    // Create a NavHost composable.
   NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // Create a composable for each route.
       composable("login") {
           LoginScreen(navController)
       }
       composable("userprofile") {
           UserProfileScreen()
       }
       composable("service") {
           ServiceScreen()
    }}
}

@Composable
fun ServiceScreen() {
    TODO("Not yet implemented")
}

@Composable
fun UserProfileScreen() {
    TODO("Not yet implemented")
}


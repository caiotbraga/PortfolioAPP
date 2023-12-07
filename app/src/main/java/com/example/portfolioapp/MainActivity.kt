package com.example.portfolioapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.portfolioapp.R
import com.example.portfolioapp.ui.theme.PortfolioAPPTheme

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object PersonalInfo : Screen("personal_info", "Bio", Icons.Default.Person)
    object Projects : Screen("projects", "Projetos", Icons.Default.Done)
    object Contacts : Screen("contacts", "Contatos", Icons.Default.Call)

}

val screens = listOf(Screen.PersonalInfo, Screen.Projects, Screen.Contacts)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val (isPersonalInfoSelected, setPersonalInfoSelected) = remember { mutableStateOf(true) }
            val (isProjectsSelected, setProjectsSelected) = remember { mutableStateOf(false) }
            val (isContactsSelected, setContactsSelected) = remember { mutableStateOf(false) }

            PortfolioAPPTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = Color(0xFFDBCA95)
                        ) {
                            BottomNavigationItem(
                                icon = {
                                    val iconColor = if (isPersonalInfoSelected) Color(0xFFC77E24) else Color.Black
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null,
                                        tint = iconColor
                                    )
                                },
                                label = {
                                    Text(text = "Bio")
                                },
                                selected = isPersonalInfoSelected,
                                onClick = {
                                    setPersonalInfoSelected(true)
                                    setProjectsSelected(false)
                                    setContactsSelected(false)
                                    navController.navigate(Screen.PersonalInfo.route)
                                }
                            )

                            BottomNavigationItem(
                                icon = {
                                    val iconColor = if (isProjectsSelected) Color(0xFFC77E24) else Color.Black
                                    Icon(
                                        imageVector = Icons.Default.Done,
                                        contentDescription = null,
                                        tint = iconColor
                                    )
                                },
                                label = {
                                    Text(text = "Projetos")
                                },
                                selected = isProjectsSelected,
                                onClick = {
                                    setPersonalInfoSelected(false)
                                    setProjectsSelected(true)
                                    setContactsSelected(false)
                                    navController.navigate(Screen.Projects.route)
                                }
                            )

                            BottomNavigationItem(
                                icon = {
                                    val iconColor = if (isContactsSelected) Color(0xFFC77E24) else Color.Black
                                    Icon(
                                        imageVector = Icons.Default.Call,
                                        contentDescription = null,
                                        tint = iconColor
                                    )
                                },
                                label = {
                                    Text(text = "Social")
                                },
                                selected = isContactsSelected,
                                onClick = {
                                    setPersonalInfoSelected(false)
                                    setProjectsSelected(false)
                                    setContactsSelected(true)
                                    navController.navigate(Screen.Contacts.route)
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PersonalInfo.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.PersonalInfo.route) {
                            PersonalInfoSection()
                        }
                        composable(Screen.Projects.route) {
                            ProjectsScreen()
                        }
                        composable(Screen.Contacts.route) {
                            ContactsScreen()
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun PortfolioContent(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)

    ) {
        item {
            PersonalInfoSection()
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Button(onClick = { navController.navigate("projects") }) {
                Text("Projetos")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Button(onClick = { navController.navigate("contacts") }) {
                Text("Contatos")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PersonalInfoSection() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Caio Braga",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.eu),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Full Stack Software Engineer with a strong background in designing, developing, and deploying innovative web applications and services. Proficient in utilizing a wide range of technologies to create efficient and scalable solutions. Skilled in both front-end and back-end development, with a keen eye for creating seamless user experiences.\n" +
                        "\n" +
                        "Technologies:\n" +
                        "- .NET Core(C#) | Spring Boot | Java | JavaScript \n" +
                        "- Microservices | RESTful APIs\n" +
                        "- SQL | PostgreSQL \n" +
                        "- Git | Docker \n" +
                        "\n" +
                        "Passionate about tackling complex challenges and collaborating with cross-functional teams to bring projects from conception to successful implementation. Committed to continuous learning and staying up-to-date with industry trends. Open to new opportunities that allow me to contribute my expertise to impactful projects.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
data class Project(val title: String, val description: String, val imageRes: Int, val url: String)

val projects = listOf(
    Project(
       title = "Portfolio WEB",
        description = "Clique para saber mais.",
       imageRes = R.drawable.githubicon  ,
       url = "https://github.com/caiotbraga/Portfolio"
   ),Project(
        title = "ScheduleAPI",
        description = "Clique para saber mais.",
        imageRes = R.drawable.githubicon  ,
        url = "https://github.com/caiotbraga/ScheduleAPI"
    ),Project(
        title = "Schedule",
        description = "Clique para saber mais.",
        imageRes = R.drawable.githubicon  ,
        url = "https://github.com/caiotbraga/Schedule"
    ),Project(
        title = "Biografia",
        description = "Clique para saber mais.",
        imageRes = R.drawable.githubicon  ,
        url = "https://github.com/caiotbraga/Biography"
    ),Project(
        title = "WEB-System",
        description = "Clique para saber mais.",
        imageRes = R.drawable.githubicon  ,
        url = "https://github.com/caiotbraga/PROJECT-WebSystem"
    ),Project(
        title = "Compiler",
        description = "Clique para saber mais.",
        imageRes = R.drawable.githubicon  ,
        url = "https://github.com/caiotbraga/Compiler"
    ),Project(
        title = "ChessGame",
        description = "Clique para saber mais.",
        imageRes = R.drawable.githubicon  ,
        url = "https://github.com/caiotbraga/PROJECT-chess-game"
    )
    )
@Composable
fun ProjectsScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Projetos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black // Cor do texto
            )
            Spacer(modifier = Modifier.height(8.dp))
            projects.forEach { project ->
                ProjectItem(project = project)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
@Composable
fun ContactsScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Conteúdo da tela de Contatos sobreposto à imagem de fundo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Contatos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black // Cor do texto
            )
            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(8.dp))
            IconWithText(icon = painterResource(id = R.drawable.emailicon), text = "E-mail: contatocaiobraga@gmail.com", url = "mailto:contatocaiobraga@gmail.com")
            IconWithText(icon = painterResource(id = R.drawable.telefoneicon), text = "Tel.: +55 81 997002021", url = "tel:+5581997002021")
            IconWithText(icon = painterResource(id = R.drawable.instaicon), text = "Instagram", url = "https://www.instagram.com/caiotbraga/" )
            IconWithText(icon = painterResource(id = R.drawable.githubicon), text = "GitHub", url = "https://github.com/caiotbraga")
            IconWithText(icon = painterResource(id = R.drawable.linkedinicon), text = "Linkedin", url = "https://www.linkedin.com/in/caiotbraga")
        }
    }
}
@Composable
fun IconWithText(icon: Painter, text: String, url: String) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            openProjectUrl(context, url)
        }

    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}
@Composable
fun ProjectItem(project: Project) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                openProjectUrl(context, project.url)
            }
    ) {
        Image(
            painter = painterResource(id = project.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = project.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = project.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
fun openProjectUrl(context: android.content.Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(context, intent, null)
}

@Preview(showBackground = true)
@Composable
fun PortfolioPreview() {
    PortfolioAPPTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "bio") {
            composable("bio") {
                PersonalInfoSection()
            }
            composable("projects") {
                ProjectsScreen()
            }
            composable("contacts") {
                ContactsScreen()
            }
        }
    }
}


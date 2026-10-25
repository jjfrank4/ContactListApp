package com.example.contactlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactListApp()
                }
            }
        }
    }
}




@Composable
fun ContactListApp() {
    val contacts = listOf(
        Contact("John Doe", "john@example.com", "555-0101"),
        Contact("Jane Smith", "jane@example.com", "555-0102"),
        Contact("Alice Johnson", "alice@example.com", "555-0103"),
        Contact("Bob Williams", "bob@example.com", "555-0104"),
        Contact("Carol Brown", "carol@example.com", "555-0105"),
        Contact("David Jones", "david@example.com", "555-0106"),
        Contact("Emma Davis", "emma@example.com", "555-0107"),
        Contact("Frank Miller", "frank@example.com", "555-0108"),
        Contact("Grace Wilson", "grace@example.com", "555-0109"),
        Contact("Henry Moore", "henry@example.com", "555-0110"),
        Contact("Isabel Taylor", "isabel@example.com", "555-0111"),
        Contact("Jack Anderson", "jack@example.com", "555-0112"),
        Contact("Karen Thomas", "karen@example.com", "555-0113"),
        Contact("Liam Jackson", "liam@example.com", "555-0114"),
        Contact("Mia White", "mia@example.com", "555-0115"),
        Contact("Noah Harris", "noah@example.com", "555-0116"),
        Contact("Olivia Martin", "olivia@example.com", "555-0117"),
        Contact("Paul Thompson", "paul@example.com", "555-0118"),
        Contact("Quinn Garcia", "quinn@example.com", "555-0119"),
        Contact("Rachel Martinez", "rachel@example.com", "555-0120"),
        Contact("Sam Robinson", "sam@example.com", "555-0121"),
        Contact("Tina Clark", "tina@example.com", "555-0122"),
        Contact("Uma Rodriguez", "uma@example.com", "555-0123"),
        Contact("Victor Lewis", "victor@example.com", "555-0124"),
        Contact("Wendy Lee", "wendy@example.com", "555-0125")
    )

    ContactList(contacts)
}

@Composable
fun ContactList(contacts: List<Contact>) {
    var selectedContact by remember { mutableStateOf<Contact?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 50.dp)
    ) {
        Text(
            text = "Contact List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = selectedContact?.let { "Selected: ${it.name}" } ?: "No contact selected",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(contacts) { contact ->
                ContactItem(
                    contact = contact,
                    isSelected = contact == selectedContact,
                    onClick = { selectedContact = contact }
                )
            }
        }

        if (selectedContact != null) {
            Button(
                onClick = { selectedContact = null },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Clear Selection")
            }
        }
    }
}

@Composable
fun ContactItem(
    contact: Contact,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (isSelected) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = contact.name.split(" ").map { it.first() }.joinToString(""),
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
                else MaterialTheme.colorScheme.onSurface

                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = contact.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColor
                )
                Text(
                    text = contact.phone,
                    style = MaterialTheme.typography.bodySmall,
                    color = textColor
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

data class Contact(
    val name: String,
    val email: String,
    val phone: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactListAppPreview() {
    ContactListApp()
}
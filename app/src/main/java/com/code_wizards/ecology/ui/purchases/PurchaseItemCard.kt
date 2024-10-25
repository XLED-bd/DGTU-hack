package com.code_wizards.ecology.ui.purchases

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.code_wizards.ecology.R
import com.code_wizards.ecology.models.Purchase

@Composable
fun PurchaseItemCard(purchase: Purchase) {
    Card(
        shape = RoundedCornerShape(12.dp),
        // backgroundColor = Color.White,
        // elevation = 4.dp,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors( containerColor = Color.White ),
        elevation = CardDefaults.cardElevation( 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Eco Icon",
                tint = if (purchase.ecoFriendly) Color(0xFF34A853) else Color.Gray,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = purchase.productName,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF344955),
                    fontSize = 18.sp
                )

                Text(
                    text = "Price: ${purchase.price}",
                    color = Color(0xFF707070),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Carbon Impact: ${purchase.carbonImpact}",
                        color = if (purchase.ecoFriendly) Color(0xFF34A853) else Color(0xFFD32F2F),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    if (purchase.ecoFriendly) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Eco-friendly",
                            tint = Color(0xFF34A853),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    PurchaseItemCard(Purchase("Какое то гвно", "15 деревянных", 15.toString(), false))
}
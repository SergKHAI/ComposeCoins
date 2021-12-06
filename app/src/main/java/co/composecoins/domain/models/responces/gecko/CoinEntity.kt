package co.composecoins.domain.models.responces.gecko

import androidx.room.Entity
import androidx.room.PrimaryKey
import drewcarlson.coingecko.models.coins.CoinList

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey
    val id: String,
    val symbol: String,
    val name: String
)
fun CoinList.toEntity() = CoinEntity(id, symbol, name)

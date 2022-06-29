package com.sergeymikhovich.android.chef.model.responses.getRecipeInformationResponse

data class WinePairing(
    val pairedWines: List<String>,
    val pairingText: String,
    val productMatches: List<ProductMatche>
)
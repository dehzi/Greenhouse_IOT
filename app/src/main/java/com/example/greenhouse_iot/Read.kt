package com.example.greenhouse_iot

data class Read(val  Reading : String? = null,
                val Humidity : String? = null,
                val Temperature : String? = null,
                val UV : String? = null,
                val WindSpeed : String? = null,
                val Motion : String? = null,
                val SoilMoisture : String? = null){

}

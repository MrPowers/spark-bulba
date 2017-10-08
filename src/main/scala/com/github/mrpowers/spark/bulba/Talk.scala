package com.github.mrpowers.spark.bulba

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Talk {

  def withGreenPokemon()(df: DataFrame): DataFrame = {
    df.withColumn(
      "green_pokemon",
      lit("bulba bulba")
    )

  }

}

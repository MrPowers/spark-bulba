package com.github.mrpowers.spark.bulba

import org.apache.spark.sql.types.StringType

import org.scalatest.FunSpec

import com.github.mrpowers.spark.fast.tests.DataFrameComparer
import com.github.mrpowers.spark.daria.sql.SparkSessionExt._

class TalkSpec
    extends FunSpec
    with SparkSessionTestWrapper
    with DataFrameComparer {

describe("withGreenPokemon") {

    it("appends a green_pokemon column to a DataFrame") {

      val sourceDF = spark.createDF(
        List(
          "grass",
          "flower",
          // it works with the null case
          null
        ), List(
          ("food", StringType, true)
        )
      )

      val actualDF = sourceDF.transform(Talk.withGreenPokemon())

      val expectedDF = spark.createDF(
        List(
          ("grass", "bulba bulba"),
          ("flower", "bulba bulba"),
          (null, "bulba bulba")
        ), List(
          ("food", StringType, true),
          ("green_pokemon", StringType, false)
        )
      )

      assertSmallDataFrameEquality(actualDF, expectedDF)

    }

  }

}

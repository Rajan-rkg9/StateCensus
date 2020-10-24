package com.Capg.CensusAnalyer.IndianState;

public class CsvBuilderFactory {
	public static ICsvBuilder createBuilder() {
		return new OpenCsvBuilder();
	}
}

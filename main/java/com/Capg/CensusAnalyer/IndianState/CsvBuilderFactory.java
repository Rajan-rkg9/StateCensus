package com.Capg.CensusAnalyer.IndianState;

public class CsvBuilderFactory {
	public static ICsvBuilder createBuilderOpen() {
		return new OpenCsvBuilder();
	}

	public static ICsvBuilder createBuilderCommons() {
		return new CommonsCsvBuilder();
	}
}

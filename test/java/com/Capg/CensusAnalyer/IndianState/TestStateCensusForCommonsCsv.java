package com.Capg.CensusAnalyer.IndianState;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;

public class TestStateCensusForCommonsCsv {
	
	public static final String RIGHT_CENSUS_CSV = "C:\\Users\\Rajan\\eclipse-workspace\\IndianState\\file\\IndiaStateCensusData.csv";
	public static final String WRONG_CENSUS_CSV = "file/IndiaStateCensusDatae.csv";
	public static final String WRONGTYPE_CENSUS_CSV = "file/IndiaStateCensusData-WrongType.pdf";
	public static final String WRONGDELIMITER_CENSUS_CSV = "file/IndiaStateCensusData-WrongDelimiter.csv";
	public static final String WRONGHEADER_CENSUS_CSV = "file/IndiaStateCensusData-WrongHeader.csv";
	public static final String RIGHT_STATE_CODE_CSV = "C:\\Users\\Rajan\\eclipse-workspace\\IndianState\\file\\IndiaStateCode.csv";
	public static final String WRONG_STATE_CODE_CSV = "file/IndiaStateCodetr.csv";
	public static final String WRONGTYPE_STATE_CODE_CSV = "file/IndiaStateCode-WrongType.pdf";
	public static final String WRONGDELIMITER_STATE_CODE_CSV = "file/IndiaStateCode-WrongDelimiter.csv";
	public static final String WRONGHEADER_STATE_CODE_CSV = "file/IndiaStateCode-WrongHeader.csv";
	@Test
	public void givenIndiaCensusDataCsv_ShouldReturnExactCount() {
		try {
			int recordsCount = new StateCensusAnalyser().loadStateCensusData(RIGHT_CENSUS_CSV , CsvBuilderType.COMMONS_CSV);
			assertEquals(29, recordsCount);
		}
		catch (StateCensusException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void givenWrongCsvFile_ShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			new StateCensusAnalyser().loadStateCensusData(WRONG_CENSUS_CSV , CsvBuilderType.COMMONS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}
	
	@Test
	public void givenWrongTypeCsvFile_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			new StateCensusAnalyser().loadStateCensusData(WRONGTYPE_CENSUS_CSV , CsvBuilderType.COMMONS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}
	
	@Test
	public void givenCsvFileIncorrectDelimiter_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {

			new StateCensusAnalyser().loadStateCensusData(WRONGDELIMITER_CENSUS_CSV , CsvBuilderType.COMMONS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
	
	@Test
	public void givenCsvFileIncorrectHeader_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {		
			new StateCensusAnalyser().loadStateCensusData(WRONGHEADER_CENSUS_CSV ,CsvBuilderType.COMMONS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
	
	@Test
	public void givenSortedOnStateCensusList_ShouldReturnCorrectFirstState() {
		try {
			String sortedStateCensusJson = new StateCensusAnalyser().getSortedCensusDataStateWise(RIGHT_CENSUS_CSV , CsvBuilderType.COMMONS_CSV);
			CSVStateCensus[] censusCsv=new Gson().fromJson(sortedStateCensusJson, CSVStateCensus[].class);
			assertEquals("Andhra Pradesh", censusCsv[0].state);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenSortedOnStateCensusList_ShouldReturnCorrectLastState() {
		try {
			String sortedStateCensusJson =  new StateCensusAnalyser().getSortedCensusDataStateWise(RIGHT_CENSUS_CSV,CsvBuilderType.COMMONS_CSV);
			CSVStateCensus[] censusCsv=new Gson().fromJson(sortedStateCensusJson, CSVStateCensus[].class);
			assertEquals("West Bengal", censusCsv[censusCsv.length-1].state);
		} catch (StateCensusException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void givenIndiaStateCodeCsv_ShouldReturnExactCount() {
		try {
			int recordsCount = new StateCensusAnalyser().loadStateCodeData(RIGHT_STATE_CODE_CSV , CsvBuilderType.COMMONS_CSV);
			assertEquals(37, recordsCount);
		}
		catch (StateCensusException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void givenWrongIndiaStateCode_ShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			new StateCensusAnalyser().loadStateCodeData(WRONG_STATE_CODE_CSV , CsvBuilderType.COMMONS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}
	
	@Test
	public void givenWrongTypeIndiaStateCode_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			new StateCensusAnalyser().loadStateCodeData(WRONGTYPE_STATE_CODE_CSV ,CsvBuilderType.COMMONS_CSV );
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}
	
	@Test
	public void givenIndiaStateCodeIncorrectDelimiter_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {

			new StateCensusAnalyser().loadStateCodeData(WRONGDELIMITER_STATE_CODE_CSV  ,CsvBuilderType.COMMONS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
	
	@Test
	public void givenIndiaStateCodeIncorrectHeader_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {		
			new StateCensusAnalyser().loadStateCodeData(WRONGHEADER_STATE_CODE_CSV , CsvBuilderType.COMMONS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
}

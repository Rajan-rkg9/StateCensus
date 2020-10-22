package com.Capg.CensusAnalyer.IndianState;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;
public class TestStateCensusAnalyser {
	public static final String RIGHT_CENSUS_CSV = "file/IndiaStateCensusData.csv";
	public static final String WRONG_CENSUS_CSV = "file/IndiaStateCensusDatae.csv";
	public static final String WRONGTYPE_CENSUS_CSV = "file/IndiaStateCensusData-WrongType.pdf";
	public static final String WRONGDELIMITER_CENSUS_CSV = "file/IndiaStateCensusData-WrongDelimiter.csv";
	public static final String WRONGHEADER_CENSUS_CSV = "file/IndiaStateCensusData-WrongHeader.csv";
	public static final String RIGHT_STATE_CODE_CSV = "file/IndiaStateCode.csv";
	public static final String WRONG_STATE_CODE_CSV = "file/IndiaStateCodetr.csv";
	public static final String WRONGTYPE_STATE_CODE_CSV = "file/IndiaStateCode-WrongType.pdf";
	public static final String WRONGDELIMITER_STATE_CODE_CSV = "file/IndiaStateCode-WrongDelimiter.csv";
	public static final String WRONGHEADER_STATE_CODE_CSV = "file/IndiaStateCode-WrongHeader.csv";
	@Test
	public void givenIndiaCensusDataCsv_ShouldReturnExactCount() {
		try {
			int recordsCount = new StateCensusAnalyser().loadStateCensusData(RIGHT_CENSUS_CSV);
			assertEquals(29, recordsCount);
		}
		catch (StateCensusException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void givenWrongCsvFile_ShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			new StateCensusAnalyser().loadStateCensusData(WRONG_CENSUS_CSV );
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}
	
	@Test
	public void givenWrongTypeCsvFile_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			new StateCensusAnalyser().loadStateCensusData(WRONGTYPE_CENSUS_CSV );
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}
	
	@Test
	public void givenCsvFileIncorrectDelimiter_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {

			new StateCensusAnalyser().loadStateCensusData(WRONGDELIMITER_CENSUS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
	
	@Test
	public void givenCsvFileIncorrectHeader_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {		
			new StateCensusAnalyser().loadStateCensusData(WRONGHEADER_CENSUS_CSV );
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
	
	@Test
	public void givenIndiaStateCodeCsv_ShouldReturnExactCount() {
		try {
			int recordsCount = new StateCensusAnalyser().loadStateCodeData(RIGHT_STATE_CODE_CSV);
			assertEquals(37, recordsCount);
		}
		catch (StateCensusException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void givenWrongIndiaStateCode_ShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			new StateCensusAnalyser().loadStateCodeData(WRONG_STATE_CODE_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}
	
	@Test
	public void givenWrongTypeIndiaStateCode_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			new StateCensusAnalyser().loadStateCodeData(WRONGTYPE_STATE_CODE_CSV );
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}
	
	@Test
	public void givenIndiaStateCodeIncorrectDelimiter_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {

			new StateCensusAnalyser().loadStateCodeData(WRONGDELIMITER_STATE_CODE_CSV  );
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
	
	@Test
	public void givenIndiaStateCodeIncorrectHeader_ShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {		
			new StateCensusAnalyser().loadStateCodeData(WRONGHEADER_STATE_CODE_CSV );
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
}

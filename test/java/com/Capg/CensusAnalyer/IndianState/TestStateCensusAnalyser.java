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

	@Test
	public void givenIndiaCensusDataCsvShouldReturnExactCount() {
		try {
			int recordsCount = new StateCensusAnalyser().loadStateCensusData(RIGHT_CENSUS_CSV);
			assertEquals(29, recordsCount);
		}
		catch (StateCensusException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void givenWrongCsvFileShouldThrowCensusAnalyserExceptionOfTypeCensusFileProblem() {
		try {
			new StateCensusAnalyser().loadStateCensusData(WRONG_CENSUS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.CENSUS_FILE_PROBLEM, e.exceptionType);
		}
	}
	
	@Test
	public void givenWrongTypeCsvFileShouldThrowCensusAnalyserExceptionOfTypeIncorrectType() {
		try {
			new StateCensusAnalyser().loadStateCensusData(WRONGTYPE_CENSUS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.INCORRECT_TYPE, e.exceptionType);
		}
	}
	
	@Test
	public void givenCsvFileIncorrectDelimiterShouldThrowCensusAnalyserExceptionOfTypeIncorrectDelimiter() {
		try {

			new StateCensusAnalyser().loadStateCensusData(WRONGDELIMITER_CENSUS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
	
	@Test
	public void givenCsvFileIncorrectHeaderShouldThrowCensusAnalyserExceptionOfTypeIncorrectHeader() {
		try {
//			
			new StateCensusAnalyser().loadStateCensusData(WRONGHEADER_CENSUS_CSV);
		}
		catch(StateCensusException e) {
			assertEquals(StateCensusExceptionType.SOME_OTHER_ERRORS, e.exceptionType);
		}
	}
}

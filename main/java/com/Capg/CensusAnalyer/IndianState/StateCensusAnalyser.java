package com.Capg.CensusAnalyer.IndianState;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	public int loadStateCensusData(String csvFilePath) throws StateCensusException {
		if(!(csvFilePath.matches(".*\\.csv$")))
			throw new StateCensusException("Incorrect Type", StateCensusExceptionType.INCORRECT_TYPE);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			
			Iterator<CSVStateCensus> censusCsvIterator = new OpenCsvBuilder().getIteratorFromCsv(reader, CSVStateCensus.class);
			return getCountFromIterator(censusCsvIterator);
		} 
		catch (IOException e) {
			throw new StateCensusException("Incorrect CSV File", StateCensusExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	public int loadStateCodeData(String csvFilePath) throws StateCensusException {
		if(!(csvFilePath.matches(".*\\.csv$")))
			throw new StateCensusException("Incorrect Type", StateCensusExceptionType.INCORRECT_TYPE);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			
			Iterator<CSVStateCode> censusCsvIterator = new OpenCsvBuilder().getIteratorFromCsv(reader, CSVStateCode.class);
			return getCountFromIterator(censusCsvIterator);
		} 
		catch (IOException e) {
			throw new StateCensusException("Incorrect CSV File", StateCensusExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	
	public <T> int getCountFromIterator(Iterator<T> csvIterator) {
		Iterable<T> csvIterable = () -> csvIterator;
		int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}	
}

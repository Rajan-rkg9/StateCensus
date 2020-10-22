package com.Capg.CensusAnalyer.IndianState;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
public class StateCensusAnalyser {
	public int loadStateCensusData(String csvFilePath) throws StateCensusException {
		if(!(csvFilePath.matches(".*\\.csv$")))
			throw new StateCensusException("Incorrect Type", StateCensusExceptionType.INCORRECT_TYPE);	
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVStateCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStateCensus> censusCsvIterator = csvToBean.iterator();
			Iterable<CSVStateCensus> csvIterable = () -> censusCsvIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} 
		catch (IOException e) {
			throw new StateCensusException("Incorrect CSV File", StateCensusExceptionType.CENSUS_FILE_PROBLEM);
		}
		catch (RuntimeException e) 
		{ 
			throw new StateCensusException("Wrong Delimiter or Header", StateCensusExceptionType.SOME_OTHER_ERRORS);			
		}
	}
}

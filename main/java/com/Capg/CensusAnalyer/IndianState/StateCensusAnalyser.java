package com.Capg.CensusAnalyer.IndianState;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	
	public int loadStateCensusData(String csvFilePath , CsvBuilderType csvBuilderType)  throws StateCensusException {
		if(!(csvFilePath.matches(".*\\.csv$")))
			throw new StateCensusException("Incorrect Type", StateCensusExceptionType.INCORRECT_TYPE);
		
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType==CsvBuilderType.OPEN_CSV?CsvBuilderFactory.createBuilderOpen():CsvBuilderFactory.createBuilderCommons();
			List<CSVStateCensus> censusCsvList = csvBuilder.getListFromCsv(reader, CSVStateCensus.class);
			return censusCsvList.size(); 	
		} 
		catch (IOException e) {
			throw new StateCensusException("Incorrect CSV File", StateCensusExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	public int loadStateCodeData(String csvFilePath , CsvBuilderType csvBuilderType) throws StateCensusException {
		if(!(csvFilePath.matches(".*\\.csv$")))
			throw new StateCensusException("Incorrect Type", StateCensusExceptionType.INCORRECT_TYPE);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			
			ICsvBuilder csvBuilder = csvBuilderType==CsvBuilderType.OPEN_CSV?CsvBuilderFactory.createBuilderOpen():CsvBuilderFactory.createBuilderCommons();
			Iterator<CSVStateCode> censusCsvIterator = csvBuilder.getIteratorFromCsv(reader, CSVStateCode.class);
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
	
	public String getSortedCensusDataStateWise(String csvFilePath, CsvBuilderType csvBuilderType) throws StateCensusException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
																			   : CsvBuilderFactory.createBuilderCommons();
			List<CSVStateCensus> censusCsvList = csvBuilder.getListFromCsv(reader, CSVStateCensus.class);
			Function<CSVStateCensus, String> stateName=census->census.state;
			Comparator<CSVStateCensus> censusComparator=Comparator.comparing(stateName);
			this.sortStateCensusList(censusCsvList, censusComparator);
			String sortedStateCensusToJson=new Gson().toJson(censusCsvList);
			return sortedStateCensusToJson;
		} 
		catch (IOException e) {
			throw new StateCensusException("Incorrect CSV File", StateCensusExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	public void sortStateCensusList(List<CSVStateCensus> censusCsvList, Comparator<CSVStateCensus> censusComparator) {
		for(int i=0;i<censusCsvList.size()-1;i++) {
			for(int j=0; j<censusCsvList.size()-i-1;j++) {
				CSVStateCensus state1=censusCsvList.get(j);
				CSVStateCensus state2=censusCsvList.get(j+1);
				if(censusComparator.compare(state1, state2)>0) {
					censusCsvList.set(j, state2);
					censusCsvList.set(j+1, state1);
				}
			}
		}
	}
}

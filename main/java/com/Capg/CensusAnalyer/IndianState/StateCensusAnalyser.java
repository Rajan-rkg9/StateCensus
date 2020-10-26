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
			List<CSVStateCode> codeCsvList = csvBuilder.getListFromCsv(reader, CSVStateCode.class);
			return codeCsvList.size(); 
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
	
	public String getSortedCensusDataStateNameWise(String csvFilePath, CsvBuilderType csvBuilderType) throws StateCensusException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
																			   : CsvBuilderFactory.createBuilderCommons();
			List<CSVStateCensus> censusCsvList = csvBuilder.getListFromCsv(reader, CSVStateCensus.class);
			Function<CSVStateCensus, String> stateName=census->census.state;
			Comparator<CSVStateCensus> censusComparator=Comparator.comparing(stateName);
			this.sortStateCensusListNameWise(censusCsvList, censusComparator);
			String sortedStateCensusToJson=new Gson().toJson(censusCsvList);
			return sortedStateCensusToJson;
		} 
		catch (IOException e) {
			throw new StateCensusException("Incorrect CSV File", StateCensusExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	public String getSortedCensusDataStatePopulationWise(String csvFilePath, CsvBuilderType csvBuilderType) throws StateCensusException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
																			   : CsvBuilderFactory.createBuilderCommons();
			List<CSVStateCensus> censusCsvList = csvBuilder.getListFromCsv(reader, CSVStateCensus.class);
			Function<CSVStateCensus, Long> populationKey=census->census.population;
			Comparator<CSVStateCensus> censusComparator=Comparator.comparing(populationKey);
			this.sortStateCensusListPopulationWise(censusCsvList, censusComparator);
			String sortedStateCensusToJson=new Gson().toJson(censusCsvList);
			return sortedStateCensusToJson;
		} 
		catch (IOException e) {
			throw new StateCensusException("Incorrect CSV File", StateCensusExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	public String getSortedCensusDataPopulationDensityWise(String csvFilePath, CsvBuilderType csvBuilderType) throws StateCensusException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
																			   : CsvBuilderFactory.createBuilderCommons();
			List<CSVStateCensus> censusCsvList = csvBuilder.getListFromCsv(reader, CSVStateCensus.class);
			Function<CSVStateCensus, Long> densityKey=census->census.density;
			Comparator<CSVStateCensus> censusComparator=Comparator.comparing(densityKey);
			this.sortStateCensusListPopulationWise(censusCsvList, censusComparator);
			String sortedStateCensusToJson=new Gson().toJson(censusCsvList);
			return sortedStateCensusToJson;
		} 
		catch (IOException e) {
			throw new StateCensusException("Incorrect CSV File", StateCensusExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	public String getSortedCensusDataStateCodeWise(String csvFilePath, CsvBuilderType csvBuilderType) throws StateCensusException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICsvBuilder csvBuilder = csvBuilderType == CsvBuilderType.OPEN_CSV ? CsvBuilderFactory.createBuilderOpen()
																			   : CsvBuilderFactory.createBuilderCommons();
			List<CSVStateCode> codeCsvList = csvBuilder.getListFromCsv( reader, CSVStateCode.class);
			Function<CSVStateCode, String> stateCode=census->census.stateCode;
			Comparator<CSVStateCode> censusComparator=Comparator.comparing(stateCode);
			this.sortStateCensusListCodeWise(codeCsvList, censusComparator);
			String sortedStateCensusToJson=new Gson().toJson(codeCsvList);
			return sortedStateCensusToJson;
		} 
		catch (IOException e) {
			throw new StateCensusException("Incorrect CSV File", StateCensusExceptionType.CENSUS_FILE_PROBLEM);
		}
	}
	
	/**
	 * SORTING METHOD
	 * StateNameWise,UC4
	 */
	public void sortStateCensusListNameWise(List<CSVStateCensus> censusCsvList, Comparator<CSVStateCensus> censusComparator) {
		for(int i=0;i<censusCsvList.size()-1;i++) 
		{
			for(int j=0; j<censusCsvList.size()-i-1;j++)
			{
				CSVStateCensus sortKey1=censusCsvList.get(j);
				CSVStateCensus sortKey2=censusCsvList.get(j+1);
				if(censusComparator.compare(sortKey1, sortKey2)>0)
				{
					censusCsvList.set(j, sortKey2);
					censusCsvList.set(j+1, sortKey1);
				}
			}
		}
	}
	/**
	 * SORTING METHOD
	 * PopulationWise,UC5
	 * PopulationDensityWise,UC6
	 */
	public void sortStateCensusListPopulationWise(List<CSVStateCensus> censusCsvList, Comparator<CSVStateCensus> censusComparator) {
		for(int i=0;i<censusCsvList.size()-1;i++) 
		{
			for(int j=0; j<censusCsvList.size()-i-1;j++)
			{
				CSVStateCensus sortKey1=censusCsvList.get(j);
				CSVStateCensus sortKey2=censusCsvList.get(j+1);
				if(censusComparator.compare(sortKey1, sortKey2)<0)
				{
					censusCsvList.set(j, sortKey2);
					censusCsvList.set(j+1, sortKey1);
				}
			}
		}
	}
	
	/**
	 * SORTING METHOD
	 * StateCodeWise
	 */
	public void sortStateCensusListCodeWise(List<CSVStateCode> censusCsvList, Comparator<CSVStateCode> censusComparator) {
		for(int i=0;i<censusCsvList.size()-1;i++) 
		{
			for(int j=0; j<censusCsvList.size()-i-1;j++) 
			{
				CSVStateCode sortKey1=censusCsvList.get(j);
				CSVStateCode sortKey2=censusCsvList.get(j+1);
				if(censusComparator.compare(sortKey1, sortKey2)>0)
				{
					censusCsvList.set(j, sortKey2);
					censusCsvList.set(j+1, sortKey1);
				}
			}
		}
	}
}

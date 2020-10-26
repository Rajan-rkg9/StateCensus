package com.Capg.CensusAnalyer.IndianState;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CommonsCsvBuilder implements ICsvBuilder {

	@Override
	public <T> Iterator<T> getIteratorFromCsv(Reader reader, Class<T> csvBindedClass) throws StateCensusException {
			try {	
				CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
				csvToBeanBuilder.withType(csvBindedClass);
				csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
				CsvToBean<T> csvToBean = csvToBeanBuilder.build();
				Iterator<T> censusCsvIterator = csvToBean.iterator();
				return censusCsvIterator;
			} 
			catch (RuntimeException e) {

				throw new StateCensusException("Wrong Delimiter or Header", StateCensusExceptionType.SOME_OTHER_ERRORS);
			}
	}
	
	public <T> CsvToBean<T> getCsvToBean(Reader reader, Class<T> csvBindedClass) throws RuntimeException {
		CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
		csvToBeanBuilder.withType(csvBindedClass);
		csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
		CsvToBean<T> csvToBean = csvToBeanBuilder.build();
		return csvToBean;
	}
	
	public <T> List<T> getListFromCsv(Reader reader, Class<T> csvBindedClass) throws StateCensusException {
		try {
			
			CsvToBean<T> csvToBean = this.getCsvToBean(reader, csvBindedClass);
			List<T> censusList = csvToBean.parse();
			return censusList;
		} 
		catch (IllegalStateException e) {

			throw new StateCensusException("Parsing Error", StateCensusExceptionType.PARSE_ERROR);
		}
		catch (RuntimeException e) {

			throw new StateCensusException("Wrong Delimiter or Header", StateCensusExceptionType.SOME_OTHER_ERRORS);
		}
		
	}

}

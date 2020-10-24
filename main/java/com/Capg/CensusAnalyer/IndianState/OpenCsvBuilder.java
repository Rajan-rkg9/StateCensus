package com.Capg.CensusAnalyer.IndianState;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder implements ICsvBuilder {
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
}

package com.Capg.CensusAnalyer.IndianState;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {
	@CsvBindByName(column = "SrNo", required = true)
	public int no;

	@CsvBindByName(column = "State Name", required = true)
	public String stateName;

	@CsvBindByName(column = "TIN", required = true)
	public int tin;

	@CsvBindByName(column = "StateCode", required = true)
	public String stateCode;

	@Override
	public String toString() {
		return "CSVStateCode [no=" + no + ", stateName=" + stateName + ", tin=" + tin + ", stateCode=" + stateCode
				+ "]";
	}

	
}

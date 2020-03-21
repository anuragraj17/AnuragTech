package com.anuragtech.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.anuragtech.Model.Login;

public class GlobalDao  <K extends GlobalBase> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected int noOfCols;
	protected String tableName;
	protected String[] columnNames;
	protected int[] columnTypes;

	protected String[] pkColumnNames;
	protected int[] pkColumnTypes;

	@SuppressWarnings("rawtypes")
	protected RowMapper rowMapper = null;
	
	private String insertQuery = null;
	private String updateQuery = null;
	
	protected String orderByClause = null;
			
	public GlobalDao()
	{

	}

	public GlobalDao(JdbcTemplate  pJdbcTemplate)
	{
		jdbcTemplate = pJdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate pJdbcTemplate)
	{
		jdbcTemplate = pJdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}


	private String getInsertQuery(){
		StringBuilder insertSql = new StringBuilder(columnNames.length * 20);
		StringBuilder queryParam = new StringBuilder(columnNames.length * 3);
		
		insertSql.append("INSERT INTO ").append(tableName).append(" (");
		
		for (int index=0; index < columnNames.length - 1; index++){
			insertSql.append(columnNames[index]).append(", ");
			queryParam.append("?, ");
		}
		queryParam.append('?');
		insertSql.append(columnNames[columnNames.length - 1])
			.append(") VALUES (")
			.append(queryParam)
			.append(')');
		return insertSql.toString();
	}
	
	public int insert(K pObject){
		if(insertQuery == null){
			insertQuery = getInsertQuery();
		}
		Object[] columnValues = pObject.getMemberValues();
		return jdbcTemplate.update(insertQuery, columnValues, columnTypes);
	}
	
	
	private String getUpdateQuery(){
		StringBuilder updateSql = new StringBuilder(columnNames.length * 25);
		updateSql.append("UPDATE ").append(tableName).append(" SET ");
		if(columnNames.length > 0){
			for (int index=0; index < columnNames.length - 1; index++){
				updateSql.append(columnNames[index]).append("=?, ");
			}
			updateSql.append(columnNames[columnNames.length - 1]).append("=? ");
		}
		updateSql.append("WHERE ");
		getPrimaryKeyClause(updateSql);
		return updateSql.toString();
	}
	public int update(K pObject) 
	{
		if(updateQuery == null){
			updateQuery = getUpdateQuery();
		}
		Object[] columnValues = pObject.getMemberValues();
		Object[] pkValues = pObject.getPrimaryValues();
		Object[] allParameters = new Object[columnValues.length + pkValues.length];
		int[] allParameterTypes = new int[columnTypes.length + pkColumnTypes.length];

		System.arraycopy(columnValues, 0, allParameters, 0, columnValues.length);
		System.arraycopy(pkValues, 0, allParameters, columnValues.length, pkValues.length);
		
		System.arraycopy(columnTypes, 0, allParameterTypes, 0, columnTypes.length);
		System.arraycopy(pkColumnTypes, 0, allParameterTypes, columnValues.length, pkColumnTypes.length);

		int[] iParamTypes = allParameterTypes;
		Object[] iParamValues = allParameters;
	
		return jdbcTemplate.update(updateQuery, iParamValues, iParamTypes);
	}
	public int delete(K pObject){
		StringBuilder deleteSql = new StringBuilder(pkColumnNames.length * 20);
		deleteSql.append("DELETE FROM ").append(tableName).append(" WHERE ");
		
		Object[] pkValues = pObject.getPrimaryValues();
		getPrimaryKeyClause(deleteSql);
		
		int[] iParamTypes = pkColumnTypes;

		
		return jdbcTemplate.update(deleteSql.toString(), pkValues, iParamTypes);
	}
	public int delete(Object... pkValues){
		if(pkValues == null || pkValues.length != pkColumnTypes.length){
			throw new IllegalArgumentException("Provide all values of primary keys.");
		}
		StringBuilder deleteSql = new StringBuilder(pkColumnNames.length * 20);
		deleteSql.append("DELETE FROM ").append(tableName).append(" WHERE ");
		
		getPrimaryKeyClause(deleteSql);
		
		int[] iParamTypes = pkColumnTypes;

		
		return jdbcTemplate.update(deleteSql.toString(), pkValues, iParamTypes);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public K select(K pObject,RowMapper rowMapper){
		StringBuilder selectSql = new StringBuilder(pkColumnNames.length * 20)
			.append("SELECT * FROM ").append(tableName).append(" WHERE ");
		
		Object[] pkValues = pObject.getPrimaryValues();
		getPrimaryKeyClause(selectSql);

		int[] iParamTypes = pkColumnTypes;
		
		
		return (K)jdbcTemplate.queryForObject(selectSql.toString(), pkValues, iParamTypes, rowMapper);
	}
	
	public K select(K pObject){
		return select(pObject, rowMapper);
	}
	@SuppressWarnings("unchecked")
	public K select(Object... pObject){
		if(pObject == null || pObject.length != pkColumnTypes.length){
			throw new IllegalArgumentException("Provide all values of primary keys.");
		}
		StringBuilder selectSql = new StringBuilder(pkColumnNames.length * 20)
		.append("SELECT * FROM ").append(tableName).append(" WHERE ");
		getPrimaryKeyClause(selectSql);
		
		int[] iParamTypes = pkColumnTypes;
		
		return (K) jdbcTemplate.queryForObject(selectSql.toString(), pObject, iParamTypes, rowMapper);
	}
	
	private void getPrimaryKeyClause(StringBuilder query){
		if(pkColumnNames.length>0){
			for (int index=0; index<pkColumnNames.length - 1; index++){
				query.append(pkColumnNames[index]).append("=? AND ");
			}
			query.append(pkColumnNames[pkColumnNames.length - 1]).append("=?");
		}
	}
	
	
	protected int [] getParamTypes(Object[] iParamTypesObj) {
		int cntr = 0;
		int [] iParamTypes = new int[iParamTypesObj.length];
    	for (Object value : iParamTypesObj) {
    		iParamTypes[cntr++] = (Integer)value;
    	}
    	return iParamTypes;
	}

	public Login selectUser(Login login) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
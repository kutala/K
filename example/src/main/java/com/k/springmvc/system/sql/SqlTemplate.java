package com.k.springmvc.system.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SqlTemplate implements ApplicationContextAware, InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(SqlTemplate.class);

	private static final String _TABLE_NAME_ = "_tableName_";
	private static final String _UPDATE_COLUMN_PARAMETERS_ = "_updateColumnParameters_";
	private static final String _COLUMN_PARAMETERS_ = "_columnParameters_";
	private static final String _COLUMN_NAME_PARAMETERS_ = "_columnNameParameters_";
	private static final String _COLUMN_VALUE_PARAMETERS_ = "_columnValueParameters_";
	private static final String _COUNT_ = "_count_";
	private static final String _FROM_ = "_from_";

	private static final Integer DEFAULT_PAGE_NUMBER = 1;
	private static final Integer DEFAULT_DISPLAY_COUNT = 100;

	private final Map<String, List<String>> tableColumns = new HashMap<>();

	private ApplicationContext context;

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public <T> int insert(final TableInfo tableInfo, final Map<String, T> parameter) {
		final Map<String, Object> newParameter = constructNewParameter(tableInfo, parameter);
		sqlSessionTemplate.insert(SqlTemplateSql.INSERT.getValue(), newParameter);
		return sqlSessionTemplate.selectOne(SqlTemplateSql.SELECT_LAST_INSERT_ID.getValue());
	}

	public <T> int insert(final TableInfo tableInfo, final List<Map<String, T>> parameters) {
		final Map<String, Object> newParameter = new HashMap<>();
		newParameter.put(_TABLE_NAME_, tableInfo.getTableName());
		final Map<String, Object> columnParameters = constructColumnParameters(tableInfo, parameters.get(0));
		newParameter.put(_COLUMN_NAME_PARAMETERS_, columnParameters.keySet());
		final List<List<Object>> columnValueParameters = new ArrayList<>(parameters.size());
		for (final Map<String, T> parameter : parameters) {
			final List<Object> rowValues = new ArrayList<>(columnParameters.keySet().size());
			for (final String column : columnParameters.keySet()) {
				rowValues.add(parameter.get(column));
			}
			columnValueParameters.add(rowValues);
		}
		newParameter.put(_COLUMN_VALUE_PARAMETERS_, columnValueParameters);
		return sqlSessionTemplate.insert(SqlTemplateSql.INSERTList.getValue(), newParameter);
	}

	public <T> int delete(final TableInfo tableInfo, final Map<String, T> parameter) {
		final Map<String, Object> newParameter = constructNewParameter(tableInfo, parameter);
		return sqlSessionTemplate.delete(SqlTemplateSql.DELETE.getValue(), newParameter);
	}

	public <T> int delete(final ISql sql, final T parameter) {
		return delete(sql.getValue(), parameter);
	}

	private <T> int delete(final String sql, final T parameter) {
		return sqlSessionTemplate.delete(sql, parameter);
	}

	public <T> int update(final TableInfo tableInfo, final Map<String, T> parameter) {
		final Map<String, Object> newParameter = new HashMap<>();
		newParameter.put(_TABLE_NAME_, tableInfo.getTableName());
		newParameter.put(_UPDATE_COLUMN_PARAMETERS_, constructUpdateColumnParameters(tableInfo, parameter));
		newParameter.put(_COLUMN_PARAMETERS_, constructColumnParameters(tableInfo, parameter));
		return update(SqlTemplateSql.UPDATE, newParameter);
	}

	public <T> int update(final ISql sql, final T parameter) {
		return update(sql.getValue(), parameter);
	}

	private <T> int update(final String sql, final T parameter) {
		return sqlSessionTemplate.update(sql, parameter);
	}

	@SuppressWarnings("unchecked")
	public <T, V> V selectOne(final TableInfo tableInfo, final Map<String, T> parameter) {
		return selectOne(SqlTemplateSql.SELECT, (Map<String, T>) constructNewParameter(tableInfo, parameter));
	}

	public <T, V> V selectOne(final ISql sql, final T parameter) {
		return selectOne(sql.getValue(), parameter);
	}

	private <T, V> V selectOne(final String sql, final T parameter) {
		final long start = System.currentTimeMillis();
		final V v = sqlSessionTemplate.selectOne(sql, parameter);
		LOG.debug("use time : " + (System.currentTimeMillis() - start) + "ms");
		return v;
	}

	@SuppressWarnings("unchecked")
	public <T, V> List<V> selectList(final TableInfo tableInfo, final Map<String, T> parameter) {
		return selectList(SqlTemplateSql.SELECT, (Map<String, T>) constructNewParameter(tableInfo, parameter));
	}

	public <T, V> List<V> selectList(final ISql sql, final T parameter) {
		return selectList(sql.getValue(), parameter);
	}

	private <T, V> List<V> selectList(final String sql, final T parameter) {
		final long start = System.currentTimeMillis();
		final List<V> resultList = sqlSessionTemplate.selectList(sql, parameter);
		LOG.debug("use time : " + (System.currentTimeMillis() - start) + "ms");
		return resultList;
	}

	public <T> Map<String, Object> selectListWithPage(final TableInfo tableInfo, final Map<String, T> parameter) {
		final long start = System.currentTimeMillis();
		final Map<String, Object> newParameter = constructNewParameterWithPage(parameter);
		final List<Map<String, Object>> resultList = selectList(tableInfo, newParameter);
		final Map<String, Object> resultMap = constructResultMapWithPage(newParameter, resultList);
		LOG.debug("use time : " + (System.currentTimeMillis() - start) + "ms");
		return resultMap;
	}

	public <T> Map<String, Object> selectListWithPage(final ISql sql, final Map<String, T> parameter) {
		return selectListWithPage(sql.getValue(), parameter);
	}

	private <T> Map<String, Object> selectListWithPage(final String sql, final Map<String, T> parameter) {
		final long start = System.currentTimeMillis();
		final Map<String, Object> newParameter = constructNewParameterWithPage(parameter);
		final List<Map<String, Object>> resultList = selectList(sql, newParameter);
		final Map<String, Object> resultMap = constructResultMapWithPage(newParameter, resultList);
		LOG.debug("use time : " + (System.currentTimeMillis() - start) + "ms");
		return resultMap;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		final Connection connection = context.getBean(DataSource.class).getConnection();
		final DatabaseMetaData databaseMetaData = connection.getMetaData();
		final ResultSet tableResultSet = databaseMetaData.getTables(null, "%", "%", new String[] { "TABLE" });
		while (tableResultSet.next()) {
			final String tableName = tableResultSet.getString("TABLE_NAME");
			final ResultSet columnResultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
			final List<String> columns = new ArrayList<>();
			while (columnResultSet.next()) {
				columns.add(columnResultSet.getString("COLUMN_NAME"));
			}
			tableColumns.put(tableName, columns);
		}
	}

	@Override
	public void setApplicationContext(final ApplicationContext context) {
		this.context = context;
	}

	private <T> Map<String, Object> constructNewParameter(final TableInfo tableInfo, final Map<String, T> parameter) {
		final Map<String, Object> newParameter = new HashMap<>(2);
		newParameter.put(_TABLE_NAME_, tableInfo.getTableName());
		if (parameter != null && !parameter.isEmpty()) {
			newParameter.put(_COLUMN_PARAMETERS_, constructColumnParameters(tableInfo, parameter));
			newParameter.put(_FROM_, parameter.get(_FROM_));
			newParameter.put(_COUNT_, parameter.get(_COUNT_));
		}
		return newParameter;
	}

	private <T> Map<String, Object> constructColumnParameters(final TableInfo tableInfo,
			final Map<String, T> parameter) {
		final Map<String, Object> columnParameters = new HashMap<>();
		for (final String key : parameter.keySet()) {
			if (tableColumns.get(tableInfo.getTableName()).contains(key)) {
				columnParameters.put(key, parameter.get(key));
			}
		}
		return columnParameters;
	}

	private <T> Map<String, Object> constructUpdateColumnParameters(final TableInfo tableInfo,
			final Map<String, T> parameter) {
		final Map<String, Object> updateColumnParameters = new HashMap<>();
		for (final String key : parameter.keySet()) {
			if (key.startsWith(SqlConstants.UPDATE_COLUMN_PREFIX)) {
				final String newKey = key.replaceAll("^" + SqlConstants.UPDATE_COLUMN_PREFIX, "");
				if (tableColumns.get(tableInfo.getTableName()).contains(newKey)) {
					updateColumnParameters.put(newKey, parameter.get(key));
				}
			}
		}
		return updateColumnParameters;
	}

	private <T> Map<String, Object> constructNewParameterWithPage(final Map<String, T> parameter) {
		Integer pageNumber = null;
		Integer displayCount = null;
		if (parameter.get(SqlConstants.PAGENUMBER) == null || parameter.get(SqlConstants.DISPLAYCOUNT) == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
			displayCount = DEFAULT_DISPLAY_COUNT;
		} else {
			pageNumber = Integer.parseInt(parameter.get(SqlConstants.PAGENUMBER).toString());
			displayCount = Integer.parseInt(parameter.get(SqlConstants.DISPLAYCOUNT).toString());
		}
		final Map<String, Object> newParameter = new HashMap<>(parameter.size() + 2);
		newParameter.putAll(parameter);
		newParameter.put(SqlConstants.PAGENUMBER, pageNumber);
		newParameter.put(SqlConstants.DISPLAYCOUNT, displayCount);
		newParameter.put(_FROM_, displayCount * (pageNumber - 1));
		newParameter.put(_COUNT_, displayCount);
		return newParameter;
	}

	private Map<String, Object> constructResultMapWithPage(final Map<String, Object> newParameter,
			final List<Map<String, Object>> resultList) {
		final Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(SqlConstants.PAGENUMBER, Integer.parseInt(newParameter.get(SqlConstants.PAGENUMBER).toString()));
		resultMap.put(SqlConstants.DISPLAYCOUNT,
				Integer.parseInt(newParameter.get(SqlConstants.DISPLAYCOUNT).toString()));
		resultMap.put(SqlConstants.TOTALRECORDNUMBER,
				sqlSessionTemplate.selectOne(SqlTemplateSql.SELECT_FOUND_ROWS.getValue(), newParameter));
		resultMap.put(SqlConstants.RESULTLIST, resultList);
		return resultMap;
	}

}

package com.accenture.fsadd.common;


/**
 * 共通定数クラス。
 *
 */
public final class FsaddConstant {
    public static final String DATE_FORMAT_YY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_YY_MM_DD = "yyyy/MM/dd";
    public static final String DATAE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
   // sonarqube mongo data key
    public static final String SONARQUBE_ACCESS_KEY = "component";
    public static final String SONARQUBE_MEASURES_URL = "api/measures/component?metricKeys=alert_status,vulnerabilities,bugs,code_smells,ncloc,lines,statements,files,tests,test_failures,test_success_density,coverage,duplicated_lines,duplicated_blocks,duplicated_files,duplicated_lines_density&componentKey=";
    public static final String SONARQUBE_ISSUES_KEY = "key";
    public static final String SONARQUBE_MEASURES_KEY = "measures";
    public static final String SONARQUBE_METRIC_KEY = "metric";
    public static final String SONARQUBE_VALUE_KEY = "value";
    public static final String INSERT_DATA_COL = "getAt";
}


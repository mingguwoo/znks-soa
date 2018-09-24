package com.sh.znks.common.base.excel;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * ����excel�������
 */
public class ExportExcelEntity {
    /**
     * http response���������
     */
    private HttpServletResponse response;
    /**
     * Ҫ�����Ķ���Ͳ������ü�ֵ�Դ洢��keyֵ��ftl�ļ��ж�Ӧ
     */
    private Map<String, Object> exportMap;
    /**
     * ģ�����ƣ�ftl�ļ���
     */
    private String templateName;
    /**
     * �����ļ����·��
     */
    private String exportPath;
    /**
     * �����ļ���
     */
    private String exportFileName;
    /**
     * Ĭ�ϱ��룬UTF-8 OR GBK
     */
    private String defaultEncoding;

    public ExportExcelEntity(HttpServletResponse response, Map<String, Object> exportMap, String templateName,
                             String exportPath, String exportFileName, String defaultEncoding) {
        this.response = response;
        this.exportMap = exportMap;
        this.templateName = templateName;
        this.exportPath = exportPath;
        this.exportFileName = exportFileName;
        this.defaultEncoding = defaultEncoding;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Map<String, Object> getExportMap() {
        return exportMap;
    }

    public void setExportMap(Map<String, Object> exportMap) {
        this.exportMap = exportMap;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getExportPath() {
        return exportPath;
    }

    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }
}

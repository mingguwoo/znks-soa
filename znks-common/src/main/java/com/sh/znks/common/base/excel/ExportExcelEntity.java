package com.sh.znks.common.base.excel;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 导出excel所需参数
 */
public class ExportExcelEntity {
    /**
     * http response，用于输出
     */
    private HttpServletResponse response;
    /**
     * 要导出的对象和参数，用键值对存储，key值与ftl文件中对应
     */
    private Map<String, Object> exportMap;
    /**
     * 模板名称（ftl文件）
     */
    private String templateName;
    /**
     * 导出文件存放路径
     */
    private String exportPath;
    /**
     * 导出文件名
     */
    private String exportFileName;
    /**
     * 默认编码，UTF-8
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

package com.sh.znks.common.base.excel;

import freemarker.template.Configuration;
import freemarker.template.Template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * ����excel������
 */
public class ExportExcelUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExportExcelUtil.class);
    private static final String RESPONSE_CONTENT_TYPE = "application/vnd.ms-excel;charset=";
    private static final String CHARSET_ISO = "ISO-8859-1";
    private static final String EXCEL_SUFFIX = ".xls";
    private static final String EXCEL_EXCEPTION = "[����excel�쳣]";
    private static final String EXCEL_CLOSE_EXCEPTION = "[close�쳣]";

    /**
     * ����excel
     * @param export ������� @see ExportExcelEntity
     */
    public static void exportExcel(ExportExcelEntity export) {
        ServletOutputStream sos = null;
        HttpServletResponse response = export.getResponse();
        Map<String, Object> exportMap = export.getExportMap(); //��Ҫ�������б�put��map�У�keyֵ��ftl�ļ����ֶ�Ӧ
        String defaultEncoding = export.getDefaultEncoding();
        String exportPath = export.getExportPath();
        String templateName = export.getTemplateName();
        String exportFileName = export.getExportFileName();
        try {
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding(defaultEncoding);
            configuration.setDirectoryForTemplateLoading(new File(exportPath));
            Template template = configuration.getTemplate(templateName, defaultEncoding);
            response.setContentType(RESPONSE_CONTENT_TYPE + defaultEncoding);
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(exportFileName.getBytes(), CHARSET_ISO) + EXCEL_SUFFIX);
            sos = response.getOutputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            OutputStreamWriter ow = new OutputStreamWriter(os, defaultEncoding);
            template.process(exportMap, ow);
            ow.flush();
            ow.close();
            sos.write(os.toByteArray());
        } catch (Exception e) {
            LOGGER.error(EXCEL_EXCEPTION + exportFileName, e);
        } finally {
            if (sos != null) {
                try {
                    sos.close();
                } catch (IOException e) {
                    LOGGER.error(EXCEL_CLOSE_EXCEPTION, e);
                }
            }
        }
    }
}
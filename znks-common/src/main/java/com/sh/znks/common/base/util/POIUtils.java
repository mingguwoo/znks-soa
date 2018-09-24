package com.sh.znks.common.base.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ��дEXCEL�ļ�
 */
@Component
public class POIUtils {


	private final String position_title = "title";
	private final String position_body = "body";


	/**
	 * �ж�excel�汾
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private Workbook openWorkbook(InputStream in, String filename)
			throws IOException {
		Workbook wb = null;
		if (filename.endsWith(".xlsx")) {
			wb = new XSSFWorkbook(in);// Excel 2007
		} else {
			wb = new HSSFWorkbook(in);// Excel 2003
		}
		return wb;
	}

	/**
	 * �����ļ�·���͹������±굼��Excel����
	 * @param fileName �ļ���
	 * @param sheetIndex �������±�
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getExcelData(MultipartFile file , String fileName, int sheetIndex) throws Exception {
		List<List<String>> dataLst = new ArrayList<List<String>>();
		Workbook wb = openWorkbook(file.getInputStream(), fileName);
		Sheet sheet = (Sheet) wb.getSheetAt(sheetIndex);// �л�������
		Row row = null;
		Cell cell = null;

		int totalRows = sheet.getPhysicalNumberOfRows();
		/** �õ�Excel������ */
		int totalCells = totalRows >= 1 && sheet.getRow(0) != null ? sheet
				.getRow(0).getPhysicalNumberOfCells() : 0;
		for (int r = 0; r < totalRows; r++) {
			row = sheet.getRow(r);
			if (row == null || curRowInsideNull(row, totalCells))
				continue;
			List<String> rowLst = new ArrayList<String>();
			for (int c = 0; c < totalCells; c++) {
				cell = row.getCell(c);
				String cellValue = "";
				if (null != cell) {
					// �������ж����ݵ�����
					switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC: // ����
							int cellStyle = cell.getCellStyle().getDataFormat();
							String cellStyleStr = cell.getCellStyle().getDataFormatString();
							if ("0.00_);[Red]\\(0.00\\)".equals(cellStyleStr)) {
								NumberFormat f = new DecimalFormat("#.##");
								cellValue = (f.format((cell.getNumericCellValue())) + "")
										.trim();
							} else if (HSSFDateUtil.isCellDateFormatted(cell)) {
								cellValue = HSSFDateUtil.getJavaDate(
										cell.getNumericCellValue()).toString();
							} else if ( cellStyle == 58 || cellStyle == 179 || "m\"��\"d\"��\";@".equals(cellStyleStr)) {
								// �����Զ������ڸ�ʽ��m��d��(ͨ���жϵ�Ԫ��ĸ�ʽid�����id��ֵ��58)
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								double value = cell.getNumericCellValue();
								Date date = org.apache.poi.ss.usermodel.DateUtil
										.getJavaDate(value);
								cellValue = sdf.format(date);
//						} else if ((cellStyle == 181 || cellStyle == 177|| cellStyle == 176)&& cellStyleStr.endsWith("@")) {
								//���ڼ� Excel�е������Զ����ʽ cellStyle���̶����ʲ���  "[$-804]aaaa;@"���ж�
							} else if ("[$-804]aaaa;@".equals(cellStyleStr)) {
								SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
								double value = cell.getNumericCellValue();
								Date date = org.apache.poi.ss.usermodel.DateUtil
										.getJavaDate(value);
								cellValue = sdf.format(date);

							} else {
								NumberFormat f = new DecimalFormat("#.##");
								cellValue = (f.format((cell.getNumericCellValue())) + "")
										.trim();
							}
							break;
						case HSSFCell.CELL_TYPE_STRING: // �ַ���
							cellValue = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
							cellValue = cell.getBooleanCellValue() + "";
							break;
						case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ
							try {
								cellValue = String.valueOf(cell.getNumericCellValue());
							} catch (IllegalStateException e) {
								try {
									cellValue = String.valueOf(cell.getRichStringCellValue());
								} catch (Exception e1) {
									cellValue="";
								}
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
//						cellValue = "";
							break;

						case HSSFCell.CELL_TYPE_ERROR: // ����
//						cellValue = "�Ƿ��ַ�";
							break;
						default:
//						cellValue = "δ֪����";
							break;
					}
				}
				rowLst.add(cellValue);
			}
			dataLst.add(rowLst);
		}
		return dataLst;
	}

	/**
	 * �жϵ�ǰ�������е�Ԫ���Ƿ�Ϊ��
	 *
	 * @param row
	 * @param totalCells
	 * @return
	 */
	private boolean curRowInsideNull(Row row, int totalCells) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < totalCells; i++) {
			row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL);
			Cell cell = row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL);
			if (cell != null) {
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						sb.append(cell.getStringCellValue().trim());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						sb.append(String.valueOf(cell.getNumericCellValue()));
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						sb.append(String.valueOf(cell.getBooleanCellValue()));
						break;
					case Cell.CELL_TYPE_FORMULA://�жϹ�ʽ���ɵĽ��
						String value = "";
						try {
							value = String.valueOf(cell.getNumericCellValue());
						} catch (IllegalStateException e) {
							try {
								value = String.valueOf(cell.getRichStringCellValue());
							} catch (Exception e1) {
								value="";
							}
						}
						sb.append(value);
						break;
					default:
						break;
				}
			}
		}
		if (sb.toString().trim().equals(""))
			return true;
		return false;
	}
}
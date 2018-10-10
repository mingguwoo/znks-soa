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
 * 读写EXCEL文件
 */
@Component
public class POIUtils {


	private final String position_title = "title";
	private final String position_body = "body";


	/**
	 * 判断excel版本
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
	 * 根据文件路径和工作薄下标导入Excel数据
	 * @param fileName 文件名
	 * @param sheetIndex 工作薄下标
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getExcelData(MultipartFile file , String fileName, int sheetIndex) throws Exception {
		List<List<String>> dataLst = new ArrayList<List<String>>();
		Workbook wb = openWorkbook(file.getInputStream(), fileName);
		Sheet sheet = (Sheet) wb.getSheetAt(sheetIndex);// 切换工作薄
		Row row = null;
		Cell cell = null;

		int totalRows = sheet.getPhysicalNumberOfRows();
		/** 得到Excel的列数 */
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
					// 以下是判断数据的类型
					switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC: // 数字
							int cellStyle = cell.getCellStyle().getDataFormat();
							String cellStyleStr = cell.getCellStyle().getDataFormatString();
							if ("0.00_);[Red]\\(0.00\\)".equals(cellStyleStr)) {
								NumberFormat f = new DecimalFormat("#.##");
								cellValue = (f.format((cell.getNumericCellValue())) + "")
										.trim();
							} else if (HSSFDateUtil.isCellDateFormatted(cell)) {
								cellValue = HSSFDateUtil.getJavaDate(
										cell.getNumericCellValue()).toString();
							} else if ( cellStyle == 58 || cellStyle == 179 || "m\"月\"d\"日\";@".equals(cellStyleStr)) {
								// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								double value = cell.getNumericCellValue();
								Date date = org.apache.poi.ss.usermodel.DateUtil
										.getJavaDate(value);
								cellValue = sdf.format(date);
//						} else if ((cellStyle == 181 || cellStyle == 177|| cellStyle == 176)&& cellStyleStr.endsWith("@")) {
								//星期几 Excel中的日期自定义格式 cellStyle不固定，故采用  "[$-804]aaaa;@"来判断
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
						case HSSFCell.CELL_TYPE_STRING: // 字符串
							cellValue = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
							cellValue = cell.getBooleanCellValue() + "";
							break;
						case HSSFCell.CELL_TYPE_FORMULA: // 公式
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
						case HSSFCell.CELL_TYPE_BLANK: // 空值
//						cellValue = "";
							break;

						case HSSFCell.CELL_TYPE_ERROR: // 故障
//						cellValue = "非法字符";
							break;
						default:
//						cellValue = "未知类型";
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
	 * 判断当前行内所有单元格是否为空
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
					case Cell.CELL_TYPE_FORMULA://判断公式生成的结果
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
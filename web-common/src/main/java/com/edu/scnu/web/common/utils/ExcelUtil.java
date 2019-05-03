package com.edu.scnu.web.common.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.multipart.MultipartFile;

import lombok.Cleanup;

/**
 * 	web的excel导入导出工具类，自己个人的实现
 * 	TODO 此类还有很多不妥之处，亟待完善
 * @author Autom
 *
 */
public class ExcelUtil {

	/**
	 * 	导出excel功能
	 * @param title  excel表头名称和文件名称
	 * @param rowName	excel首行每一列的文字标题
	 * @param dataList	列表数据，对于一般的List<T>对象，可以通过 本类的 @see{bean2ObjList} 方法进行转换
	 * @param response  Http请求的响应对象
	 * @throws Exception
	 */
	public static void export(String title, String[] rowName, List<Object[]> dataList, HttpServletResponse response) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		
		HSSFRow rowm = sheet.createRow(0);
		HSSFCell cellTitle = rowm.createCell(0);
		HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);
		HSSFCellStyle style = getStyle(workbook);
		
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));  
        cellTitle.setCellStyle(columnTopStyle);
        cellTitle.setCellValue(title);
        
        // 定义所需列数
        int columnNum = rowName.length;
        HSSFRow rowRowName = sheet.createRow(2);
        
        for(int n=0;n<columnNum;n++){
            HSSFCell  cellRowName = rowRowName.createCell(n);                //创建列头对应个数的单元格
            cellRowName.setCellType(CellType.STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
            cellRowName.setCellValue(text);                                    //设置列头单元格的值
            cellRowName.setCellStyle(columnTopStyle);                        //设置列头单元格样式
        }
        
        for(int i=0;i<dataList.size();i++){
            
            Object[] obj = dataList.get(i);//遍历每个对象
            HSSFRow row = sheet.createRow(i+3);//创建所需的行数
            
            for(int j=0; j<obj.length; j++){
            	Object col = obj[j];
                HSSFCell  cell = null;   //设置单元格的数据类型
                if(col != null){
                	if (col instanceof String) {
                		cell = row.createCell(j, CellType.STRING);
                		cell.setCellValue(col.toString());//设置单元格的值
                	} else if (col instanceof Number) {
                		cell = row.createCell(j, CellType.NUMERIC);
                		cell.setCellValue(col.toString());//设置单元格的值
                	} else if (col instanceof Date) {
                		cell = row.createCell(j, CellType.BLANK);
                		cell.setCellValue((Date) col);
                	} else if (col instanceof Boolean) {
                		cell = row.createCell(j, CellType.BOOLEAN);
                		cell.setCellValue((Boolean) col);
                	} else {
                		cell = row.createCell(j, CellType.STRING);
                		cell.setCellValue(col.toString());//设置单元格的值
                	}
                } else {
                	cell = row.createCell(j, CellType.BLANK); 
                	cell.setCellValue("");
                }
                cell.setCellStyle(style);                                    //设置单元格样式
            }
        }
        //让列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < columnNum; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellTypeEnum().equals(CellType.STRING)) {
                        int length = currentCell.getStringCellValue().length();
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            if(colNum == 0){
                sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
            }else{
                sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
            }
        }
        
        if(workbook !=null){
        	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = title + df.format(new Date()) + ".xls";
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String headStr = "attachment; filename=\"" + codedFileName + "\"";
            // response.setContentType("APPLICATION/OCTET-STREAM");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", headStr);
            OutputStream out = response.getOutputStream();
            workbook.write(out);
        }   
        
	}
	
	private static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
		 // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)14);
        //字体加粗
        font.setBold(true);
        //设置字体名字 
        font.setFontName("Courier New");
        //设置样式; 
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框; 
        // style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;  
        // style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;   
        // style.setBorderLeft(BorderStyle.);
        //设置左边框颜色; 
        // style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框; 
        // style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色; 
        // style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框; 
        // style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;  
        // style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;  
        style.setFont(font);
        //设置自动换行; 
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;  
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐; 
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        
        return style;
	}
	
    private static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
    	 // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //字体加粗
        font.setBold(false);
        //设置字体名字 
        font.setFontName("Courier New");
        //设置样式; 
        HSSFCellStyle style = workbook.createCellStyle();
        //在样式用应用设置的字体;  
        style.setFont(font);
        //设置自动换行; 
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;  
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐; 
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        
        return style;
  }
	
    /**
     * 	导入excel，将Excel表中的数据转换为List<T>的bean对象。
     * @param excelFile		上传的Excel文件对象
     * @param fieldNames	每一列对应于bean实体的字段名
     * @param clazz			bean实体的类型
     * @return				List<T>的bean对象
     * @throws Exception
     */
	public static<T> List<T> importExcel(MultipartFile excelFile, String[] fieldNames, Class<T> clazz) throws Exception {
    	String filename = excelFile.getOriginalFilename();
    	String ext = FilenameUtils.getExtension(filename);
    	if(!"xls".equals(ext)) {
    		throw new RuntimeException("导入的文件非xls格式的文件");
    	}
    	
    	List<T> result = new ArrayList<>();
    	
    	@Cleanup InputStream inputStream = excelFile.getInputStream();
    	
    	@Cleanup HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
    	Sheet sheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
    	
    	for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
    		Row row = sheet.getRow(i);
    		T bean = (T) clazz.newInstance();
    		for(int j = 0; j < fieldNames.length; j++) {
    			String fieldName = fieldNames[j];
    			Cell cell = row.getCell(j);
    			Field field = clazz.getDeclaredField(fieldName);
    			Class<?> type = field.getType();
    			String setMethodName = "set" + firstUpperCase(fieldName);
    			Method setMethod = clazz.getMethod(setMethodName, type);
    			
    			Method cellMethod = cell.getClass().getMethod(getCellTypeMethodName(type));
    			Object cellValue = cellMethod.invoke(cell);
    			setMethod.invoke(bean, numericConvertor(type, cellValue));
    		}
    		result.add(bean);
    	}
    	
    	return result;
    }
	
	private static String getCellTypeMethodName(Class<?> type) {
		if(Number.class.isAssignableFrom(type)) {
			return "getNumericCellValue";
		} else if (type.equals(String.class)) {
			return "getStringCellValue";
		} else if (type.equals(Date.class)) {
			return "getDateCellValue";
		} else if (type.equals(Boolean.class)) {
			return "getBooleanCellValue";
		}
		return null;
	}
	
	public static Object numericConvertor(Class<?> clazz, Object value) {
		if (value instanceof Double) {
			Double v = (Double) value;
			if(clazz.equals(Integer.class)) {
				return Integer.valueOf((int) v.intValue());
			} else if(clazz.equals(Double.class)) {
				return Double.valueOf(v.doubleValue());
			}
		}
		
		return value;
	}
    
    /**
     * 	转换对象格式，用于导出，使用bean对象的get方法获取字段
     * @return
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    public static<T> List<Object[]> bean2ObjList(List<T> beanList, String[] methodNames) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	List<Object[]>  dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        if(beanList != null && beanList.size() != 0) {
	        for(T bean: beanList) {
	        	objs = new Object[methodNames.length];
	        	for(int i = 0; i < objs.length; ++i) {
	        		String methodName = "get" + firstUpperCase(methodNames[i]);
	        		Method method = bean.getClass().getMethod(methodName);
	        		objs[i] = method.invoke(bean);
	        	}
	        	dataList.add(objs);
	        }
        }
        return dataList;
    }
    
    /**
     * 	首字母大写的方法，没有任何校验，因此使用时请自己注意下.....
     * @param str
     * @return
     */
    public static String firstUpperCase(String str) {
    	char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}

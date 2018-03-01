package com.hz.tt.util.excel;

import android.content.Context;
import android.util.SparseArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class WriteExcelUtils {
    /**
     *excle表格的后缀
     */
    public static final String SUFFIX=".xls";
    public static final String DIR_FILE_NAME="EXCLE_DIR";

    public static final String FIRST_ROW_CONTENT="2018年3月";
    public static final  String[]  SENCOND_VALUES={"1","2"};
    public static final String[] THREE_VALUES={"编码","国家","产地","品类","数量","位置"};
    public static final String[][] FOUR_VALUES={
            {"336002146501","中国","贵州","干性","25","杭州" }

    };

    private static Workbook createWorkbook(){
        return new HSSFWorkbook();
    }

    public static boolean writeExecleToFile(Context context,String[][] excelarr){
        //创建工作簿
        Workbook workbook=createWorkbook();
        //创建execl中的一个表
        Sheet sheet= workbook.createSheet();
//         setSheet(sheet);

        /**
         * 创建第一行
         */
        Row headerRow=sheet.createRow(0);
        // 设置第一行：48pt的字体的内容
        headerRow.setHeightInPoints(60);
        //创建第一行中第一单元格
        Cell cell=headerRow.createCell(0);
        cell.setCellValue(FIRST_ROW_CONTENT);
//        cell.setCellStyle(cellStyles.get(0));
        mergingCells(sheet, CellRangeAddress.valueOf("$A$1:$F$1"));

        //创建第三行
        Row threedRow=sheet.createRow(2);
        threedRow.setHeightInPoints(40);
        for (int j=0;j<6;++j){
            Cell cell1=threedRow.createCell(j);
            cell1.setCellValue(THREE_VALUES[j]);
        }

        //创建第四行
        for (int i=0;i<excelarr.length;++i){
            Row fourRow=sheet.createRow(3+i);
            fourRow.setHeightInPoints(45);
            for (int j=0;j<excelarr[i].length;++j){
                Cell cell1=fourRow.createCell(j);
//                cell1.setCellValue(FOUR_VALUES[i][j]);
                cell1.setCellValue(excelarr[i][j]);
            }
        }

        MyUtils.FILE_PATH=getFile(context);
        writeFile(workbook,MyUtils.FILE_PATH);

        return true;
    }
    /**
     * 合并cell单元格
     *
     *  CellRangeAddress构造器中参数：
     *    参数1：first row(0-based)
     *    参数2：last row(0-based)
     *    参数3：first column(0-based)
     *    参数4：last column(0-based)
     *
     * @param sheet
     * @param cellRangeAddress
     */
    private static  void mergingCells(Sheet sheet, CellRangeAddress cellRangeAddress){
        sheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 设置Sheet表
     * @param sheet
     */
    private static void setSheet(Sheet sheet){
        // turn off gridlines（关闭网络线）
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(false);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        //只对HSSF需要用到的
        sheet.setAutobreaks(true);
        printSetup.setFitHeight((short)1);
        printSetup.setFitWidth((short)1);
    }

    /**
     * 获取指定文件
     * @param context
     * @return
     */
    public static  String getFile(Context context){
        File dirFile=MyUtils.getCacheFile(context,DIR_FILE_NAME);
        if(dirFile!=null&&!dirFile.exists()){
            dirFile.mkdirs();
        }
        return  dirFile.getAbsolutePath()+File.separator+new Date().getTime()+SUFFIX;
    }

    /**
     * 创建各种不同单元格特征,根据个人需求不同而定 。
     * @return
     */
    private static SparseArray<CellStyle> creatCellStyles(Workbook workbook){
        SparseArray<CellStyle> array=new SparseArray<>();
        //第一行的单元格特征
        CellStyle cellStyle0=createBorderedStyle(workbook);
        Font font0=creatFont(workbook);
        font0.setFontHeightInPoints((short) 14);
        font0.setBold(true);
        cellStyle0.setFont(font0);
        array.put(0,cellStyle0);

        //第二行的单元格特征
        CellStyle cellStyle1=createBorderedStyle(workbook);
        Font font1=creatFont(workbook);
        font1.setBold(true);
        cellStyle1.setFont(font1);
        array.put(1,cellStyle1);
        //第三行的单元格特征
        array.put(2,cellStyle1);
        //第四行的单元格特征

        CellStyle cellStyle3= workbook.createCellStyle();
        cellStyle3.setAlignment(HorizontalAlignment.CENTER);
        cellStyle3.setVerticalAlignment(VerticalAlignment.TOP);
        //使用换行符，需要开启wrap. 换行的文本用“\n”连接
        cellStyle3.setWrapText(true);
        array.put(3,cellStyle3);
        return    array;
    }
    /**
     * 设置表格的内容到四边的距离,表格四边的颜色
     *
     * 对齐方式：
     *   水平： setAlignment();
     *   竖直：setVerticalAlignment()
     *
     * 四边颜色：
     *    底边：  cellStyle.setBottomBorderColor()
     *
     * 四边边距：
     *
     * 填充：
     *
     * 缩进一个字符：
     *   setIndention()
     *
     * 内容类型：
     *   setDataFormat()
     * @param workbook
     * @return
     */
    private  static CellStyle createBorderedStyle(Workbook workbook){
        CellStyle cellStyle= workbook.createCellStyle();
        //对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
/*     //重新设置单元格的四边颜色
    BorderStyle thin=BorderStyle.THIN;
    short blackColor_Index=IndexedColors.BLACK.getIndex();
    cellStyle.setBottomBorderColor(blackColor_Index);
    cellStyle.setBorderBottom(thin);
    cellStyle.setTopBorderColor(blackColor_Index);
    cellStyle.setBorderTop(thin);
    cellStyle.setRightBorderColor(blackColor_Index);
    cellStyle.setBorderRight(thin);
    cellStyle.setLeftBorderColor(blackColor_Index);
    cellStyle.setBorderLeft(thin);*/
        return  cellStyle;
    }
    /**
     * 创建Font
     *
     * 注意点：excle工作簿中字体最大限制为32767，应该重用字体，而不是为每个单元格都创建字体。
     *
     *  其API:
     *    setBold():设置粗体
     *    setFontHeightInPoints():设置字体的点数
     *    setColor():设置字体颜色
     *    setItalic():设置斜体
     * @param workbook
     * @return
     */
    private static Font creatFont(Workbook workbook){
        Font font=workbook.createFont();
        return font;
    }
    /**
     *  将Excle表格写入文件中
     *
     * @param workbook
     * @param fileName
     */
    private static void   writeFile(Workbook workbook ,String fileName){
        FileOutputStream outputStream=null;
        try{
            outputStream=new FileOutputStream(fileName);
            workbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(outputStream!=null){
                    outputStream.close();
                }
                if(workbook!=null){
                    workbook.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
package com.cesgroup.prison.yjct.util;

import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.emergency.handle.record.entity.EmerHandleRecord;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordMember.entity.EmerHandleRecordMember;
import com.cesgroup.prison.emergency.handle.recordStep.entity.EmerHandleRecordStep;
import com.cesgroup.prison.yjct.entity.GzzcyEntity;
import com.cesgroup.prison.yjct.entity.YjyabgEntity;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 陈才岛
 * @create 2019-08-15 15:00
 */
public class ExprotSTUtil {
    public  XWPFDocument exportSTToWord(YjyabgEntity yjyabgEntity) throws IOException{
        //创建document对象
        XWPFDocument document = new XWPFDocument();
        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("预案报告");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setBold(true);  //设置加粗
        titleParagraphRun.setFontSize(20);
        List<List<GzzcyEntity>> workgroup = yjyabgEntity.getWorkgroup();
        //动态生成表格的行数
        int x=0;
        int m=yjyabgEntity.getWorkgroup().size();
        for(int i=0;i<workgroup.size();i++){
            List<GzzcyEntity> gzzcyEntities = workgroup.get(i);
            if(gzzcyEntities.size()==0){
                m--;
            }
            int size = gzzcyEntities.size();
            x+=size;
        }
        //创建表格
        int row = m+x+6;
        System.out.println("表格的行数："+row);
        //int column = 4;
        XWPFTable table = document.createTable();
        //列宽自动
        CTTblWidth infoTableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));
        //创建一个段落
        XWPFParagraph p = document.createParagraph();
        //第一行
        XWPFTableRow row1 = table.getRow(0);
        XWPFTableCell cell1 = row1.getCell(0);
        XWPFTableCell cell2 = row1.addNewTableCell();
        XWPFTableCell cell3 = row1.addNewTableCell();
        XWPFTableCell cell4 = row1.addNewTableCell();
        //跨列合并  合并第一行 第1列到第4列
        mergeCellsHorizontal(table,0,0,3);
        p = cell1.addParagraph();
        XWPFRun cell1Run= p.createRun();
        cell1Run.setText("基本信息：");
        cell1Run.setFontSize(12);
        cell1Run.setBold(true);
        cell1Run.setColor("000000");
        //表格第二行
        XWPFTableRow row2 = table.createRow();
        XWPFTableCell row2Cell1 = row2.getCell(0);
        XWPFTableCell row2Cell2 = row2.getCell(1);
        XWPFTableCell row2Cell3 = row2.getCell(2);
        XWPFTableCell row2Cell4 = row2.getCell(3);
        XWPFRun row2Run1 = row2Cell1.addParagraph().createRun();
        row2Run1.setText("报警地点：");
        row2Run1.setFontSize(12);
        row2Run1.setColor("000000");
        XWPFRun row2Run2 = row2Cell2.addParagraph().createRun();
        row2Run2.setText(yjyabgEntity.getAlertArea());
        XWPFRun row2Run3 = row2Cell3.addParagraph().createRun();
        row2Run3.setText("报警时间：");
        row2Run3.setFontSize(12);
        row2Run3.setColor("000000");
        XWPFRun row2Run4 = row2Cell4.addParagraph().createRun();
        row2Run4.setText(yjyabgEntity.getAlertTime());
        //表格第三行
        XWPFTableRow row3 = table.createRow();
        XWPFTableCell row3cell1 = row3.getCell(0);
        XWPFTableCell row3cell2 = row3.getCell(1);
        XWPFTableCell row3cell3 = row3.getCell(2);
        XWPFTableCell row3cell4 = row3.getCell(3);
        XWPFRun row3cell1Run = row3cell1.addParagraph().createRun();
        row3cell1Run.setText("预案详情：");
        row3cell1Run.setFontSize(12);
        row3cell1Run.setBold(true);
        row3cell1Run.setColor("000000");
        //跨列合并  合并第三行 第1列到第4列
        mergeCellsHorizontal(table,2,0,3);
        //表格第四行
        XWPFTableRow row4 = table.createRow();
        XWPFTableCell row4Cell1 = row4.getCell(0);
        XWPFTableCell row4Cell2 = row4.getCell(1);
        XWPFTableCell row4Cell3 = row4.getCell(2);
        XWPFTableCell row4Cell4 = row4.getCell(3);
        XWPFRun row4Cell1Run = row4Cell1.addParagraph().createRun();
        row4Cell1Run.setText("预案名称：");
        row4Cell1Run.setFontSize(12);
        row4Cell1Run.setColor("000000");
        XWPFRun row4Cell2Run = row4Cell2.addParagraph().createRun();
        row4Cell2Run.setText(yjyabgEntity.getPlanName());
        XWPFRun row4Cell3Run = row4Cell3.addParagraph().createRun();
        row4Cell3Run.setText("启动时间：");
        row4Cell3Run.setFontSize(12);
        row4Cell3Run.setColor("000000");
        XWPFRun row4Cell4Run = row4Cell4.addParagraph().createRun();
        row4Cell4Run.setText(yjyabgEntity.getCreateTime());
        //表格第五行
        XWPFTableRow row5 = table.createRow();
        XWPFTableCell row5Cell1 = row5.getCell(0);
        XWPFTableCell row5Cell2 = row5.getCell(1);
        XWPFTableCell row5Cell3 = row5.getCell(2);
        XWPFTableCell row5Cell4 = row5.getCell(3);
        XWPFRun row5Cell1run = row5Cell1.addParagraph().createRun();
        row5Cell1run.setText("接警人：");
        row5Cell1run.setFontSize(12);
        row5Cell1run.setColor("000000");
        XWPFRun row5Cell2run = row5Cell2.addParagraph().createRun();
        row5Cell2run.setText(yjyabgEntity.getReceiveAlarmPolice());
        XWPFRun row5Cell3run = row5Cell3.addParagraph().createRun();
        row5Cell3run.setText("接警时间：");
        row5Cell3run.setFontSize(12);
        row5Cell3run.setColor("000000");
        XWPFRun row5Cell4run = row5Cell4.addParagraph().createRun();
        row5Cell4run.setText(yjyabgEntity.getReceiveTime());
        //动态生成行
        int count=0;
        int n=0;
        for(int i=0;i<workgroup.size();i++){
            List<GzzcyEntity> gzzcyEntities = workgroup.get(i);
            if(gzzcyEntities.size()>0){
                XWPFTableRow gzz = table.createRow();
                XWPFTableCell RowiCell1 = gzz.getCell(0);
                XWPFTableCell RowiCell2 = gzz.getCell(1);
                XWPFTableCell RowiCell3 = gzz.getCell(2);
                XWPFTableCell RowiCell4 = gzz.getCell(3);
                XWPFRun RowiCell1run1 = RowiCell1.addParagraph().createRun();
                RowiCell1run1.setText("工作组"+(i+1)+":");
                RowiCell1run1.setFontSize(12);
                RowiCell1run1.setBold(true);
                RowiCell1run1.setColor("000000");
                n++;
                int size = gzzcyEntities.size();
                if(n==1){ //第一次进入
                    //跨列合并  合并第6行 第2列到第4列
                    mergeCellsHorizontal(table,5,1,3);
                    count+=size;
                }
                if(n>1){//第二次进入
                    //跨列合并  合并第6行 第2列到第4列
                    mergeCellsHorizontal(table,5+count+1,1,3);
                    count=count+size+1;
                }
                for(int j=0;j<size;j++){
                    XWPFTableRow gzzcy = table.createRow();
                    XWPFTableCell RowjCell1 = gzzcy.getCell(0);
                    XWPFTableCell RowjCell2 = gzzcy.getCell(1);
                    XWPFTableCell RowjCell3 = gzzcy.getCell(2);
                    XWPFTableCell RowjCell4 = gzzcy.getCell(3);
                    XWPFRun RowjCell1run = RowjCell1.addParagraph().createRun();
                    RowjCell1run.setText("姓名：");
                    RowjCell1run.setFontSize(12);
                    RowjCell1run.setColor("000000");
                    XWPFRun RowjCell2run = RowjCell2.addParagraph().createRun();
                    RowjCell2run.setText(gzzcyEntities.get(j).getWgmPoliceName());
                    XWPFRun RowjCell3run = RowjCell3.addParagraph().createRun();
                    RowjCell3run.setText("联系方式：");
                    RowjCell3run.setFontSize(12);
                    RowjCell3run.setColor("000000");
                    XWPFRun RowjCell4run = RowjCell4.addParagraph().createRun();
                    RowjCell4run.setText(gzzcyEntities.get(j).getPbdPhone());
                }
            }
        }
        //最后一行
        XWPFTableRow lastRow = table.createRow();
        XWPFTableCell lastRowcell1 = lastRow.getCell(0);
        XWPFTableCell lastRowcell2 = lastRow.getCell(1);
        XWPFTableCell lastRowcell3 = lastRow.getCell(2);
        XWPFTableCell lastRowcell4 = lastRow.getCell(3);
        XWPFRun lastRowcell1run = lastRowcell1.addParagraph().createRun();
        lastRowcell1run.setText("过程记录：");
        lastRowcell1run.setBold(true);
        lastRowcell1run.setFontSize(12);
        lastRowcell1run.setColor("000000");
        XWPFRun lastRowcell2run = lastRowcell2.addParagraph().createRun();
        lastRowcell2run.setText(yjyabgEntity.getProcessRecord());
        //跨列合并  合并最后一行 第2列到第4列
        int a=row-1;
        mergeCellsHorizontal(table,a,1,3);

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        //设置为右对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
        FileOutputStream out=null;
       return document;
    }

    /***
     *  合并行
     * @param table
     * @param col  所合并的列（哪一列需要合并行）
     * @param fromRow 起始行
     * @param toRow   终止行
     */
    private void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if ( rowIndex == fromRow ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }


    /***
     * 合并列
     * @param table
     * @param row 所合并的行（哪一行需要合并列）
     * @param fromCell  起始列
     * @param toCell   终止列
     */
    private  void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if ( cellIndex == fromCell ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    /***
     * 导出word 设置行宽
     * @param table
     * @param width
     */
    private  void setTableWidth(XWPFTable table,String width){
        CTTbl ttbl = table.getCTTbl();
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        CTJc cTJc=tblPr.addNewJc();
        cTJc.setVal(STJc.Enum.forString("center"));
        tblWidth.setW(new BigInteger(width));
        tblWidth.setType(STTblWidth.DXA);
    }






/**
 * liyanzhe
 *
 * */

    public  XWPFDocument exportSTToWord1(EmerHandleRecord emerHandleRecord, AlarmRecordEntity alarmRecordEntity) throws IOException{
        List<EmerHandleRecordStep> emerHandleRecordSteps = emerHandleRecord.getHandleStepList();
        //动态生成表格的行数
        int x=0;//有多少人收到的行数
        int m=emerHandleRecord.getHandleStepList().size()*2;//有几步和过程记录的行数
        for (EmerHandleRecordStep emerHandleRecordStep : emerHandleRecordSteps) {
            List<EmerHandleRecordGroup> emerHandleRecordGroups = emerHandleRecordStep.getHandleGroupList();
            if (emerHandleRecordGroups!=null) {
                for (EmerHandleRecordGroup emerHandleRecordGroup : emerHandleRecordGroups) {
                    if(emerHandleRecordGroup.getHandleMemberList()!=null){
                        x+= emerHandleRecordGroup.getHandleMemberList().size();
                    }else{
                        x++;
                    }
                }
            }else{
                x++;
            }
        }
        //表格总行数
        int row = m+x+4;
        System.out.println("表格的行数："+row);
        //date转string
        String pattern2 = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf2 = new SimpleDateFormat(pattern2);
        //创建document对象
        XWPFDocument document = new XWPFDocument();
        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("应急处置报告");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setBold(true);  //设置加粗
        titleParagraphRun.setFontSize(20);
        // 创建表格
        XWPFTable table = document.createTable(row, 4);
        //列宽自动
        CTTblWidth infoTableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));
        //表格样式
        tableBorderStyle(table);
        // 第一行
        List<XWPFTableCell> tableCells1 = table.getRow(0).getTableCells();
        tableTextStyle(tableCells1,0,"报警地点",true,false);
        tableTextStyle(tableCells1,1,emerHandleRecord.getAlarmAreaName(),false,false);
        tableTextStyle(tableCells1,2,"报警时间",true,false);
        tableTextStyle(tableCells1,3,sdf2.format(alarmRecordEntity.getArdAlertTime()),false,false);
        // 第二行
        List<XWPFTableCell> tableCells2 = table.getRow(1).getTableCells();
        tableTextStyle(tableCells2,0,"接警人",true,false);
        tableTextStyle(tableCells2,1,alarmRecordEntity.getArdReceiveAlarmPolice(),false,false);
        tableTextStyle(tableCells2,2,"接警时间",true,false);
        tableTextStyle(tableCells2,3,sdf2.format(alarmRecordEntity.getArdReceiveTime()),false,false);//alarmRecordEntity.getArdReceiveTime()这个里面没值，bug
        // 第三行
        List<XWPFTableCell> tableCells3 = table.getRow(2).getTableCells();
        tableTextStyle(tableCells3,0,"预案名称",true,false);
        tableTextStyle(tableCells3,1,emerHandleRecord.getPreplanName(),false,false);
        tableTextStyle(tableCells3,2,"启动时间",true,false);
        tableTextStyle(tableCells3,3,sdf2.format(emerHandleRecord.getStartTime()),false,false);

        int num = 0;//计算行数
        int firstnum = 0;//小队名称开始行
            for(int i=0;i<emerHandleRecordSteps.size();i++){
                EmerHandleRecordStep emerHandleRecordStep = emerHandleRecordSteps.get(i);
                //第几步那行的合并
                mergeCellsHorizontal(table,3+num,0,3);
                String stepName = emerHandleRecordStep.getStepName();//第几步
                //第几步那行赋值
                List<XWPFTableCell> tableCellsi = table.getRow(3+num).getTableCells();
                tableTextStyle(tableCellsi,0,stepName,true,true);
                String handleContent = emerHandleRecordStep.getHandleContent();//过程记录
                List<EmerHandleRecordGroup> emerHandleRecordGroups = emerHandleRecordStep.getHandleGroupList();
                if (emerHandleRecordGroups!=null) {
                    for (EmerHandleRecordGroup emerHandleRecordGroup : emerHandleRecordGroups) {
                        String groupName = emerHandleRecordGroup.getGroupName();//小队名称
                        if(emerHandleRecordGroup.getHandleMemberList()!=null){
                            List<EmerHandleRecordMember> emerHandleRecordMembers= emerHandleRecordGroup.getHandleMemberList();
                            for(int l=0;l< emerHandleRecordMembers.size();l++){
                                EmerHandleRecordMember emerHandleRecordMember = emerHandleRecordMembers.get(l);
                                String meberName =  emerHandleRecordMember.getMemberName();//方式那列名称
                                String callNo = emerHandleRecordMember.getCallNo();//呼叫号
                                String callresult = emerHandleRecordMember.getCallResult();//呼叫内容已发送
                                List<XWPFTableCell> tableCellsy = table.getRow(4+num).getTableCells();
                                if(l==0){
                                    //第一次进入，给小队名称赋值
                                    tableTextStyle(tableCellsy,0,groupName,true,false);//在有多个成员的时候给小队名称赋一个值
                                    //记录第一次的行数，为多个成员时，合并小队名称有初始行
                                    firstnum = 4+num;
                                }
                                //方式，呼叫号，呼叫内容已发送
                                tableTextStyle(tableCellsy,1,meberName,false,false);
                                tableTextStyle(tableCellsy,2,callNo,false,false);
                                tableTextStyle(tableCellsy,3,callresult,false,false);

                                if(l==emerHandleRecordMembers.size()-1){
                                    //多个成员时，合并小队名称
                                    mergeCellsVertically(table, 0, firstnum, 4+num);
                                }
                                num++;
                            }
                            //过程记录
                            List<XWPFTableCell> tableCellsz = table.getRow(4+num).getTableCells();
                            tableTextStyle(tableCellsz,0,"过程记录",true,false);
                            mergeCellsHorizontal(table,4+num,1,3);
                            tableTextStyle(tableCellsz,1,handleContent,false,false);
                        }else{
                            //没有成员的时候，显示的小队名称
                            List<XWPFTableCell> tableCellsy = table.getRow(4+num).getTableCells();
                            tableTextStyle(tableCellsy,0,groupName,true,false);
                            //过程记录
                            List<XWPFTableCell> tableCellsz = table.getRow(5+num).getTableCells();
                            tableTextStyle(tableCellsz,0,"过程记录",true,false);
                            mergeCellsHorizontal(table,5+num,1,3);
                            tableTextStyle(tableCellsz,1,handleContent,false,false);
                            num++;
                        }
                        num++;
                    }
                }else{
                    num++;
                }
                num++;
            }
            //最后一行
        String experience = emerHandleRecord.getExperience();//经验总结
        List<XWPFTableCell> tableCellsl = table.getRow(row-1).getTableCells();
        tableTextStyle(tableCellsl,0,"经验总结",true,false);
        mergeCellsHorizontal(table,row-1,1,3);
        tableTextStyle(tableCellsl,1,experience,false,false);
        return document;

    }


    private static void tableBorderStyle(XWPFTable table){
        //表格属性
        CTTblPr tablePr = table.getCTTbl().addNewTblPr();
        //表格宽度
        CTTblWidth width = tablePr.addNewTblW();
        width.setW(BigInteger.valueOf(8000));
        //表格颜色
        CTTblBorders borders=table.getCTTbl().getTblPr().addNewTblBorders();
        //表格内部横向表格颜色
        CTBorder hBorder=borders.addNewInsideH();
        hBorder.setVal(STBorder.Enum.forString("single"));
        hBorder.setSz(new BigInteger("1"));
        hBorder.setColor("dddddd");
        //表格内部纵向表格颜色
        CTBorder vBorder=borders.addNewInsideV();
        vBorder.setVal(STBorder.Enum.forString("single"));
        vBorder.setSz(new BigInteger("1"));
        vBorder.setColor("dddddd");
        //表格最左边一条线的样式
        CTBorder lBorder=borders.addNewLeft();
        lBorder.setVal(STBorder.Enum.forString("single"));
        lBorder.setSz(new BigInteger("1"));
        lBorder.setColor("dddddd");
        //表格最左边一条线的样式
        CTBorder rBorder=borders.addNewRight();
        rBorder.setVal(STBorder.Enum.forString("single"));
        rBorder.setSz(new BigInteger("1"));
        rBorder.setColor("dddddd");
        //表格最上边一条线（顶部）的样式
        CTBorder tBorder=borders.addNewTop();
        tBorder.setVal(STBorder.Enum.forString("single"));
        tBorder.setSz(new BigInteger("1"));
        tBorder.setColor("dddddd");
        //表格最下边一条线（底部）的样式
        CTBorder bBorder=borders.addNewBottom();
        bBorder.setVal(STBorder.Enum.forString("single"));
        bBorder.setSz(new BigInteger("1"));
        bBorder.setColor("dddddd");
    }


    private static void tableTextStyle(List<XWPFTableCell> tableCells1,int index,String text,boolean bold,boolean bjColor){
        tableTextStyle(tableCells1.get(index),text,bold,bjColor);
    }

    private static void tableTextStyle(XWPFTableCell tableCell,String text,boolean bold,boolean bjColor){

        if(bjColor){
            tableCell.setColor("DBDBDB");//设置表格背景颜色
        }



        tableCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);//垂直居中

        XWPFParagraph p0 = tableCell.addParagraph();
        tableCell.setParagraph(p0);
        XWPFRun r0 = p0.createRun();
        // 设置字体是否加粗
        r0.setBold(bold);
        r0.setFontSize(8);

        // 设置使用何种字体
        r0.setFontFamily("微软雅黑");
        // 设置上下两行之间的间距
        //r0.setTextPosition(8);
        r0.setColor("333333");
        r0.setText(text);
    }





}
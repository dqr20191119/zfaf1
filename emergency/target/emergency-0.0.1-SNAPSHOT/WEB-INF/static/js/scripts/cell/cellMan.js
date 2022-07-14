
    /**
     * Cell管理类
     * <p>Copyright: Copyright (c) 2004 CES</p>
     * <p>Company: CES</p>
     * <p>@author Reamy</p>
     * <p>@version 1.0</p>
     */
    function CELL () {
        this.initFlag = false ; // 是否已经初始化打印参数标志 boolean

        this.cellObj = null ; // Object 装载Cell对象的引用

        this.rowBgColor = -1 ; // 行的背景颜色
        this.changRowUseColor = 2 ; // 相邻行区分的颜色索引
        this.nomalRowBgColor = -1 ; // 行的背景默认颜色

        // 打印参数
        this.printArgument = function () {
            paperSize = 9 ; // 打印纸张的大小 long
            scale = 1 ; // 打印比例 double
            orient = 0 ; // 打印的进纸方向 long
            head = '||' ; // 打印页的页眉内容 string
            foot = '||' ; // 打印页的页脚内容 string
            printername = '' ; // 打印机名称 string
            sideTop = 0 ; // 表头的页边距 long
            sideBottom = 0 ; // 表底的页边距 long
            sideLeft = 0 ; // 表左的页边距 long
            sideRight = 0 ; // 表右的页边距 long
            label = 0 ; // 打印时是否打印行标和列标 long
            printPara = 0 ; // 打印时的打印参数 long
        }

        // 图表参数
        this.chartArgument = function () {
            topCol = null ; // 图表数据区域的起始列号 int
            topRow = null ; // 图表数据区域的起始行号 int
            bottomCol = null ; // 图表数据区域的结束列号 int
            bottomRow = null ; // 图表数据区域的结束行号 int
            sheet = null ; // 页号 int
            chartType = 0 ; // 图表的类型 int
            chartTitle = '' ; // 图表标题 String
            chartXTitle = '' ; // X坐标轴标题 String
            chartYTitle = '' ; // Y坐标轴标题 String
            fontName = null ;  // 字体名称 String
            fontSize = null ;  // 字体大小 int
            fontColor = null ; // 字体颜色值 String
            bgColor = null ; // 背景颜色 String
            borderColor = null ; // 边框颜色 String
            chartIndex = null ; // 图表索引 int
            seriesDirection = 0 ; // 图表系列产生的方向 int
            seriesName = null ; // 图表系列名称 Array
            labelTopCol = null ; // 图表分类轴(X)标志数据区域左上角列号
            labelTopRow = null ; // 图表分类轴(X)标志数据区域左上角行号
            labelBottomCol = null ; // 图表分类轴(X)标志数据区域右下角列号
            labelBottomRow = null ; // 图表分类轴(X)标志数据区域右下角行号
            showDataSign = false ;  // 显示数值标记
        }

        // 工具栏
        this.toolbar = [['cmdPrintFile','打印','/jggz/res/image/cell/print.gif'],
            ['Divider'],['cmdPrintPreview','打印预览','/jggz/res/image/cell/printpreview.gif'],
            ['cmdEditCut','剪切','/jggz/res/image/cell/cut.gif'],['cmdEditCopy','复制','/jggz/res/image/cell/copy.gif'],
            ['cmdEditPaste','粘贴','/jggz/res/image/cell/paste.gif'],['Divider'],
            ['cmdEditFind','查找','/jggz/res/image/cell/find.gif'],['cmdEditUndo','撤消','/jggz/res/image/cell/undo.gif'],
            ['Divider'],['cmdEditRedo','重做','/jggz/res/image/cell/redo.gif'],
            ['cmdViewFreeze','不滚动区域','/jggz/res/image/cell/freeze.gif'],['cmdFormatPainter','格式刷','/jggz/res/image/cell/painter.gif'],
            ['cmdSortAscending','升序排列','/jggz/res/image/cell/sorta.gif'],['Divider'],
            ['cmdSortDescending','降序排列','/jggz/res/image/cell/sortd.gif'],['cmdFormulaInput','输入公式','/jggz/res/image/cell/formula.gif'],
            ['Divider'],['cmdFormulaSerial','填充单元公式序列','/jggz/res/image/cell/formulaS.gif'],
            ['cmdFormulaSumH','水平求和','/jggz/res/image/cell/sumh.gif'],['cmdFormulaSumV','垂直求和','/jggz/res/image/cell/sumv.gif'],
            ['Divider'],['cmdFormulaSumHV','双向求和','/jggz/res/image/cell/sum.gif'],
            ['cmdChartWzd','图表向导','/jggz/res/image/cell/chartw.gif'],['cmdInsertPic','插入图片','/jggz/res/image/cell/insertpic.gif'],
            ['cmdHyperLink','超级链接','/jggz/res/image/cell/hyperlink.gif'],['Divider'],
            ['cmdWzdBarcode','条形码向导','/jggz/res/image/cell/barcode.gif'],['cmdViewScale','viewScaleSelect','显示比例',1],
            ['cmdShowGridline','显示/隐藏背景网格线','/jggz/res/image/cell/gridline.gif'],['cmdVPagebreak','垂直分隔线','/jggz/res/image/cell/vpagebreak.gif'],
            ['cmdHPagebreak','水平分隔线','/jggz/res/image/cell/hpagebreak.gif'],['Divider'],
            ['cmdShowPagebreak','显示/隐藏分隔线','/jggz/res/image/cell/pagebreak.gif'],['EndRow'],
            ['cmdFontName','FontNameSelect','字体',2],['cmdFontSize','FontSizeSelect','字号',3],
            ['cmdBold','粗体','/jggz/res/image/cell/bold.gif'],['cmdItalic','斜体','/jggz/res/image/cell/italic.gif'],
            ['cmdUnderline','下划线','/jggz/res/image/cell/underline.gif'],['cmdBackColor','背景色','/jggz/res/image/cell/backcolor.gif'],
            ['Divider'],['cmdForeColor','前景色','/jggz/res/image/cell/forecolor.gif'],
            ['cmdWordWrap','自动折行','/jggz/res/image/cell/wordwrap.gif'],['cmdAlignLeft','居左对齐','/jggz/res/image/cell/alignleft.gif'],
            ['cmdAlignCenter','居中对齐','/jggz/res/image/cell/aligncenter.gif'],['cmdAlignRight','居右对齐','/jggz/res/image/cell/alignright.gif'],
            ['cmdAlignTop','居上对齐','/jggz/res/image/cell/aligntop.gif'],['cmdAlignMiddle','垂直居中','/jggz/res/image/cell/alignmiddle.gif'],
            ['Divider'],['cmdAlignBottom','居下对齐','/jggz/res/image/cell/alignbottom.gif'],
            ['cmdBoderType','BorderTypeSelect','边框类型',4],['cmdDrawBorder','画边框线','/jggz/res/image/cell/border.gif'],
            ['cmdEraseBorder','抹边框线','/jggz/res/image/cell/erase.gif'],['cmdCurrency','货币符号','/jggz/res/image/cell/currency.gif'],
            ['cmdPercent','百分号','/jggz/res/image/cell/percent.gif'],['Divider'],
            ['cmdThousand','千分位','/jggz/res/image/cell/thousand.gif'],['EndRow'],
            ['cmdInsertCol','插入列','/jggz/res/image/cell/insertcol.gif'],['cmdInsertRow','插入行','/jggz/res/image/cell/insertrow.gif'],
            ['cmdAppendCol','追加列','/jggz/res/image/cell/appendcol.gif'],['Divider'],
            ['cmdAppendRow','追加行','/jggz/res/image/cell/appendrow.gif'],['cmdDeleteCol','删除列','/jggz/res/image/cell/deletecol.gif'],
            ['cmdDeleteRow','删除行','/jggz/res/image/cell/deleterow.gif'],['Divider'],
            ['cmdSheetSize','表页尺寸','/jggz/res/image/cell/sheetsize.gif'],['cmdMergeCell','组合单元格','/jggz/res/image/cell/mergecell.gif'],
            ['cmdUnMergeCell','取消单元格组合','/jggz/res/image/cell/unmergecell.gif'],['cmdMergeRow','行组合','/jggz/res/image/cell/mergerows.gif'],
            ['Divider'],['cmdMergeCol','列组合','/jggz/res/image/cell/mergecols.gif'],
            ['cmdReCalcAll','重算全表','/jggz/res/image/cell/calculateall.gif'],['cmdFormulaSum3D','设置汇总公式','/jggz/res/image/cell/sum3d.gif'],
            ['Divider'],['cmdReadOnly','单元格只读','/jggz/res/image/cell/readonly.gif'],
            ['cmdFillType','FillTypeSelect','填充方式',5],['Divider'],
            ['cmdFillSerial','序列填充','/jggz/res/image/cell/fillserial.gif'],['cmdDateType','DateTypeSelect','日期类型',6],
            ['cmdTimeType','TimeTypeSelect','时间类型',7]] ;
    }

/**
 * 初始化Cell对象
 * @param cellObj Object 装载Cell对象的引用
 */
CELL.prototype.setCell = function (cellObj) {
    if (cellObj != null) {
        this.cellObj = cellObj ;

        try {
            var fontNameSelectObj = document.getElementById('FontNameSelect') ;

            if (fontNameSelectObj != null) {
                var strFontnames = cellObj.GetDisplayFontnames() ;
                var arrFontname = strFontnames.split('|') ;
                arrFontname.sort() ;
                var i ;
                var sysFont ;

                if(cellObj.GetSysLangID () == 2052) {
                    sysFont = "宋体" ;
                } else {
                    sysFont = "Arial" ;
                }

                for(i = 0 ; i < arrFontname.length ; i++ ) {
                    var oOption = document.createElement("OPTION") ;
                    fontNameSelectObj.options.add(oOption) ;
                    oOption.innerText = arrFontname[i] ;
                    oOption.value = arrFontname[i] ;
                    if( arrFontname[i] == sysFont ) oOption.selected = true ;
                }
            }

            DCellWeb1 = this.cellObj ;
        } catch(ex) {}
    }
}
/**
 * 注册Cell
 * @param cellObj Object 装载Cell对象的引用
 */
CELL.prototype.register = function (cellObj) {
    if (cellObj != null) {
        this.cellObj = cellObj ;
    }
    if (this.cellObj != null) {
        this.cellObj.Login("中信报表", "",13040409, "5160-0447-0112-5004") ;
    }
}

/**
 * 打开Cell文件
 * @param cellObj Object 装载Cell对象的引用
 * @param filePath String 要打开的文件名
 * @param filePassword String 要打开的文件的密码
 * @return String 空：成功；其他：失败原因
 */
CELL.prototype.open = function (cellObj,filePath,filePassword) {
    var returnInfo = '' ;

    if (cellObj != null) {
        this.cellObj = cellObj ;
    }

    if (this.cellObj != null) {
        if (filePath == null) {
            filePath = '' ;
        }
        if (filePassword == null) {
            filePassword = '' ;
        }

        var temp = this.cellObj.OpenFile(filePath,filePassword) ;

        if (temp == -1) {
            returnInfo = '文件不存在' ;
        } else if (temp == -2) {
            returnInfo = '文件操作错误' ;
        } else if (temp == -3) {
            returnInfo = '文件格式错误' ;
        } else if (temp == -4) {
            returnInfo = '密码错误' ;
        } else if (temp == -5) {
            returnInfo = '不能打开高版本文件' ;
        } else if (temp == -6) {
            returnInfo = '不能打开特定版本文件' ;
        } else {
            this.cellObj.SetCurSheet(0) ;
        }
    } else {
        returnInfo = '给定的Cell对象不存在' ;
    }

    return returnInfo ;
}

/**
 * 将Cell值为只读或可写
 * @param cellObj Object 装载Cell对象的引用
 * @param readOnlySwitch boolean 是否将Cell置为只读，可选
 */
CELL.prototype.setReadOnly = function (cellObj,readOnlySwitch) {
    if (cellObj != null) {
        this.cellObj = cellObj ;
    }

    if (this.cellObj != null) {
        var readOnlyFlag = 1 ;

        if (readOnlySwitch == null) {
            readOnlySwitch = true ;
        }
        if (readOnlySwitch) {
            readOnlyFlag = 0 ;
        }

        this.cellObj.WorkBookReadOnly = readOnlySwitch ;
        this.cellObj.EnableUndo(readOnlySwitch) ;

        var curSheet = this.cellObj.GetCurSheet() ;

        for(var i = 0 ; i < this.cellObj.GetTotalSheets() ; i++){
            this.cellObj.SetCurSheet(i);
            this.cellObj.ShowSideLabel(readOnlyFlag,i);
            this.cellObj.ShowTopLabel(readOnlyFlag,i);
        }

        this.cellObj.SetCurSheet(curSheet) ;
    }
}

/**
 * Cell文件另存为本地文件
 * @param cellObj Object 装载Cell对象的引用
 */
CELL.prototype.saveAs = function (cellObj) {
    if (cellObj != null) {
        this.cellObj = cellObj ;
    }

    if (this.cellObj != null) {
        this.cellObj.SaveFile() ;
    }
}

/**
 * 保存Cell文件到指定路径
 * @param cellObj Object 装载Cell对象的引用
 * @param path String 要保存Cell的路径，相对路径
 */
CELL.prototype.saveFile = function (cellObj,path) {
    if (cellObj != null) {
        this.cellObj = cellObj ;
    }

    if (this.cellObj != null && path != null && path != '') {
        this.cellObj.UpLoadFile("/jggz/saveCellFile.do?path="+path) ;
    }
}

/**
 * 保存Cell文件到指定路径
 * @param cellObj Object 装载Cell对象的引用
 * @param path String Cell要提交的Action URL
 */
CELL.prototype.save = function (cellObj,path) {
    if (cellObj != null && path != null && path != '') {
        cellObj.UpLoadFile(path) ;
    }
}

/**
 * 将数据写入Cell对应页签中的对应位置
 * @param cellObj Object 装载Cell对象的引用
 * @param row int 要写入的行号
 * @param col int 要写入的列号
 * @param dataString String 要写入的数据
 * @param sheet int 要写入的页签，可选 默认当前页
 * @param numFlag boolean 要写入的数据是否是数值
 */
CELL.prototype.writeData = function (cellObj,row,col,dataString,sheet,numFlag) {
    if (cellObj != null && !isNaN(row) && !isNaN(col)) {
        if (sheet == null || isNaN(sheet)) {
            sheet = cellObj.GetCurSheet() ;
        }

        if (dataString == null) {
            dataString = '' ;
        }

        if (row >= cellObj.GetRows(sheet)) {
            cellObj.InsertRow(row,1,sheet) ;
        }

        if (numFlag != null && numFlag) {
            cellObj.D(col,row,sheet,dataString) ;
        } else {
            cellObj.S(col,row,sheet,dataString) ;
        }

        cellObj.SetCellBackColor(col,row,sheet,this.rowBgColor) ;
    }
}

/**
 * 循环将数据写入Cell中
 * @param cellObj Object 装载Cell对象的引用
 * @param startRow int 要写入数据的起始行
 * @param endRow int 要写入数据的截止行
 * @param startCol int 要写入数据的起始列
 * @param endCol int 要写入数据的截止列
 * @param dataListArray Array Array 要写入的数据集合
 * @param sheet int 要写入数据的页签
 * @param changeColorFlag boolean 是否使用相邻行用不同的背景颜色区分
 */
CELL.prototype.loopWriteData = function (cellObj,startRow,startCol,dataListArray,endCol,endRow,sheet,changeColorFlag) {
    if (cellObj != null && !isNaN(startRow) && !isNaN(startCol) && dataListArray != null && dataListArray.length > 0) {
        if (sheet == null || isNaN(sheet)) {
            sheet = cellObj.GetCurSheet() ;
        }
        if (endRow == null || isNaN(endRow)) {
            endRow = dataListArray.length + startRow ;
        }
        if (endCol == null || isNaN(endCol)) {
            setEndColFlag = true ;
        }
        if (changeColorFlag == null) {
            changeColorFlag = false ;
        }

        for (var i = startRow ; i < endRow ; i++) {
            var rowArray = dataListArray[i-startRow] ;

            if (rowArray != null && rowArray.length > 0) {
                if (setEndColFlag) {
                    endCol = rowArray.length + startCol ;
                }

                if (cellObj.IsRowHidden(i,sheet) == 1) {
                    cellObj.SetRowUnhidden(i,i+1) ;
                }

                if (changeColorFlag) {
                    if (this.rowBgColor != this.nomalRowBgColor) {
                        this.rowBgColor = this.nomalRowBgColor ;
                    } else {
                        this.rowBgColor = this.changRowUseColor ;
                    }
                }

                for (var j = startCol ; j < endCol ; j++) {
                    var cellData = rowArray[j-startCol] ;
                    var dataFlag = false ;

                    if (!isNaN(cellData)) {
                        dataFlag = true ;
                    }

                    if (cellData != null && cellData.Trim() == '') {
                        dataFlag = false ;
                    }

                    this.writeData(cellObj,i,j,cellData,sheet,dataFlag) ;
                }
            }
        }

        for (var i = startRow + 1 ; i < endRow ; i++) {
            cellObj.SetRowHeight(1,cellObj.GetRowHeight(1,i-1,sheet),i,sheet) ;
        }

        if (endRow < cellObj.GetRows(sheet)) {
            cellObj.SetRowHidden(endRow,cellObj.GetRows(sheet)) ;
        }
    }
}


/**
 * 设置相邻行迭代颜色
 * @param cellObj Object 装载Cell对象的引用
 * @param [startRow] int 起始行
 * @param [startCol] int 起始列
 * @param [endRow] int 截至行
 * @param [endCol] int 截至列
 * @param sheet int 要处理的页签
 */
CELL.prototype.setJoinRowBgColor = function (cellObj,startRow,startCol,endRow,endCol,sheet) {
    if (cellObj != null && !isNaN(startRow) && !isNaN(startCol)) {
        if (sheet == null || isNaN(sheet)) {
            sheet = cellObj.GetCurSheet() ;
        }
        if (endRow == null || isNaN(endRow)) {
            endRow = cellObj.GetRows(sheet) ;
        }
        if (endCol == null || isNaN(endCol)) {
            endCol = cellObj.GetCols(sheet) ;
        }

        for (var i = startRow ; i < endRow ; i++) {
            if (cellObj.IsRowHidden(i,sheet) == 1) {
                continue ;
            }

            if (this.rowBgColor != this.nomalRowBgColor) {
                this.rowBgColor = this.nomalRowBgColor ;
            } else {
                this.rowBgColor = this.changRowUseColor ;
            }

            for (var j = startCol ; j < endCol ; j++) {
                cellObj.SetCellBackColor(j,i,sheet,this.rowBgColor) ;
            }
        }
    }
}

/**
 * 根据给定的比较值，将符合条件的行隐藏
 * @param cellObj Object 装载Cell对象的引用
 * @param comValue String 要隐藏的值，如有多个用"||"分隔
 * @param [startRow] int 起始行
 * @param [startCol] int 起始列
 * @param [endRow] int 截至行
 * @param [endCol] int 截至列
 * @param sheet int 要处理的页签
 */
CELL.prototype.hiddenRow = function (cellObj,comValue,startRow,startCol,endRow,endCol,sheet) {
    if (cellObj != null && !isNaN(startRow) && !isNaN(startCol) && comValue != null) {
        if (sheet == null || isNaN(sheet)) {
            sheet = cellObj.GetCurSheet() ;
        }
        if (endRow == null || isNaN(endRow)) {
            endRow = cellObj.GetRows(sheet) ;
        }
        if (endCol == null || isNaN(endCol)) {
            endCol = cellObj.GetCols(sheet) ;
        }

        var comValueA = comValue.split('||') ;

        for (var i = startRow ; i < endRow ; i++) {
            var hiddenFlag = false ;

            for (var j = startCol ; j < endCol ; j++) {
                var cellValue = cellObj.GetCellString(j,i,sheet) ;

                if (isHiddenValue(cellValue)) {
                    hiddenFlag = true ;
                } else {
                    hiddenFlag = false ;
                    break ;
                }
            }

            if (hiddenFlag) {
                cellObj.SetRowHidden(i,i) ;
            }
        }
    }

    function isHiddenValue (checkStr) {
        for (var i = 0 ; i < comValueA.length ; i++) {
            if (checkStr == comValueA[i]) {
                return true ;
            }
        }

        return false ;
    }
}

/**
 * 打印预览
 * @param cellObj Object 装载Cell对象的引用
 * @param previewSheet int 要预览的数据的页号
 * @param pageSet int 是否显示打印设置对话框
 */
CELL.prototype.printPreview = function (cellObj,previewSheet,pageSet) {
    if (cellObj != null) {
        if (pageSet == null) {
            pageSet = 0 ;
        }
        if (previewSheet == null) {
            previewSheet = cellObj.GetCurSheet() ;
        }

        cellObj.PrintPreview(pageSet,previewSheet) ;
        this.savePrintArgument(cellObj) ;
    }
}

/**
 * 打印设置
 * @param cellObj Object 装载Cell对象的引用
 */
CELL.prototype.printSetup = function (cellObj) {
    if (cellObj != null) {
        cellObj.PrintPageSetup() ;
        this.savePrintArgument(cellObj) ;
    }
}

/**
 * 打印
 * @param cellObj Object 装载Cell对象的引用
 * @param valueSheet int 要打印的页数
 * @param withdlg int 是否显示打印设置对话框
 */
CELL.prototype.print = function (cellObj,valueSheet,withdlg) {
    if (cellObj != null) {
        if (valueSheet == null) {
            valueSheet = 1 ;
        }
        if (withdlg == null) {
            withdlg = 0 ;
        }

        cellObj.PrintSheet(withdlg,valueSheet) ;
    }
}

/**
 * 	打印范围
 * 	@param cellObj Object 装载Cell对象的引用
 * 	@param fromPgNo	int			打印的起始页
 * 	@param endPgNo	int			打印的结束页
 *   @param copys	int			重复次数
 */
CELL.prototype.printRange = function (cellObj, fromPgNo, endPgNo, copys) {
    var totalSheets = cellObj.GetTotalSheets();
    if (cellObj != null) {
        if (fromPgNo == null || fromPgNo == '') {
            fromPgNo = 1;
        }
        if (endPgNo == null || endPgNo == '') {
            endPgNo = totalSheets;
        }
        if (copys == null || copys == '') {
            copys = 1;
        }
        if (fromPgNo > totalSheets) {
            fromPgNo = totalSheets
        }
        if (endPgNo > totalSheets) {
            endPgNo = totalSheets;
        }
        cellObj.PrintPara(-1, 1, 0, 0);
        cellObj.PrintSetPrintCopies(copys);
        cellObj.PrintSetPrintRange(2,''+fromPgNo+'-'+endPgNo);
        cellObj.PrintSheet(0,cellObj.GetCurSheet);
    }
}

/**
 * 批量打印文件
 * @param cellObj Object 装载Cell对象的引用
 * @param path String 要打印的文件存放路径
 * @param fileNameList Array 要打印的文件名称数组
 * @param valueSheet int 要打印的页数
 */
CELL.prototype.printBatch = function (cellObj,path,fileNameList,valueSheet) {
    if (cellObj != null && path != null && fileNameList != null && fileNameList.length > 0) {
        if (valueSheet == null) {
            valueSheet = 1 ;
        }

        for (var i = 0 ; i < fileNameList.length ; i++) {
            var filePath = path + fileNameList[i] ;

            if (this.open(cellObj,filePath) == '') {
                this.print(cellObj,valueSheet) ;
            }
        }
    }
}

/**
 * 存储打印参数
 * @param cellObj Object 装载Cell对象的引用
 */
CELL.prototype.savePrintArgument = function (cellObj) {
    if (cellObj != null) {
        this.initFlag = true ;
        this.printArgument.paperSize = cellObj.PrintGetPaper() ;
        this.printArgument.scale = cellObj.PrintGetScale() ;
        this.printArgument.orient = cellObj.PrintGetOrient() ;
        this.printArgument.head = cellObj.PrintGetHeader() ;
        this.printArgument.foot = cellObj.PrintGetFooter() ;
        this.printArgument.printername = cellObj.GetPrinter() ;
        this.printArgument.sideLeft = cellObj.PrintGetMargin(0) ;
        this.printArgument.sideTop = cellObj.PrintGetMargin(1) ;
        this.printArgument.sideBottom = cellObj.PrintGetMargin(2) ;
        this.printArgument.sideRight = cellObj.PrintGetMargin(3) ;
        this.printArgument.label = cellObj.GetPrintLabel() ;
        this.printArgument.printPara = cellObj.GetPrintPara() ;
    }
}

/**
 * 设置打印参数
 * @param cellObj Object 装载Cell对象的引用
 * @param printArgumentP Object 要设置的打印参数
 */
CELL.prototype.setPrintArgument = function (cellObj,printArgumentP) {
    if (cellObj != null && printArgumentP != null) {
        cellObj.PrintSetPaper(printArgumentP.paperSize) ;
        cellObj.PrintSetScale(printArgumentP.scale) ;
        cellObj.PrintSetOrient(printArgumentP.orient)

        var headStr = printArgumentP.head ;
        if (headStr != null) {
            var headA = headStr.split('|') ;

            if (headA != null) {
                for (var i = 0 ; i < 3 ; i++) {
                    if (headA[i] == null) {
                        headA[i] = '' ;
                    }
                }
            } else {
                headA[0] = '' ;
                headA[1] = '' ;
                headA[2] = '' ;
            }

            cellObj.PrintSetHead(headA[0],headA[2],headA[1]) ;
        }

        var footStr = printArgumentP.foot ;
        if (footStr != null) {
            var footA = footStr.split('|') ;

            if (footA != null) {
                for (var i = 0 ; i < 3 ; i++) {
                    if (footA[i] == null) {
                        footA[i] = '' ;
                    }
                }
            } else {
                footA[0] = '' ;
                footA[1] = '' ;
                footA[2] = '' ;
            }

            cellObj.Printsetfoot(footA[0],footA[2],footA[1]) ;
        }

        cellObj.SetPrinter(printArgumentP.printername) ;
        cellObj.PrintSetMargin(printArgumentP.sideTop,printArgumentP.sideLeft,printArgumentP.sideBottom,printArgumentP.sideRight) ;

        if (printArgumentP.label == 0) {
            cellObj.PrintLabel(0,0) ;
        } else if (printArgumentP.label == 1) {
            cellObj.PrintLabel(1,0) ;
        } else if (printArgumentP.label ==  2) {
            cellObj.PrintLabel(0,1) ;
        } else if (printArgumentP.label == 3) {
            cellObj.PrintLabel(1,1) ;
        }

        var temp = printArgumentP.printPara ;
        var temp3 = temp >> 3 ;
        if (temp3 == 1) {
            temp -= 8 ;
        }
        var temp2 = temp >> 2 ;
        if (temp2 == 1) {
            temp -= 4 ;
        }
        var temp1 = temp >> 1 ;
        if (temp1 == 1) {
            temp -= 2 ;
        }
        cellObj.PrintPara(temp,temp1,temp2,temp3) ;
    }
}

/**
 * 取得打印参数
 */
CELL.prototype.getPrintArgument = function () {
    if (this.initFlag) {
        return this.printArgument ;
    } else {
        return null ;
    }
}

/**
 * 设置图表数据区域
 * @param topRow int 图表数据区域的起始行号
 * @param topCol int 图表数据区域的起始列号
 * @param bottomRow int 图表数据区域的结束行号
 * @param bottomCol int 图表数据区域的结束列号
 * @param sheet int 页号
 */
CELL.prototype.setChartDataArea = function (topRow,topCol,bottomRow,bottomCol,sheet) {
    if (!isNaN(topCol)) {
        this.chartArgument.topCol = topCol ;
    }
    if (!isNaN(topRow)) {
        this.chartArgument.topRow = topRow ;
    }
    if (!isNaN(bottomCol)) {
        this.chartArgument.bottomCol = bottomCol ;
    }
    if (!isNaN(bottomRow)) {
        this.chartArgument.bottomRow = bottomRow ;
    }
    if (!isNaN(sheet)) {
        this.chartArgument.sheet = sheet ;
    }
}

/**
 * 设置图表标题
 * @param title String 图表标题
 * @param xTitle String X坐标轴标题
 * @param yTitle String Y坐标轴标题
 */
CELL.prototype.setChartTitle = function (title,xTitle,yTitle) {
    if (title != null && title != '') {
        this.chartArgument.chartTitle = title ;
    }
    if (xTitle != null && xTitle != '') {
        this.chartArgument.chartXTitle = xTitle ;
    }
    if (yTitle != null && yTitle != '') {
        this.chartArgument.chartYTitle = yTitle ;
    }
}

/**
 * 设置图表标题字体
 * @param fontName String 字体名称
 * @param fontSize int 字体大小
 * @param fontColor String 字体颜色值
 */
CELL.prototype.setChartFont = function (fontName,fontSize,fontColor) {
    if (fontName != null && fontName != '') {
        this.chartArgument.fontName = fontName ;
    }
    if (!isNaN(fontSize)) {
        this.chartArgument.fontSize = fontSize ;
    }
    if (!isNaN(fontColor)) {
        this.chartArgument.fontColor = fontColor ;
    }
}

/**
 * 设置表各行的颜色
 * @param bgColor String 背景颜色
 */
CELL.prototype.setRowBgColor = function (bgColor) {
    if (!isNaN(bgColor)) {
        this.rowBgColor = bgColor ;
    }
}

/**
 * 设置图表的颜色
 * @param bgColor String 背景颜色
 * @param borderColor String 边框颜色
 */
CELL.prototype.setChartColor = function (bgColor,borderColor) {
    if (!isNaN(bgColor)) {
        this.chartArgument.bgColor = bgColor ;
    }
    if (!isNaN(borderColor)) {
        this.chartArgument.borderColor = borderColor ;
    }
}

/**
 * 设置图表系列产生的方向和名称
 * @param name Array 系列名称
 * @param seriesDirection int 图表系列产生的方向
 */
CELL.prototype.setChartSeries = function (name,seriesDirection) {
    if (name != null && name.length > 0) {
        this.chartArgument.seriesName = name ;
    }
    if (!isNaN(seriesDirection)) {
        this.chartArgument.seriesDirection = seriesDirection ;
    }
}

/**
 * 显示数值标记
 * @param flag boolean TRUE，显示数值标记。FALSE，隐藏数值标记
 */
CELL.prototype.isShowDataSign = function (flag) {
    if (flag != null && flag) {
        this.chartArgument.showDataSign = true ;
    }
}

/**
 * 设置图表分类轴(X)标志数据区域
 * @param topRow int 图表分类轴(X)标志数据区域左上角行号
 * @param topCol int 图表分类轴(X)标志数据区域左上角列号
 * @param bottomRow int 图表分类轴(X)标志数据区域右下角行号
 * @param bottomCol int 图表分类轴(X)标志数据区域右下角列号
 */
CELL.prototype.setChartLabelArea = function (topRow,topCol,bottomRow,bottomCol) {
    if (!isNaN(topRow)) {
        this.chartArgument.labelTopRow = topRow ;
    }
    if (!isNaN(topCol)) {
        this.chartArgument.labelTopCol = topCol ;
    }
    if (!isNaN(bottomRow)) {
        this.chartArgument.labelBottomRow = bottomRow ;
    }
    if (!isNaN(bottomCol)) {
        this.chartArgument.labelBottomCol = bottomCol ;
    }
}

/**
 * 根据给定参数创建图表
 * @param cellObj Object 装载Cell对象的引用
 * @param chartType int 图表的类型
 * @return boolean 成功返回TRUE，失败返回FALSE
 */
CELL.prototype.createChart = function (cellObj,chartType) {
    if (cellObj != null) {
        if (!isNaN(chartType)) {
            this.chartArgument.chartType = chartType ;
        }
        if (this.chartArgument.sheet == null) {
            this.chartArgument.sheet = cellObj.GetCurSheet() ;
        }

        if (this.chartArgument.topCol == null || this.chartArgument.topRow == null ||
            this.chartArgument.bottomCol == null || this.chartArgument.bottomRow == null) {
            return false ;
        }

        if (!cellObj.CreateChart(this.chartArgument.topCol,this.chartArgument.topRow,this.chartArgument.bottomCol,this.chartArgument.bottomRow,this.chartArgument.sheet)) {
            return false ;
        }

        this.chartArgument.chartIndex = cellObj.GetTopChartIndex(this.chartArgument.sheet) ;

        if (!isNaN(this.chartArgument.chartIndex)) {
            cellObj.SetChartType(this.chartArgument.chartType,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            cellObj.SetSeriesOri(this.chartArgument.seriesDirection,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            cellObj.ShowDataSign(this.chartArgument.showDataSign,this.chartArgument.chartIndex,this.chartArgument.sheet) ;

            if (this.chartArgument.chartTitle != null) {
                cellObj.SetChartTittle(this.chartArgument.chartTitle,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            }
            if (this.chartArgument.chartXTitle != null) {
                cellObj.SetXAxisTittle(this.chartArgument.chartXTitle,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            }
            if (this.chartArgument.chartYTitle != null) {
                cellObj.SetYAxisTittle(this.chartArgument.chartYTitle,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            }
            if (this.chartArgument.seriesName != null && this.chartArgument.seriesName.length > 0) {
                for (var i = 0 ; i < this.chartArgument.seriesName.length ; i++) {
                    cellObj.SetSeriesName(this.chartArgument.seriesName[i],i,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
                }
            }
            if (this.chartArgument.labelTopCol != null && this.chartArgument.labelTopRow != null &&
                this.chartArgument.labelBottomCol != null && this.chartArgument.labelBottomRow != null) {
                cellObj.SetXLabelArea(this.chartArgument.labelTopCol,this.chartArgument.labelTopRow,this.chartArgument.labelBottomCol,this.chartArgument.labelBottomRow,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            }
            if (this.chartArgument.fontName != null && !isNaN(this.chartArgument.fontSize)) {
                cellObj.SetChartFont(this.chartArgument.fontName,this.chartArgument.fontSize,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            }
            if (!isNaN(this.chartArgument.fontColor)) {
                cellObj.SetChartFontColor(this.chartArgument.fontColor,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            }
            if (!isNaN(this.chartArgument.bgColor)) {
                cellObj.SetChartBkColor(this.chartArgument.bgColor,0,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
                cellObj.SetChartBkColor(this.chartArgument.bgColor,1,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
                cellObj.SetChartBkColor(this.chartArgument.bgColor,2,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
                cellObj.SetChartBkColor(this.chartArgument.bgColor,3,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
                cellObj.SetChartBkColor(this.chartArgument.bgColor,4,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
                cellObj.SetChartBkColor(this.chartArgument.bgColor,5,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            }
            if (!isNaN(this.chartArgument.borderColor)) {
                cellObj.SetChartBorderColor(this.chartArgument.borderColor,0,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
                cellObj.SetChartBorderColor(this.chartArgument.borderColor,1,this.chartArgument.chartIndex,this.chartArgument.sheet) ;
            }
        }

        return true ;
    }
}

/**
 * 删除图标
 * @param cellObj Object 装载Cell对象的引用
 * @return boolean 成功返回TRUE，失败返回FALSE
 */
CELL.prototype.deleteChart = function (cellObj) {
    if (cellObj != null) {
        if (!isNaN(this.chartArgument.chartIndex)) {
            if (this.chartArgument.sheet == null) {
                this.chartArgument.sheet = cellObj.GetCurSheet() ;
            }

            return cellObj.DeleteChart(this.chartArgument.chartIndex,this.chartArgument.sheet) ;
        }
    }
}

/**
 * 重画图表
 * @param cellObj Object 装载Cell对象的引用
 * @return boolean 成功返回TRUE，失败返回FALSE
 */
CELL.prototype.reDrawChart = function (cellObj) {
    if (cellObj != null) {
        if (!isNaN(this.chartArgument.chartIndex)) {
            if (this.chartArgument.sheet == null) {
                this.chartArgument.sheet = cellObj.GetCurSheet() ;
            }

            return cellObj.ReDrawChart(this.chartArgument.chartIndex,this.chartArgument.sheet) ;
        }
    }
}

/**
 * 进入图表向导
 * @param cellObj Object 装载Cell对象的引用
 */
CELL.prototype.showChartDialog = function (cellObj) {
    if (cellObj != null) {
        cellObj.WzdChartDlg() ;
    }
}

/**
 * 添加Cell工具栏
 * @param cellMName String cellMan实例的名称
 */
CELL.prototype.writeToolBar = function (cellMName) {
    var toolBarStr = "<TABLE class='cellToolbar' cellpadding='0' cellspacing='0' width='100%'><TR>" ;

    for (var i = 0 ; i < this.toolbar.length ; i++) {
        var temp = this.toolbar[i] ;

        if (temp.length == 3) {
            toolBarStr += "<TD NOWRAP><A class='cellButton' id='" + temp[0] + "' title='" + temp[1] + "' href='javascript:///' name='cellCMDButtion'>" ;
            toolBarStr += "<IMG align=absMiddle src='" + temp[2] + "' width='16' height='16'></A></TD>" ;
        } else if (temp.length == 1) {
            if (temp[0] == 'Divider') {
                i++ ;
                temp = this.toolbar[i] ;
                toolBarStr += "<TD class='cellDivider' NOWRAP><A class='cellButton' id='" + temp[0] + "' title='" + temp[1] + "' href='javascript:///' name='cellCMDButtion'>" ;
                toolBarStr += "<IMG align=absMiddle src='" + temp[2] + "' width='16' height='16'></A></TD>" ;
            } else if (temp[0] == 'EndRow') {
                toolBarStr += "<TD NOWRAP width='100%'></TD></TR></TABLE>" ;
                toolBarStr += "<TABLE class='cellToolbar' cellpadding='0' cellspacing='0' width='100%'><TR>" ;
            }
        } else if (temp.length == 4) {
            toolBarStr += this.getSelectToolBar(temp,cellMName) ;
        }
    }

    toolBarStr += "<TD NOWRAP width='100%'></TD></TR></TABLE>" ;

    document.write(toolBarStr) ;
}

/**
 * 取得Cell工具栏中的Select组件
 * @param selectInfoA Array select组件的信息
 * @param cellMName String cellMan实例的名称
 */
CELL.prototype.getSelectToolBar = function (selectInfoA,cellMName) {
    var selectStr = '' ;

    switch (selectInfoA[3]) {
        case 1:
            selectStr += "<TD class='cellDivider' NOWRAP id='" + selectInfoA[0] + "' Title='" + selectInfoA[2] + "'>" ;
            selectStr += "<SELECT name='" + selectInfoA[1] + "' style='WIDTH: 89px; HEIGHT: 23px' onChange='javascript:" + cellMName + ".changeViewScaleJS(this);' size='1'>" ;
            selectStr += "<option value='200'>200%</option>" ;
            selectStr += "<option value='150'>150%</option>" ;
            selectStr += "<option value='120'>120%</option>" ;
            selectStr += "<option value='110'>110%</option>" ;
            selectStr += "<option selected value='100'>100%</option>" ;
            selectStr += "<option value='90'>90%</option>" ;
            selectStr += "<option value='80'>80%</option>" ;
            selectStr += "<option value='70'>70%</option>" ;
            selectStr += "<option value='50'>50%</option>" ;
            selectStr += "<option value='30'>30%</option>" ;
            selectStr += "<option value='20'>20%</option>" ;
            selectStr += "<option value='15'>15%</option>" ;
            selectStr += "<option value='10'>10%</option>" ;
            selectStr += "<option value='5'>5%</option>" ;
            selectStr += "<option value='3'>3%</option>" ;
            selectStr += "<option value='1'>1%</option>" ;
            selectStr += "</SELECT></TD>" ;
            break ;
        case 2:
            selectStr += "<TD NOWRAP id='" + selectInfoA[0] + "' Title='" + selectInfoA[2] + "'>" ;
            selectStr += "<SELECT name='" + selectInfoA[1] + "' style='WIDTH: 225px; HEIGHT: 23px' onChange='javascript:" + cellMName + ".changeFontNameJS(this);' size='1'>" ;
            selectStr += "&nbsp;</SELECT></TD>" ;
            break ;
        case 3:
            selectStr += "<TD NOWRAP class='cellDivider' id='" + selectInfoA[0] + "' Title='" + selectInfoA[2] + "'>" ;
            selectStr += "<SELECT name='" + selectInfoA[1] + "' style='WIDTH: 67px; HEIGHT: 23px' onChange='javascript:" + cellMName + ".changeFontSizeJS(this);' size='1'>" ;
            selectStr += "<option value='5'>5</option>" ;
            selectStr += "<option value='6'>6</option>" ;
            selectStr += "<option value='7'>7</option>" ;
            selectStr += "<option value='8'>8</option>" ;
            selectStr += "<option value='9'>9</option>" ;
            selectStr += "<option selected value='10'>10</option>" ;
            selectStr += "<option value='11'>11</option>" ;
            selectStr += "<option value='12'>12</option>" ;
            selectStr += "<option value='14'>14</option>" ;
            selectStr += "<option value='16'>16</option>" ;
            selectStr += "<option value='18'>18</option>" ;
            selectStr += "<option value='20'>20</option>" ;
            selectStr += "<option value='22'>22</option>" ;
            selectStr += "<option value='24'>24</option>" ;
            selectStr += "<option value='26'>26</option>" ;
            selectStr += "<option value='28'>28</option>" ;
            selectStr += "<option value='30'>30</option>" ;
            selectStr += "<option value='36'>36</option>" ;
            selectStr += "<option value='42'>42</option>" ;
            selectStr += "<option value='48'>48</option>" ;
            selectStr += "<option value='72'>72</option>" ;
            selectStr += "<option value='100'>100</option>" ;
            selectStr += "<option value='150'>150</option>" ;
            selectStr += "<option value='300'>300</option>" ;
            selectStr += "<option value='500'>500</option>" ;
            selectStr += "<option value='800'>800</option>" ;
            selectStr += "<option value='1200'>1200</option>" ;
            selectStr += "<option value='2000'>2000</option>" ;
            selectStr += "</SELECT></TD>" ;
            break ;
        case 4:
            selectStr += "<TD NOWRAP id='" + selectInfoA[0] + "' Title='" + selectInfoA[2] + "'>" ;
            selectStr += "<SELECT name='" + selectInfoA[1] + "' style='WIDTH: 109px; HEIGHT: 23px' size='1'>" ;
            selectStr += "<option value='2' selected>细线</option>" ;
            selectStr += "<option value='3'>中线</option>" ;
            selectStr += "<option value='4'>粗线</option>" ;
            selectStr += "<option value='5'>划线</option>" ;
            selectStr += "<option value='6'>点线</option>" ;
            selectStr += "<option value='7'>点划线</option>" ;
            selectStr += "<option value='8'>点点划线</option>" ;
            selectStr += "<option value='9'>粗划线</option>" ;
            selectStr += "<option value='10'>粗点线</option>" ;
            selectStr += "<option value='11'>粗点划线</option>" ;
            selectStr += "<option value='12'>粗点点划线</option>" ;
            selectStr += "</Select></TD>" ;
            break ;
        case 5:
            selectStr += "<TD NOWRAP id='" + selectInfoA[0] + "' Title='" + selectInfoA[2] + "'>" ;
            selectStr += "<SELECT name='" + selectInfoA[1] + "' style='WIDTH: 102px; HEIGHT: 23px' onChange='javascript:" + cellMName + ".changeFillTypeJS(this);' size='1'>" ;
            selectStr += "<option value='1' selected>向下填充</option>" ;
            selectStr += "<option value='2'>向右填充</option>" ;
            selectStr += "<option value='4'>向上填充</option>" ;
            selectStr += "<option value='8'>向左填充</option>" ;
            selectStr += "<option value='16'>重复填充</option>" ;
            selectStr += "<option value='32'>等差填充</option>" ;
            selectStr += "<option value='64'>等比填充</option>" ;
            selectStr += "</SELECT></TD>" ;
            break ;
        case 6:
            selectStr += "<TD NOWRAP id='" + selectInfoA[0] + "' Title='" + selectInfoA[2] + "'>" ;
            selectStr += "<SELECT name='" + selectInfoA[1] + "' style='WIDTH: 191px; HEIGHT: 23px' onChange='javascript:" + cellMName + ".changeDateTypeJS(this);' size='1'>" ;
            selectStr += "<option value='1' selected>1997-3-4</option>" ;
            selectStr += "<option value='2'>1997-03-04 13:30:12</option>" ;
            selectStr += "<option value='3'>1997-3-4 1:30 PM</option>" ;
            selectStr += "<option value='4'>1997-3-4 13:30</option>" ;
            selectStr += "<option value='5'>97-3-4</option>" ;
            selectStr += "<option value='6'>3-4-97</option>" ;
            selectStr += "<option value='7'>03-04-97</option>" ;
            selectStr += "<option value='8'>3-4</option>" ;
            selectStr += "<option value='9'>一九九七年三月四日</option>" ;
            selectStr += "<option value='10'>一九九七年三月</option>" ;
            selectStr += "<option value='11'>三月四日</option>" ;
            selectStr += "<option value='12'>1997年3月4日</option>" ;
            selectStr += "<option value='13'>1997年3月</option>" ;
            selectStr += "<option value='14'>3月4日</option>" ;
            selectStr += "<option value='15'>星期二</option>" ;
            selectStr += "<option value='16'>二</option>" ;
            selectStr += "<option value='17'>4-Mar</option>" ;
            selectStr += "<option value='18'>4-Mar-97</option>" ;
            selectStr += "<option value='19'>04-Mar-97</option>" ;
            selectStr += "<option value='20'>Mar-97</option>" ;
            selectStr += "<option value='21'>March-97</option>" ;
            selectStr += "<option value='22'>1997-03-04</option>" ;
            selectStr += "</SELECT></TD>" ;
            break ;
        case 7:
            selectStr += "<TD class='cellDivider' NOWRAP id='" + selectInfoA[0] + "' Title='" + selectInfoA[2] + "'>" ;
            selectStr += "<SELECT name='" + selectInfoA[1] + "' style='WIDTH: 154px; HEIGHT: 23px' onChange='javascript:" + cellMName + ".changeTimeTypeJS(this);' size='1'>" ;
            selectStr += "<option value='1' selected>1:30</option>" ;
            selectStr += "<option value='2'>1:30 PM</option>" ;
            selectStr += "<option value='3'>13:30:00</option>" ;
            selectStr += "<option value='4'>1:30:00 PM</option>" ;
            selectStr += "<option value='5'>13时30分</option>" ;
            selectStr += "<option value='6'>13时30分00秒</option>" ;
            selectStr += "<option value='7'>下午1时30分</option>" ;
            selectStr += "<option value='8'>下午1时30分00秒</option>" ;
            selectStr += "<option value='9'>十三时三十分</option>" ;
            selectStr += "<option value='10'>下午一时三十分</option>" ;
            selectStr += "</SELECT></TD>" ;
            break ;
        default :
            break ;
    }

    return selectStr ;
}

/**
 * 设置显示比例
 * @param theObj Object 比例选择组件对象的引用
 */
CELL.prototype.changeViewScaleJS = function (theObj) {
    if (this.cellObj != null) {
        try {
            if (DCellWeb1 == null) {
                DCellWeb1 = this.cellObj ;
            }
            changeViewScale(theObj.value) ;
        } catch(ex) {}
    }
}

/**
 * 设置字体
 * @param theObj Object 字体选择组件对象的引用
 */
CELL.prototype.changeFontNameJS = function (theObj) {
    if (this.cellObj != null) {
        try {
            if (DCellWeb1 == null) {
                DCellWeb1 = this.cellObj ;
            }
            changeFontName(theObj.value) ;
        } catch(ex) {}
    }
}

/**
 * 设置字号
 * @param theObj Object 字号选择组件对象的引用
 */
CELL.prototype.changeFontSizeJS = function (theObj) {
    if (this.cellObj != null) {
        try {
            if (DCellWeb1 == null) {
                DCellWeb1 = this.cellObj ;
            }
            changeFontSize(theObj.value) ;
        } catch(ex) {}
    }
}

/**
 * 设置填充类型
 * @param theObj Object 填充类型选择组件对象的引用
 */
CELL.prototype.changeFillTypeJS = function (theObj) {
    if (this.cellObj != null) {
        try {
            if (DCellWeb1 == null) {
                DCellWeb1 = this.cellObj ;
            }
            changeFillType(theObj.value) ;
        } catch(ex) {}
    }
}

/**
 * 设置日期类型
 * @param theObj Object 日期类型选择组件对象的引用
 */
CELL.prototype.changeDateTypeJS = function (theObj) {
    if (this.cellObj != null) {
        try {
            if (DCellWeb1 == null) {
                DCellWeb1 = this.cellObj ;
            }
            changeDateType(theObj.value) ;
        } catch(ex) {}
    }
}

/**
 * 设置时间类型
 * @param theObj Object 时间类型选择组件对象的引用
 */
CELL.prototype.changeTimeTypeJS = function (theObj) {
    if (this.cellObj != null) {
        try {
            if (DCellWeb1 == null) {
                DCellWeb1 = this.cellObj ;
            }
            changeTimeType(theObj.value) ;
        } catch(ex) {}
    }
}

CELL.prototype.toolBarButtonMouseDown = function () {
    this.className = 'cellButtonDown' ;
    this.onmouseout = _cell_toolBar_onCbMouseOut ;
    this.onmouseup = _cell_toolBar_onCbMouseUp ;
}

CELL.prototype.toolBarButtonClick = function (obj) {
    if (null != event) {
        event.cancelBubble = true ;
    }

    //开始命令
    switch(obj.id.toUpperCase()) {
        case "CMDPRINTFILE"://打印文档
            DCellWeb1.PrintSheet(false,DCellWeb1.GetCurSheet()) ;
            break;
        case "CMDPRINTPREVIEW"://打印预览文档
            mnuFilePrintPreview_click();
            break;
        case "CMDEDITCUT"://剪切
            mnuEditCut_click();
            break;
        case "CMDEDITCOPY"://复制
            mnuEditCopy_click();
            break;
        case "CMDEDITPASTE"://粘贴
            mnuEditPaste_click();
            break;
        case "CMDEDITFIND"://查找
            mnuEditFind_click();
            break;
        case "CMDEDITUNDO"://撤消
            mnuEditUndo_click();
            break;
        case "CMDEDITREDO"://重做
            mnuEditRedo_click();
            break;
        case "CMDVIEWFREEZE"://不滚动行列
            mnuViewFreezed_click();
            break;
        case "CMDFORMATPAINTER"://格式刷
            DCellWeb1.FormatPainter();
            break;
        case "CMDSORTASCENDING"://升序排序
            cmdSortAscending_click();
            break;
        case "CMDSORTDESCENDING"://降序排序
            cmdSortDescending_click();
            break;
        case "CMDFORMULAINPUT"://输入公式
            mnuFormulaInput_click();
            break;
        case "cmdFormulaSerial"://填充单元公式序列
            mnuFormulaSerial_click();
            break;
        case "CMDFORMULASUMH"://水平求和
            cmdFormulaSumH_click();
            break;
        case "CMDFORMULASUMV"://垂直求和
            cmdFormulaSumV_click();
            break;
        case "CMDFORMULASUMHV"://双向求和
            cmdFormulaSumHV_click();
            break;
        case "CMDCHARTWZD"://图表向导
            mnuDataWzdChart_click();
            break;
        case "CMDINSERTPIC"://插入图片
            mnuFormatInsertPic_click();
            break;
        case "CMDHYPERLINK"://超级链接
            mnuEditHyperlink_click();
            break;
        case "CMDWZDBARCODE"://条形码向导
            mnuDataWzdBarcode_click();
            break;
        case "CMDSHOWGRIDLINE"://显示/隐藏背景网格线
            with(DCellWeb1){
                ShowGridLine(!GetGridLineState(GetCurSheet()), GetCurSheet() );
            }
            break;
        case "CMDVPAGEBREAK"://垂直分隔线
            with(DCellWeb1){
                if(IsColPageBreak(GetCurrentCol())) {
                    SetColPageBreak( GetCurrentCol(), 0);
                } else {
                    SetColPageBreak( GetCurrentCol(), 1);
                }
            }
            break;
        case "CMDHPAGEBREAK"://水平分隔线
            with(DCellWeb1){
                if (IsRowPageBreak(GetCurrentRow())) {
                    SetRowPageBreak( GetCurrentRow(), 0);
                } else {
                    SetRowPageBreak( GetCurrentRow(), 1);
                }
            }
            break;
        case "CMDSHOWPAGEBREAK"://显示/隐藏分隔线
            with(DCellWeb1){
                ShowPageBreak(!GetPageBreakState());
                Invalidate();
            }
            break;
        //***********************************************************
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        case "CMDBOLD"://设置粗体
            cmdBold_click();
            break;
        case "CMDITALIC"://设置斜体
            cmdItalic_click();
            break;
        case "CMDUNDERLINE"://设置下划线
            cmdUnderline_click();
            break;
        case "CMDBACKCOLOR"://设置背景色
            cmdBackColor_click();
            break;
        case "CMDFORECOLOR"://设置前景色
            cmdForeColor_click();
            break;
        case "CMDWORDWRAP"://设置自动折行
            cmdWordWrap_click();
            break;
        case "CMDALIGNLEFT"://左对齐
            cmdAlignLeft_click();
            break;
        case "CMDALIGNCENTER"://居中对齐
            cmdAlignCenter_click();
            break;
        case "CMDALIGNRIGHT"://居右对齐
            cmdAlignRight_click();
            break;
        case "CMDALIGNTOP"://居上对齐
            cmdAlignTop_click();
            break;
        case "CMDALIGNMIDDLE"://垂直居中对齐
            cmdAlignMiddle_click();
            break;
        case "CMDALIGNBOTTOM"://居下对齐
            cmdAlignBottom_click();
            break;
        case "CMDDRAWBORDER"://画框线
            cmdDrawBorder_click();
            break;
        case "CMDERASEBORDER"://抹框线
            cmdEraseBorder_click();
            break;
        case "CMDCURRENCY"://货币符号
            cmdCurrency_click();
            break;
        case "CMDPERCENT"://百分号
            cmdPercent_click();
            break;
        case "CMDTHOUSAND"://千分位
            cmdThousand_click();
            break;
        case "CMDABOUT"://关于华表插件
            cmdAbout_click();
            break;
        //***********************************************************
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        case "CMDINSERTCOL"://插入列
            cmdInsertCol_click();
            break;
        case "CMDINSERTROW"://插入行
            cmdInsertRow_click();
            break;
        case "CMDAPPENDCOL"://追加列
            cmdAppendCol_click();
            break;
        case "CMDAPPENDROW"://追加行
            cmdAppendRow_click();
            break;
        case "CMDDELETECOL"://删除列
            cmdDeleteCol_click();
            break;
        case "CMDDELETEROW"://删除行
            cmdDeleteRow_click();
            break;
        case "CMDSHEETSIZE"://表页尺寸
            cmdSheetSize_click();
            break;
        case "CMDMERGECELL"://合并单元格
            mnuFormatMergeCell_click();
            break;
        case "CMDUNMERGECELL"://取消合并单元格
            mnuFormatUnMergeCell_click();
            break;
        case "CMDMERGEROW"://行组合
            cmdMergeRow_click();
            break;
        case "CMDMERGECOL"://列组合
            cmdMergeCol_click();
            break;
        case "CMDRECALCALL"://重算全表
            mnuFormulaReCalc_click();
            break;
        case "CMDFORMULASUM3D"://设置汇总公式
            cmdFormulaSum3D_click();
            break;
        case "CMDREADONLY"://单元格只读
            cmdReadOnly_click();
            break;
        case "CMDFILLSERIAL"://序列填充
            DCellWeb1.FillSerialDlg();
            break;
        default :
            break ;
    }

    return(false) ;
}

function _cell_toolBar_onCbMouseOut() {
    event.cancelBubble = true ;

    if (null != this.sticky) {
        if (true == this.buttondown) {
            return ;
        }
    }

    this.className = (true == this.raised) ? "cellButtonRaise" : "cellButton" ;
    this.onmouseout = null ;
}

function _cell_toolBar_onCbMouseUp () {
    event.cancelBubble = true ;

    if (null != this.sticky)	{
        if (true == this.buttondown) {
            return ;
        }
    }

    this.className = (true == this.raised) ? "cellButtonRaise" : "cellButton" ;
    this.onmouseup = null ;
}

String.prototype.Trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
//-->

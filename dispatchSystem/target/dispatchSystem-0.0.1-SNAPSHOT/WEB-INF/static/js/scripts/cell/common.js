function doPreview()
{
    document.all("DCellWeb1").PrintPara( - 1, 1, 0, 0);
    document.all("DCellWeb1").PrintPreview(1, document.all("DCellWeb1").GetCurSheet);
}

function doPrint()
{
    /*if(!isInteger(document.all("printNo").value)){
      alert("请输入正确的打印份数！");
      document.all("printNo").focus();
      return;
    }

    */
    document.all("DCellWeb1").PrintPara( - 1, 1, 0, 0); /*
  document.all("DCellWeb1").PrintSetPrintCopies(document.all("printNo").value);
  */
    document.all("DCellWeb1").PrintSheet(1, document.all("DCellWeb1").GetCurSheet);
}

function doPageSet()
{
    document.all("DCellWeb1").PrintPageSetup();
}
function setBorder(col,row){
    document.all("DCellWeb1").SetCellBorder(col,row,0,0,2);
    document.all("DCellWeb1").SetCellBorder(col,row,0,1,2);
    document.all("DCellWeb1").SetCellBorder(col,row,0,2,2);
    document.all("DCellWeb1").SetCellBorder(col,row,0,3,2);
}

//报表、查询 cell页面的修改
function modifyData(the){
    if (the.checked){
        this.changeReadMode(document.all("DCellWeb1"),false) ;
        return true;
    }else{
        this.changeReadMode(document.all("DCellWeb1"),true) ;
        return false;
    }
}
//要是结果是‘0.0’，返回空；
function convertToNull(cellvalue){
    if(cellvalue==0.0){
        return "";
    }else{
        return cellvalue;
    }
}
//要是结果是‘0.0’，返回‘0’
function convertToZero(cellvalue){
    if(cellvalue==0.0 || cellvalue == 0.00){
        return "0";
    }else{
        return cellvalue;
    }
}
//对数据处理。防止系统中float数值相加，小数出现多位！
function checkFloatNum(theValue){
    theValue = theValue.clearNull() ;

    if (theValue !=null && theValue != '') {
        theValue = theValue.toString() ;
        var indexTemp = theValue.indexOf(".");

        if (theValue.indexOf(".") != -1) {//有小数
            var intValue = parseFloat(theValue.substring(0,indexTemp)) ;
            var afValue = theValue.substring(indexTemp+1,theValue.length) ;

            if (afValue.length > 2) {//小数点后面超出两位
                var valueTemp = parseFloat(afValue.substring(2,3));
                afValue = parseFloat(afValue.substring(0,2)) ;

                if (valueTemp >= 5){//四舍五入
                    afValue += 1 ;

                    if (afValue == 100) {
                        intValue += 1 ;
                        afValue = 0 ;
                    }
                }

                return intValue + '.' + afValue ;
            } else {
                return theValue ;
            }
        } else {
            return theValue ;
        }
    } else {
        return '0' ;
    }
}

function checkNum(theValue){
    if (theValue != null && theValue != ''){
        if (isNum(theValue)){
            var keyTemp = theValue.indexOf(".");
            if (keyTemp != -1){
                var intValue =theValue.substring(0,indexTemp) ;
                var afValue = theValue.substring(indexTemp+1,theValue.length);
                if (afValue != null && afValue!= ''){
                    if (intValue ==null || intValue == ''){
                        theValue = '0'+theValue;
                    }
                }
            }
        }
    }
    return theValue;
}

/**
 * cell文档内容只读，但是允许排版
 * 2006-02-13 xiangzi
 */
function changeReadMode(cellObj,flag){
    var readOnlyFlag = 1 ;
    if (cellObj != null){
        if (flag){
            readOnlyFlag = 0 ;
        }
        cellObj.EnableUndo(readOnlyFlag) ;
        cellObj.WorkBookReadOnly = flag;
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
 * 注册cell
 * 2015-05-07 xuyi
 */
function registerCell(cellObj){
    alert(cellObj!=null);
    if (cellObj != null) {
        cellObj.Login("中信报表", "",13040409, "5160-0447-0112-5004") ;
    }
}
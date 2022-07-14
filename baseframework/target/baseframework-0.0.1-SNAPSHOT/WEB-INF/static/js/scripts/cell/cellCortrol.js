
    function cell_SaveAs(theCellObj){
        theCellObj.SaveFile();
    }

function cell_Preview(theCellObj){
    theCellObj.PrintSetDefautPrinter();
    theCellObj.PrintPara(-1, 1, 0, 0);
    theCellObj.PrintPreviewEx(0,theCellObj.GetCurSheet,false);
}
/*
直接打印（2008-10-13）
*/
function cell_Print1(theCellObj){
    theCellObj.PrintSetDefautPrinter();
    theCellObj.PrintPara(-1, 1, 0, 0);
    //theCellObj.PrintSetPrintCopies(copys);
    theCellObj.PrintSetPrintRange(0,0);
    theCellObj.PrintSheet(1,theCellObj.GetCurSheet);
}
function cell_Print(theCellObj,copys){
    theCellObj.PrintPara(-1, 1, 0, 0);
    theCellObj.PrintSetPrintCopies(copys);
    theCellObj.PrintSetPrintRange(0,0);
    theCellObj.PrintSheet(1,theCellObj.GetCurSheet);
}
function cell_PageSet(theCellObj){
    theCellObj.PrintPageSetup();
}
function cell_SetReadOnly(theCellObj,flag){
    if(!flag){
        theCellObj.WorkBookReadOnly=false;
        for(i=0;i<theCellObj.GetTotalSheets();i++){
            theCellObj.ShowSideLabel(1,i);
            theCellObj.ShowTopLabel(1,i);
        }
        //theCellObj.ShowSheetLabel(1,0);
    }else{
        theCellObj.WorkBookReadOnly=true;
        for(i=0;i<theCellObj.GetTotalSheets();i++){
            // alert(i);
            theCellObj.SetCurSheet(i);
            theCellObj.ShowSideLabel(0,i);
            theCellObj.ShowTopLabel(0,i);
        }
        //theCellObj.ShowSheetLabel(0,0);
    }
}

/**************Cell Data Class**************/
function CellData(cell,col,row,sheet,val){
    this.cellObj = document.all(cell);
    this.colindex = parseInt(col);
    this.rowindex = parseInt(row);
    this.sheetindex = parseInt(sheet);
    this.cellvalue = val;
}
CellData.prototype.show = function () {
    //alert("ok");
    this.cellObj.SetCurSheet(this.sheetindex);
    var aTemp = this.cellvalue.split(",");
    //alert(trim(aTemp[0]));

    if(trim(aTemp[0]) == "$R"){//Row Cyc
        cycTimes = trim(aTemp[1]);
        //alert(this.cellObj.GetCols(this.sheetindex));
        this.cellObj.CopyRange(this.colindex,this.rowindex,this.cellObj.GetCols(this.sheetindex)-1,this.rowindex);
        this.cellObj.InsertRow(this.rowindex+1,cycTimes,this.sheetindex);
        //alert(this.rowindex+","+cycTimes);
        for(cycTemp=1;cycTemp<=cycTimes;cycTemp++){
            this.cellObj.Paste(this.colindex,this.rowindex+cycTemp,3,0,0);
        }
        //alert("ok");
    }
    else if(trim(aTemp[0]) == "$C"){//Col Cyc
        cycTimes = trim(aTemp[1]);
        //alert(this.cellObj.GetCols(this.sheetindex));
        this.cellObj.CopyRange(this.colindex,this.rowindex,this.colindex,this.rowindex);
        this.cellObj.InsertCol(this.colindex+1,cycTimes,this.sheetindex);
        //this.cellObj.Paste(this.colindex,this.rowindex,3,0,0);
    }
    else {
        //if(trim(aTemp[0]) != "$R"&&trim(aTemp[0]) != "$C"){
        this.cellObj.S(this.colindex,this.rowindex,this.sheetindex,this.cellvalue);
    }
    //alert("ok");
}

CellData.prototype.init = function () {
    //alert("ok");
    this.cellObj.SetCurSheet(this.sheetindex);
    var aTemp = this.cellvalue.split(",");
    //alert(trim(aTemp[0]));
    if(trim(aTemp[0]) == "$R"){//Row Cyc
        cycTimes = trim(aTemp[1]);
        //alert(this.cellObj.GetCols(this.sheetindex));
        this.cellObj.CopyRange(this.colindex,this.rowindex,this.cellObj.GetCols(this.sheetindex)-1,this.rowindex);
        this.cellObj.InsertRow(this.rowindex+1,cycTimes,this.sheetindex);
        for(cycTemp=1;cycTemp<=cycTimes;cycTemp++){
            this.cellObj.Paste(this.colindex,this.rowindex+cycTemp,3,0,0);
        }
        //alert("ok");
    }
    else if(trim(aTemp[0]) == "$C"){//Col Cyc
        cycTimes = trim(aTemp[1]);
        //alert(this.cellObj.GetCols(this.sheetindex));
        this.cellObj.CopyRange(this.colindex,this.rowindex,this.colindex,this.rowindex);
        this.cellObj.InsertCol(this.colindex+1,cycTimes,this.sheetindex);
        //this.cellObj.Paste(this.colindex,this.rowindex,3,0,0);
    }
    //alert("ok");
}

function startLoadReportXML(cellName,url){
    var xmlHttp = XmlHttp.create();
    xmlHttp.open("GET", url, true); // async
    xmlHttp.onreadystatechange = function (){
        reportXMLLoaded(xmlHttp,cellName);
    };
    // call in new thread to allow ui to update
    window.setTimeout(function () {
        xmlHttp.send(null);
    }, 10);
}
function reportXMLLoaded(xmlHttp,cellName){
    if (xmlHttp.readyState == 4) {
        var xmlReports = xmlHttp.responseXML.documentElement.childNodes;
        if(xmlReports == null||xmlReports.length == 0){
            alert("生成报表错误，没有取得报表数据");
            return null;
        }
        var datalen = xmlReports.length;
        var rpDatas = new Array(datalen);
        var value = "", value_1 = "";
        var index_1 = 0, index_2 = datalen-1;
        for(i=0; i<datalen; i++){
            value = xmlReports[i].getAttribute("cellValue");
            value_1 = value.split(",");
            if(trim(value_1[0]) == "$R" || trim(value_1[0]) == "$C"){
                rpDatas[index_1++] = new CellData(cellName,xmlReports[i].getAttribute("colIndex"),xmlReports[i].getAttribute("rowIndex"),xmlReports[i].getAttribute("sheetIndex"),value);
            } else {
                rpDatas[index_2--] = new CellData(cellName,xmlReports[i].getAttribute("colIndex"),xmlReports[i].getAttribute("rowIndex"),xmlReports[i].getAttribute("sheetIndex"),value);
            }
        }
        theXReportLoaded(rpDatas);
    }
}
//-->
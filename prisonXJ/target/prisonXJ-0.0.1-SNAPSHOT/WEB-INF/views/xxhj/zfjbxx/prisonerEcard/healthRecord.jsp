<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
	#jkda_table td {
		padding:10px 0px 5px 0px;
		vertical-align:middle;
		font-weight: bold;
		white-space:nowrap;
		}

	.jkda_textarea td {
		padding:10px 0px 5px 0px;
		vertical-align:middle;
		text-align:center;
		font-weight: bold;
	}
	
	.jkda_three td {
		padding:10px 0px 5px 0px;
		vertical-align:middle;
		text-align:center;
		font-weight: bold;
		width:80;
	}
	#formId_healthRecord {
		padding-top:0px;
		padding-bottom:0px;
	}
</style>

<div id="jkda_div">
	<cui:form id="formId_healthRecord">
		<div style="margin: 20px 5px 5px 5px;">
			<table id="jkda_table" style="width: 100%; border: #B3D0F4 2px solid; border-collapse:separate; border-spacing:8px; background-color:#F5FAFA;">
			    <tr>
			    	<td colspan="6" style="font-size: 18px;font-weight: bold;padding:5px;text-align:center">罪犯收监身体检查表</td>
			    <tr>
				<tr>
					<td style="text-align:right">姓名：</td>
					<td><cui:input id="xingMing" name="XINGMING"></cui:input></td>
					<td style="text-align:right">性别：</td>
					<td><cui:combobox id="XINGBIE"  name="XINGBIE" data="combobox_xb"></cui:combobox></td>
					<td style="text-align:right">民族：</td>
					<td><cui:combobox id="minZu"  data="combobox_mz" name="MINZU"></cui:combobox></td>
				</tr>
			    <tr>
			    	<td style="text-align:right">出生日期：</td>
					<td><cui:input id="chuShengRiQi" name="CHUSHENGRIQI"></cui:input></td>
					<td style="text-align:right">刑期：</td>
					<td><cui:input id="xingQi" name="XINGQI"></cui:input></td>
					<td style="text-align:right">体温：</td>
	 				<td><cui:input id="tiWen" name="TIWEN"></cui:input></td>
	 			</tr>
				<tr>
					<td style="text-align:right">血型：</td>
					<td><cui:input id="xueXing" name="XUEXING"></cui:input></td>
					<td style="text-align:right">血压(mmHg)：</td>
					<td><cui:input id="xueYa" name="XUEYA"></cui:input></td>
					<td style="text-align:right">身高(cm)：</td>
					<td><cui:input id="shenGao" name="SHENGAO"></cui:input></td>
				</tr>
				<tr>
					<td style="text-align:right">体重(kg)：</td>
					<td><cui:input id="tiZhong" name="TIZHONG"></cui:input></td>
					<td style="text-align:right">罪名：</td>
					<td colspan="3" align="left"><cui:combotree id="zuiMing"  multiple="true"  data="combobox_zm" name="ZUIMING"  ></cui:combotree></td>
				</tr>
			
				<tr>
				    <td style="text-align:right">家庭住址：</td>
					<td colspan="5"><cui:combobox id="shengShiQu" data="combobox_jg" name="SHENGSHIQU" ></cui:combobox>
					<cui:input id="jiaTingdiZhi" name="JIATINGDIZHI"></cui:input></td>
				</tr>
				
				 <tr>
					<td style="text-align:right">既往病史：</td>
					<td colspan="5"><cui:textarea width="850" id="jiWangBingShi" name="JIWANGBINGSHI" readonly="readonly"></cui:textarea></td>
				</tr>
			</table>
		</div>
		<div style="margin: 20px 5px 5px 5px;">
			<table class="jkda_textarea" style="width: 100%; border: #B3D0F4 2px solid; border-collapse:separate; border-spacing:8px; background-color:#F5FAFA;"> 
				<tr>
					<td colspan="4" style="font-size: 18px;font-weight: bold;padding:5px;text-align:center">体检结果</td>
				</tr> 
				<tr>
					<td style="width:70px;text-align:right">一般情况：</td>
					<td colspan="3" style="text-align:left;"><cui:textarea id="yiBanQingKuang" name="YIBANQINGKUANG" readonly="readonly" width="850"></cui:textarea></td>
				</tr>
				<tr>
					<td width="60px" style="text-align:right">内科：</td>
					<td colspan="3" style="text-align:left;"><cui:textarea id="neiKe" name="NEIKE" readonly="readonly" width="850"></cui:textarea></td>
				</tr>
				<tr>
					<td width="60px" style="text-align:right">外科：</td>
					<td colspan="3" style="text-align:left;"><cui:textarea  id="waiKe"  name="WAIKE" readonly="readonly" width="850" ></cui:textarea></td>
				</tr>
				<tr>
					<td width="60px" style="text-align:right">五官：</td>
					<td colspan="3" style="text-align:left;"><cui:textarea  id="wuGuan" name="WUGUAN" readonly="readonly" width="850" ></cui:textarea></td>
				</tr>
				<tr>
					<td width="60px" style="text-align:right" >皮肤：</td>
					<td colspan="3" style="text-align:left;"><cui:textarea  id="piFu" name="PIFU" readonly="readonly" width="850"></cui:textarea></td>
				</tr>
				<tr>
					<td width="60px" style="text-align:right">妇科：</td>
					<td colspan="3" style="text-align:left;"><cui:textarea id="fuKe" name="FUKE" readonly="readonly" width="850"></cui:textarea></td>
				</tr>
				<tr>
					<td width="60px" style="text-align:right">化验：</td>
					<td colspan="3" style="text-align:left;"><cui:textarea id="huaYan" name="HUAYAN" readonly="readonly" width="850"></cui:textarea></td>
				</tr>
				<tr>
					<td width="60px" style="text-align:right">其他检查：</td>
					<td colspan="3" style="text-align:left;"><cui:textarea id="qiTaJianCha" name="QITAJIANCHA" readonly="readonly" width="850"></cui:textarea></td>
				</tr>
			
				<tr>	                   
					<td width="60px">主检医师意见:</td>
					<td style="text-align:left;"><cui:textarea id='zhuJianYiShengYiJian' name="ZHUJIANYISHENGYIJIAN" width="370"></cui:textarea></td>
					<td width="60px" style="text-align:right">医院意见:</td>
					<td style="text-align:left;"><cui:textarea id="yiYuanYiJian" name="YIYUANYIJIAN" width="370"></cui:textarea></td>
				</tr>
				<tr>
				 	<td></td>
					<td style="text-align:right;"> 体检医师签字：<cui:input id="zhuJianYiShengQianMing" name="ZHUJIANYISHENGQIANMING" width="150"></cui:input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td></td>
					<td style="text-align:right;"> 医院盖章:<cui:input id="yiYuanQianMing" name="YIYUANQIANMING" width="150"></cui:input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table> 
		</div>
		<div style="margin: 20px 5px 5px 5px;">
			<table class="jkda_three" style="width: 100%; border: #B3D0F4 2px solid; border-collapse:separate; border-spacing:8px; background-color:#F5FAFA;">
				<tr><td colspan="2" style="font-size: 18px;font-weight: bold;padding:5px;text-align:center">罪犯入监(所)前所患疾病追述</td></tr>
				<tr>
					<td width="60px">入监(所)前所患疾病：</td>
					<td style="text-align:left;"><cui:textarea  width="850" id="ruJianQianHuanBing" name="RUJIANQIANHUANBING" readonly="readonly"></cui:textarea></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align:right;"> 医生签名:<cui:input id="ruJianQianHuanBingYiShengQianMing" name="RUJIANQIANHUANBINGYISHENGQIANMING" width="150"></cui:input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td width="60px">阳性体征：</td>
					<td style="text-align:left;"><cui:textarea id="yangXingTiZheng" name="YANGXINGTIZHENG" readonly="readonly" width="850"></cui:textarea></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align:right;"> 医生签名:<cui:input id="yangXingTiZhengYiShengQianMing" name="YANGXINGTIZHENGYISHENGQIANMING" width="150"></cui:input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td width="60px">治疗经过及结果：</td>
					<td style="text-align:left;"><cui:textarea id="zhiLiaoJingGuo" name="ZHILIAOJINGGUO" readonly="readonly" width="850" ></cui:textarea></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align:right;"> 医生签名:<cui:input id="zhiLiaoJingGuoYiShengQianMing" name="ZHILIAOJINGGUOYISHENGQIANMING" width="150"></cui:input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</div>
 	</cui:form> 
</div>
 
<script type="text/javascript">
	var combobox_mz = <%=CodeFacade.loadCode2Json("4.3.6")%>;
	var combobox_xb = <%=CodeFacade.loadCode2Json("4.3.3")%>;
	var combobox_zm = <%=CodeFacade.loadCode2SimpleTreeJson("4.4.1", null, 5)%>
	var combobox_jg = <%=CodeFacade.loadCode2Json("4.3.2")%>;
	
	 $.parseDone(function() {

		 var url = "${ctx}/xxhj/zfjbxx/listPrisonerHealthy?pheCusNumber=${pbdCusNumber}&&phePrsnrIdnty=${pbdPrsnrIdnty}";
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				success : function(data) {
			     if (data) {
						$("#formId_healthRecord").form("loadData",data.data[0]);
						$("#formId_healthRecord").form("setReadOnly",true);
					}  
				},
			});	
			
			$("#jkda_div").mCustomScrollbar({
				theme : "minimal-light",
				autoExpandScrollbar : true,
				axis: "y"
			}); 
	}); 

</script>
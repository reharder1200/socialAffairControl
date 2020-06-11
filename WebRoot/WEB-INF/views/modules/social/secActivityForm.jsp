<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/viewer/css/viewer.min.css">
	<style>
		#viewer { width: 700px; margin: 0 auto; margin-left:0;font-size: 0;}
		#viewer li { display: inline-block; width: 25%; margin-left: 1%; padding-top: 1%;}
		#viewer li img { width: 100%;}	
		.viewer-footer li {line-height:0;}
	</style>
	<script src="${ctxStatic}/viewer/js/viewer-jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("#viewer").viewer({
				//inline:'true',
				zIndex:'5000',
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/social/secActivity/">活动列表</a></li>
		<li class="active"><a href="${ctx}/social/secActivity/form?id=${secActivity.id}"><shiro:hasPermission name="social:secActivity:edit">${not empty secActivity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="social:secActivity:edit">查看</shiro:lacksPermission>活动</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="secActivity" action="${ctx}/social/secActivity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动类别：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('activity_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${secActivity.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${secActivity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最少人数：</label>
			<div class="controls">
				<form:input path="minPeople" htmlEscape="false" maxlength="15" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大人数：</label>
			<div class="controls">
				<form:input path="maxPeople" htmlEscape="false" maxlength="15" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">省编码：</label>
			<div class="controls">
				<sys:treeselect id="province" name="province.id" value="${area.parent.id}" labelName="parent.name" labelValue="${secActivity.province}"  codeName="province" codeValue="${area.code}" 
					title="区域" url="/sys/area/treeData" extId="${area.id}" cssClass="" allowClear="true"/>
				<form:input path="province" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市编码：</label>
			<div class="controls">
				<sys:treeselect id="city" name="city.id" value="${area.parent.id}" labelName="parent.name" labelValue="${secActivity.city}"  codeName="city" codeValue="${area.code}"
					title="区域" url="/sys/area/treeData" extId="${area.id}" cssClass="" allowClear="true"/>
				<form:input path="city" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区编码：</label>
			<div class="controls">
				<sys:treeselect id="district" name="district.id" value="${area.parent.id}" labelName="parent.name" labelValue="${secActivity.district}"  codeName="district" codeValue="${area.code}"
					title="区域" url="/sys/area/treeData" extId="${area.id}" cssClass="" allowClear="true"/>
				<form:input path="district" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">位置：</label>
			<div class="controls">
				<form:input path="location" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">位置详情：</label>
			<div class="controls">
				<form:input path="locationDetatil" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经度：</label>
			<div class="controls">
				<form:input path="activityLat" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纬度：</label>
			<div class="controls">
				<form:input path="activityLon" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收费模式：</label>
			<div class="controls">
				<form:select path="chargeType" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('charge_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收费金额：</label>
			<div class="controls">
				<form:input path="chargeAmount" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动描述：</label>
			<div class="controls">
				<form:textarea path="activityDescription" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动图片：</label>
			<div class="controls">
				<%-- <form:hidden id="picSaveUrl" path="picSaveUrl" htmlEscape="false" maxlength="1000" class="input-xlarge required"/> 
				<sys:ckfinder input="picSaveUrl" type="images" uploadPath="/photo" selectMultiple="true" maxWidth="100" maxHeight="100"/>--%>
				<%-- <form:input path="picSaveUrl" htmlEscape="false" maxlength="1000" class="input-xlarge "/> --%>
				 <ul id="viewer">
					 <c:forEach var="imgUrl" items="${fn:split(secActivity.picSaveUrl,',')}">
			            <li><img src="${imgUrl }" alt="图片1"></li>
			         </c:forEach>
		         </ul>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动联系人微信号：</label>
			<div class="controls">
				<form:input path="contactWx" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人手机：</label>
			<div class="controls">
				<form:input path="contactMobile" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动状态：</label>
			<div class="controls">
				<form:select path="activityStatus" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('activity_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">押金状态：</label>
			<div class="controls">
				<form:select path="depositStatus" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('deposit_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">押金订单号：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动发起人：</label>
			<div class="controls">
				<form:select path="activityStarter.id" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${secUserList}" itemLabel="nickname" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="social:secActivity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户基本信息管理</title>
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
		<li><a href="${ctx}/social/secUser/">用户基本信息列表</a></li>
		<li class="active"><a href="${ctx}/social/secUser/form?id=${secUser.id}">用户基本信息<shiro:hasPermission name="social:secUser:edit">${not empty secUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="social:secUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="secUser" action="${ctx}/social/secUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">手机号码：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="15" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">昵称：</label>
			<div class="controls">
				<form:input path="nickname" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">个性签名：</label>
			<div class="controls">
				<form:input path="wxSign" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信id：</label>
			<div class="controls">
				<form:input path="wxOpenid" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头像：</label>
			<div class="controls">
				<%-- <form:hidden id="avatar" path="avatar" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<sys:ckfinder input="avatar" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/> --%>
				<%-- <form:input path="avatar" htmlEscape="false" maxlength="1000" class="input-xlarge "/> --%>
				<ul id="viewer">
					 <c:forEach var="imgUrl" items="${fn:split(secUser.avatar,',')}">
			            <li><img src="${imgUrl }" alt="图片1"></li>
			         </c:forEach>
		         </ul>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型：</label>
			<div class="controls">
				<form:select path="userType" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<%-- <form:input path="userType" htmlEscape="false" maxlength="1" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否实名：</label>
			<div class="controls">
				<form:select path="isRealName" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<%-- <form:input path="isRealName" htmlEscape="false" maxlength="1" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('user_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<%-- <form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册时间：</label>
			<div class="controls">
				<input name="registerDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${secUser.registerDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="social:secUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
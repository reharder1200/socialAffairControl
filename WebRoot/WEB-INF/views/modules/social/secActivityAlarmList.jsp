<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动提醒管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/social/secActivityAlarm/">活动提醒列表</a></li>
		<shiro:hasPermission name="social:secActivityAlarm:edit"><li><a href="${ctx}/social/secActivityAlarm/form">活动提醒添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="secActivityAlarm" action="${ctx}/social/secActivityAlarm/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>活动标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>评论人：</label>
				<form:input path="user.nickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>活动</th>
				<th>提醒人</th>
				<th>报名时间</th>
				<th>备注</th>
				<shiro:hasPermission name="social:secActivityAlarm:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="secActivityAlarm">
			<tr>
				<td><a href="${ctx}/social/secActivity/form?id=${secActivityAlarm.activityId}">
					${secActivityAlarm.title}
				</a></td>
				<td><a href="${ctx}/social/secUser/form?id=${secActivityAlarm.user.id}">
					${secActivityAlarm.user.nickname}
				</a></td>
				<td><fmt:formatDate value="${secActivityAlarm.alarmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					${secActivityAlarm.remarks}
				</td>
				<shiro:hasPermission name="social:secActivityAlarm:edit"><td>
    				<a href="${ctx}/social/secActivityAlarm/form?id=${secActivityAlarm.id}">修改</a>
					<a href="${ctx}/social/secActivityAlarm/delete?id=${secActivityAlarm.id}" onclick="return confirmx('确认要删除该活动提醒吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
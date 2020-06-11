<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动报名信息管理</title>
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
		<li class="active"><a href="${ctx}/social/secActivityApply/">活动报名信息列表</a></li>
		<shiro:hasPermission name="social:secActivityApply:edit"><li><a href="${ctx}/social/secActivityApply/form">活动报名信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="secActivityApply" action="${ctx}/social/secActivityApply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>活动标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>评论人：</label>
				<form:input path="user.nickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>活动状态：</label>
				<form:select path="signType" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sign_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>报名人</th>
				<th>报名时间</th>
				<th>报名状态</th>
				<th>备注</th>
				<shiro:hasPermission name="social:secActivityApply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="secActivityApply">
			<tr>
				<td><a href="${ctx}/social/secActivity/form?id=${secActivityApply.activityId}">
					${secActivityApply.title}
				</a></td>
				<td><a href="${ctx}/social/secUser/form?id=${secActivityApply.user.id}">
					${secActivityApply.user.nickname}
				</a></td>
				<td><fmt:formatDate value="${secActivityApply.signDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					${fns:getDictLabel(secActivityApply.signType, 'sign_type', '')}
				</td>
				<td>
					${secActivityApply.remarks}
				</td>
				<shiro:hasPermission name="social:secActivityApply:edit"><td>
    				<a href="${ctx}/social/secActivityApply/form?id=${secActivityApply.id}">修改</a>
					<a href="${ctx}/social/secActivityApply/delete?id=${secActivityApply.id}" onclick="return confirmx('确认要删除该活动报名信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价信息管理</title>
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
		<li class="active"><a href="${ctx}/social/secEvaluate/">评价信息列表</a></li>
		<shiro:hasPermission name="social:secEvaluate:edit"><li><a href="${ctx}/social/secEvaluate/form">评价信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="secEvaluate" action="${ctx}/social/secEvaluate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>活动标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>评论人：</label>
				<form:input path="user.nickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>是否显示：</label>
				<form:select path="isShow" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>活动标题</th>
				<th>评论人</th>
				<th>活动得分</th>
				<th>发起人得分</th>
				<th>是否显示</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="social:secEvaluate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="secEvaluate">
			<tr>
				<td><a href="${ctx}/social/secActivity/form?id=${secEvaluate.activityId}">
					${secEvaluate.title}
				</a></td>
				<td><a href="${ctx}/social/secUser/form?id=${secEvaluate.user.id}">
					${secEvaluate.user.nickname}
				</a></td>
				<td>
					${secEvaluate.activityScore}
				</td>
				<td>
					${secEvaluate.organizerScore}
				</td>
				<td>
					${fns:getDictLabel(secEvaluate.isShow, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${secEvaluate.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${secEvaluate.remarks}
				</td>
				<shiro:hasPermission name="social:secEvaluate:edit"><td>
    				<a href="${ctx}/social/secEvaluate/form?id=${secEvaluate.id}">修改</a>
					<a href="${ctx}/social/secEvaluate/delete?id=${secEvaluate.id}" onclick="return confirmx('确认要删除该评价信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
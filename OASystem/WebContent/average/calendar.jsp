<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="henu.bean.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>OA办公系统</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon" href="../img/OA.ico" type="image/x-icon" />
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css">

<link href='../css/fullcalendar.css' rel='stylesheet' />

<!-- Font Awesome -->
<link rel="stylesheet" href="../css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="../css/ionicons.min.css">
<!-- jvectormap -->
<link rel="stylesheet" href="../css/jquery-jvectormap.css">
<!-- Theme style -->
<link rel="stylesheet" href="../css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="../css/_all-skins.min.css">
<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<style>

/* 语言选择 */
#top {
	background: #eee;
	border-bottom: 1px solid #ddd;
	padding: 0 10px;
	line-height: 40px;
	font-size: 12px;
}
/* 日历 */
#calendar {
	margin: 40px auto;
	padding: 0 10px;
}
/* Event 参数 className 的值 */
.done:before {
	content: "【 已完成 】";
	background-color: yellow;
	color: green;
	text-align: center;
	font-weight: bold;
	width: 100%;
}
/* Event 参数 className 的值 */
.doing:before {
	content: "【 未完成 】";
	background-color: yellow;
	color: red;
	text-align: center;
	font-weight: bold;
}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<c:if test="${sessionScope.em.employeeID==null}" > 
<c:redirect url="../index.html"/>
</c:if>
<div class="wrapper">
  <header class="main-header">

    <!-- Logo -->
    <a href="averageHome.jsp" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Admin</b>LTE</span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
               <i class="fa fa-code-fork"> 友情链接</i>
            </a>
            <ul class="dropdown-menu">
              <li>
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="https://mail.qq.com/" target="view_window">
                      <i class="fa fa-qq mr-2"></i>                        
                      &#12288;邮箱&#12288;&#12288;
                      <small><i class="fa fa-hand-o-right"></i> 中国流行的电子邮件网站</small>
                    </a>
                  </li>
                  <!-- end message -->
                  <li>
                    <a href="https://www.baidu.com" target="view_window">
                      <i class="fa fa-search mr-2"></i>                       
                      &#12288;百度  &#12288;&#12288;
                      <small><i class="fa fa-hand-o-right"></i> 中国流行的搜索引擎</small> 
                    </a>
                  </li>
                  <li>
                    <a href="https://weibo.com" target="view_window">
                      <i class="fa fa-weibo mr-2"></i>                        
                      &#12288;微博 &#12288;&#12288;
                       <small><i class="fa fa-hand-o-right"></i> 中国最流行的社交网站</small> 
                    </a>
                  </li>
  
                </ul>
              </li>
            </ul>
          </li>
           </ul>
          <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
               <i class="fa fa-question-circle"> 帮助</i>
            </a>
            <ul class="dropdown-menu">
              <li>
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="http://id.tudou.com/i/UMzI0MjE5NjMxMg==?spm=a2h3j.8428770.0.0" target="view_window">
                      <i class="fa fa-video-camera mr-2"></i> &#12288;视频教程&#12288;
                      <small><i class="fa fa-hand-o-right"></i> 一个让你容易上手的视频</small>
                    </a>
                  </li>
                  <!-- end message -->
                  <li>
                    <a href="../explain.html" target="view_window">
                     <i class="fa fa-archive mr-2"></i> &#12288;帮助文档&#12288;
                      <small><i class="fa fa-hand-o-right"></i> 一本通俗易懂的帮助文档</small> 
                    </a>
                  </li>
                </ul>
              </li>
            </ul>
          </li>
           </ul>
                    <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
               <i class="fa fa-user"> 我的</i>
            </a>
            <ul class="dropdown-menu">
              <li>
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="../a_Person?method=findinfo">
                      <i class="fa fa-edit mr-2"></i>                        
                      &#12288;个人信息&#12288;&#12288;
                      <small><i class="fa fa-hand-o-right"></i> 查看和完善个人信息</small>
                    </a>
                  </li>
                  <!-- end message -->
                  <li>
                    <a href="pwd_update.jsp">
                     <i class="fa fa-key mr-2"></i>                        
                      &#12288;修改密码&#12288;&#12288;
                      <small><i class="fa fa-hand-o-right"></i> 修改个人密码</small> 
                    </a>
                  </li>
                  <li>
                    <a href="../LoginServlet?method=logout">
                      <i class="fa fa-power-off mr-2"></i>                       
                      &#12288; 退出系统&#12288;&#12288;
                       <small><i class="fa fa-hand-o-right"></i> 退出系统</small> 
                    </a>
                  </li>
  
                </ul>
              </li>
            </ul>
          </li>
           </ul>
      </div>

    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${sessionScope.em.employeeName}</p>
          <a href="#"><i class="fa fa-circle text-success"></i>${sessionScope.em.deptName}${sessionScope.em.jobName}</a>
        </div>
      </div>

      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li >
          <a href="../a_Colleague?method=search">
            <i class="fa fa-group"></i> <span>同事</span>
          </a>
        </li>
        <li>
          <a href="../a_Note?method=allnote">
            <i class="fa fa-volume-up"></i> <span>公告</span>
          </a>
        </li>
        <li>
          <a href="../a_Activity?method=activity_part">
            <i class="fa fa-bar-chart"></i> <span>活动投票</span>
          </a>
        </li>
        <li>
          <a href="../a_File?method=file_receive">
            <i class="fa fa-file-text-o"></i> <span>文件</span>
          </a>
        </li>
        <li>
          <a href="../a_Meeting?method=myMeeting">
            <i class="fa fa-briefcase"></i> <span>会议</span>
          </a>
        </li>
        <li> 
          <a href="../a_Mail?method=mail_receivebox">
            <i class="fa fa-envelope-o"></i> <span>邮件</span>
          </a>
        </li>
        <li class="active">
          <a href="calendar.jsp">
            <i class="fa fa-calendar"></i> <span>日程</span>
          </a>
        </li>
         <li>
          <a href="../a_Leave?method=leave_my">
            <i class="fa fa-check-square-o"></i> <span>请假</span>
          </a>
        </li>
        <li>
          <a href="../a_Task?method=myTask">
            <i class="fa fa-tasks"></i> <span>任务</span>
          </a>
        </li>
       </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
 <!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>日程</h1>
				<ol class="breadcrumb">

					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">日程</li>
				</ol>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box box-primary">
							<div class="box-body no-padding">

								<!-- THE CALENDAR -->
								<div id="calendar"></div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /. box -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>

	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="addModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button onClick="hideModal('addModal');" type="button"
						class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="addModalLabel">Add</h4>
				</div>
				<div class="modal-body" id="addModalBody">

					<form class="form-horizontal" id="addForm">
						<div class="alert alert-info" role="alert">
							<strong>声明 !</strong> 如果日程时间精确到“天”请选择 <strong> 全天 </strong>
							，如果精确到“时间”请选择 <strong> 非全天 </strong> ！
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">&nbsp;</label>
							<div class="col-sm-10">
								<div style="margin: auto;">
									<p class="radio-p" class="allDayType">
										<label class="js-radio-box" style="left: 0%;"> <input type="radio" value="1" id="7allDayAdd" name="allDay" checked />
										<label class="genderLab" for="7allDayAdd" style="font-size: 15px;">全天</label>&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" value="0" id="8allDayAdd" name="allDay" />
											<label class="genderLab" for="8allDayAdd" style="font-size: 15px;">非全天</label>
										</label>
									</p>
								</div>
							</div>
						</div>
						<div class="form-group onAllDay">
							<label class="col-sm-2 control-label">开始时间:</label>
							<div class="col-sm-10">
								<input name="startTime" value="" type="text" readonly
									class="Wdate form-control validate[required,maxSize[60]] form_datetime"
									onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"
									id="form-validation-field-0">
							</div>
						</div>
						<div class="form-group onAllDay">
							<label class="col-sm-2 control-label">结束时间:</label>
							<div class="col-sm-10">
								<input name="endTime" value="" type="text" readonly
									class="Wdate form-control validate[required,maxSize[60]] form_datetime"
									onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"
									id="form-validation-field-0"> <span style="color: red;">如果是当天的日程，结束时间可以不填！</span>
							</div>
						</div>
						<div class="form-group unAllDay" style="display: none;">
							<label class="col-sm-2 control-label">开始时间:</label>
							<div class="col-sm-10">
								<input name="startTime" value="" type="text" readonly
									class="Wdate form-control validate[required,maxSize[60]] form_datetime"
									onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})"
									id="form-validation-field-0">
							</div>
						</div>
						<div class="form-group unAllDay" style="display: none;">
							<label class="col-sm-2 control-label">结束时间:</label>
							<div class="col-sm-10">
								<input name="endTime" value="" type="text" readonly
									class="Wdate form-control validate[required,maxSize[60]] form_datetime"
									onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})"
									id="form-validation-field-0"> <span style="color: red;">如果是当天的日程，结束时间可以不填！</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">内容:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="title" id=""
									placeholder="请输入内容">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">类型:</label>
							<div class="col-sm-10">
								<select id="color" name="color" class="form-control">
									<option value="green">轻松</option>
									<option value="#3a87ad">普通</option>
									<option value="red">紧急</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">完成状态:</label>
							<div class="col-sm-10">
								<select id="isFinish" name="isFinish" class="form-control">
									<option value="0">未完成</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" class="btn btn-success addBtn">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="updatefLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button onClick="hideModal('updateModal');" type="button"
						class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="updateModalLabel">Update</h4>
				</div>
				<div class="modal-body" id="updateModalBody">

					<form class="form-horizontal" id="updateForm">
						<div class="alert alert-info" role="alert">
							<strong>声明 !</strong> 如果日程时间精确到“天”请选择 <strong> 全天 </strong>
							，如果精确到“时间”请选择 <strong> 非全天 </strong> ！
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">&nbsp;</label> <input
								type="hidden" class="form-control" name="id" id="updateID"
								value="">
							<div class="col-sm-10">
								<div style="margin: auto;">
									<p class="radio-p" class="allDayType">
										<label class="js-radio-box" style="left: 0%;"> <input
											type="radio" value="1" id="7allDayUpdate" name="allDay"
											checked /><label class="genderLab" for="7allDayUpdate"
											style="font-size: 15px;">全天</label>&nbsp;&nbsp;&nbsp;&nbsp; <input
											type="radio" value="0" id="8allDayUpdate" name="allDay" /><label
											class="genderLab" for="8allDayUpdate"
											style="font-size: 15px;">非全天</label>
										</label>
									</p>
								</div>
							</div>
						</div>
						<div class="form-group onAllDay">
							<label class="col-sm-2 control-label">开始时间:</label>
							<div class="col-sm-10">
								<input name="startTime" value="" type="text" readonly
									class="Wdate form-control validate[required,maxSize[60]] form_datetime"
									onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"
									id="form-validation-field-0">
							</div>
						</div>
						<div class="form-group onAllDay">
							<label class="col-sm-2 control-label">结束时间:</label>
							<div class="col-sm-10">
								<input name="endTime" value="" type="text" readonly
									class="Wdate form-control validate[required,maxSize[60]] form_datetime"
									onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"
									id="form-validation-field-0"> <span style="color: red;">如果是当天的日程，结束时间可以不填！</span>
							</div>
						</div>
						<div class="form-group unAllDay" style="display: none;">
							<label class="col-sm-2 control-label">开始时间:</label>
							<div class="col-sm-10">
								<input name="startTime" value="" type="text" readonly
									class="Wdate form-control validate[required,maxSize[60]] form_datetime"
									onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})"
									id="form-validation-field-0">
							</div>
						</div>
						<div class="form-group unAllDay" style="display: none;">
							<label class="col-sm-2 control-label">结束时间:</label>
							<div class="col-sm-10">
								<input name="endTime" value="" type="text" readonly
									class="Wdate form-control validate[required,maxSize[60]] form_datetime"
									onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})"
									id="form-validation-field-0"> <span style="color: red;">如果是当天的日程，结束时间可以不填！</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">内容:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="title" id=""
									placeholder="请输入内容">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">类型:</label>
							<div class="col-sm-10">
								<select id="color" name="color" class="form-control">
									<option value="green">轻松</option>
									<option value="#3a87ad">普通</option>
									<option value="red">紧急</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">完成状态:</label>
							<div class="col-sm-10">
								<select id="isFinish" name="isFinish" class="form-control">
									<option value="0">未完成</option>
									<option value="1">已完成</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" class="btn btn-success updateBtn">提交</button>
							</div>
						</div>
					</form>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


	<!-- jQuery 3 -->
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap-datetimepicker.js"></script>
	<script src="../js/bootstrap-datetimepicker.fr.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src='../js/moment.min.js'></script>
	<script src='../js/fullcalendar.min.js'></script>
	<script src='../js/lang-all.js'></script>
	<!-- Bootstrap 3.3.7 -->
	<!-- AdminLTE App -->
	<script src="../js/adminlte.min.js"></script>
	<script src="../js/icheck.min.js"></script>
	<script type="text/javascript">

$(document).ready(function() {
		var initialLangCode = 'zh-cn';
		//alert("2222222222"); 

		$('#calendar').fullCalendar({
			//alert("2222222222"),
		
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
            defaultView: 'month',
			weekMode: 'liquid',
			lang: initialLangCode,
			businessHours: true,
			eventClick: function(event) { 
				document.getElementById("updateForm").reset(); //清除上一次点击任务的显示记录
				$("#updateForm").find(".onAllDay input[name=startTime]").val(event.start.format('YYYY-MM-DD'));
            	$("#updateForm").find(".unAllDay input[name=startTime]").val(event.start.format('YYYY-MM-DD HH:mm'));
            	if(event.end !== null){
	            	$("#updateForm").find(".onAllDay input[name=endTime]").val(event.end.format('YYYY-MM-DD'));
	            	$("#updateForm").find(".unAllDay input[name=endTime]").val(event.end.format('YYYY-MM-DD HH:mm'));
                }
                $("#updateForm").find("input[name=title]").val(event.title);
                $("#updateForm").find("select[name=color]").val(event.color);
                $("#updateForm").find("select[name=isFinish]").val(event.isFinish);
                if(!event.allDay){
                	$("#updateForm").find("input[id=8allDayUpdate]").click();
                }
                $("#updateID").val(event.id);
                $("#updateModal").modal();
               
            },  
            dayClick: function(date){
            	
            	$("#addForm").find(".onAllDay input[name=startTime]").val(date.format('YYYY-MM-DD'));
            	$("#addForm").find(".unAllDay input[name=startTime]").val(date.format('YYYY-MM-DD HH:mm'));
                $("#addModal").modal('show');
            },
			selectable: false,
			selectHelper: false,
			select: function(start, end) {
				alert(start);
				alert(end);
				var title = prompt('Event Title:');
				var eventData;
				if (title) {
					eventData = {
						title: title,
						start: start,
						end: end
					};
					$('#calendar').fullCalendar('renderEvent', eventData, false); // stick? = true
				}
				$('#calendar').fullCalendar('unselect');
			},
			eventLimit: true,
			events: 
			{
				url: '../s_Calendar?method=show',  
                type: 'post' 
			}
		});

		$('#lang-selector').append(
			$('<option/>')
				.attr('value', "en")
				.prop('selected', "en" == initialLangCode)
				.text("en")
		).append(
			$('<option/>')
				.attr('value', "zh-cn")
				.prop('selected', "zh-cn" == initialLangCode)
				.text("zh-cn")
		);

		$('#lang-selector').on('change', function() {
			if (this.value) {
				$('#calendar').fullCalendar('option', 'lang', this.value);
			}
		});
		
		$("#addForm").find("input[type=radio]").each(function(){
			//alert("3333333");
			var $this = $(this);
			$this.bind("click",function(){
				if($this.val() == 1){
					$("#addForm").find(".onAllDay").show();
					$("#addForm").find(".unAllDay").hide();
				}else{
					$("#addForm").find(".onAllDay").hide();
					$("#addForm").find(".unAllDay").show();
				}
			});
		});
		
		$(".addBtn").unbind("click").bind("click",function(){
			var title = $("#addForm").find("input[name=title]").val();
			var startTime = "";
			var endTime ="";
			var allDay = $("#addForm").find("input[type=radio]:checked").val();
			if(allDay == 1){
				startTime = $("#addForm").find(".onAllDay input[name=startTime]").val();
				endTime = $("#addForm").find(".onAllDay input[name=endTime]").val();
				if(endTime == ""){
					endTime = null;
				}
			}else{
				startTime = $("#addForm").find(".unAllDay input[name=startTime]").val();
				endTime = $("#addForm").find(".unAllDay input[name=endTime]").val();
				if(endTime == ""){
					endTime = null;
				}
			}
			var color = $("#addForm").find("select[name=color]").val();
			var isFinish = $("#addForm").find("select[name=isFinish]").val();
			
			var addData = {
				"title":title,
				"startTimeStr":startTime,
				"endTimeStr":endTime,
				"allDay":allDay,
				"color":color,
				"isFinish":isFinish
			};
			
			$.ajax({
				url: '../s_Calendar?method=add',
				type: 'post',
				data: addData,
				dataType: 'text',
				success:function(d){
					if(d == "ADD"){
						alert("添加成功!");
						location.reload();
					}else{
						alert("添加失败，请确保填入信息正确!");
					}
				}
			})
		});
		
		
		$("#updateForm").find("input[type=radio]").each(function(){
			var $this = $(this);
			$this.bind("click",function(){
				if($this.val() == 1){
					$("#updateForm").find(".onAllDay").show();
					$("#updateForm").find(".unAllDay").hide();
				}else{
					$("#updateForm").find(".onAllDay").hide();
					$("#updateForm").find(".unAllDay").show();
				}
			});
		});
		
		$(".updateBtn").unbind("click").bind("click",function(){
			var id = $("#updateID").val();
			var title = $("#updateForm").find("input[name=title]").val();
			var startTime = "";
			var endTime ="";
			var allDay = $("#updateForm").find("input[type=radio]:checked").val();
			if(allDay == 1){
				startTime = $("#updateForm").find(".onAllDay input[name=startTime]").val();
				endTime = $("#updateForm").find(".onAllDay input[name=endTime]").val();
				if(endTime == ""){
					endTime = null;
				}
			}else{
				startTime = $("#updateForm").find(".unAllDay input[name=startTime]").val();
				endTime = $("#updateForm").find(".unAllDay input[name=endTime]").val();
				if(endTime == ""){
					endTime = null;
				}
			}
			var color = $("#updateForm").find("select[name=color]").val();
			var isFinish = $("#updateForm").find("select[name=isFinish]").val();
			
			var updateData = {
				"id":id,
				"title":title,
				"startTimeStr":startTime,
				"endTimeStr":endTime,
				"allDay":allDay,
				"color":color,
				"isFinish":isFinish
			};
			
			$.ajax({
				url: '../s_Calendar?method=update',
				type: 'post',
				data: updateData,
				dataType: 'text',
				success:function(d){
					if(d == "UPDATE"){
						alert("修改成功!");
						location.reload();
					}else{
						alert("修改失败，请确保填入信息正确!");
					}
				}
			})
		});
	});
</script>
	<script type="text/javascript">
    $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
</script>
</body>
</html>

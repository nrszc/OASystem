<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="henu.bean.*"
    %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>OA办公系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="shortcut icon" href="../img/OA.ico" type="image/x-icon" />
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="../css/bootstrap.min.css">
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
        <li>
          <a href="calendar.jsp">
            <i class="fa fa-calendar"></i> <span>日程</span>
          </a>
        </li>
         <li class="active">
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
  
   <ul class="nav nav-tabs ">
        <li role="presentation" ><a href="../a_Leave?method=leave_my">我的请假</a></li>
        <li role="presentation" class="active"><a href="leave_add.jsp">我要请假</a></li>
 </ul>
  
   <section class="content-header">
      <h1>
        我要请假
      </h1>
      <ol class="breadcrumb">
      
        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#"><i class="fa fa-dashboard"></i> 请假</a></li>
        <li class="active">我要请假</li>
      </ol>
    </section>

    <div class="pad margin no-print">
      <div class="callout callout-info" style="margin-bottom: 0!important;">
        <h4><i class="fa fa-info"></i> 注意 :</h4>
                   世界那么大，我想去看看
        </div>
    </div>
     
     <!-- Main content -->
    <section class="content">
    <div class="box box-default">
     <div class="box-body">
          <div class="row">
          <form method="post" action="../a_Leave?method=leave_add">
           <div class="col-md-12">
              <div class="form-group">
                <label>请假事由</label>
                <textarea class="form-control" rows="3" name="leaveText"></textarea>
              </div>
            </div>
            <div class="col-md-12">
              <div class="form-group">
                <label>请假开始时间</label>
                <input type="date" class="form-control" name="startTime" id="start" onchange="days()">
              </div>
            </div>
            <div class="col-md-12">
              <div class="form-group">
                <label>请假结束时间</label>
                <input type="date" class="form-control" name="endTime" id="end" onchange="days()" >
              </div>
            </div>
            <div class="col-md-12">
              <div class="form-group">
                <label>请假天数（*请假超过三天，只能向高管审批）</label>
                <input type="text" class="form-control" readonly name="day" id="day" >
              </div>
            </div>
            <div class="col-md-12">
              <div class="form-group">
                <label>审批人</label>
                <select class="form-control" id="leaveTo" name="leaveTo">   
                     <option >请先选择请假日期</option>
                </select>
              </div>
            </div>
            <div class="box-footer">
                <button type="submit" class="btn btn-primary">确认请假</button>
              </div>
              </form>
            </div>
            </div>
            </div>
    </section>
    
    
</div>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="../js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="../js/adminlte.min.js"></script>

<script>
function days()
{
	var start = document.getElementById("start").value;
	var end = document.getElementById("end").value;
	if(start!=""&&end!=""){
	   var a = DateDiff(start,end);
	   document.getElementById("day").value = a;
	   $("#leaveTo").empty();
	   if(a<=3){
	   $.ajax({  
           type : "post",  
           url : "../a_Leave?method=showemployee",  
           dataType : "text",  
           async: false,
           success : function(data) {  
               data = eval(data);  
               //构造前先清空源节点  
               //document.getElementById("data").innerHTML = "";  
               var expressCompany = $("#leaveTo");
               var str="";
               //alert(data.length)
               for(var o=0;o<data.length;o++) {
               	str += '<option value="'+data[o].employeeID+'">'+data[o].employeeName+'</option>';	
               }
               //直接在select后添加 
               expressCompany.append(str);  
           },  
           error : function() {  
               alert("出错了");  
           }  
 
       });  
	   }
	   else
		   {
		   $.ajax({  
	           type : "post",  
	           url : "../a_Leave?method=showemployee1",  
	           dataType : "text",  
	           async: false,
	           success : function(data) {  
	               data = eval(data);  
	               //构造前先清空源节点  
	               //document.getElementById("data").innerHTML = "";  
	               var expressCompany = $("#leaveTo");
	               var str="";
	               //alert(data.length)
	               for(var o=0;o<data.length;o++) {
	               	str += '<option value="'+data[o].employeeID+'">'+data[o].employeeName+'</option>';	
	               }
	               //直接在select后添加 
	               expressCompany.append(str);  
	           },  
	           error : function() {  
	               alert("出错了");  
	           }  
	 
	       });  
		   }
	}
}
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
    var  aDate,  oDate1,  oDate2,  iDays  
    aDate  =  sDate1.split("-")  
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式  
    aDate  =  sDate2.split("-")  
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数  
    return  iDays  
}    
</script>
</body>
</html>
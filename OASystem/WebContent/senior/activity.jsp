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
<body class="hold-transition skin-blue sidebar-mini" data-spy="scroll" data-target="#myScrollspy" onLoad="selectDept()">
<c:if test="${sessionScope.em.employeeID==null}" > 
<c:redirect url="../index.html"/>
</c:if>
<div class="wrapper">
  <header class="main-header">

    <!-- Logo -->
    <a href="seniorHome.jsp" class="logo">
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
                    <a href="../s_Person?method=findinfo">
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
          <a href="../s_Colleague?method=search">
            <i class="fa fa-group"></i> <span>同事</span>
          </a>
        </li>
        <li >
          <a href="../s_Job?method=job">
            <i class="fa fa-black-tie"></i> <span>职位</span>
          </a>
        </li>
        <li>
          <a href="../s_Dept?method=dept">
          <i class="fa fa-tags"></i> <span>部门</span>
          </a>
        </li>
        <li>
          <a href="../s_Note?method=allnote">
            <i class="fa fa-volume-up"></i> <span>公告</span>
          </a>
        </li>
        <li class="active">
          <a href="../s_Activity?method=myactivity">
            <i class="fa fa-bar-chart"></i> <span>活动投票</span>
          </a>
        </li>
        <li>
          <a href="../s_File?method=file_receive">
            <i class="fa fa-file-text-o"></i> <span>文件</span>
          </a>
        </li>
        <li>
          <a href="../s_Meeting?method=myMeeting">
            <i class="fa fa-briefcase"></i> <span>会议</span>
          </a>
        </li>
        <li>
          <a href="../s_Mail?method=mail_receivebox">
            <i class="fa fa-envelope-o"></i> <span>邮件</span>
          </a>
        </li>
        <li>
          <a href="calendar.jsp">
            <i class="fa fa-calendar"></i> <span>日程</span>
          </a>
        </li>
        <li>
          <a href="../s_Leave?method=leave">
            <i class="fa fa-check-square-o"></i> <span>请假审批</span>
          </a>
        </li>
        <li>
          <a href="../s_Task?method=myReleaseTask">
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
      <ul class="nav nav-tabs ">
        <li role="presentation" class="active"><a href="../s_Activity?method=myactivity">我发起的</a></li>
        <li role="presentation"><a href="../s_Activity?method=activity_part">我参与的</a></li>
        <li role="presentation"><a href="../s_Activity?method=activity_unpart">未参与的</a></li>
        <li role="presentation">
        <button class="btn btn-success btn-lg" data-toggle="modal" data-target="#addactivity">
	                     新增活动投票
        </button>
        </li>
      </ul>
      
      <section class="content-header">
      <h1>
        我发起的
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#"><i class="fa fa-dashboard"></i> 活动投票</a></li>
        <li class="active">我发起的</li>
      </ol>
    </section>
  
      
    <!-- Main content -->
    <section class="content">
      <!-- Info boxes -->
      <div class="row">
        <div class="col-md-3" id="myScrollspy">
          <div class="box box-solid">
            <div class="box-header with-border">
              <h3 class="box-title"> <i class="fa fa-bar-chart"></i>&nbsp;&nbsp;活动投票</h3>

              <div class="box-tools">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="box-body no-padding">
              <ul class="nav nav-pills nav-stacked" data-offset-top="150">
              <c:choose>
              <c:when test="${sessionScope.active==null || fn:length(sessionScope.active) == 0}"> 
                <li>您还没有发起过活动投票哦</li>
              </c:when>
              <c:otherwise>
              <c:forEach items="${sessionScope.active}" var="item" varStatus="status">
              <li style="white-space:nowrap; overflow: hidden;"><a href="#section-${item.activeID}">${item.employeeName}&#12288;${item.activeName} </a></li>
              </c:forEach>
              </c:otherwise>
              </c:choose>
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          </div>
          <!-- /.box -->
      <div class="col-md-9">
          <c:choose>
              <c:when test="${sessionScope.active==null || fn:length(sessionScope.active) == 0}"> 
              </c:when>
              <c:otherwise>
              <c:forEach items="${sessionScope.active}" var="item" varStatus="status">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title" id="section-${item.activeID}">
                             发起人：${item.employeeName}&#12288;
                             发起时间：${item.createTime}&#12288;
                             对象：${item.scope}&#12288;主题：${item.activeName}
              </h3><p>
              <h3 class="box-title" >${item.activeInfo}</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <table class="table table-condensed">
                <tr>
                  <th style="width: 10px">#</th>
                  <th>选项</th>
                  <th>投票情况</th>
                  <th style="width: 120px">参与人数：${item.sum}</th>
                </tr>
                <tr>
                  <td>1.</td>
                  <td>双手赞成</td>
                  <td>
                    <div class="progress progress-xs">
                      <div class="progress-bar progress-bar-green" style="width: ${item.agreePercent}"></div>
                    </div>
                  </td>
                  <td><span class="badge bg-green">${item.activeAgreeNum}人&#12288;${item.agreePercent}</span></td>
                </tr>
                <tr>
                  <td>2.</td>
                  <td>低调反对</td>
                  <td>
                    <div class="progress progress-xs">
                      <div class="progress-bar progress-bar-red" style="width: ${item.disagreePercent}"></div>
                    </div>
                  </td>
                  <td><span class="badge bg-red">${item.activeDisagreeNum}人&#12288;${item.disagreePercent}</span></td>
                </tr>
                <tr>
                  <td>3.</td>
                  <td>绝对中立</td>
                  <td>
                    <div class="progress progress-xs progress-striped active">
                      <div class="progress-bar progress-bar-primary" style="width: ${item.othersPercent}"></div>
                    </div>
                  </td>
                  <td><span class="badge bg-light-blue">${item.activeOthersNum}人&#12288;${item.othersPercent}</span></td>
                </tr>
              </table>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
            <form action="../s_Activity?method=deleteactivity&activeID=${item.activeID}" method="post">
            <button type="submit" class="btn btn-primary">删除</button>
            </form>
            </div>
          </div>
          <!-- /.box -->
          </c:forEach>
          </c:otherwise>
          </c:choose>
          
          </div>
        </div>
        
        <!-- /.col -->
     </section>
    </div>
</div>

<!-- 模态框（Modal） -->
   <div class="modal fade" id="addactivity" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
		   <div class="modal-content">
			   <div class="modal-header">
				   <h4 class="modal-title" id="myModalLabel">
					   新增活动投票
				   </h4>
				   <button type="button" class="close" data-dismiss="modal" 
					  aria-hidden="true">×
				   </button>
			   </div>
			   <form method="post" action="../s_Activity?method=addactivity">
			   <div class="modal-body">
				      活动主题：<input type="text" name="activeName">   <br>
				     <span style="vertical-align: top">活动内容：</span><textarea name="activeInfo" cols="40" rows="5"></textarea><br>
				     面向部门（公司）：<select name="deptID" id="deptID" >
				           </select>
			   </div>
			   <div class="modal-footer">
				   <button type="button" class="btn btn-default" 
					  data-dismiss="modal">关闭
				   </button>
				   <button type="submit" class="btn btn-primary">
					   添加
				   </button>
			  </div>
			  </form>
		  </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="../js/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="../js/adminlte.min.js"></script>

<script>  
    function selectDept() {  
        $.ajax({  
            type : "post",  
            url : "../s_Job?method=selectDept1",  
            dataType : "text",  
            success : function(data) {  
                data = eval(data);  
                //构造前先清空源节点  
                //document.getElementById("data").innerHTML = "";  
                var expressCompany = $("#deptID");
                var str;
                for(var o in data) {
                	str += '<option value="'+data[o].deptID+'">'+data[o].deptName+'</option>';
                }
                //alert(str);  
                //直接在select后添加 
                expressCompany.append(str);  
            },  
            error : function() {  
                alert("出错了");  
            }  
  
        });  
    }  
</script> 
</body>
</html>
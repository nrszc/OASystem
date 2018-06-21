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
<body class="hold-transition skin-blue sidebar-mini" onLoad="selectDept()">
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
          <a href="#"><i class="fa fa-circle text-success"></i>${sessionScope.em.deptName};${sessionScope.em.jobName}</a>
        </div>
      </div>

      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li>
          <a href="../s_Colleague?method=search">
            <i class="fa fa-group"></i> <span>同事</span>
          </a>
        </li>
        <li class="active">
          <a href="../s_Job?method=job">
            <i class="fa fa-black-tie"></i> <span>职位</span>
          </a>
        </li>
        <li>
          <a href="../s_Dept?method=dept">
            <i class="fa fa-tags"></i>部门
          </a>
        </li>
        <li>
          <a href="../s_Note?method=allnote">
            <i class="fa fa-volume-up"></i> <span>公告</span>
          </a>
        </li>
        <li>
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
    <section class="content-header">
      <h1>
        职位
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li class="active">职位</li>
      </ol>
    </section>
    <section class="content">
      <!-- Info boxes -->
       <div class="row">
       <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">
              <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addjob">
	                     新增职位
              </button>
              </h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
        <c:choose>
    <c:when test="${sessionScope.job==null || fn:length(sessionScope.job) == 0}">  
    <center>  
    <strong>还没有职位的数据哦！请点击新增职位来添加吧~</strong>  
    </center>  
    </c:when>  
    <c:otherwise>
	<table class="table table-hover table-bordered" id="table">
		<thead>
			<tr>
			<th>编号</th>
            <th>职位名称</th>
            <th>职位介绍</th>
            <th>所属部门</th>
            <th>等级</th>
            <th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${sessionScope.job}" var="item" varStatus="status">  
		<tr>
		    <td>${item.jobID }</td>
		    <td>${item.jobName }</td>
		    <td>${item.jobText }</td>
		    <td>${item.deptName }</td>
		    <td>${item.level }</td>
            <td>
            <a href="#" data-toggle="modal" data-target="#myModal" onclick="values(${item.jobID })">修改</a>
            <a href="../s_Job?method=deletejob&jobID=${item.jobID }">删除</a>
            </td>
	    </tr>
	    </c:forEach>
   	    </tbody>
    </table>
    </c:otherwise>
    </c:choose>
      </div>   
     </div></div>
         
   </div>
   
   <!-- 模态框（Modal） -->
   <div class="modal fade" id="addjob" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
		   <div class="modal-content">
			   <div class="modal-header">
				   <h4 class="modal-title" id="myModalLabel">
					   新增职位
				   </h4>
				   <button type="button" class="close" data-dismiss="modal" 
					  aria-hidden="true">×
				   </button>
			   </div>
			   <form method="post" action="../s_Job?method=addjob">
			   <div class="modal-body">
				      职位名称：<input type="text" name="jobName">   <br>
				     <span style="vertical-align: top">职位介绍：</span><textarea name="jobText" cols="40" rows="5"></textarea><br>
				     所属部门：<select name="deptID" id="deptID" >
				           </select><br>
				     &#12288;&#12288;等级：<select name="level">
				     <option value="普通">普通</option>
				     <option value="中级">中级</option>
				     <option value="高级">高级</option>
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

 <!-- 模态框（Modal） -->
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
		   <div class="modal-content">
			   <div class="modal-header">
				   <h4 class="modal-title" id="myModalLabel">
					   修改部门信息
				   </h4>
				   <button type="button" class="close" data-dismiss="modal" 
					  aria-hidden="true">×
				   </button>
			   </div>
			   <form method="post" action="../s_Job?method=updatejob">
			   <div class="modal-body">
			                <input type="hidden" name="jobID" id="jobID" />
				       职位名称：<input type="text" name="jobName" id="jobName" />   <br>
				     <span style="vertical-align: top">职位介绍：</span><textarea name="jobText" cols="40" rows="5" id="jobText"></textarea><br>
				    所属部门：<select name="deptID" id="deptID1" >
				           </select><br>
				     &#12288;&#12288;等级：<select name="level" id="level">
				     <option value="普通">普通</option>
				     <option value="中级">中级</option>
				     <option value="高级">高级</option>
				     </select>
			   </div>
			   <div class="modal-footer">
				   <button type="button" class="btn btn-default" 
					  data-dismiss="modal">关闭
				   </button>
				   <button type="submit" class="btn btn-primary">
					   提交更改
				   </button>
			  </div>
			  </form>
		  </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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

<script type="text/javascript">
function values(jobID) {  
    var id=0;  
    while(1)
    	{
    	if(jobID==document.getElementById("table").rows[id].cells[0].innerText)
    		break;
    	id++;
    	}
    //获取表格中的一行数据  
    var jobID= document.getElementById("table").rows[id].cells[0].innerText;  
    var jobName = document.getElementById("table").rows[id].cells[1].innerText;  
    var jobText = document.getElementById("table").rows[id].cells[2].innerText;
    var deptName = document.getElementById("table").rows[id].cells[3].innerText;
    var level = document.getElementById("table").rows[id].cells[4].innerText;
    var obj = document.getElementById("deptID1");
    var i = 0;
    while(1)
	{
	if(deptName==obj.options[i].text)
		break;
	i++;
	}
    obj.options[i].selected = true;
    
    var obj1 = document.getElementById("level");
    var a = 0;
    while(1)
	{
	if(level==obj1.options[a].text)
		break;
	a++;
	}
    obj1.options[a].selected = true;
   
    //向模态框中传值  
    document.getElementById("jobID").value = jobID;
    document.getElementById("jobName").value = jobName;
    document.getElementById("jobText").value = jobText;
}
</script>

<script>  
    function selectDept() {  
        $.ajax({  
            type : "post",  
            url : "../s_Job?method=selectDept",  
            dataType : "text",  
            success : function(data) {  
                data = eval(data);  
                //构造前先清空源节点  
                //document.getElementById("data").innerHTML = "";  
                var expressCompany = $("#deptID");
                var dept = $("#deptID1");
                var str;
                for(var o in data) {
                	str += '<option value="'+data[o].deptID+'">'+data[o].deptName+'</option>';
                	//alert(str);

                }
                //alert(str);  
                //直接在select后添加 
                expressCompany.append(str);  
                dept.append(str);
            },  
            error : function() {  
                alert("出错了");  
            }  
  
        });  
    }  
</script> 

</body>
</html>
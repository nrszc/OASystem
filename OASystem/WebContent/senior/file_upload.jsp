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
  <link rel="stylesheet" href="../css/jquery.tagsinput.css">  
  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini" data-spy="scroll" data-target="#myScrollspy" >
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
        <li>
          <a href="../s_Activity?method=myactivity">
            <i class="fa fa-bar-chart"></i> <span>活动投票</span>
          </a>
        </li>
        <li class="active">
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
        <li role="presentation" ><a href="../s_File?method=file_receive">接收文件</a></li>
        <li role="presentation" ><a href="../s_File?method=file_send">我发送的</a></li>
        <li role="presentation" class="active"><a href="../s_File?method=showemployee">发送文件</a></li>
    </ul>
    <section class="content-header">
      <h1>
        发送文件
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#"><i class="fa fa-dashboard"></i> 文件</a></li>
        <li class="active">发送文件</li>
      </ol>
    </section>

 <!-- Main content -->
    <section class="content">

      <!-- SELECT2 EXAMPLE -->
      <div class="box box-default">
        <div class="box-header with-border">
          <h3 class="box-title">请填写发送文件信息</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
          <form action="../s_File?method=uploadfile" method="post" enctype="multipart/form-data">
          <div class="col-md-12">
              <div class="form-group">
                <label >文件描述</label>
                <textarea class="form-control" rows="3" name="filetext"></textarea>
              </div>
            </div>
            <div class="col-md-12">
              <div class="form-group">
                <label>文件类型</label>
                <select class="form-control" name="filetype" id="filetype" onchange="changeSelect(this.value)">
                    <option>请选择</option>
                    <option value="company">公司文件</option>
                    <option value="dept">部门文件</option>
                    <option value="private">私人文件</option>
                </select>
              </div>
            </div>
            <div class="col-md-12">
              <div class="form-group">
                <label>接受人(公司、部门)</label>
                <select class="form-control" id="test">   
                <option >请先选择文件类型</option>
                </select>
                <select class="form-control" name="companyfile" id="companyfile" >   
                <option value="company">全公司</option>
                </select>
                <select class="form-control" name="deptfile" id="deptfile" >                
                </select>
                <a data-toggle="modal" data-target="#myModal" id="t88888">
                <input type="text" id="tags"  class="form-control tags" />
                </a>
                <input type="text" id="fruitChecked" name="sendTo" />  
              </div>
            </div>
            <div class="col-md-12">
               <div class="form-group">
                 <label class="sr-only" for="inputfile">文件输入</label>
                   <input type="file" name="filename">
               </div>
            </div>
            <div class="box-footer">
              <div class="pull-left">
                <button type="submit" class="btn btn-primary">发送文件</button>
              </div>
            </div>
            </form>
            </div>
          <!-- /.row -->
        </div>
       </div>
      </section>
   </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					添加收件人
				</h4>
			</div>
			<div class="modal-body">
				 <c:forEach items="${sessionScope.list1}" var="item" varStatus="status">  
				 <input type="checkbox" value="${item.employeeName }" name="fruit" onclick="radioHandle(this.value, '${item.employeeID}')" />${item.employeeName }(${item.deptName }${item.jobName } ) 
				 </c:forEach>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">
					确定
				</button>
			</div>
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
<script src="../js/icheck.min.js"></script>
<script src="../js/jquery.tagsinput.js" ></script>  
<script type="text/javascript">  
    var fruitValue = new Array();  
    var fruitName = new Array();   
  
    Array.prototype.removeByValue = function(val) {  
        for(var i=0; i<this.length; i++) {  
          if(this[i] == val) {  
            this.splice(i, 1); 
            break;  
          }  
        }  
    };  
  
      
    $('#tags').tagsInput({  
        interactive: false,  
        removeWithBackspace: false,  
        onRemoveTag:  function(value){  
                        //重点 通过值获得数组的索引  
                        //删除  checkbox不勾选 数组remove值 显示改变  
                        var index = fruitName.indexOf(value);  
                        //alert(index);
                        $("input:checkbox[name=fruit][value="+value+"]").prop("checked",false);  
                        fruitValue.splice(index, 1); 
                        fruitName.splice(index, 1); 
                        $('#fruitChecked').val(fruitValue.toString());  

                    }  
    
    });  
  
    function radioHandle(value,text){  
        // --- 选中 push--  
        if($("input:checkbox[name=fruit][value="+value+"]").is(':checked')){  
            if($('#fruitChecked').val().indexOf(text) == -1){  
                fruitValue.push(text); 
                fruitName.push(value);
                //alert(fruitName.toString());
                $('#tags').addTag(value); 
                $('#fruitChecked').val(fruitValue.toString());  

            }  
        }else{  
            if($('#fruitChecked').val().indexOf(text) > -1){  
                var ind = fruitName.indexOf(value);
                fruitValue.removeByValue(value);
                fruitName.removeByValue(text);
                $('#tags').removeTag(value);  
                $('#fruitChecked').val(fruitValue.toString());  

            }  
        } 
        $('#fruitChecked').val(fruitValue.toString());  
    }
    </script> 
    
<script>
$(document).ready(function(){
	$('#companyfile').hide(); //隐藏公司
	$('#deptfile').hide();    //隐藏部门
	$('#t88888').hide();      //隐藏私人
	$('#fruitChecked').hide(); 
});
</script>

<script>
function changeSelect(value)
{
	$('#test').hide(); 
	if(value=="company"){
		$('#deptfile').hide();    //隐藏部门
		$('#t88888').hide();      //隐藏私人
		$('#companyfile').show();
	}
	else if(value=="dept"){
		$('#companyfile').hide();    //隐藏公司
		$('#t88888').hide();      //隐藏私人
		$('#deptfile').empty();  //清空初始化
	      $.ajax({  
	            type : "post",  
	            url : "../s_File?method=showdept",  
	            dataType : "text",  
	            async: false,
	            success : function(data) {  
	                data = eval(data);  
	                //构造前先清空源节点  
	                //document.getElementById("data").innerHTML = "";  
	                var expressCompany = $("#deptfile");
	                var str="";
	                //alert(data.length)
	                for(var o=0;o<data.length;o++) {
	                	str += '<option value="'+data[o].deptID+'">'+data[o].deptName+'</option>';	
	                }
	                //直接在select后添加 
	                expressCompany.append(str);  
	            },  
	            error : function() {  
	                alert("出错了");  
	            }  
	  
	        });  
		$('#deptfile').show();
		
	}
	else if(value=="private"){
		$('#companyfile').hide(); //隐藏公司
		$('#deptfile').hide();    //隐藏部门
		
		$('#t88888').show(); 
	}
}
</script>

     
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Iniciar sesion</title>

    <!-- Bootstrap core CSS >
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet" -->
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug >
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet"-->

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/cover.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <!--div class="inner">
              <h3 class="masthead-brand">Cover</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a href="#">Home</a></li>
                  <li><a href="#">Features</a></li>
                  <li><a href="#">Contact</a></li>
                </ul>
              </nav>
            </div-->
          </div>

          <div class="inner cover">
          <form action="${pageContext.request.contextPath}/uaa/login" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <h1 class="cover-heading">Bienvenido!</h1>
            <!-- p class="lead"></p-->
            <div class="form-group">
            	<label for="username">Usuario</label>
            	<input type="text" class="form-control" id="username" name="username" placeholder="Usuario">
            </div>
            <div class="form-group">
            	<label for="password">Password</label>
            	<input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
            
            <p class="lead">
              <button type="submit" class="btn btn-lg btn-default btn-block">Ingresar</button>
            </p>
          </form>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>oauth@yian.org.pe</p>
            </div>
          </div>

        </div>

      </div>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

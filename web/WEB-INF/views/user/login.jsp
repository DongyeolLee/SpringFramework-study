<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose/dtd">
<html>
<head>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
//        self.location = "/sboard/list";
    </script>
    <div class="container" style="margin: auto; margin-top: 10%">
        <div class="panel panel-default">
            <div class="panel-body">
    <form action="/user/loginPost" method="post">
        <div class="form-group">
        <div class="form-group has-feedback">
            <input type="text" name="uid" class="form-control" placeholder="USER ID"/>
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="password" name="upw" class="form-control" placeholder="PASSWORD"/>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="row">
            <div class="col-xs-8">
                <div class="checkbox icheck">
                    <label>
                        <input type="checkbox" name="useCookie"> Remember me
                    </label>
                </div>
            </div>
            <div class="col-xs-4">
                <button type="submit" class="btn btn-primary btn-block btn-flat">Sign in</button>
            </div>
        </div>
        </div>
    </form>
            </div>
        </div>
    </div>
</body>
</html>

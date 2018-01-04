<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
         <style>
            iframe {
                width: 0px;
                height: 0px;
                border: 0px;
            }
        </style>
    </head>
    <body>

        <form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" target="zeroFrame">
            <input type="file" name="file"><input type="submit">
        </form>

        <iframe name="zeroFrame"></iframe>

        <script>
            function addFilePath(msg) {
                alert(msg);
                document.getElementById("form1").reset();
            }
        </script>
    </body>
</html>

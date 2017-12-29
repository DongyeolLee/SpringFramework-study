<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Test</title>
    </head>
    <body>
        <div id="displayDiv">

        </div>

        <ul id="replies">

        </ul>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
        <script>
            var source = "<h1><p>{{name}}</p><p>{{userid}}</p><p>{{addr}}</p></h1>";
            var template = Handlebars.compile(source);
            var data = {name: "lee", userid: "user", addr: "address"};

            $("#displayDiv").html(template(data));
        </script>
        <script id="template" type="text/x-handlebars-template">
            {{#each .}}
                <li class="replyLi">
                    <div>{{rno}}</div>
                    <div>{{replytext}}</div>
                    <div>{{replydate}}</div>
                </li>
            {{/each}}
        </script>
        <script>
            var source = $("#template").html();
            var template = Handlebars.compile(source);
            var data = [
                {rno: "1", replytext: "user", replydate: "address"},
                {rno: "2", replytext: "user", replydate: "address"},
                {rno: "3", replytext: "user", replydate: "address"},
                {rno: "4", replytext: "user", replydate: "address"},
                {rno: "5", replytext: "user", replydate: "address"},
                ];

            $("#replies").html(template(data));
        </script>
    </body>
</html>

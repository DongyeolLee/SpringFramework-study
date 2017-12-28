<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h2>Test Page</h2>

        <div>
            <div>
                Replyer <input type="text" name="replyer" id="newReplyWriter"/>
            </div>
            <div>
                Text <input type="text" name="replytext" id="newReplyText"/>
            </div>
            <button id="replyAddBtn">Add Reply</button>
        </div>

        <div id="modDiv" style="display: none">
            <div class="modal-title"></div>
            <div>
                <input type="text" id="replytext">
            </div>
            <div>
                <button type="button" id="replyModBtn">Modify</button>
                <button type="button" id="replyDelBtn">delete</button>
                <button type="button" id="closeBtn">close</button>
            </div>
        </div>

        <ul id="replies" >
        </ul>

        <ul class="pagination">
        </ul>


        <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
        <script>
            var bno = 6;
            var replyPage = 1;
            function getAllList() {
                var bno = 6;
                var str = '';

                console.log("**********************************");
                $.getJSON("/replies/all/" + bno, function (data) {
                    console.log("---------------");
                    console.log(data);
                    console.log(data.length);

                    $(data).each(function () {
                        str += "<li data-rno='"+this.rno+"' class='replyLi'>" + this.rno + ":" + this.replytext + "<button>mod</button></li>";
                    });

                    $("#replies").html(str);
                }).fail(function (a, status, err) {
                    console.log("error");
                    console.log(status);
                    console.log(err);
                });
                console.log("**********************************");
            }
            function getPageList(page) {
                $.getJSON("/replies/"+bno+"/"+page, function (data) {
                    console.log(data.list.length);
                    var str = "";

                    $(data.list).each(function () {
                        str += "<li data-rno='" + this.rno+"' class='replyLi'>" + this.rno+":"+this.replytext+"<button>mod</button></li>";
                    });

                    $("#replies").html(str);
                    printPaging(data.pageMaker);
                });
            }
            function printPaging(pageMaker) {
                var str = ""
                if(pageMaker.prev) {
                    str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
                }

                for(var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i ++) {
                    var strClass = pageMaker.cri.page == i? 'class=active':'';
                    str += "<li"+strClass+"><a href='" +i+"'>"+i+"</a><li>";
                }

                if(pageMaker.next) {
                    str += "<li><a href='"+(pageMaker.startPage+1)+"'> >> </a></li>";
                }
                $('.pagination').html(str);
            }
            $(".pagination").on("click", "li a", function (event) {
                event.preventDefault();
                replyPage = $(this).attr("href");
                getPageList(replyPage);
            });
            $("#replyAddBtn").on("click", function () {

               var replyer = $("#newReplyWriter").val();
               var replyText = $("#newReplyText").val();
               var bno = 6;

               $.ajax({
                   type : 'post',
                   url : 'replies',
                   headers : {
                       "Content-Type" : "application/json",
                       "X-HTTP-Method-Overide" : "POST"
                   },
                   dataType : 'text',
                   data : JSON.stringify({
                       bno : bno,
                       replyer : replyer,
                       replytext : replyText
                   }),
                   success : function (result) {
                       if(result == "SUCCESS") {
                           alert("등록");
//                           getAllList();
                           getPageList(1);
                       }
                   }
               })
            });
            $("#replies").on("click", ".replyLi button", function () {

                var reply = $(this).parent();
                var rno = reply.attr("data-rno");
                var replytext = reply.text();

                $(".modal-title").html(rno);
                $("#replytext").val(replytext);
                $("#modDiv").show("slow");
            });
            $("#replyDelBtn").on("click", function () {

                var rno = $(".modal-title").html();
                var replytext = $("#replytext").val();

                $.ajax({
                    type: 'delete',
                    url: '/replies/' + rno,
                    headers: {
                        "Content-Type": "application/json",
                        "X-HTTP-Method-Overide": "DELETE"
                    },
                    dataType: 'text',
                    success: function (result) {
                        if (result == "SUCCESS") {
                            alert("삭제");
                            $("#modDiv").hide("slow");
                            getAllList();
                        }
                    }
                });
            })
            $("#replyModBtn").on("click", function () {

                var rno = $(".modal-title").html();
                var replyText = $("#replytext").val();

                $.ajax({
                    type : 'put',
                    url : '/replies/' + rno,
                    headers : {
                        "Content-Type" : "application/json",
                        "X-HTTP-Method-Overide" : "PUT"
                    },
                    dataType : 'text',
                    data : JSON.stringify({replytext : replyText}),
                    success : function (result) {
                        if(result == "SUCCESS") {
                            alert("수정");
                            $("#modDiv").show("slow");
                            getAllList();
//                            getPageList(replyPage);
                        }
                    }
                })
            })
        </script>

        <style>
            #modDiv {
                width: 300px;
                height: 100px;
                background-color: grey;
                position: absolute;
                top: 50%;
                left: 50%;
                margin-top: -50px;
                margin-left: -150px;
                padding: 10px;
                z-index: 10000;
            }

        </style>
    </body>
</html>

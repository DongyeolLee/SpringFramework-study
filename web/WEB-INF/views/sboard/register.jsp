<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<style>
    .fileDrop {
        width: 100%;
        height: 200px;
        border: 1px dotted blue;
    }
</style>
<!-- Main content -->
<section class="content">
    <div class="row">
        <!-- left column -->
        <div class="col-md-12">
            <!-- general form elements -->
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">REGISTER BOARD</h3>
                </div>
                <!-- /.box-header -->

                <form id="registerForm" role="form" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Title</label> <input type="text"
                                                                                 name='title' class="form-control" placeholder="Enter Title">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Content</label>
                            <textarea class="form-control" name="content" rows="3"
                                      placeholder="Enter ..."></textarea>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Writer</label>
                            <input type="text"
                                                                                  name="writer" class="form-control" placeholder="Enter Writer" value='${login.uid}' readonly>
                        </div>

                        <div class="form-group">
                            <label for="exampleInputEmail1">File DROP Here</label>
                            <div class="fileDrop"></div>
                        </div>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                        <div>
                            <hr>
                        </div>

                        <ul class="mailbox-attachments clearfix uploadedList">
                        </ul>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>


            </div>
            <!-- /.box -->
        </div>
        <!--/.col (left) -->

    </div>
    <!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script id="template" type="text/x-handlebars-template">
    <li>
        <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
        <div class="mailbox-attachment-info" data-src={{fullName}}>
            <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
            <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i> </a>
        </div>
    </li>
</script>
<script>
    console.log($("#template").html());
    var template = Handlebars.compile($("#template").html());

    $(".fileDrop").on("dragenter dragover", function (event) {
        console.log("***** not yet")
        event.preventDefault();
    });

    $(".fileDrop").on("drop", function (event) {
        event.preventDefault();
        console.log("*******drop");
        var files = event.originalEvent.dataTransfer.files;
        console.log(event);
        console.log(files)
        var file = files[0];
        console.log(file);
        var formData = new FormData();
        console.log(formData);
        formData.append("file", file);
        console.log(formData.has("file"));
        console.log("******* sending");
        $.ajax({
            url: '/uploadAjax',
            data: formData,
            dataType: 'text',
            processData: false,
            contentType: false,
            type: 'POST',
            success: function (data) {
                var fileInfo = getFileInfo(data);
                var html = template(fileInfo);

                $(".uploadedList").append(html);
            }
        })
    });

    $("#registerForm").submit(function (event) {
        event.preventDefault();
        var that = $(this);
        var str = "";

        $(".uploadedList .delbtn").each(function (index) {
            str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "'>";
        });
        that.append(str);
        that.get(0).submit();
    });

    $(".uploadedList").on("click", ".mailbox-attachment-info a", function (event) {
        event.preventDefault();
        var that = $(this);
        console.log("delete");
        $.ajax({
            url: "/deleteFile",
            type: "post",
            data: {fileName: $(this).parent().attr("data-src")},
            dataType: "text",
            success: function (result) {
                if(result == 'deleted') {
                    console.log(that.parent("div"));
                    console.log(that.parent().parent("li"));
//                    that.parent().parent("div").remove();
                    that.parent().parent("li").remove();
                }
            }
        });
    });
</script>
<script type="application/javascript" src="/resources/upload.js"></script>
<%@include file="../include/footer.jsp"%>

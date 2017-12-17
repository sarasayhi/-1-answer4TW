var MaxInputs = 8; //maximum input boxes allowed
var $InputsWrapper = $("#InputsWrapper"); //Input boxes wrapper ID
var $AddButton = $("#AddMoreFileBox"); //Add button ID
var FieldCount = 1; //to keep track of text box added
function pageInit() {
    eventTrigger();
    initDataList();
}
function initDataList() {
    var params = {};
    params['acc'] = "ddd";
    console.log(JSON.stringify(params));
    epm.ajax("http://localhost:1024/list/doc",JSON.stringify(params), function (result) {
        // if (result.ans != 'ok') {
        //     return;
        // }
        console.log(JSON.stringify(result));
        // [
        //     {
        //         "id":1,
        //         "name":"0371e1de-0cce-11e4-985a-32de0f717c8f.jpg",
        //         "content":"",
        //         "tags":"tags",
        //         "collectCnt":0,
        //         "createTime":1513343304000,
        //         "updateTime":1513343304000,
        //         "userId":0,
        //         "deleted":0,
        //         "ext":null
        //     },
        //     {
        //         "id":2,
        //         "name":"files",
        //         "content":"",
        //         "tags":"tags",
        //         "collectCnt":0,
        //         "createTime":1513449917000,
        //         "updateTime":1513449917000,
        //         "userId":0,
        //         "deleted":0,
        //         "ext":"files"
        //     }
        // ]
        var $dataList = $('.detail-list'),
            // $load = $('.load-text'),
            // $dataItem = $('.cm-label-item'),
            html = '';


        // if (epm.isEmpty(result['data'][0]) && epm.isEmpty(epm.getUrlParam('sid'))) {
        //     if ($dataItem.length == 0) {
        //         $('.cm-empty').fadeIn(100).slideDown(200).prev().hide();
        //     } else {
        //         $load.show().removeClass('load').text('没有数据了,亲');
        //     }
        // } else if ((epm.isEmpty(result['data'][0]) && !epm.isEmpty(epm.getUrlParam('sid'))) || result.data['length'] < params['rows']) {
        //     $load.show().removeClass('load').text('没有数据了,亲');
        // } else {
        //     $load.show().addClass('load').text('加载更多');
        // }
        var path = "";
        $.each(result, function(index, value) {
            //
            //     if (index == rows - 1) {
            //         return;
            //     }
            // for (var i = 0; i < 5; i++) {
            if(index == 0){
                path = value['tags'];
            }
            html += '<li class="">'
                + '<div class="detail-list-title">'
                + '<div title="' + value["id"] + '">' + value["id"] + '</div>'
                + '<div title="' + value["name"] + '">' + value["name"] + '</div>'
                + '<div title="' + value["name"] + '">' + value["name"] + '</div>'
                + '<div>' + epm.getDateTime(value["createTime"]) + '</div>'
                + '<div>' + value["tags"] + '</div>'
                + '<div>'
                + '<a class="cm-btn-sm cm-btn-active">详情</a>'
                + '<a href="'+ path + value["name"] + '" class="cm-btn-sm cm-btn-active download" data-name="' + value["name"] + '">下载</a>'
                + '<a class="cm-btn-sm cm-btn-active">编辑</a>'
                + '<a class="cm-btn-sm cm-btn-active">删除</a>'
                + '</div>'
                + '</div></li>';
            // }
        });
        $dataList.append(html);

        $('a.download').on({
            click:function () {
                var fileName = $(this).attr('data-name');
                console.log(fileName);
                epm.ajax("http://localhost:1024/download",fileName, function (result) {
                    console.log("yest download");
                });

            }
        });
    });

// }
// )
// ;
}

function eventTrigger() {
    //on add input button click
    $AddButton.click(function (e) {
        var x = $InputsWrapper.find('input[type="file"]').length; //initlal text box count
        if (x <= MaxInputs) //max input box allowed
        {
            FieldCount++; //text box added increment
            //add input box
            $InputsWrapper.append('<div><input type="file" name="files" id="field_' + FieldCount + '"/><a href="#" class="removeclass">×</a></div>');
            x++; //text box increment
        }
        return false;
    });

    $("body").on("click", ".removeclass", function (e) { //user click on remove text
        var x = $InputsWrapper.find('input[type="file"]').length;
        if (x > 1) {
            $(this).parent('div').remove(); //remove text box
            x--; //decrement textbox
        }
        return false;
    });

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });
}
function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload/multi",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //http://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<style>
    body {
        background: white;
        color: black;
        padding: 40px;
        margin: 0;
        font-family: Gotham, Helvetica, Arial, sans-serif;
        line-height: 1;
    }

    h1 {
        font-weight: bold;
        text-transform: uppercase;
        font-size: 20px;
        margin: 0 0 5px 0;
    }

    h2 {
        font-family: "Lucida Grande", Verdana, sans-serif;
        font-size: 10px;
        color: #777777;
        margin: 0 0 20px 0;
    }

    h2 b {
        color: #279919;
    }

    h3 {
        font-size: 18px;
        margin: 10px 0px;
        border-top: 1px solid #CCC;
        padding-top: 10px;
        color: #666;
    }

    h3 em {
        color: #CCC;
        font-size: 9px;
    }

    h3 .hapi {
        margin-left: 20px;
    }

    div.content {
        margin-left: 10px;
    }

    div.right-content {
        margin-left: 10px;
    }

    .hidden {
        display: none;
    }

    .clear {
        clear: both;
    }

    .api .api_method {
        font-weight: bold;
        color: #804000;
    }

    .api .api_uri {
        color: #008040;
    }

    .hapi .api_method {
        color: #804000;
        font-size: 11px;
    }

    .hapi .api_uri {
        color: #008040;
        font-size: 11px;
    }

    .module {
        margin: 20px 0px;
        list-style: none;
        color: #6d8fb0;
    }

    .module a {
        color: #6d8fb0;
    }

    .module.highlight {
        background: #ffffa5;
    }

    #modules {
        float: right;
        background: #f3f7fb;
        padding: 0px;
        position: fixed;
        right: 20px;
        top: 6px;
        font-size: 9px;
    }

    #modules li {
        margin: 10px 20px;
        list-style: none;
        color: #6d8fb0;
    }

    #apis {
        padding: 0px;
        margin: 0px;
        margin-right: 10px;
    }

    #apis li {
        list-style: none;
    }

    p {
        margin: 10px 0px;
    }

    pre {
        margin: 10px 0px;
        line-height: 1.5em;
        background: #EEEEEE;
        border: 1px solid #E6E6E6;
        color: #333;
        padding: 10px;
        font-size: 12px;
    }

    pre.ok {
        background: #D9EDF7;
        border-color: #BCE8F1;
    }

    pre.error {
        background: #F9DFDB;
    }

    p.api_meta {
        font-size: 10px;
    }

    p.api_meta input {
        font-size: 10px;
        color: #999;
        width: 120px;
        height: 14px;
        border: 1px solid #EFEFEF;
    }

    h4 {
        margin: 10px 0px;
        font-size: 14px;
    }

    table.params {
        margin: 10px 0px;
        padding: 0;
        border-collapse: collapse;
    }

    table.params th {
        font: bold 16px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #FFFFFF;
        border-right: 1px solid #C1DAD7;
        border-bottom: 1px solid #C1DAD7;
        border-top: 1px solid #C1DAD7;
        letter-spacing: 2px;
        text-transform: uppercase;
        text-align: center;
        padding: 2px 6px 2px 6px;
        background: #CF5738 no-repeat;
    }

    table.params td {
        border: 1px solid #ececec;
        background: #fff;
        padding: 4px 6px 4px 6px;
        color: #AAA;
        font-size: 16px;
    }

    table.params td.select_param {
        cursor: pointer;
    }

    table.params tr.selected td {
        color: #4f6b72;
    }

    table.params td input.example_input {
        width: 160px;
    }

    table.params td .example_value:hover {
        background: #CFEEEA;
        color: #1F3035;
    }

    .menu {
        position: absolute;
        list-style-type: none;
        margin: 0px;
        padding: 0px;
        border: 1px solid #008000;
    }

    .menu li {
        list-style-type: none;
        padding: 3px 10px;
        font-size: 12px;
        background: #fff;
        cursor: pointer;
    }

    .menu li.hover {
        background: #DBE8F3;
    }

    .api_desc {
        text-align: left;
        display: inline;
        padding-right: 100px;
    }

    .api_sub_desc_value {
        color: #FE6200;
    }

    .api_desc_div {
        padding-bottom: 10px;
    }

    .big_title {
        display: inline;
    }

    .big_title_h1 {
        width: 80%;
    }

    .big_title_h4 {
        width: 80%;
    }

    .main-content {
        display: inline;
        float: left;
    }

    #content {
        width: 80%;
    }

    #right-content {
        width: 17%;
        margin-left: 10px;
    }
</style>
<script src="./js/jquery-1.4.4.min.js"></script>
<style type="text/css">
</style>
<script src="./js/ajaxfileupload.js"></script>
<script src="./js/jquery.md5.js"></script>
<link href="./css/bootstrap.css" rel="stylesheet">
<link href="./css/bootstrap-responsive.css" rel="stylesheet">
<script>
    function syntaxHighlight(json) {
        if (typeof json != 'string') {
            json = JSON.stringify(json, undefined, 4);
        }
        //json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
        return json
                .replace(
                /("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g,
                function (match) {
                    var cls = 'number';
                    if (/^"/.test(match)) {
                        if (/:$/.test(match)) {
                            //"key"
                            cls = 'text-error';
                        } else {
                            //"string"
                            cls = 'text-info';
                        }
                    } else if (/true|false/.test(match)) {
                        cls = 'text-warning';
                    } else if (/null/.test(match)) {
                        cls = 'text-success';
                    }
                    return '<span class="' + cls + '">' + match
                            + '</span>';
                });
    }

    function hideOther(otherId) {
        var radioType = $("[id='radioType']").text();
        if (otherId == "business") {
            $("[id='suggest']").hide();
            $("[id='search']").show();
        } else {
            $("[id='search']").hide();
            $("[id='suggest']").show();

        }
        if (radioType != otherId || otherId.lenth == 0) {
            $("[id='api_result_71']").html("None");
        }
    }
    $(document).ready(function () {
        var val = $("[id='api_result_71']").text();
        val = jQuery.parseJSON(val);
        //val = JSON.stringify(val,null,4);
        val = syntaxHighlight(val);
        $("[id='api_result_71']").html(val);
    });

</script>
<body>
<h2>O2O团购列表接口</h2>

<form method="post"
      action="<%=request.getContextPath()%>/o2o.do?method=listGrp">
    <div id="search">
        <table id="api_params_71" class="params">
            <tbody>
            <tr class="">
                <td> category:</td>
                <td><input type="text" title="category" id="category"
                           value=${category}></td>
                <td> 类型</td>
            </tr>
            <tr class="">
                <td> subCat:</td>
                <td><input type="text" title="subCat" id="subCat"
                           value=${subCat}></td>
                <td> 子类</td>
            </tr>
            <tr class="">
                <td> city:</td>
                <td><input type="text" title="city" id="city"
                           value=${city}></td>
                <td> 城市</td>
            </tr>
            <tr class="">
                <td> region:</td>
                <td><input type="text" title="region" id="region"
                           value=${region}></td>
                <td> 区域</td>
            </tr>
            <tr class="">
                <td> district:</td>
                <td><input type="text" title="district" id="district"
                           value=${district}></td>
                <td> 商圈</td>
            </tr>
            <tr class="">
                <td> longitude:</td>
                <td><input type="text" title="longitude" id="longitude"
                           value=${longitude}></td>
                <td> 经度</td>
            </tr>
            <tr class="">
                <td> latitude:</td>
                <td><input type="text" title="latitude" id="latitude"
                           value=${latitude}></td>
                <td> 纬度</td>
            </tr>
            <tr class="">
                <td> page:</td>
                <td><input type="text" title="page" id="page"
                           value=${page}></td>
                <td> 第几页,默认0是第一页</td>
            </tr>
            <tr class="">
                <td> limit:</td>
                <td><input type="text" title="limit" id="limit"
                           value=${limit}></td>
                <td>每页记录数</td>
            </tr>
            <tr class="">
                <td> sort:</td>
                <td><select title="sort" id="sort">
                    <option value="0" ${sort=="0"?'selected':''}>离我最近</option>
                    <option value="1" ${sort=="1"?'selected':''}>价格最低</option>
                    <option value="2" ${sort=="2"?'selected':''}>最新发布</option>
                </select>
                </td>
                <td> 排序条件</td>
            </tr>
            </tbody>
        </table>
    </div>
    <p class="api_test">
        <input class="btn btn-success" type="submit" value="Request">
</form>
<pre id="api_result_71" class="brush: js;">${searchModel}</pre>
</body>
</html>

//本文件用于放置站点需要的基础js变量或函数
//设置远程访问的服务器路径
const WEBURL="http://localhost:8080/kqxt_springboot/";
//const WEBURL = "http://120.76.118.101:8080/kqxt_springboot/";
const WEBURL_API = WEBURL+"api/";//专门访问api接口
//设置状态码
const code_success=0;  //设置状态码，0表示成功
const code_error=-1;    //设置状态码，-1表示失败

//设置本地存储变量
var localStorage=window.localStorage
//设置session变量
var sessionStorage=window.sessionStorage
var toABsURL=function (url) {
    var div=document.createElement('div');
    div.innerHTML='<a href="'+url.replace(/"/g,'%22')+'"/>';
    return div.firstChild.href;
};

$(function () {
    //设置ajax的默认的设置，即每次访问都会为header设置token
    $.ajaxSetup({
        timeout:500000 , //设置超时时间，单位为毫秒
        cache:false,    //是否进行数据缓存
        //contentType:"application/json",这里不能随意加，否则会导致表单（$myForm.serialize()方式）传值时，服务端可能接收不到
        //crossDomain: true,
        xhrFields:{
            withCredentials:true,  //进行跨域设置，服务器设置为允许，可以保持登录状态

        },
        complete:function (XMLHttpRequest,status) {//请求完成后最终执行参数
            if (status=='timeout') {//超时，status还有success，error等值情况
                alert("请求超时！")
            }
        }
    })
});
/**
 * 根据源码重写的get、put、post、delete请求，针对resful风格
 * hzn
 * */
jQuery.each(["getRestful","putRestful","postRestful","deleteRestful"],function (i,method) {
    jQuery[method]=function (url,data,callback,type) {
        if (method=="getRestful") {
            method="get";
        }else if (method=="putRestful") {
            method="put";
        }else if (method=="postRestful"){
            method="post";
        }else if (method=="deleteRestful"){
            method="delete";
        }
        // Shift arguments if data argument was omitted
        if (jQuery.isFunction(data)) {
            type=type || callback;
            callback=data;
            data=undefined;
        }
        // The url can be an options object (which then must have .url)
        return jQuery.ajax(jQuery.extend({
            url:url,
            type:method,
            contentType:"application/json",
            dataType:type,
            data:data,
            success:callback
        },jQuery.isPlainObject (url) && url ));
    }
})


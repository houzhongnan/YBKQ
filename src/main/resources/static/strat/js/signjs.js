var dataObjs = null;
//Demo 参考了这个例子
//https://frankgu2014.github.io/%E7%AD%BE%E5%88%B0/demo.html

window.onload = function () {
    //构建日期数据
    var da = buildData();
    dataObjs = da;
    //控件初始化
    initControls(da);
    //渲染
    renderData(da);
    //通过接口获取数据，合并再重新渲染
    getData();
}
//控件
function initControls(da) {
    $("#spCurrentDay").text(da.current);
    //$('td').height($('#th0').width());

    $("#btnHistory").on('click', function () { $('#divHistory').fadeIn(); })
    $("#btnPopClose").on('click', function () { $('#divHistory').fadeOut(); });

    $("#btnSign").on('click', function () {
        //if (dataObjs && dataObjs.isSigned) return;
        //调用服务器的签到方法
        var btn = $("#btnSign");
        btn.attr('disabled', 'disabled');
        btn.removeClass('actived');
        btn.text('已签到');
        //WH.getAjax(baseUrl + 'Sign').done(function (d) {
        //    if (!d.IsSuccess) {
        //        Msg.notifyWarn(d.ResultMessage);
        //        return;
        //    }
        //    dataObjs.signToday();
        //    var btn = $("#btnSign");
        //    btn.attr('disabled', 'disabled');
        //    btn.removeClass('actived');
        //    btn.text('已签到');
        //}).fail(ajaxError);
    });


}

//生成日期数据
function buildData() {
    var da = {
        dates: [],//日期数据，从1号开始
        current: '',//当前日期
        monthFirst: 1,//获取当月的1日等于星期几
        month: 0,//当前月份
        days: 30,//当前月份共有多少天
        day: 0,//今天几号了
        isSigned: false,//今天是否已经签到
        signLastDays: 3,//连续签到日子

        signToday: function () {
            this.isSigned = true;
            this.dates[this.day].isSigned = true;
        },
    };
    var ds = [];
    //初始化日期数据
    var dt = new Date();
    da.current = dt.getFullYear() + '年' + (dt.getMonth() + 1) + '月' + dt.getDay() + '日'; //dt.ToString('yyyy年M月d日');
    da.monthFirst = new Date(dt.getFullYear(), dt.getMonth(), 1).getDay();
    da.month = dt.getMonth() + 1;
    da.days = new Date(dt.getFullYear(), parseInt(da.month), 0).getDate();//获取当前月的天数
    da.day = dt.getDate();

    for (var i = 1; i < da.days + 1; i++) {
        var o = {
            isSigned: false,//是否签到了
            num: i,//日期
            isToday: i == da.day,//是否今天
            isPass: i < da.day,//时间已过去
        };
        ds[i] = o;
    }
    da.dates = ds;
    return da;
}

//渲染数据
function renderData(da) {
    var signDays = document.getElementById('spSignDays');
    signDays.innerText = da.signLastDays;

    var root = document.getElementById("signTable");
    root.innerHTML = '';

    var tr, td;
    var st = da.monthFirst;
    var dates = da.dates;

    var rowcount = 0;
    //最多6行
    for (var i = 0; i < 42; i++) {
        if (i % 7 == 0) {
            //如果没有日期了，中断
            if (i > (st + da.days))
                break;

            tr = document.createElement('tr');
            tr.className = 'darkcolor trb';
            root.appendChild(tr);
            rowcount++;
        }
        //前面或后面的空白
        if (i < st || !dates[i - st + 1]) {
            td = document.createElement('td');
            td.innerHTML = '<div class="sign-blank"><span></span></div>';
            tr.appendChild(td);
            continue;
        }
        //填充数字部分
        var d = dates[i - st + 1];
        td = document.createElement('td');
        var tdcss = '';
        if (d.isToday)
            tdcss = 'sign-today';
        else if (d.isPass)
            tdcss = 'sign-pass';
        else
            tdcss = 'sign-future';

        if (d.isSigned) {
            tdcss = 'sign-signed ' + tdcss;
            td.innerHTML = '<div class="' + tdcss + '"><span>' + d.num + '</span><svg xmlns="http://www.w3.org/2000/svg" version="1.1" class="sign-pin svg-triangle "><polygon points="0,0 35,0 0,35" /></svg></div>';
        } else {
            tdcss = 'sign-unsign ' + tdcss;
            td.innerHTML = '<div class="' + tdcss + '"><span>' + d.num + '</span></div>';
        }
        tr.appendChild(td);
    }
    //计算是否需要添加最后一行
    if ((st + da.days + 1) / 7 > rowcount)
        root.appendChild(tr);
}

//从服务器获取数据
function getData() {
    //是否已经签到，签到日期
    var d = { IsSigned: true, SignDays: [1, 2, 3, 5, 8] };
    var da = dataObjs;
    if (!da) return;
    da.isSigned = d.IsSigned;
    for (var i = 1; i <= da.days; i++) {
        var dx = da.dates[i];
        dx.isSigned = d.SignDays.indexOf(dx.num) >= 0;
    }
    renderData(da);
}
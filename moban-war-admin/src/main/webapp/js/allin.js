// 加载css
var dynamicLoading = {
    css : function(path,id){
        if(!path || path.length === 0){
            throw new Error('argument "path" is required !');
        }
        var head = document.getElementsByTagName('head')[0];
        var link = document.createElement('link');
        link.href = path;
        link.rel = 'stylesheet';
        link.type = 'text/css';
        link.id = id;
        head.appendChild(link);
    },
    js : function(path){
        if(!path || path.length === 0){
            throw new Error('argument "path" is required !');
        }
        var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        script.src = path;
        script.type = 'text/javascript';
        head.appendChild(script);
    }
};
// js设置cookie
function setCookie(c_name,value,expiredays)
{
    var exdate=new Date()
    exdate.setDate(exdate.getDate()+expiredays)
    document.cookie=c_name+ "=" +escape(value)+
        ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}
// js 获取cookie
function getCookie(c_name)
{
    if (document.cookie.length>0)
    {
        c_start=document.cookie.indexOf(c_name + "=")
        if (c_start!=-1)
        {
            c_start=c_start + c_name.length+1
            c_end=document.cookie.indexOf(";",c_start)
            if (c_end==-1) c_end=document.cookie.length
            return unescape(document.cookie.substring(c_start,c_end))
        }
    }
    return ""
}

function getTheme(){
    var  themeName = getCookie('themeName');
    //alert(themeName);
    if(!themeName || themeName=='style_blue'){
        return 'blue';
    }else if(themeName=='style_orange'){
        return 'orange';
    }
    return null;
}
//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
// 橘色
var  themeName = getCookie('themeName');
if(!themeName){
    themeName = 'style_blue';
}

dynamicLoading.css("../css/easyui/"+themeName+"/easyui.css",'easyuiCss');
dynamicLoading.css("../css/"+themeName+".css",'styleCss');
dynamicLoading.css("../css/"+themeName+".css",'selectCss');
dynamicLoading.css("../css/easyui/icon.css",'');
//dynamicLoading.js("../js/jquery.easyui.min.js",'');
dynamicLoading.js("../js/locale/easyui-lang-zh_CN.js",'');
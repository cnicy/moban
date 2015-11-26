/**
 * jQuery Utils
 * 
 * 基于jQuery的封装工具类库
 */
(function($){
	var validkeypress = function(e,obj){
		var k = window.event ? e.keyCode : e.which;
	      if(k==46){
	     	 if($(obj).val().indexOf(".")==-1){
	     		 return true;
	     	 }else{
	              return false;
	     	 }
	      }
	     else if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
	     	return true;
	      } else {
	          return false;
	      }
	};
	var _startWith = function(str,prefix){
		return str.substring(0,prefix.length) == prefix;
	};
	var _endWith = function(str,suffix){
		return str.substring(str.length-suffix.length) == suffix;
	};
	var _isEmpty = function(str){
		if(!$.trim(str)){
			return true;
		}
		return false;
	};
	var _isBlank = function(str){
		if(!$.trim(str)){
			return true;
		}
		return false;
	};
    // 获取长度为len的随机字符串
    var _getRandomString = function(len) {
        len = len || 32;
        var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; 
        var maxPos = $chars.length;
        var pwd = '';
        for (var i = 0; i < len; i++) {
            pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
        }
        return pwd;
    };
	jQuery.extend({
		//格式化日期函数，即毫秒转成日期格式
		formatDate:function(ms,pattern){
			if(!ms){
				throw Error("you must pass in a parameter！");
			}
			var p = "yyyy-MM-dd HH:mm:ss";//默认格式;
			if(pattern){
				p = pattern;
			}
			var date = new Date(ms);
			var year = date.getFullYear();
			/*if(year<1900){
				year = year + 1900;
			}*/
			var month = date.getMonth()+1;
			var d = date.getDate();
			var hour = date.getHours();
			var minute = date.getMinutes();
			var second = date.getSeconds();
			if(month < 10){
				month = "0"+month;
			}
			if(d < 10){
				d = "0"+d;
			}
			if(hour < 10){
				hour = "0"+hour;
			}
			if(minute < 10){
				minute = "0"+minute;
			}
			if(second < 10){
				second = "0"+second;
			}
			var strdate = p.replace("yyyy",year).replace("MM",month).replace("dd",d).replace("HH",hour).replace("mm",minute).replace("ss",second);
			//var strdate = year + "-" + month + "-" + d+" "+hour+":"+minute+":"+second;
			return strdate;
		},
		getIntervalTime:function(remain){//获得剩余的天数
			var days=Math.floor(remain/(24*3600*1000));
			//计算出小时数
			var leave1=remain%(24*3600*1000);    //计算天数后剩余的毫秒数
			var hours=Math.floor(leave1/(3600*1000));
			//计算相差分钟数
			var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
			var minutes=Math.floor(leave2/(60*1000));
			//计算相差秒数
			var leave3=leave2%(60*1000);  //计算分钟数后剩余的毫秒数
			var seconds=Math.round(leave3/1000);
			return days+"天 "+hours+"小时 "+minutes+"分钟 "+seconds+" 秒";
		},
		createRandom:function(len){
			return _getRandomString(len);
		},
		stringifyJSON:function(json){
			if(!json){
				throw "You must pass in a json";
			}
			if (typeof json != "object" || !json.sort) {
				throw "You must pass in an object or Array";
			}
			var str="[";
			for(var s = 0;s<json.length;s++){
				str+="{";
				for(var i in json[s]){
					str+="\"";
					str+=i;
					str+="\":";
					if(typeof json[s][i] == "object" && json[s][i] instanceof Object){
						str+=$.stringifyJSON(json[s][i]);
						str+=",";
					}else{
						if(typeof json[s][i] == "string"){
							str+="\"";
							str+=json[s][i];
							str+="\",";
						}else{
							str+="";
							str+=json[s][i];
							str+=",";
						}

					}
				}
				if(str.endWith(",")){
					str=str.substring(0,str.length-1);
				}
					
				str+="},";
			}
			if(str.endWith(",")){
				str=str.substring(0,str.length-1);
			}
			str+="]";
			return str;
		},
		oneJSON:function(json){
			var str="{";
			for(var i in json){
				str+="\"";
				str+=i;
				str+="\":";
				if(typeof json[i] == "object" && json[i] instanceof Object){
					var array=new Array();
					array.push(json);
					str+=$.stringifyJSON(array);
					str+=",";
				}else{
					if(typeof json[i] == "string"){
						str+="\"";
						str+=json[i];
						str+="\",";
					}else{
						str+="";
						str+=json[i];
						str+=",";
					}
				}
			}
			if(str.endWith(",")){
				str=str.substring(0,str.length-1);
			}
			str+="}";
			return str;
		},
		ajaxData:function(json){
			var str="";
			for(var i in json){
				str+="\"";
				str+=i;
				str+="\":";
				if(typeof json[i] == "object" && json[i] instanceof Object){
					var array=new Array();
					array.push(json);
					str+=$.stringifyJSON(array);
					str+=",";
				}else{
					if(typeof json[i] == "string"){
						str+="\"";
						str+=json[i];
						str+="\",";
					}else{
						str+="";
						str+=json[i];
						str+=",";
					}
				}
			}
			if(str.endWith(",")){
				str=str.substring(0,str.length-1);
			}
			return str;
		},
		IsNum:function(e){
			var reg = new RegExp("^[0-9]*$");
			return reg.test(e);
		},
		isBlank:function(str){
			return _isBlank(str);
		},
		isNotBlank:function(str){
			return !_isBlank(str);
		},
		isEmpty:function(str){
			return _isEmpty(str);
		},
		isNotEmpty:function(str){
			return !_isEmpty(str);
		}
	});
	var isMouseDown    = false;

	var currentElement = null;

	var dropCallbacks = {};
	var dragCallbacks = {};
	
	var bubblings = {};

	var lastMouseX;
	var lastMouseY;
	var lastElemTop;
	var lastElemLeft;
	
	var dragStatus = {};	

	var holdingHandler = false;

	$.getMousePosition = function(e){
		var posx = 0;
		var posy = 0;

		if (!e) var e = window.event;

		if (e.pageX || e.pageY) {
			posx = e.pageX;
			posy = e.pageY;
		}
		else if (e.clientX || e.clientY) {
			posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop  + document.documentElement.scrollTop;
		}

		return { 'x': posx, 'y': posy };
	};

	$.updatePosition = function(e) {
		var pos = $.getMousePosition(e);

		var spanX = (pos.x - lastMouseX);
		var spanY = (pos.y - lastMouseY);

		$(currentElement).css("top",  (lastElemTop + spanY));
		$(currentElement).css("left", (lastElemLeft + spanX));
	};

	$(document).mousemove(function(e){
		if(isMouseDown && dragStatus[currentElement.id] != 'false'){
			$.updatePosition(e);
			if(dragCallbacks[currentElement.id] != undefined){
				dragCallbacks[currentElement.id](e, currentElement);
			}

			return false;
		}
	});

	$(document).mouseup(function(e){
		if(isMouseDown && dragStatus[currentElement.id] != 'false'){
			isMouseDown = false;
			if(dropCallbacks[currentElement.id] != undefined){
				dropCallbacks[currentElement.id](e, currentElement);
			}

			return false;
		}
	});

	$.fn.ondrag = function(callback){
		return this.each(function(){
			dragCallbacks[this.id] = callback;
		});
	};

	$.fn.ondrop = function(callback){
		return this.each(function(){
			dropCallbacks[this.id] = callback;
		});
	};
	
	$.fn.dragOff = function(){
		return this.each(function(){
			dragStatus[this.id] = 'off';
		});
	};
	
	$.fn.dragOn = function(){
		return this.each(function(){
			dragStatus[this.id] = 'on';
		});
	};
	
	$.fn.setHandler = function(handlerId){
		return this.each(function(){
			var draggable = this;
			
			bubblings[this.id] = true;
			
			$(draggable).css("cursor", "");
			
			dragStatus[draggable.id] = "handler";

			$("#"+handlerId).css("cursor", "pointer");	
			
			$("#"+handlerId).mousedown(function(e){
				holdingHandler = true;
				$(draggable).trigger('mousedown', e);
			});
			
			$("#"+handlerId).mouseup(function(e){
				holdingHandler = false;
			});
		});
	};

	$.fn.drag = function(allowBubbling){
		return this.each(function(){
			if(undefined == this.id || !this.id.length) this.id = "easydrag"+(new Date().getTime());
			
			bubblings[this.id] = allowBubbling ? true : false;

			dragStatus[this.id] = "on";

			$(this).css("cursor", "move");

			$(this).mousedown(function(e){
				if((dragStatus[this.id] == "off") || (dragStatus[this.id] == "handler" && !holdingHandler))
					return bubblings[this.id];

				$(this).css("position", "absolute");

				$(this).css("z-index", parseInt( new Date().getTime()/1000 ));

				isMouseDown    = true;
				currentElement = this;

				var pos    = $.getMousePosition(e);
				lastMouseX = pos.x;
				lastMouseY = pos.y;

				lastElemTop  = this.offsetTop;
				lastElemLeft = this.offsetLeft;
				
				$.updatePosition(e);

				return bubblings[this.id];
			});
		});
	};
	$.fn.createCode = function(len){
		var ranstr = _getRandomString(len);
		$(this).css("font-family","Arial,宋体");
		$(this).css("font-style","italic");
		$(this).css("color","green");
		$(this).css("border","0");
		$(this).css("padding","2px 3px");
		$(this).css("letter-spacing","3px");
		$(this).css("font-weight","bolder");
		$(this).val(ranstr);
	};
	$.fn.showPic = function(obj){
		var ei = $(this);
		ei.css("position","absolute");
	    ei.css("display","none");
	    $(obj).mousemove(function(e){
	    	var top = $(obj).position().top-$(obj).height();
	    	var left = $(obj).position().left;
	    	ei.html('<img src="' + this.src + '" width="140px" height="110px" />').offset({top:top,left:left}).css("display","block");
	    });
	    $(obj).mouseout(function(){
	    	ei.css("display","none");
	    });
	};
	
	//以下为字符串的处理函数
	String.prototype.startWith = function(prefix){
		return _startWith(this,prefix);
	};
	String.prototype.endWith = function(suffix){
		return _endWith(this,suffix);
	};
	String.prototype.isEmpty = function(){
		return _isEmpty(this);
	};
	String.prototype.isNotEmpty = function(){
		return !_isEmpty(this);
	};
	String.prototype.isBlank= function(){
		return _isBlank(this);
	};
	String.prototype.isNotBlank = function(){
		return !_isBlank(this);
	};
	//判断data是否在数组里面
	//包含返回true，否则返回false
	Array.prototype.contains = function(data){
		var flag = false;
		for(var i = 0;i<this.length;i++){
			if(data === this[i]){
				flag = true;
				break;
			}
		}
		return flag;
	}
	//String.prototype.
})(jQuery);

function pro(){
    $.messager.progress({title:'提示',msg:'请稍等,数据加载中...'});
} 
function hide(){
	$.messager.progress('close');
}
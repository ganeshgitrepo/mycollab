/* Sticky Tooltip script (v1.0)
* Created: Nov 25th, 2009. This notice must stay intact for usage 
* Author: Dynamic Drive at http://www.dynamicdrive.com/
* Visit http://www.dynamicdrive.com/ for full source code
*/
var mousePosition = {
	currentMousePos :{ x: -1, y: -1 },
	getMousePosition:function(){
		jQuery(function($) {
		    $(document).mousemove(function(event) {
		    	mousePosition.currentMousePos.x = event.pageX;
		    	mousePosition.currentMousePos.y = event.pageY;
		    });
		    $(document).click(function(e){
		    	$('.stickytooltip').hide();
		    });
		});
	}
}
// bind event mouse move for body document
mousePosition.getMousePosition();

var stickytooltip={
	tooltipoffsets: [20, -30], // additional x and y offset from mouse cursor
								// for tooltips
	fadeinspeed: 200, // duration of fade effect in milliseconds
	rightclickstick: true, // sticky tooltip when user right clicks over the
							// triggering element (apart from pressing "s" key)
							// ?
	stickybordercolors: ["black", "darkred"], // border color of tooltip
												// depending on sticky state
	stickynotice1: ["Press \"s\"", "or right click", "to sticky box"], // customize
																		// tooltip
																		// status
																		// message
	stickynotice2: "Click outside this box to hide it", // customize tooltip
														// status message

	// ***** NO NEED TO EDIT BEYOND HERE

	isdocked: false,

	positiontooltip:function($, $tooltip, e){
		var x=e.pageX+this.tooltipoffsets[0], y=e.pageY+this.tooltipoffsets[1]
		var tipw=$tooltip.outerWidth(), tiph=$tooltip.outerHeight(), 
		x=(x+tipw>$(document).scrollLeft()+$(window).width())? x-tipw-(stickytooltip.tooltipoffsets[0]*2) : x
		y=(y+tiph>$(document).scrollTop()+$(window).height())? $(document).scrollTop()+$(window).height()-tiph-10 : y
		$tooltip.css({left:x, top:y})
	},
	
	showbox:function($, $tooltip, e){
		$tooltip.fadeIn(this.fadeinspeed)
		this.positiontooltip($, $tooltip, e)
	},

	hidebox:function($, $tooltip){
		if (!this.isdocked){
			$tooltip.stop(false, true).hide()
			$tooltip.css({borderColor:'black'}).find('.stickystatus:eq(0)').css({background:this.stickybordercolors[0]}).html(this.stickynotice1)
		}
	},

	docktooltip:function($, $tooltip, e){
		this.isdocked=true
		$tooltip.css({borderColor:'black'}).find('.stickystatus:eq(0)').css({background:this.stickybordercolors[0]}).html(this.stickynotice1)
	},
	
	showboxForFirstTime:function($, $tooltip, o){
		$tooltip.fadeIn(this.fadeinspeed)
		this.positiontooltipForFirstTime($, $tooltip, o)
	},
	
	positiontooltipForFirstTime:function($, $tooltip, o){
		var x=o.x+this.tooltipoffsets[0], y=o.y+this.tooltipoffsets[1]
		var tipw=$tooltip.outerWidth(), tiph=$tooltip.outerHeight(), 
		x=(x+tipw>$(document).scrollLeft()+$(window).width())? x-tipw-(stickytooltip.tooltipoffsets[0]*2) : x
		y=(y+tiph>$(document).scrollTop()+$(window).height())? $(document).scrollTop()+$(window).height()-tiph-10 : y
		$tooltip.css({left:x, top:y})
	},

	init:function(targetselector, tipid){
		$('.stickytooltip').stop(false, true).hide();
		var $targets=$(targetselector)
		var $tooltip=$('#'+tipid).appendTo(document.body)
		if ($targets.length==0)
			return
		var $alltips=$tooltip.find('div.atip')
		if (!stickytooltip.rightclickstick)
			stickytooltip.stickynotice1[1]=''
		// show box
		$tooltip.show();
		stickytooltip.showboxForFirstTime($, $tooltip, mousePosition.currentMousePos)		
		$targets.bind('mouseleave', function(e){
			if(stickytooltip.isdocked == false){
				stickytooltip.isdocked = false;
				stickytooltip.hidebox($, $tooltip)
			}
		})
		$targets.bind('mousemove', function(e){
// if (!stickytooltip.isdocked){
// stickytooltip.positiontooltip($, $tooltip, e)
// }
		})
		$tooltip.bind('mouseenter', function(){
			// stickytooltip.hidebox($, $tooltip)
		})
		
		$tooltip.bind('mouseleave',function(){
			stickytooltip.hidebox($, $tooltip)
		})
		
		$tooltip.bind('click', function(e){
			e.stopPropagation()
		})
		
		$(this).bind("click", function(e){
			if (e.button==0){
				stickytooltip.isdocked=false
				stickytooltip.hidebox($, $tooltip)
			}
		})
		$(this).bind("contextmenu", function(e){
			if (stickytooltip.rightclickstick && $(e.target).parents().andSelf().filter(targetselector).length==1){ // if
																													// oncontextmenu
																													// over
																													// a
																													// target
																													// element
				stickytooltip.docktooltip($, $tooltip, e)
				return false
			}
		})
		$(this).bind('keypress', function(e){
			var keyunicode=e.charCode || e.keyCode
			if (keyunicode==115){ // if "s" key was pressed
				stickytooltip.docktooltip($, $tooltip, e)
			}
		})
	},
	
	init2:function(targetselector, tipid){
		$('.stickytooltip').stop(false, true).hide();
		var $targets=$(targetselector)
		var $tooltip=$('#'+tipid).appendTo(document.body)
		if ($targets.length==0)
			return
		var $alltips=$tooltip.find('div.atip')
		if (!stickytooltip.rightclickstick)
			stickytooltip.stickynotice1[1]=''
		// show box
		$tooltip.show();
		stickytooltip.showboxForFirstTime($, $tooltip, mousePosition.currentMousePos)		
		stickytooltip.docktooltip($, $tooltip, null);
		$targets.bind('mouseleave', function(e){
			if(stickytooltip.isdocked == false){
				stickytooltip.isdocked = false;
				stickytooltip.hidebox($, $tooltip)
			}
		})
		$targets.bind('mousemove', function(e){
// if (!stickytooltip.isdocked){
// stickytooltip.positiontooltip($, $tooltip, e)
// }
		})
		$tooltip.bind('mouseenter', function(){
			// stickytooltip.hidebox($, $tooltip)
		})
		
		$tooltip.bind('mouseleave',function(){
			stickytooltip.hidebox($, $tooltip)
		})
		
		$tooltip.bind('click', function(e){
			e.stopPropagation()
		})
		
		$(this).bind("click", function(e){
			if (e.button==0){
				stickytooltip.isdocked=false
				stickytooltip.hidebox($, $tooltip)
			}
		})
		$(this).bind("contextmenu", function(e){
			if (stickytooltip.rightclickstick && $(e.target).parents().andSelf().filter(targetselector).length==1){ // if
																													// oncontextmenu
																													// over
																													// a
																													// target
																													// element
				stickytooltip.docktooltip($, $tooltip, e)
				return false
			}
		})
		$(this).bind('keypress', function(e){
			var keyunicode=e.charCode || e.keyCode
			if (keyunicode==115){ // if "s" key was pressed
				stickytooltip.docktooltip($, $tooltip, e)
			}
		})
	}
}

function overIt(dateTimeTypeIdStr, type, typeId, url, sAccountId, siteURL, timeZone){
	var idDIVserverdata = "serverdata" + dateTimeTypeIdStr;
	var idStickyToolTipDiv = "mystickyTooltip"+dateTimeTypeIdStr;
	var idTagA = "tagA"+ dateTimeTypeIdStr;
	$('#'+idStickyToolTipDiv).stop(false, true).hide();
	$('.stickytooltip').bind('mouseleave',function(e){
    	$('.stickytooltip').stop(false, true).hide();
	});
	if($("#"+idDIVserverdata).html()== ""){
		$.ajax({
		      type: 'POST',
		      url: url,
		      data : { type: type, typeId: typeId , sAccountId : sAccountId, siteURL: siteURL , timeZone: timeZone},
		      success: function(data){
		      	 if(data.trim()!= "null"){
		      	 		$("#"+ idTagA).attr('data-tooltip', idStickyToolTipDiv);
		      	 		$("#"+idDIVserverdata).html(data);
		      	 		stickytooltip.init("*[data-tooltip]", idStickyToolTipDiv);
		      	 }
		      }
		});
	}else{
		stickytooltip.init2("*[data-tooltip]", idStickyToolTipDiv);
	}
}

function projectOverViewOverIt(dateTimeTypeIdStr, type, typeId, url, sAccountId, siteURL, timeZone){
	var idDIVserverdata = "projectOverViewserverdata" + dateTimeTypeIdStr;
	var idStickyToolTipDiv = "projectOverViewmystickyTooltip"+dateTimeTypeIdStr;
	var idTagA = "projectOverViewtagA"+ dateTimeTypeIdStr;
	$('#'+idStickyToolTipDiv).stop(false, true).hide();
	$('.stickytooltip').bind('mouseleave',function(e){
    	$('.stickytooltip').stop(false, true).hide();
	});
	if($("#"+idDIVserverdata).html()== ""){
		$.ajax({
		      type: 'POST',
		      url: url,
		      data : { type: type, typeId: typeId , sAccountId : sAccountId, siteURL: siteURL , timeZone: timeZone},
		      success: function(data){
		      	 if(data.trim()!= "null"){
		      	 		$("#"+ idTagA).attr('data-tooltip', idStickyToolTipDiv);
		      	 		$("#"+idDIVserverdata).html(data);
		      	 		stickytooltip.init("*[data-tooltip]", idStickyToolTipDiv);
		      	 }
		      }
		});
	}else{
		stickytooltip.init2("*[data-tooltip]", idStickyToolTipDiv);
	}
}

function crmActivityOverIt(dateTimeTypeIdStr, type, typeId, url, sAccountId, siteURL, timeZone){
	var idDIVserverdata = "crmActivityserverdata" + dateTimeTypeIdStr;
	var idStickyToolTipDiv = "crmActivitymystickyTooltip"+dateTimeTypeIdStr;
	var idTagA = "crmActivitytagA"+ dateTimeTypeIdStr;
	$('#'+idStickyToolTipDiv).stop(false, true).hide();
	$('.stickytooltip').bind('mouseleave',function(e){
    	$('.stickytooltip').stop(false, true).hide();
	});
	if(type=="Task") type="CRMTask";
	if($("#"+idDIVserverdata).html()== ""){
		$.ajax({
		      type: 'POST',
		      url: url,
		      data : { type: type, typeId: typeId , sAccountId : sAccountId, siteURL: siteURL , timeZone: timeZone},
		      success: function(data){
		      	 if(data.trim()!= "null"){
		      	 		$("#"+ idTagA).attr('data-tooltip', idStickyToolTipDiv);
		      	 		$("#"+idDIVserverdata).html(data);
		      	 		stickytooltip.init("*[data-tooltip]", idStickyToolTipDiv);
		      	 }
		      }
		});
	}else{
		stickytooltip.init2("*[data-tooltip]", idStickyToolTipDiv);
	}
}

function useroverIt(dateTimeTypeIdStr, username, url, siteURL, timeZone, sAccountId){
	var idDIVserverdata = "userserverdata" + dateTimeTypeIdStr;
	var idStickyToolTipDiv = "usermystickyTooltip"+dateTimeTypeIdStr;
	var idTagA = "usertagA"+ dateTimeTypeIdStr;
	$('#'+idStickyToolTipDiv).stop(false, true).hide();
	$('.stickytooltip').bind('mouseleave',function(e){
    	$('.stickytooltip').stop(false, true).hide();
	});
	if($("#"+idDIVserverdata).html()== ""){
		$.ajax({
		      type: 'POST',
		      url: url,
		      data : { type: "User", username: username ,siteURL: siteURL , timeZone: timeZone , sAccountId:sAccountId},
		      success: function(data){
		      	 if(data.trim()!= "null"){
		      	 		$("#"+ idTagA).attr('data-tooltip', idStickyToolTipDiv);
		      	 		$("#"+idDIVserverdata).html(data);
		      	 		stickytooltip.init("*[data-tooltip]", idStickyToolTipDiv);
		      	 }
		      }
		});
	}else{
		stickytooltip.init2("*[data-tooltip]", idStickyToolTipDiv);
	}
}

function projectuseroverIt(dateTimeTypeIdStr, username, url, siteURL, timeZone, sAccountId){
	var idDIVserverdata = "projectuserserverdata" + dateTimeTypeIdStr;
	var idStickyToolTipDiv = "projectusermystickyTooltip"+dateTimeTypeIdStr;
	var idTagA = "projectusertagA"+ dateTimeTypeIdStr;
	$('#'+idStickyToolTipDiv).stop(false, true).hide();
	$('.stickytooltip').bind('mouseleave',function(e){
    	$('.stickytooltip').stop(false, true).hide();
	});
	if($("#"+idDIVserverdata).html()== ""){
		$.ajax({
		      type: 'POST',
		      url: url,
		      data : { type: "User", username: username ,siteURL: siteURL , timeZone: timeZone, sAccountId:sAccountId},
		      success: function(data){
		      	 if(data.trim()!= "null"){
		      	 		$("#"+ idTagA).attr('data-tooltip', idStickyToolTipDiv);
		      	 		$("#"+idDIVserverdata).html(data);
		      	 		stickytooltip.init("*[data-tooltip]", idStickyToolTipDiv);
		      	 }
		      }
		});
	}else{
		stickytooltip.init2("*[data-tooltip]", idStickyToolTipDiv);
	}
}

function crmuseroverIt(dateTimeTypeIdStr, username, url, siteURL, timeZone,sAccountId){
	var idDIVserverdata = "crmuserserverdata" + dateTimeTypeIdStr;
	var idStickyToolTipDiv = "crmusermystickyTooltip"+dateTimeTypeIdStr;
	var idTagA = "crmusertagA"+ dateTimeTypeIdStr;
	$('#'+idStickyToolTipDiv).stop(false, true).hide();
	$('.stickytooltip').bind('mouseleave',function(e){
    	$('.stickytooltip').stop(false, true).hide();
	});
	if($("#"+idDIVserverdata).html()== ""){
		$.ajax({
		      type: 'POST',
		      url: url,
		      data : { type: "User", username: username ,siteURL: siteURL , timeZone: timeZone,sAccountId:sAccountId},
		      success: function(data){
		      	 if(data.trim()!= "null"){
		      	 		$("#"+ idTagA).attr('data-tooltip', idStickyToolTipDiv);
		      	 		$("#"+idDIVserverdata).html(data);
		      	 		stickytooltip.init("*[data-tooltip]", idStickyToolTipDiv);
		      	 }
		      }
		});
	}else{
		stickytooltip.init2("*[data-tooltip]", idStickyToolTipDiv);
	}
}
/*! cui 2017-11-03 */
$.component("coral.splitcontainer",{version:"4.1.4",options:{minWidth:"600"},_create:function(){var a,b=this,c=this.options,d=(this.element,$(this.element)),e=$(this.element).attr("id");d.addClass("coral-splitcontainer coral-splitcontainer-showhide");var f=$('<ul id="'+e+"_tree\" style='display:none'></ul>").appendTo("body");f.tree({},[{id:"appRoot",name:"appRoot",children:[{id:"appMaster",name:"appMaster",children:[]},{id:"appDetails",name:"appDetails",children:[]}]}]),this.curDetailNode,this.pageIdArray=[],this.historyIdArray=[],this.arr=[];var g=$("#"+e).children("div")[0],h=$("#"+e).children("div")[1];""==g.id&&(g.id="splitContainer-Master"),""==h.id&&(h.id="splitContainer-Detail"),$("#"+g.id).addClass("coral-nav coral-splitcontainer-master coral-splitcontainer-mastervisible"),$("#"+h.id).addClass("coral-nav coral-splitcontainer-detail");var i=b.getTree().tree("getNodeByParam","id","appMaster"),j=b.getTree().tree("getNodeByParam","id","appDetails");this.curDetailNode=j,$("#"+g.id).children("div").each(function(a){c=$.parser.parseOptions(this,[]);var d=b._createView(this.id,c,$("#"+g.id),i);b.getTree().tree("addNodes",i,d),b.options.initialMaster||0!=a||(b.options.initialMaster=this.id),b.initialMaster=$("#"+b.options.initialMaster)}),$("#"+h.id).children("div").each(function(a){c=$.parser.parseOptions(this,[]);var d=b._createView(this.id,c,$("#"+h.id),j);b.getTree().tree("addNodes",j,d),b.options.initialDetail||0!=a||(b.options.initialDetail=this.id),c.title,b.initialDetail=$("#"+b.options.initialDetail)}),a=j.children[0].id,j.viewId="view_"+a,this.addView(a,$("#"+a)),this.toDetail(a),this.pageIdArray.push(a),this.historyIdArray.push(a),this._bindEvent()},addView:function(a,b){var c=$(this.element).attr("id"),d=$("#"+c).children("div")[1],e=this.getTree().tree("getNodeByParam","id",a);if(e){if(b&&"object"==typeof b)if(b.url)$("#"+e.id).panel("reload",b.url);else if(b.content){$("#"+e.id).panel("setContent",b.content);var f=this.initToolbar(b);$("#"+e.id).prev().find(".ctrl-init-toolbar").toolbar("reload",f)}}else{var g=this._createView(a,b,$("#"+d.id),this.curDetailNode);this.getTree().tree("addNodes",this.curDetailNode,g),e=this.getTree().tree("getNodeByParam","id",a)}return this.curDetailNode=e,this.pageIdArray.push(e.id),this.historyIdArray.push(e.id),e.level-2===this.arr.length&&this.arr.push(e),$("#"+e.id)},getCurrentView:function(a){return $("#"+this.curDetailNode.id)},toDetail:function(a,b){var c=(this.options,$(this.element).attr("id")),d=($("#"+c).children("div")[1],this.getTree().tree("getNodeByParam","id",a));d&&($("#"+d.viewId).siblings().hide(),$("#"+d.viewId).show()),d.data=b,$.coral.refreshAllComponent($("#"+a).parent()),this._trigger("onDetailNavigate",null,[{from:this.curDetailNode,fromId:this.curDetailNode.id,to:d,toId:a,direction:"to"}])},NodeRadio:function(a,b,c,d,e,f){var g={};return g.id=a,g.pId=b,g.name=a,g.isParent=c,g.open=d,g.icon=e,g.nocheck=f,g},_createView:function(a,b,c,d){var e,f,g,h=$("#"+a),i=$.inArray(a,this.pageIdArray);0==h.length&&(h=$("<div id='"+a+"'></div>")),i>-1?(g=this.getTree().tree("getNodeByParam","id",this.pageIdArray[i-1]),f=g.viewId,e=$("#"+f)):(f="view_"+a,e=$("<div id='"+f+"' class='coral-nav-item' style='width:100%'></div>").appendTo(c)),h.appendTo(e),this.initToolbar(b);var j=$.extend({},{fit:!0,componentCls:"coral-page",collapsible:!1},b);h.panel(j);var k=this.NodeRadio(a,d.id,!1,!0,null,!1);return k.viewId=f,k},initToolbar:function(a){var b=this,c={type:"button",icons:"cui-icon-arrow-left3",label:"back",text:!1,onClick:function(){b.backDetail()}},d={type:"html",content:a.title};return a.toolbarOptions=a.toolbarOptions||{},a.toolbarOptions.data=a.toolbarOptions.data||[],a.title&&(a.toolbarOptions.data.unshift("->"),a.toolbarOptions.data.unshift(d)),a.showNavButton&&(a.toolbarOptions.data.unshift("->"),a.toolbarOptions.data.unshift(c)),$.each(a.toolbarOptions.data,function(b,d){"navBtn"===this&&(a.toolbarOptions.data[b]=c)}),a.toolbarOptions.data},_bindEvent:function(){var a=this,b=$(this.element).attr("id");$("#"+b).children("div")[0];this._on(this.document,{click:function(b){var c=$(b.target),d=$(a.element).attr("id"),e=$("#"+d).children("div")[0],f=a.element.find(".coral-splitcontainer-master"),g=f.offset();(b.pageX<g.left||b.pageX>g.left+f.width()||b.pageY<g.top||b.pageY>g.top+f.height())&&!c.parent().is("button")&&$("#"+e.id).hasClass("showMaster")&&a.hideMaster()}})},showMaster:function(){var a=$(this.element).attr("id"),b=$("#"+a).children("div")[0];$("#"+a).children("div")[1];$("#"+b.id).addClass("showMaster")},hideMaster:function(){var a=$(this.element).attr("id"),b=$("#"+a).children("div")[0];$("#"+a).children("div")[1];$("#"+b.id).removeClass("showMaster")},maxDetail:function(a){var b=$(this.element).attr("id"),c=$("#"+b).children("div")[0],d=$("#"+b).children("div")[1];"max"==a?$("#"+b).removeClass("coral-splitcontainer-showhide").addClass("coral-splitcontainer-hidemode"):$("#"+b).removeClass("coral-splitcontainer-hidemode").addClass("coral-splitcontainer-showhide"),$("#"+c.id).removeClass("coral-splitcontainer-mastervisible showMaster").addClass("coral-splitcontainer-masterhidden"),$.coral.refreshAllComponent($("#"+d.id).parent())},refresh:function(){var a,b,c,d=this,e=$(this.element).attr("id"),f=$("#"+e).children("div")[0],g=$("#"+e).children("div")[1];this.initialDetail&&(b=$("#"+this.options.initialDetail).panel("option","title"),b&&(a=$("#"+this.options.initialDetail).panel("toolbar"))),$(window).width()<this.options.minWidth?($("#"+e).addClass("coral-splitcontainer-portrait"),a&&(c=a.toolbar("isExist",this.options.id+"_detailMenu"),c||a.toolbar("add",0,{id:this.options.id+"_detailMenu",type:"button",onClick:function(){d.showMaster()},icons:"cui-icon-menu7",text:!1,label:"菜单"})),$("#"+f.id).removeClass("coral-splitcontainer-mastervisible showMaster").addClass("coral-splitcontainer-masterhidden")):($("#"+e).removeClass("coral-splitcontainer-portrait"),$("#"+f.id).removeClass("coral-splitcontainer-masterhidden showMaster").addClass("coral-splitcontainer-mastervisible"),a&&(c=a.toolbar("isExist",this.options.id+"_detailMenu"),c&&a.toolbar("remove",0))),$.coral.refreshAllComponent($("#"+f.id)),$.coral.refreshAllComponent($("#"+g.id))},backDetail:function(a){var b=$.inArray(this.curDetailNode.id,this.historyIdArray);if(!(b<=0)){var c=this.getTree().tree("getNodeByParam","id",this.historyIdArray[b-1]);if($("#"+c.viewId).siblings().hide(),$("#"+c.viewId).show(),c.backData=a,b>-1){var d=this.historyIdArray.length,e=d-b;this.historyIdArray.splice(b,e)}$.coral.refreshAllComponent($("#"+c.id).parent()),this._trigger("onDetailNavigate",null,[{from:this.curDetailNode,fromId:this.curDetailNode.id,to:c,toId:c.id,direction:"back"}]),this.curDetailNode=c}},backToView:function(a,b){var c=$.inArray(a,this.historyIdArray);if(!(c<0)){var d=this.getTree().tree("getNodeByParam","id",a);$("#"+d.viewId).siblings().hide(),$("#"+d.viewId).show(),d.backData=b,$.coral.refreshAllComponent($("#"+a).parent()),this._trigger("onDetailNavigate",null,[{from:this.curDetailNode,fromId:this.curDetailNode.id,to:d,toId:d.id,direction:"backToView"}])}},backToTopDetail:function(a){var b=this.getTree().tree("getNodeByParam","id","appDetails");$("#"+b.viewId).siblings().hide(),$("#"+b.viewId).show(),b.backData=a,$.coral.refreshAllComponent($("#"+b.id).parent()),this._trigger("onDetailNavigate",null,[{from:this.curDetailNode,fromId:this.curDetailNode.id,to:b,toId:b.id,direction:"backToTop"}])},reload:function(a,b){var c;b||(this.curDetailNode=c=this.getTree().tree("getNodeByParam","id","appDetails")),this.toDetail(a)},getTree:function(){return $("#"+$(this.element).attr("id")+"_tree")}});
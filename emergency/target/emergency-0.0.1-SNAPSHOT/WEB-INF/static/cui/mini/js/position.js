/*! cui 2017-11-03 */
!function(){function a(a,b,c){return[parseFloat(a[0])*(m.test(a[0])?b/100:1),parseFloat(a[1])*(m.test(a[1])?c/100:1)]}function b(a,b){return parseInt($.css(a,b),10)||0}function c(a){var b=a[0];return 9===b.nodeType?{width:a.width(),height:a.height(),offset:{top:0,left:0}}:$.isWindow(b)?{width:a.width(),height:a.height(),offset:{top:a.scrollTop(),left:a.scrollLeft()}}:b.preventDefault?{width:0,height:0,offset:{top:b.pageY,left:b.pageX}}:{width:a.outerWidth(),height:a.outerHeight(),offset:a.offset()}}$.coral=$.coral||{};var d,e,f=Math.max,g=Math.abs,h=Math.round,i=/left|center|right/,j=/top|center|bottom/,k=/[\+\-]\d+(\.[\d]+)?%?/,l=/^\w+/,m=/%$/,n=$.fn.position;$.position={scrollbarWidth:function(){if(void 0!==d)return d;var a,b,c=$("<div style='display:block;position:absolute;width:50px;height:50px;overflow:hidden;'><div style='height:100px;width:auto;'></div></div>"),e=c.children()[0];return $("body").append(c),a=e.offsetWidth,c.css("overflow","scroll"),b=e.offsetWidth,a===b&&(b=c[0].clientWidth),c.remove(),d=a-b},getScrollInfo:function(a){var b=a.isWindow||a.isDocument?"":a.element.css("overflow-x"),c=a.isWindow||a.isDocument?"":a.element.css("overflow-y"),d="scroll"===b||"auto"===b&&a.width<a.element[0].scrollWidth,e="scroll"===c||"auto"===c&&a.height<a.element[0].scrollHeight;return{width:e?$.position.scrollbarWidth():0,height:d?$.position.scrollbarWidth():0}},getWithinInfo:function(a){var b=$(a||window),c=$.isWindow(b[0]),d=!!b[0]&&9===b[0].nodeType;return{element:b,isWindow:c,isDocument:d,offset:b.offset()||{left:0,top:0},scrollLeft:b.scrollLeft(),scrollTop:b.scrollTop(),width:c||d?b.width():b.outerWidth(),height:c||d?b.height():b.outerHeight()}}},$.fn.position=function(d){if(!d||!d.of)return n.apply(this,arguments);d=$.extend({},d);var m,o,p,q,r,s,t=$(d.of),u=$.position.getWithinInfo(d.within),v=$.position.getScrollInfo(u),w=(d.collision||"flip").split(" "),x={};return s=c(t),t[0].preventDefault&&(d.at="left top"),o=s.width,p=s.height,q=s.offset,r=$.extend({},q),$.each(["my","at"],function(){var a,b,c=(d[this]||"").split(" ");1===c.length&&(c=i.test(c[0])?c.concat(["center"]):j.test(c[0])?["center"].concat(c):["center","center"]),c[0]=i.test(c[0])?c[0]:"center",c[1]=j.test(c[1])?c[1]:"center",a=k.exec(c[0]),b=k.exec(c[1]),x[this]=[a?a[0]:0,b?b[0]:0],d[this]=[l.exec(c[0])[0],l.exec(c[1])[0]]}),1===w.length&&(w[1]=w[0]),"right"===d.at[0]?r.left+=o:"center"===d.at[0]&&(r.left+=o/2),"bottom"===d.at[1]?r.top+=p:"center"===d.at[1]&&(r.top+=p/2),m=a(x.at,o,p),r.left+=m[0],r.top+=m[1],this.each(function(){var c,i,j=$(this),k=j.outerWidth(),l=j.outerHeight(),n=b(this,"marginLeft"),s=b(this,"marginTop"),y=k+n+b(this,"marginRight")+v.width,z=l+s+b(this,"marginBottom")+v.height,A=$.extend({},r),B=a(x.my,j.outerWidth(),j.outerHeight());"right"===d.my[0]?A.left-=k:"center"===d.my[0]&&(A.left-=k/2),"bottom"===d.my[1]?A.top-=l:"center"===d.my[1]&&(A.top-=l/2),A.left+=B[0],A.top+=B[1],e||(A.left=h(A.left),A.top=h(A.top)),c={marginLeft:n,marginTop:s},$.each(["left","top"],function(a,b){$.coral.position[w[a]]&&$.coral.position[w[a]][b](A,{targetWidth:o,targetHeight:p,elemWidth:k,elemHeight:l,collisionPosition:c,collisionWidth:y,collisionHeight:z,offset:[m[0]+B[0],m[1]+B[1]],my:d.my,at:d.at,within:u,elem:j})}),d.using&&(i=function(a){var b=q.left-A.left,c=b+o-k,e=q.top-A.top,h=e+p-l,i={target:{element:t,left:q.left,top:q.top,width:o,height:p},element:{element:j,left:A.left,top:A.top,width:k,height:l},horizontal:c<0?"left":b>0?"right":"center",vertical:h<0?"top":e>0?"bottom":"middle"};o<k&&g(b+c)<o&&(i.horizontal="center"),p<l&&g(e+h)<p&&(i.vertical="middle"),f(g(b),g(c))>f(g(e),g(h))?i.important="horizontal":i.important="vertical",d.using.call(this,a,i)}),j.offset($.extend(A,{using:i}))})},$.coral.position={fit:{left:function(a,b){var c,d=b.within,e=d.isWindow?d.scrollLeft:d.offset.left,g=d.width,h=a.left-b.collisionPosition.marginLeft,i=e-h,j=h+b.collisionWidth-g-e;b.collisionWidth>g?i>0&&j<=0?(c=a.left+i+b.collisionWidth-g-e,a.left+=i-c):j>0&&i<=0?a.left=e:i>j?a.left=e+g-b.collisionWidth:a.left=e:i>0?a.left+=i:j>0?a.left-=j:a.left=f(a.left-h,a.left)},top:function(a,b){var c,d=b.within,e=d.isWindow?d.scrollTop:d.offset.top,g=b.within.height,h=a.top-b.collisionPosition.marginTop,i=e-h,j=h+b.collisionHeight-g-e;b.collisionHeight>g?i>0&&j<=0?(c=a.top+i+b.collisionHeight-g-e,a.top+=i-c):j>0&&i<=0?a.top=e:i>j?a.top=e+g-b.collisionHeight:a.top=e:i>0?a.top+=i:j>0?a.top-=j:a.top=f(a.top-h,a.top)}},flip:{left:function(a,b){var c,d,e=b.within,f=e.offset.left+e.scrollLeft,h=e.width,i=e.isWindow?e.scrollLeft:e.offset.left,j=a.left-b.collisionPosition.marginLeft,k=j-i,l=j+b.collisionWidth-h-i,m="left"===b.my[0]?-b.elemWidth:"right"===b.my[0]?b.elemWidth:0,n="left"===b.at[0]?b.targetWidth:"right"===b.at[0]?-b.targetWidth:0,o=-2*b.offset[0];k<0?(c=a.left+m+n+o+b.collisionWidth-h-f,(c<0||c<g(k))&&(a.left+=m+n+o)):l>0&&(d=a.left-b.collisionPosition.marginLeft+m+n+o-i,(d>0||g(d)<l)&&(a.left+=m+n+o))},top:function(a,b){var c,d,e=b.within,f=e.offset.top+e.scrollTop,h=e.height,i=e.isWindow?e.scrollTop:e.offset.top,j=a.top-b.collisionPosition.marginTop,k=j-i,l=j+b.collisionHeight-h-i,m="top"===b.my[1],n=m?-b.elemHeight:"bottom"===b.my[1]?b.elemHeight:0,o="top"===b.at[1]?b.targetHeight:"bottom"===b.at[1]?-b.targetHeight:0,p=-2*b.offset[1];k<0?(d=a.top+n+o+p+b.collisionHeight-h-f,a.top+n+o+p>k&&(d<0||d<g(k))&&(a.top+=n+o+p)):l>0&&(c=a.top-b.collisionPosition.marginTop+n+o+p-i,a.top+n+o+p>l&&(c>0||g(c)<l)&&(a.top+=n+o+p))}},flipfit:{left:function(){$.coral.position.flip.left.apply(this,arguments),$.coral.position.fit.left.apply(this,arguments)},top:function(){$.coral.position.flip.top.apply(this,arguments),$.coral.position.fit.top.apply(this,arguments)}}},function(){var a,b,c,d,f,g=document.getElementsByTagName("body")[0],h=document.createElement("div");a=document.createElement(g?"div":"body"),c={visibility:"hidden",width:0,height:0,border:0,margin:0,background:"none"},g&&$.extend(c,{position:"absolute",left:"-1000px",top:"-1000px"});for(f in c)a.style[f]=c[f];a.appendChild(h),b=g||document.documentElement,b.insertBefore(a,b.firstChild),h.style.cssText="position: absolute; left: 10.7432222px;",d=$(h).offset().left,e=d>10&&d<11,a.innerHTML="",b.removeChild(a)}()}();
/**
 *
 * @authors Your Name (you@example.org)
 * @date    2015-03-12 11:23:24
 * @version $Id$
 */
 $(function(){

 	//头部菜单事件
 	$('#headMenu>li').click(function(){
 		$(this).addClass('current').siblings().removeClass('current');
 		if($(this).hasClass('siteMaintenance')){

 		}else{
 			$('.siteMaintenance').children('.droparrow').addClass('down2').removeClass('up2');
			$('.moremenudrop').hide();
			$('.moremenudrop > ul > li').removeClass('current');
 		}
 	})

 	//头部菜单事件
 	$('.moremenudrop>ul>li').click(function(){
 		$(this).addClass('current').siblings().removeClass('current');
 	})
 	
 	
 	
 	


 })

/**
 * ===============================遮挡面板插件===============================
 */
(function () {
	var DEF_SPEED = 100;
	var DEF_ICON = 'waiting';
	var DEF_MSG = '正在加载，请稍等...';
	var DEF_STR = '<div class="hz-mask"><div class="table"><div class="table-cell"><a class="waiting"><span class="mask-msg"></span></a></div></div></div>';

	/*
	 * 显示遮面板
	 * @param msg	显示消息
	 * @param speed 显示速度
	 * @param icon	显示的图标：waiting（默认）
	 */
	function _showMask (msg, speed, icon) {
		if (typeof speed != 'number') {
			icon = icon || speed;
			speed = DEF_SPEED;
		}

		$('div.hz-mask').length || $('body').prepend(DEF_STR);
		$('div.hz-mask span.mask-msg').html(msg || DEF_MSG);
		$('div.hz-mask a').removeClass().addClass(icon || DEF_ICON);
		$('div.hz-mask').fadeIn(speed);
	}

	/*
	 * 显示警告的提示
	 * @param msg	显示消息
	 * @param speed 显示速度
	 */
	function _warnMask (msg, speed) {
		_showMask(msg, speed, 'warn');
	}

	/*
	 * 隐藏遮面板
	 * @param speed 隐藏速度
	 */
	function _hideMask (speed) {
		$('div.hz-mask').fadeOut(speed || DEF_SPEED);
	}

	/**
	 * 遮面板模块注册
	 */
	try {
		// 自动载入样式
//		var elements = document.getElementsByTagName('script');
//		var src = null;
//		var link = null;
//		for (var i = 0, I = elements.length; i < I; i++) {
//			src = elements.item(i).src;
//			if (src.indexOf('hz.mask.js') > 0) {
//				src = src.replace('plugins/hz.mask.js', 'themes/default/hzmask.css');
//				link = document.createElement('link');
//				link.ref = 'stylesheet';
//				link.type = 'text/css';
//				link.href = src;
//				document.getElementsByTagName('head').item(0).appendChild(link);
//				break;
//			}
//		}

		window['hzMask'] = {
			'show': _showMask,
			'warn': _warnMask,
			'hide': _hideMask
		};
		console.log('模块注册 --> 遮面板模块注册完成...');
	} catch (e) {
		console.warn('模块注册 --> 遮面板模块注册异常...', e);
	}
})();
/**
 * <p>Copyright:Copyright(c) 2014</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.ces.prison.common.utils</p>
 * <p>文件名:PinYin4jUtil.java</p>
 * <p>类更新历史信息</p>
 * @todo hp(冯有双  E-mail:feng.youshuang@cesgroup.com.cn) 
 * 创建于 2014-12-11 上午9:28:32
 */
package com.cesgroup.prison.code.tool;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * <p>描述:</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
 * @date 2014-12-11  上午9:28:32
 * @version 1.0.2014.
 */
public class PinYin4jUtil {

	/**
	 * 根据汉字取得汉语拼音
	 * 
	 * @param hanzi
	 *            汉字
	 * @param lowerCase
	 *            是否小写
	 * @param isFirst
	 *            是否只取每个汉字的首字母
	 * @return
	 */
	public static String getHanyuPinyinOfHanzi(String hanzi, boolean lowerCase,
			boolean isFirst) {
		String pinyin = "";
		for (int j = 0; j < hanzi.length(); j++) {
			char temp = hanzi.charAt(j);
			String[] pinyins = (String[]) null;
			try {
				HanyuPinyinOutputFormat hanyupinyinFormat = new HanyuPinyinOutputFormat();
				hanyupinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
				hanyupinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
				hanyupinyinFormat
						.setCaseType(lowerCase ? HanyuPinyinCaseType.LOWERCASE
								: HanyuPinyinCaseType.UPPERCASE);
				pinyins = PinyinHelper.toHanyuPinyinStringArray(temp,
						hanyupinyinFormat);
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
			if (pinyins == null) {
				pinyins = new String[] { String.valueOf(temp) };
			}
			if (pinyins.length > 0) {
				String allpinyin = pinyins[0];
				if (isFirst)
					allpinyin = allpinyin.substring(0, 1);
				else {
					allpinyin = allpinyin + " ";
				}
				pinyin = pinyin + allpinyin;
			}
		}
		return pinyin;
	}
	
}

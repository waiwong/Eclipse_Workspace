package tools;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Cn2Spell {
	/**
	 * ����ת��λ����ƴ������ĸ��Ӣ���ַ�����
	 * 
	 * @param chines
	 *            ����
	 * @return ƴ��
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/**
	 * ����ת��λ����ƴ����Ӣ���ַ�����
	 * 
	 * @param chines
	 *            ����
	 * @return ƴ��
	 */
	public static String converterToSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	public static void main(String[] args) {

		System.out.println(converterToFirstSpell("ҽԺ��վ�е�ҽ��������վҽ����ͨ����ƽ̨"));
		System.out.println(converterToFirstSpell("����Ժ��̨��������ؾٴ�"));
		System.out.println(converterToFirstSpell("����ҽ���Ҷ�˭���й��ô��"));
		System.out.println(converterToFirstSpell("����ҽ�Ƹ�������ҽ��APP�ɿ�·�ȷ�"));
		System.out.println(converterToFirstSpell("����ҽԺ�����ʵ����̽��"));
	}
}

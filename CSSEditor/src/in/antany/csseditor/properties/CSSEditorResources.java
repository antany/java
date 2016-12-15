package in.antany.csseditor.properties;

import java.util.Locale;
import java.util.ResourceBundle;

public class CSSEditorResources {
	private static String language;
	private static String country;
	private static ResourceBundle messages;
	private static Locale currentLocale;

	static {
		language = new String("en");
		country = new String("US");
		currentLocale = new Locale(language, country);
		messages = ResourceBundle.getBundle(
				"in.antany.csseditor.properties.MessageBundle", currentLocale);
	}

	public static String getValue(String messageKey) {
		return messages.getString(messageKey);
	}
}

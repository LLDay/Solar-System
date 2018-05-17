package solarsystem.propertypanel;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextFieldLimit extends PlainDocument {
	private int limit;

	public TextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	public void insertString(int offset, String string, AttributeSet attribute) throws BadLocationException {
		if (string == null)
			return;

		if (getLength() + string.length() <= limit)
			super.insertString(offset, string, attribute);
	}
}
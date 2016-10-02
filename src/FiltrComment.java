/*
 * система фильтрации комментариев на каком-то веб-портале
 * —пам будем фильтровать по наличию указанных ключевых слов в тексте.
 * Ќегативное содержание будем определ€ть по наличию одного из 
 * трех смайликов Ц :( =( :|
 * —лишком длинные комментарии будем определ€ть исход€ из 
 * данного числа Ц максимальной длины комментари€.
 */
public class FiltrComment {
	
	interface TextAnalyzer {
	    Label processText(String text);
	}
	
	enum Label {
	    SPAM, NEGATIVE_TEXT, TOO_LONG, OK
	}
	
	abstract class KeywordAnalyzer implements TextAnalyzer {
		protected abstract String[] getKeywords();
		protected abstract Label getLabel();
		
		@Override
		public Label processText(String text) {
			for (String keyword : getKeywords()) {
		        if (text.contains(keyword))
		            return getLabel();
		    }
		    return Label.OK;
		}
		
	}

	class SpamAnalyzer extends KeywordAnalyzer {
		private String[] keywords;

		public SpamAnalyzer(String[] keywords) {
			this.keywords = keywords;
		}

		@Override
		public String[] getKeywords() {
			return keywords;
		}

		@Override
		public Label getLabel() {
			return Label.SPAM;
		}		
	}

	class NegativeTextAnalyzer extends KeywordAnalyzer {

		@Override
		public String[] getKeywords() {
			return new String[] {":(", "=(", ":|"};
		}

		@Override
		public Label getLabel() {
			return Label.NEGATIVE_TEXT;
		}
		
	}

	class TooLongTextAnalyzer implements TextAnalyzer {
		private int maxLength;

		public TooLongTextAnalyzer(int maxLength) {
			this.maxLength = maxLength;
		}

		@Override
		public Label processText(String text) {
			if (maxLength < text.length()) return Label.TOO_LONG;
			return Label.OK;
		}
	}
	
	public Label checkLabels(TextAnalyzer[] analyzers, String text) {	
		Label label = Label.OK;
		for (TextAnalyzer analyzer : analyzers) {
			label = analyzer.processText(text);
			if (label != Label.OK) break;
		}
	    return label;
	}

	public static void main(String[] args) {
		String text1 = "this is first test, and it is spam";
		String text2 = "this is third test, and it is not spam";
		String text3 = "first spam";
		
		TextAnalyzer[] analyzers = new TextAnalyzer[3];
		
		FiltrComment fc = new FiltrComment();
		SpamAnalyzer sa = fc.new SpamAnalyzer(new String[]{"first", "second"});
		NegativeTextAnalyzer nta = fc.new NegativeTextAnalyzer();
		TooLongTextAnalyzer tlta = fc.new TooLongTextAnalyzer(100);
		analyzers = new TextAnalyzer[]{sa, nta, tlta};
		System.out.println(fc.checkLabels(analyzers, text1));
	}

}

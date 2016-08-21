package tokyo.northside.omegat.commonmark;


import org.commonmark.node.Text;

/**
 * Entry Handler for CommonMark parser.
 * <p>
 * Convert serializer callback to OmegaT entries.
 * @author Hiroshi Miura
 */
class EntryHandler {

    private OmegatCommonmarkFilter filter;
    private String article;

    private StringBuilder entryBuf;
    private int para;

    EntryHandler(final OmegatCommonmarkFilter filter, final String article) {
        this.filter = filter;
        this.article = article;
        entryBuf = new StringBuilder();
        para = 0;
    }

    String getArticle() {
        return article;
    }

    private void resetEntryBuf() {
        entryBuf = new StringBuilder();
        para = 0;
    }

    /**
     * Convenient function to call from Serializer.
     * @param node PegDown's TextNode node.
     */
    void putEntry(final Text node) {
        String text = node.getLiteral();
        putEntry(text);
    }

    void putMark(final String chars) {
        if (chars.startsWith("**")) {
            putEntry("<b>");
        } else if (chars.startsWith("__")) { // italic
            putEntry("<i>");
        } else if (chars.startsWith("<")) {
            putEntry(chars);
        }
    }

    void startPara() {
        para++;
    }

    void endPara() {
        para--;
        if (para <= 0) {
            filter.writeTranslate(entryBuf.toString(), true);
            resetEntryBuf();
        }
    }

    void putEntry(final String text) {
        if (para > 0) {
            entryBuf.append(text);
        } else {
            filter.writeTranslate(text, true);
        }
    }

    void finish() {
    }
}

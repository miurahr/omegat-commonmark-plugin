package tokyo.northside.omegat.commonmark;

import org.commonmark.node.*;


/**
 * Markdown parser and serializer class.
 * @author Hiroshi Miura
 */
class CommonmarkVisitor extends AbstractVisitor {
    private EntryHandler handler;

    CommonmarkVisitor(final EntryHandler entryHandler) {
        handler = entryHandler;
    }

    @Override
    public void visit(final Text text) {
        handler.putEntry(text);
    }

    @Override
    public void visit(Paragraph paragraph) {
        handler.startPara();
        visitChildren(paragraph);
        handler.endPara();
    }

}

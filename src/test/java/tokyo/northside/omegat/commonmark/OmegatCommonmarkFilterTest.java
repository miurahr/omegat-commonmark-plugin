package tokyo.northside.omegat.commonmark;

import static org.testng.Assert.*;

import org.omegat.filters2.FilterContext;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;

/**
 * Test for OmegatMarkdownFilter plugin for omegat.
 * Created by miurahr on 16/08/23.
 */
class OmegatCommonmarkFilterTest extends TestFilterBase {
    @Test
    void testGetFileFormatName() throws Exception {
        String expected = "Commonmark Filter";
        OmegatCommonmarkFilter mdf = new OmegatCommonmarkFilter();
        assertEquals(mdf.getFileFormatName(), expected);
    }

    @Test
    void testGetHint() throws Exception {
        String expected = "Note: Filter to translate Markdown files.";
        OmegatCommonmarkFilter mdf = new OmegatCommonmarkFilter();
        assertEquals(mdf.getHint(), expected);
    }

    @Test
    void testIsSourceEncodingVariable() throws Exception {
        OmegatCommonmarkFilter mdf = new OmegatCommonmarkFilter();
        assertFalse(mdf.isSourceEncodingVariable());
    }

    @Test
    void testIsTargetEncodingVariable() throws Exception {
        OmegatCommonmarkFilter mdf = new OmegatCommonmarkFilter();
        assertFalse(mdf.isTargetEncodingVariable());
    }

    @Test
    void testIsFileSupported_true() throws Exception {
        OmegatCommonmarkFilter mdf = new OmegatCommonmarkFilter();
        File target = new File(this.getClass().getResource("/source/case0.md").getFile());
        FilterContext fc = new FilterContext();
        assertTrue(mdf.isFileSupported(target, null, fc));
    }

    @Test
    void testIsFileSupported_false() throws Exception {
        OmegatCommonmarkFilter mdf = new OmegatCommonmarkFilter();
        File target = new File(this.getClass().getResource("/source/nomarkdown.txt").getFile());
        FilterContext fc = new FilterContext();
        assertFalse(mdf.isFileSupported(target, null, fc));
    }

    @Test
    void testProcess_case1() throws Exception {
        OmegatCommonmarkFilter mdf = new OmegatCommonmarkFilter();
        List<String> entries = parse(mdf, "/source/case1.md");
    }

    @Test
    void testTranslate_case1() throws Exception {
        OmegatCommonmarkFilter mdf = new OmegatCommonmarkFilter();
        translateText(mdf, "/source/case1.md");
    }
}

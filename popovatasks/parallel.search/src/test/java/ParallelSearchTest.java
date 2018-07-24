import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParallelSearchTest {

    private ParallelSearch parallelSearch;

    @Before
    public void loadBefore() throws InterruptedException{
        String root = "D:\\мануал";
        String text = "text";
        List<String> exts = new ArrayList<>(Arrays.asList("txt"));
        parallelSearch = new ParallelSearch(root, text, exts);
        parallelSearch.init();
    }

    @Test
    public void findAllFiles() {
        Set<String> result = parallelSearch.result();
        for (String string : result) {
            System.out.println(string);
        }
        assertThat(2, is(result.size()) );
    }

}
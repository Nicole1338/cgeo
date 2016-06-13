package cgeo.geocaching.filter;

import junit.framework.TestCase;

import java.util.ArrayList;

import cgeo.geocaching.models.Geocache;
import cgeo.geocaching.enumerations.CacheType;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeFilterTest extends TestCase {

    private TypeFilter traditionalFilter;
    private Geocache traditional;
    private Geocache mystery;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        traditionalFilter = new TypeFilter(CacheType.TRADITIONAL);

        traditional = new Geocache();
        traditional.setType(CacheType.TRADITIONAL);

        mystery = new Geocache();
        mystery.setType(CacheType.MYSTERY);
    }

    public void testAccepts() {
        assertThat(traditionalFilter.accepts(traditional)).isTrue();
        assertThat(traditionalFilter.accepts(mystery)).isFalse();
    }

    public void testFilter() {
        final ArrayList<Geocache> list = new ArrayList<>();
        traditionalFilter.filter(list);
        assertThat(list).isEmpty();

        list.add(traditional);
        list.add(mystery);
        assertThat(list).hasSize(2);

        traditionalFilter.filter(list);
        assertThat(list).containsExactly(traditional);

    }

    public static void testGetAllFilters() {
        final int expectedEntries = CacheType.values().length - 1; // hide "all"
        assertThat(new TypeFilter.Factory().getFilters()).hasSize(expectedEntries);
    }

}

package org.jboss.weld.tests.ejb.singleton;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.BeanArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.weld.tests.category.Integration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@Category(Integration.class)
@RunWith(Arquillian.class)
public class SingletonStartupTest {

    @Deployment
    public static Archive<?> deploy() {
        return ShrinkWrap.create(BeanArchive.class)
                .addPackage(SingletonStartupTest.class.getPackage());
    }

    @Test
    public void testSingletonStartup() {
        assert Foo.isPostConstructCalled();
    }

    @Test
    @Ignore("WELD-1885")
    public void testSingletonStartupCount(Foo foo) {
        for (int i = 0; foo.getSomeValue() && i < 10; i++) {
        }
        Assert.assertEquals(1, Foo.getCountOfPostConstructCalled());
        Assert.assertEquals(1, Foo.getCountOfConstructCalled());
    }
}

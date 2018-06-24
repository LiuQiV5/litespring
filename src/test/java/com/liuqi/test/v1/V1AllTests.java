package com.liuqi.test.v1;

import com.liuqi.test.v1.ApplicationContextTest;
import com.liuqi.test.v1.BeanFactoryTest;
import com.liuqi.test.v1.ResourceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationContextTest.class, BeanFactoryTest.class, ResourceTest.class})
public class V1AllTests {
}

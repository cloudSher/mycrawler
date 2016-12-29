package com.sher.mycrawler;

import com.sher.mycrawler.crawl.scripts.ScriptLang;
import com.sher.mycrawler.crawl.scripts.ScriptProcessor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import us.codecraft.webmagic.Spider;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testCrawler(){
        Spider.create(new ScriptProcessor.Builder().addLang(ScriptLang.JAVASCRIPT).scriptFromClassPath("js/github.js").build())
                .addUrl("https://github.com/code4craft")
                .run();
    }
}

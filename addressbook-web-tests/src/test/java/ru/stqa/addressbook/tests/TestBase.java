package ru.stqa.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] parametrs) {
        logger.info(String.format("Start test %s, with parameters %s", method.getName(), Arrays.asList(parametrs)));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        logger.info(String.format("Stop test %s", method.getName()));

    }


    //C:\Program Files\Java\jdk1.8.0_101\bin\java C:\8bitRepositories\LeadBit\Threads\build\resources\test\ru.stqa.threads.generators.ThreadsDataGenerator -c 3 -d json -s data -e dev

    //ru.stqa.addressbook.generators.GroupDataGenerator -c 10 -f src/test/resources/groups -d json

    //"C:\Program Files\Java\jdk1.8.0_101\bin\java"
    // -Didea.launcher.port=7534
    // "-Didea.launcher.bin.path=C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2016.2.4\bin"
    // -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_101\jre\lib\charsets.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\access-bridge-64.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\dnsns.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\jfxrt.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\nashorn.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunjce_provider.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\sunpkcs11.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\javaws.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\jfr.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\jsse.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\plugin.jar;
    // C:\Program Files\Java\jdk1.8.0_101\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_101\jre\lib\rt.jar;
    // C:\AutomationTesting\JavaTraining\addressbook-web-tests\build\classes\test;
    // C:\AutomationTesting\JavaTraining\addressbook-web-tests\build\resources\test;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.slf4j\slf4j-api\1.7.20\867d63093eff0a0cb527bf13d397d850af3dcae3\slf4j-api-1.7.20.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.eclipse.jetty.websocket\websocket-api\9.2.18.v20160721\d039da78ea75bd60dea3a0258a0b5a7fc45cc9c9\websocket-api-9.2.18.v20160721.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.hamcrest\hamcrest-core\1.3\42a25dc3219429f0e5d060061f71acb49bf010a0\hamcrest-core-1.3.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.eclipse.jetty.websocket\websocket-common\9.2.18.v20160721\b48b198c5dd66e497a3ffa3c5b019c243aef793f\websocket-common-9.2.18.v20160721.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.eclipse.jetty\jetty-io\9.2.18.v20160721\9e2318fdf42815e78008f5942258cbf0ab9806d4\jetty-io-9.2.18.v20160721.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.eclipse.jetty\jetty-util\9.2.18.v20160721\b304329684d004c1d258609f6ccd9a8e2a0393f1\jetty-util-9.2.18.v20160721.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.eclipse.jetty.websocket\websocket-client\9.2.18.v20160721\6e253091f8eed8d1364c956a53d4f4c13145a853\websocket-client-9.2.18.v20160721.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-api\3.0.1\1b9f071c79d7ae546c4c4d3398c9cc5828cf2864\selenium-api-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\commons-io\commons-io\2.5\2852e6e05fbb95076fc091f6d1780f1f8fe35e0f\commons-io-2.5.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\net.sourceforge.cssparser\cssparser\0.9.20\ae63f187dbfc6000f2853e89bc5127d0f2b94351\cssparser-0.9.20.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\net.sourceforge.htmlunit\neko-htmlunit\2.23\924c2587736b71433d44f4660953c7b837563418\neko-htmlunit-2.23.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\net.sourceforge.htmlunit\htmlunit-core-js\2.23\6ade18a1568c4da458ca197aaa6d3fe213c69928\htmlunit-core-js-2.23.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\junit\junit\4.12\2973d150c0dc1fefe998f834810d68f278ea58ec\junit-4.12.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.apache.ant\ant-launcher\1.9.7\224857a490283e72da13ffe3082dea62c558ec76\ant-launcher-1.9.7.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-remote-driver\3.0.1\963199423ff445b2c09ef2d5469c964f9a628df3\selenium-remote-driver-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\com.codeborne\phantomjsdriver\1.3.0\670cb2d15e6fe65e08feff3312e757bb2eee6ff5\phantomjsdriver-1.3.0.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\net.sourceforge.htmlunit\htmlunit\2.23\51e8f01b7ce983a5eba4a690ddcf946573c75138\htmlunit-2.23.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-support\3.0.1\e9c4c37db74ad57554ac747b18e8465055c9e231\selenium-support-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-safari-driver\3.0.1\3b36bd9e0708f0fa3a4c41487d5de13be1b1d33d\selenium-safari-driver-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-opera-driver\3.0.1\461b1381eae4485225cc93749f894cf4c752b4fe\selenium-opera-driver-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-ie-driver\3.0.1\e4b241603a368d877e406790eae4bee417814fc6\selenium-ie-driver-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-edge-driver\3.0.1\ec33db1d7e5a1ab2da248635366920a35a51465a\selenium-edge-driver-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.apache.ant\ant\1.9.7\3b2a10512ee6537d3852c9b693a0284dcab5de68\ant-1.9.7.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\mysql\mysql-connector-java\6.0.5\9d8df5a1f3273110f2a9ad304961bdd03d696d86\mysql-connector-java-6.0.5.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-firefox-driver\3.0.1\653064ccbd3083113ca1e8b5d9c8c822e5822d83\selenium-firefox-driver-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-chrome-driver\3.0.1\3ad968cea2754de3009d8a19f3adc52ec94915b3\selenium-chrome-driver-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.seleniumhq.selenium\selenium-java\3.0.1\e33671f4c2e4fa225ce0279bbbe8eb4fcd9b0c21\selenium-java-3.0.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.testng\testng\6.9.13.6\e93ec1cd3be2d256d0f9d5ebc017911e48805c87\testng-6.9.13.6.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\xpp3\xpp3_min\1.1.4c\19d4e90b43059058f6e056f794f0ea4030d60b86\xpp3_min-1.1.4c.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\xmlpull\xmlpull\1.1.3.1\2b8e230d2ab644e4ecaa94db7cdedbc40c805dfa\xmlpull-1.1.3.1.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\com.thoughtworks.xstream\xstream\1.4.9\c43f6e6bfa79b56e04a8898a923c3cf7144dd460\xstream-1.4.9.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\io.netty\netty\3.5.7.Final\811465e6dfc89d7c78d21de6a9747b6046cb5403\netty-3.5.7.Final.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\net.java.dev.jna\jna-platform\4.1.0\23457ad1cf75c2c16763330de5565a0e67b4bc0a\jna-platform-4.1.0.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\net.java.dev.jna\jna\4.1.0\1c12d070e602efd8021891cdd7fd18bc129372d4\jna-4.1.0.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.apache.commons\commons-exec\1.3\8dfb9facd0830a27b1b5f29f84593f0aeee7773b\commons-exec-1.3.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\xml-apis\xml-apis\1.4.01\3789d9fada2d3d458c4ba2de349d48780f381ee3\xml-apis-1.4.01.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.w3c.css\sac\1.3\cdb2dcb4e22b83d6b32b93095f644c3462739e82\sac-1.3.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\xerces\xercesImpl\2.11.0\9bb329db1cfc4e22462c9d6b43a8432f5850e92c\xercesImpl-2.11.0.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\xalan\serializer\2.7.2\24247f3bb052ee068971393bdb83e04512bb1c3c\serializer-2.7.2.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.apache.httpcomponents\httpmime\4.5.2\22b4c53dd9b6761024258de8f9240c3dce6ea368\httpmime-4.5.2.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\xalan\xalan\2.7.2\d55d3f02a56ec4c25695fe67e1334ff8c2ecea23\xalan-2.7.2.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\commons-logging\commons-logging\1.2\4bfc12adfe4842bf07b657f0369c4cb522955686\commons-logging-1.2.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.apache.httpcomponents\httpcore\4.4.4\b31526a230871fbe285fbcbe2813f9c0839ae9b0\httpcore-4.4.4.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\commons-codec\commons-codec\1.10\4b95f4897fa13f2cd904aee711aeafc0c5295cd8\commons-codec-1.10.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\cglib\cglib-nodep\3.2.4\42c972dee25a436b32c6027e848287ff47ed6750\cglib-nodep-3.2.4.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.apache.commons\commons-lang3\3.4\5fe28b9518e58819180a43a850fbc0dd24b7c050\commons-lang3-3.4.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\com.google.guava\guava\19.0\6ce200f6b23222af3d8abb6b6459e6c44f4bb0e9\guava-19.0.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.apache.httpcomponents\httpclient\4.5.2\733db77aa8d9b2d68015189df76ab06304406e50\httpclient-4.5.2.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\com.google.code.gson\gson\2.7\751f548c85fa49f330cecbb1875893f971b33c4e\gson-2.7.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.hamcrest\java-hamcrest\2.0.0.0\f1c8853ade0ecf707f5a261c830e98893983813\java-hamcrest-2.0.0.0.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\org.beanshell\bsh\2.0b4\a05f0a0feefa8d8467ac80e16e7de071489f0d9c\bsh-2.0b4.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\com.beust\jcommander\1.58\f87aedf052aa17fa6d2557e5cc680a70bc6211f\jcommander-1.58.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\ch.qos.logback\logback-classic\1.1.7\9865cf6994f9ff13fce0bf93f2054ef6c65bb462\logback-classic-1.1.7.jar;
    // C:\Users\Tester4\.gradle\caches\modules-2\files-2.1\ch.qos.logback\logback-core\1.1.7\7873092d39ef741575ca91378a6a21c388363ac8\logback-core-1.1.7.jar;
    // C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2016.2.4\lib\idea_rt.jar"
    // com.intellij.rt.execution.application.AppMain ru.stqa.addressbook.generators.ContactDataGenerator -d json -f src/test/resources/contacts.json -c 10



}

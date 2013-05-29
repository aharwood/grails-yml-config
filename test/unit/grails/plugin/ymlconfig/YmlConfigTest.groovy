package grails.plugin.ymlconfig

import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.junit.Before
import org.junit.Test
import org.codehaus.groovy.tools.shell.util.Logger

import static junit.framework.Assert.assertEquals

class YmlConfigTest {

    def grailsApplication

    @Before
    void setup() {
        this.grailsApplication = new DefaultGrailsApplication()
        this.grailsApplication.config = [:]
    }

    @Test
    void rootLevelPropertiesShouldBeSetIntoGrailsConfig() {
        YmlConfig ymlConfig = newYmlConfig("test/unit/resources/singlePropertyTest.yml")
        ymlConfig.init()
        assertEquals("test", ymlConfig.config.baseProperty)
    }

    @Test
    void nestedPropertiesShouldBeSetIntoGrailsConfig() {
        YmlConfig ymlConfig = newYmlConfig("test/unit/resources/nestedPropertyTest.yml")
        ymlConfig.init()
        assertEquals("test0", ymlConfig.config.nested."property-zero")
        assertEquals("test1", ymlConfig.config.nested.nested."property-one")
    }

    @Test(expected = FileNotFoundException)
    void nonExistentFileShouldThrowFileNotFoundExceptionByDefault() {
        YmlConfig ymlConfig = newYmlConfig("fail.yml")
        ymlConfig.init()
    }

    @Test
    void nonExistentFileShouldNotThrowExceptionIfConfiguredNotTo() {
        YmlConfig ymlConfig = newYmlConfig("fail.yml")
        ymlConfig.config.grails.plugin.ymlconfig.failOnError = false
        ymlConfig.init()
    }

    @Test(expected = FileNotFoundException)
    void nonExistentFileShouldThrowExceptionIfConfiguredTo() {
        YmlConfig ymlConfig = newYmlConfig("fail.yml")
        ymlConfig.config.grails.plugin.ymlconfig.failOnError = true
        ymlConfig.init()
    }

    @Test
    void emptyYmlFileShouldDoNothing() {
        YmlConfig ymlConfig = newYmlConfig("test/unit/resources/empty.yml")
        ymlConfig.init()
    }

    YmlConfig newYmlConfig(String ymlPath) {
        YmlConfig ymlConfig = new YmlConfig(ymlFilePath: ymlPath)
        ymlConfig.config = grailsApplication.config
        ymlConfig.log = new Logger()
        ymlConfig
    }
}

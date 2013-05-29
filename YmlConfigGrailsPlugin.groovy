import grails.plugin.ymlconfig.YmlConfig

class YmlConfigGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.2 > *"
    def pluginExcludes = []

    def title = "Yml Config Plugin"
    def author = "Adam Harwood"
    def authorEmail = "adamtroyh@gmail.com"
    def description = '''\
This plugin reads a yml file specified by a property in Config.groovy and inserts the properties
inside that yml file into the Grails Config.
'''

    def documentation = "https://github.com/aharwood/grails-yml-config"

    def license = "APACHE"

    def developers = [ [ name: "Adam Harwood", email: "adamtroyh@gmail.com" ]]

    def scm = [ url: "https://github.com/aharwood/grails-yml-config" ]

    def doWithSpring = {
        //NOTE: this is created explicitly here and not as a spring bean. This is to ensure the config is updated now,
        //before applications beans are created in resources.groovy (so that those beans can use the configuration from
        //the yml file).
        def ymlConfig = new YmlConfig(config: application.config,
                                      log: log,
                                      ymlFilePath: application.config.grails.plugin.ymlconfig.filePath)
        ymlConfig.init()
    }
}

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
        ymlConfig(YmlConfig) { bean ->
            bean.initMethod = "init"
        }
    }
}

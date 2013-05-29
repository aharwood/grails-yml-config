package grails.plugin.ymlconfig

import org.yaml.snakeyaml.Yaml

class YmlConfig {

    def ymlFilePath
    def config
    def log

    void init() {
        def ymlConfig
        File ymlFile = new File(this.ymlFilePath)
        if (ymlFile.exists()) {
            ymlConfig = readYml(ymlFile)
        } else if (!config.grails.plugin.ymlconfig.containsKey("failOnError") ||
                   config.grails.plugin.ymlconfig.failOnError) {
            throw new FileNotFoundException("Could not find yml file: $ymlFilePath")
        } else {
            log.warn("Could not find yml file at $ymlFilePath")
            ymlConfig = [:]
        }

        if (ymlConfig) {
            this.config.putAll(ymlConfig)
        }
    }

    def readYml(File ymlFile) {
        ymlFile.withReader { reader -> new Yaml().load(reader) }
    }
}

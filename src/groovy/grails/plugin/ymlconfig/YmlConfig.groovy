package grails.plugin.ymlconfig

import org.yaml.snakeyaml.Yaml

class YmlConfig {

    def ymlFilePath
    def config
    def log

    void init() {
        def ymlConfig = null
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

        this.config.putAll(ymlConfig)
    }

    def readYml(File ymlFile) {
        def ymlConfig = null
        ymlFile.withReader { reader ->
            Yaml yaml = new Yaml()
            ymlConfig = yaml.load(reader)
        }
        ymlConfig
    }
}

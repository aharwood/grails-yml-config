package grails.plugin.ymlconfig

import org.yaml.snakeyaml.Yaml

class YmlConfig {

    def ymlFilePath
    def config
    def log

    public YmlConfig() {
        String hello = "world"
    }

    void init() {
        def ymlConfig = null
        File ymlfile = new File(this.ymlFilePath)
        if (ymlfile.exists()) {
            ymlfile.withReader { reader ->
                Yaml yaml = new Yaml()
                ymlConfig = yaml.load(reader)
            }
        } else if (!config.grails.plugin.ymlconfig.containsKey("failOnError") ||
                   config.grails.plugin.ymlconfig.failOnError) {
            throw new FileNotFoundException("Could not find yml file: $ymlFilePath")
        } else {
            log.warn("Could not find yml file at $ymlFilePath")
            ymlConfig = [:]
        }

        this.config.putAll(ymlConfig)
    }
}

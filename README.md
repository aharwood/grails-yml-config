grails-yml-config
=================

Injects properties from a yml file into the Grails config. This promotes the externalisation of configuration, which ops tends to like in production deployments. By doing this, ops can tweak
your settings without requiring a new version of your application (something that matters at 3am).

After installing the plugin, all you need to do is specify the following in your Config.groovy:
```
grails.plugin.ymlconfig.filePath = "path/to/config.yml"
```

The plugin will then read this yml file and inject the properties into the grails configuration (e.g. accessible under grailsApplication.config). This will happen before any spring beans are loaded,
so you can use the values in the yml file to configure them.

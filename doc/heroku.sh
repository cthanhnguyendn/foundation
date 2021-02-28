heroku addons:create cleardb:ignite -a boot-spring
heroku config -a boot-spring | grep CLEARDB_PINK_URL

heroku addons:create jawsdb:kitefin --name=boot-spring-db --version=8.0.23 -a boot-spring
heroku config -a boot-spring | grep JAWSDB_TEAL_URL
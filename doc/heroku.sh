heroku addons:create cleardb:ignite -a env-admin
heroku config -a env-admin | grep CLEARDB_DATABASE_URL
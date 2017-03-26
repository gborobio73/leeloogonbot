#Security fw
https://hapijs.com/tutorials/auth
https://github.com/jaredhanson/passport

#Bot
##Running in heroku
https://github.com/rubenlagus/TelegramBots/issues/106
Change your bot type to longpolling
Change your heroku app type to worker (inside the procfile, specify it like "worker: command_to_launch" instead of "web: some_command")
Push changes to heroku
Launch your bot: "heroku ps:scale worker=1"

##Telegram bots
https://core.telegram.org/bots
https://github.com/rubenlagus/TelegramBots/
https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started

##Scaling bots in Heroku
heroku ps:scale worker=1


##Leeloo Bot Features
* tell the weather
* every hour sends bullshit quote -> http://www.wisdomofchopra.com/
* commit generator -> http://whatthecommit.com/


http://nixmash.com/java/using-spring-boot-commandlinerunner/
http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

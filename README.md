# BotFruit

Aggressively simplifying Java bot development. Magic included.

BotFruit is a framework for developing bots in Java.

The BotFruit Framework has a couple of goals (as it is in early development, they may not be implemented yet):

* **Make bot development in Java as simple as possible.** Wanna do a thing? Just do that thing.
Annotations (optionally) allow you to just tell the framework what you need to do, and it will let you do it with
minimal boilerplate overhead.

* **Make security automatic.** Forgot to set permissions on that ban command? Don't worry - Security is the default.

* **Portability and automatic degradation.** BotFruit is designed to be portable across different bot platforms. Write a
bot once, and make minimal changes to your business logic when porting. BotFruit allows you to slowly port your bot to a
new platform, and will automatically degrade functionality as you work on porting.

* **Easy asynchronity** BotFruit is powered by [Project Reactor](https://projectreactor.io/), making asynchronity simple and functional.

* **Database consistency and reliability** While no database will be bundled in, BotFruit aims to add tools that will allow you
to make sure that your database remains consistent while also staying out of your way.

What The Framework will not do:

* Solder on your RAM and instantly become obsolete

* Attempt to railroad you into a specific database, or a specific backend, or a specific what-have-you. You should be able to
choose the rest of your stack, whether you are writing a Discord bot, or hitting it on IRC. BotFruit may provide some implementations
for your convenience, but they will be optional.

* Make your development experience perfect - there will be bugs, and there will be design decisions that you disagree with. However,
feedback is always appreciated.

## Getting Started

BotFruit is still in development, so you will have to figure out the jitpack situation. I am also waiting until things are
more stable before I make code examples. You can run ./gradlew javadoc to generate javadocs if you'd like to see the
documentation.
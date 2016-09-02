TridentSDK [![Build Status](https://travis-ci.org/TridentSDK/TridentSDK.svg?branch=revamp)](https://travis-ci.org/TridentSDK/TridentSDK) [![Coverage Status](https://coveralls.io/repos/github/TridentSDK/TridentSDK/badge.svg?branch=revamp)](https://coveralls.io/github/TridentSDK/TridentSDK?branch=revamp)
=========

The TridentSDK project. The API for the new generation of multithreaded, high-performance, cleanroom Minecraft servers.

* [Website](https://tridentsdk.net)
* [Chat](https://www.hipchat.com/g20bt22H2?v=2)
* [Issues](https://tridentsdk.atlassian.net/projects/SDK/issues)
* [Wiki](https://tridentsdkwiki.atlassian.net/wiki/dashboard.action)
* [Javadoc](https://tridentsdk.github.io/javadocs)
* [Contributing](https://tridentsdkwiki.atlassian.net/wiki/display/DEV/Trident+Development)
* [Trident](https://github.com/TridentSDK/Trident)

## Latest Release ##

Not released

## Getting a JAR ##

One would need a TridentSDK JAR to debug plugins, use the plugin API, or otherwise any application that isn't [evil](http://i.imgur.com/CCFoMhu.jpg).

### Method one: Build it yourself ###

If you have decided that our forms of distribution are questionable, or you would like to modify something before getting a JAR file, you want to build from the source directly.

#### Prerequisites ####

1. A [computer](https://en.wikipedia.org/wiki/Computer)
2. [Git](https://git-scm.com/)
3. [Maven](https://maven.apache.org/)

#### Directions ####

Type in the following into the command line:

```bash
git clone -b [master|bleeding-edge] https://github.com/TridentSDK/TridentSDK.git
cd TridentSDK
mvn clean install
```

One would use master to ensure compatibility with the latest build of [Trident](https://github.com/TridentSDK/Trident). Otherwise, for the most up-to-date (and potentially breaking) build of TridentSDK, one would use `bleeding-edge`. If you are building the JAR yourself, it is your responsibility to know which one is right for you.

The built JAR will `./TridentSDK/target/tridentsdk-*.jar`

### Method two: Download from Sonatype Nexus ###

For a precompiled solution, one which has passed the tests we have wrote, as well as if you are too lazy to download 2 files and install the [Method one](#method-one-build-it-yourself) prerequeisites, you can download one yourself.

#### Prerequisites ####

1. A [mouse](https://en.wikipedia.org/wiki/Mouse_(computing))
2. A [web browser](https://en.wikipedia.org/wiki/Web_browser)
3. A [computer](https://en.wikipedia.org/wiki/Computer)

#### Directions ####

1. [Click](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=snapshots&g=net.tridentsdk&a=tridentsdk&v=0.3-SNAPSHOT&e=jar)
2. Click "Keep"

### Method three: Get one from us ###

If we've released a JAR for you to use, it usually comes with a nice read. So if you like nice reads or the look of our [official website](https://tridentsdk.net), you can go rummage around the releases forum and look for a download link.

#### Prerequisites ####

1. A [mouse](https://en.wikipedia.org/wiki/Mouse_(computing))
2. A [web browser](https://en.wikipedia.org/wiki/Web_browser)
3. A [computer](https://en.wikipedia.org/wiki/Computer)

#### Directions ####

Fortunately, because there are no releases yet, you don't need to do that quite yet.

## Tools ##

None yet

## API ##

TridentSDK provides an extensive API which allows both high and low level control over the Trident server.

A jar placed in the `plugins` folder is a valid plugin, if it has a class that does not have the same descriptor as an existing Trident or TridentSDK class. It must also have one and only one class whose superclass is `net.tridentsdk.plugin.Plugin`, annotated with `net.tridentsdk.plugin.annotation.PluginDesc` with a non-empty name field.

Example:

```java
package com.example;

import net.tridentsdk.plugin.Plugin;

@PluginDesc(name = "Plugin")
public class ExamplePlugin extends Plugin {
}
```

This is the minimal requirements to have a plugin loaded by the server.

For more information on how to use the API, visit the [Wiki](https://tridentsdkwiki.atlassian.net/wiki/dashboard.action) page.

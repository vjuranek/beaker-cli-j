#!/bin/bash

source etc/dev.properties
java -Djavax.net.ssl.trustStore=$trustStore -Djavax.net.ssl.trustStorePassword=$trustStorePassword -Djsse.enableSNIExtension=false -jar target/beaker-cli-j-0.0.1-SNAPSHOT-jar-with-dependencies.jar -s $beaker_server  $@

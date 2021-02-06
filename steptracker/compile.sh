#!/bin/bash -ex

mvn -q clean
mvn -q compile
mvn exec:java -Dexec.mainClass=cs1302.arcade.ArcadeDriver

#!/bin/bash

BASEDIR=$(dirname $0)
javac "${BASEDIR}/Main.java" \
  && java -cp "${BASEDIR}" Main \
  && rm "${BASEDIR}"/*.class

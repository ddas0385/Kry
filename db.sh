#!/bin/bash

locationdataab="$1/data.ab"
locationdatatar="$1/data.tar"

echo $1
echo $locationdataab
echo $locationdatatar 

adb backup -f $locationdataab -noapk com.kri.kry

java -jar ~/Downloads/android-backup-extractor-20151102-bin/abe.jar unpack $locationdataab $locationdatatar

tar -xvf $locationdatatar $1




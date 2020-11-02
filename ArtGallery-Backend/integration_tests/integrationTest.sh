#!/bin/bash

for filename in integration_tests/*.json; do
    $(newman run $filename);
done
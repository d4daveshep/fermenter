#!/bin/bash

rsync -av -e 'ssh -i ~/Desktop/david/Documents/amazon/daveshep-aws.pem' pom.xml admin@54.201.254.241:~/fermenter/
rsync -av -e 'ssh -i ~/Desktop/david/Documents/amazon/daveshep-aws.pem' src admin@54.201.254.241:~/fermenter
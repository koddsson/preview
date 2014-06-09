from dockerfile/java

ADD build/libs/poi-test-all.jar /data/poi.jar

ENTRYPOINT ["java", "-jar", "/data/poi.jar"]

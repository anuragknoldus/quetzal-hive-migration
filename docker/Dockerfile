FROM ubuntu
FROM java:8

RUN wget http://redrockdigimark.com/apachemirror/hadoop/common/hadoop-2.8.2/hadoop-2.8.2.tar.gz -O hadoop.tar.gz
RUN wget http://redrockdigimark.com/apachemirror/hive/hive-2.3.0/apache-hive-2.3.0-bin.tar.gz -O hive.tar.gz
RUN tar -xzvf hadoop.tar.gz
RUN tar -xzvf hive.tar.gz

RUN echo "<configuration><property><name>hive.metastore.schema.verification</name><value>false</value></property><property><name>hive.metastore.warehouse.dir</name><value>file:///tmp</value><description></description></property><property><name>fs.default.name</name><value>file:///tmp</value></property></configuration>" > hive-site.xml

RUN echo "export HADOOP_HOME=/hadoop-2.8.2" > hive-env.sh

RUN mv hive-site.xml apache-hive-2.3.0-bin/conf/
RUN mv hive-env.sh apache-hive-2.3.0-bin/conf/
RUN rm -rf apache-hive-2.3.0-bin/metastore_db/
RUN apache-hive-2.3.0-bin/bin/schematool -initSchema -dbType derby
CMD apache-hive-2.3.0-bin/bin/hiveserver2 >> hiveserver.log 

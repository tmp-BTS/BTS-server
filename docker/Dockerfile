FROM centos:7

# Install basic packages
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8
ENV LANG kr_KO.UTF-8

RUN yum -y update
RUN yum install -y wget telnet nc tar vim unzip net-tools java-1.8-openjdk-devel

# tmp-BTS setup
RUN mkdir -p /tmp-BTS/operation
VOLUME ["/BTS/operation"]
EXPOSE 80 8080
WORKDIR /tmp-BTS
ADD run.sh /tmp-BTS/run.sh
RUN chmod 755 /tmp-BTS/run.sh
ADD war/*.war /tmp-BTS/

ENTRYPOINT ["/tmp-BTS/run.sh"]
FROM sbtscala/scala-sbt:eclipse-temurin-jammy-19.0.1_10_1.9.0_3.3.0

# Install necessary packages
RUN apt-get update && apt-get install -y libxrender1 libxtst6 libxi6 apt-utils xvfb

# Set up a virtual display using Xvfb
ENV DISPLAY=:99
RUN Xvfb :99 -screen 0 1024x768x16 &

# Set the working directory
WORKDIR /minesweeper

# Copy the project files into the container
COPY . .

# Compile the project
RUN sbt clean compile

# Run the application using Xvfb
CMD xvfb-run --auto-servernum --server-num=1 sbt test

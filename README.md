# Jersey Java Api Docker Demo (jersey-docker-demo)

This repository houses the source for Dockerized Java Api Demo image, files required to build the image, files to run a local instance for development and docker compose file.


## Contents

<!-- TOC depthFrom:2 -->

- [Contents](#contents)
- [Files](#files)
    - [Image Specifc Files](#image-specifc-files)
    - [Environment Specific Files](#environment-specific-files)
    - [Local Environment Specific Files](#local-environment-specific-files)
- [Local Environment Setup](#local-environment-setup)
    - [Installs](#installs)
        - [Windows Installation Notes](#windows-installation-notes)
        - [Docker Toolbox Notes](#docker-toolbox-notes)
    - [Building and Running the Server from source](#building-and-Running-the-Server-from-source)
    - [Running Server with help of Docker](#running-server-with-help-of-docker)
- [Common Commands](#common-commands)

<!-- /TOC -->

## Files

### Image Specifc Files

* `Dockerfile`: Defines the docker image.
* `.dockerignore`: Used to prevent unwanted files/folder from being included in the Docker image. Usage is the same `.gitignore`.

### Local Environment Specific Files

* `docker-compose.yml`: Docker compose file for running your local environment.

## Local Environment Setup

### Installs

1. Install [Docker CE](https://www.docker.com/community-edition#/download)
1. Add host entry to your hosts file:

   ```text
   127.0.0.1     jersey-docker-demo.com
   ```

1. [Windows Only] Install a linux bash shell. Options are:
    * [Git Bash](https://git-scm.com/download/win)
    * Windows 10 Bash
    * [Cygwin](https://www.cygwin.com/)

#### Windows Installation Notes

* During the Docker install, choose to run linux based containers.

#### Docker Toolbox Notes

If you are are not able to use the full Docker CE installation and are using Docker Toolbox, be mindful of the following:

* Due to some permissions issues, put the repo in your user directory: `C:\Users\<username>\`.

* You may need to use the following in the host file instead of what is stated above:

    ```text
    192.168.99.100 jersey-docker-demo.com
    ```

### Building and Running the Server from source

1. Make sure you have Java and Maven installed

1. Clone this repo if you haven't already.

1. Navigate to the root of the repo.

1. Using your chosen bash shell, build sources:

   ```bash
   mvn install
   ```

1. Run server:

   ```bash
   mvn exec:java
   ```

### Running Server with help of Docker

1. Clone this repo if you haven't already.

1. Navigate to the root of the repo.

1. Using your chosen bash shell, build the image:

   ```bash
   docker build -t jersey-docker-demo .
   ```

1. Run docker-compose:

   ```bash
   docker-compose up
   ```

1. Browse to http://jersey-docke-demo.com:18080


## Common Commands

Here are a few common commands used when dealing with `Docker` and [`docker-compose`](https://docs.docker.com/compose/).

* Bring up the containers:

    ```bash
    docker-compose up
    ```

* Take down the containers:

    ```bash
    docker-compose down
    ```

* Information about running containers:

    ```bash
    docker-compose ps
    ```

* See recent container logs:

    ```bash
    docker-compose logs
    ```
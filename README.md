# Jersey Java Api Docker Demo (jersey-docker-demo)

This repository houses the source for Dockerized Java Api Demo image, files required to build the image, files to run a local instance for development and docker compose file.

This Demo has been written for following story:

```
We in Amazing Co need to model how our company is structured so we can do awesome stuff.

Every company has a root node (only one) and several children nodes, each one with its own children as well. It's a tree-based structure.

Something like:

         root
        /    \
       a      b
       | 
       c

We need two HTTP APIs that will serve the two basic operations:

1) Get all children nodes of a given node (the given node can be anyone in the tree structure).
2) Change the parent node of a given node (the given node can be anyone in the tree structure).

They need to answer quickly, even with tons of nodes. Also, we can't afford to lose this information, so some sort of persistence is required.

Each node should have the following info:
1) node identification
2) who is the parent node
3) who is the root node
4) the height of the node. In the above example, height(root) = 0 and height(a) == 1.

Our boss is evil and we can only have docker and docker-compose on our machines. So your server needs to be ran using them.
```


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
- [Api Usage](#api-usage)

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

## Api Usage

* To get child companies for company with id 4:

    ```
        GET http://jersey-docker-demo.com:18080/api/companies/4/divisions

        Result will be:

        [
            {"height":1,"id":4,"name":"Company C","parentId":1,"rootId":1},
            {"height":2,"id":14,"name":"Company C1","parentId":4,"rootId":1}
        ]

    ```

* To update parent company for company with id 14 from 4 to 3:

    ```
        PUT http://jersey-docker-demo.com:18080/api/companies/14

        with body

        {"id": 14, "parentId": 4}
    ```
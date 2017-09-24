# IT3910-Project-I

Install
=====
**NodeJS** is required. So please install its lastest version from **[NodeJS Homepage](https://nodejs.org)**

Ofcourse also with [Git](https://git-scm.com/downloads)

Then clone this repo, using:
```
git clone https://github.com/ThanhxD/IT3910-Project-I.git
```
Run install node package in the terminal, using:
```
npm i
```

Usage
=====
_(This will be updated during the project)_

### Client
_(for client)_

### Server
_(for server)_
##### Crawler

First, create a file named **_access-token.js_** under **_src/server/crawler/_** and copy lines below into it.
```javascript
const access_token = "xxx";

module.exports = access_token;
```
Replace **_xxx_** in line 1 by _your own access token_. 

Then you can run crawler to crawl posts and comments from some groups on Facebook. Using: 
```
npm run crawl
```

Facebook Group Ids to crawl are located in  **_src/server/crawler/fb-groups-id.js_**.

Datas crawled will be saved in **_src/database/data/raw_**.
It will be used to extract informations, then saved to **_src/database/data/out_** and ready to use.

### Database
_(for database)_

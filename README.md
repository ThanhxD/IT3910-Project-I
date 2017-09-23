# IT3910-Project-I


## Install
**NodeJS** is required. So please install its lastest version from **[NodeJS Homepage](https://nodejs.org)** 

## Usage
_(This will be updated during the project)_

First, create a file name access-token.js under **_src/crawler/_**. Copy the text below into the file, and remember to replace **_xxx_** by your own access token.
```javascript
const access_token = "xxx";

module.exports = access_token;
```

Then you can run crawler to crawl posts and comments from some groups on Facebook. Using: 
```
node src/crawler/index.js
```

Facebook Group Ids to crawl are located in  **_src/crawler/fb-groups-id.js_**.

Datas be crawled will be saved in **_src/crawler/data/raw_**.
It will be used to extract informations, then saved to **_src/crawler/data/out_** and ready to use.
